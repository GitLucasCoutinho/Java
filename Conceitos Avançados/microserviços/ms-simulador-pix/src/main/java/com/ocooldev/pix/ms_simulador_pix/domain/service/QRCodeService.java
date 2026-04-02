package com.ocooldev.pix.ms_simulador_pix.domain.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

@Service
public class QRCodeService {

    // Gera payload Pix simplificado
    public String generatePixPayload(String chave, BigDecimal valor, String txid, String merchantName, String city) {
        StringBuilder payload = new StringBuilder();
        payload.append("000201"); // Payload Format Indicator
        payload.append("010211"); // Point of Initiation Method (11 = static, 12 = dynamic)
        payload.append("2636"); // Merchant Account Information
        payload.append("0014br.gov.bcb.pix"); // GUI
        payload.append("01").append(String.format("%02d", chave.length())).append(chave); // Chave
        payload.append("52040000"); // Merchant Category Code
        payload.append("5303986"); // Transaction Currency (986 = BRL)
        payload.append("54").append(String.format("%02d", valor.toString().length())).append(valor); // Transaction Amount
        payload.append("5802BR"); // Country Code
        payload.append("59").append(String.format("%02d", merchantName.length())).append(merchantName); // Merchant Name
        payload.append("60").append(String.format("%02d", city.length())).append(city); // Merchant City
        payload.append("62").append(String.format("%02d", ("05" + String.format("%02d", txid.length()) + txid).length())).append("05").append(String.format("%02d", txid.length())).append(txid); // Additional Data Field
        payload.append("6304"); // CRC16 placeholder

        // Calcular CRC16
        String crc = calculateCRC16(payload.toString());
        payload.append(crc);

        return payload.toString();
    }

    // Calcula CRC16 (simplificado, usar CRC32 para demo)
    private String calculateCRC16(String data) {
        CRC32 crc = new CRC32();
        crc.update(data.getBytes(StandardCharsets.UTF_8));
        return String.format("%04X", crc.getValue() & 0xFFFF).toUpperCase();
    }

    // Gera QR Code como byte array (PNG)
    public byte[] generateQRCode(String payload, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(payload, BarcodeFormat.QR_CODE, width, height);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(bufferedImage, "PNG", baos);
        return baos.toByteArray();
    }
}
