import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

// Classe que representa uma Thread que realiza um download real
public class DownloadRealThread extends Thread {

    private String urlArquivo; // URL do PDF
    private String destino;    // Nome do arquivo salvo localmente

    // Construtor para receber a URL e o destino
    public DownloadRealThread(String urlArquivo, String destino) {
        this.urlArquivo = urlArquivo;
        this.destino = destino;
    }

    // O método run() é o que será executado quando a thread for iniciada
    @Override
    public void run() {
        try (
                // Abre conexão com a URL
                BufferedInputStream in = new BufferedInputStream(new URL(urlArquivo).openStream());
                // Cria arquivo local para salvar os dados
                FileOutputStream fileOutputStream = new FileOutputStream(destino)
        ) {
            byte[] dados = new byte[1024]; // buffer de 1KB
            int bytesLidos;
            int progresso = 0;

            System.out.println("Iniciando download do arquivo: " + destino);

            // Lê os dados da internet e escreve no arquivo local
            while ((bytesLidos = in.read(dados, 0, 1024)) != -1) {
                fileOutputStream.write(dados, 0, bytesLidos);
                progresso += bytesLidos;
                System.out.println("Baixando... " + progresso + " bytes recebidos");
            }

            System.out.println("Download concluído! Arquivo salvo em: " + destino);

        } catch (IOException e) {
            System.out.println("Erro ao realizar download: " + e.getMessage());
        }

    }

    }