package org.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // ============================================================
        // BLOCO 1 - API ANTIGA (Date, SimpleDateFormat)
        // ============================================================
        System.out.println("=== BLOCO 1 - API ANTIGA ===");

        var date1 = new Date(System.currentTimeMillis() - 999999999L);
        System.out.println("Data calculada: " + date1);

        var date2 = new Date();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        System.out.println("Data atual: " + date2);
        System.out.println("Formatado: " + formatter.format(date2));

        var milliseconds = System.currentTimeMillis();
        var date3 = new Date(milliseconds);
        var novaData = new Date(milliseconds + 99999);
        System.out.println("Nova data é depois da base? " + novaData.after(date3));
        System.out.println("Nova data é antes da base? " + novaData.before(date3));

        // ============================================================
        // BLOCO 2 - API INTERMEDIÁRIA (Java 7 - Calendar)
        // ============================================================
        System.out.println("\n=== BLOCO 2 - API INTERMEDIÁRIA (Calendar) ===");

        Calendar calendario = Calendar.getInstance();
        System.out.println("Data atual (Calendar): " + calendario.getTime());

        calendario.add(Calendar.DAY_OF_MONTH, 5);
        System.out.println("Data +5 dias: " + calendario.getTime());

        calendario.add(Calendar.MONTH, -2);
        System.out.println("Data -2 meses: " + calendario.getTime());

        Calendar outraData = Calendar.getInstance();
        outraData.add(Calendar.DAY_OF_MONTH, 10);
        System.out.println("Outra data é depois da base? " + outraData.after(Calendar.getInstance()));
        System.out.println("Outra data é antes da base? " + outraData.before(Calendar.getInstance()));

        // ============================================================
        // BLOCO 3 - API MODERNA (Java 8+ - java.time)
        // ============================================================
        System.out.println("\n=== BLOCO 3 - API MODERNA ===");

        // Exemplo 1: manipulando datas com minusDays()
        LocalDateTime data1 = LocalDateTime.now().minusDays(11);
        System.out.println("Data calculada: " + data1);

        // Exemplo 2: formatando a data atual
        LocalDateTime data2 = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        System.out.println("Data atual: " + data2);
        System.out.println("Formatado: " + data2.format(formato));

        // Exemplo 3: comparando datas com isAfter() e isBefore()
        LocalDateTime base = LocalDateTime.now();
        LocalDateTime novaData2 = base.plusSeconds(99999);
        System.out.println("Nova data é depois da base? " + novaData2.isAfter(base));
        System.out.println("Nova data é antes da base? " + novaData2.isBefore(base));

        // Exemplo 4: usando LocalDate e plusYears()
        LocalDate hoje = LocalDate.now();
        LocalDate daqui20Anos = hoje.plusYears(20);
        System.out.println("Data atual (LocalDate): " + hoje);
        System.out.println("Data daqui a 20 anos: " + daqui20Anos);

        // Exemplo 5: calculando diferença entre duas datas (Period)
        LocalDate aniversario = LocalDate.of(2000, 2, 13);
        Period idade = Period.between(aniversario, hoje);
        System.out.println("Idade: " + idade.getYears() + " anos, " + idade.getMonths() + " meses, " + idade.getDays() + " dias");

        // Exemplo 6: calculando duração entre dois horários (Duration)
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusHours(5).plusMinutes(30);
        Duration duracao = Duration.between(inicio, fim);
        System.out.println("Duração entre horários: " + duracao.toHours() + " horas e " + duracao.toMinutesPart() + " minutos");

        // Exemplo 7: manipulando semanas e meses
        System.out.println("Daqui 2 semanas: " + hoje.plusWeeks(2));
        System.out.println("Mês passado: " + hoje.minusMonths(1));

        // Exemplo 8: comparando com isEqual()
        LocalDate dataA = LocalDate.of(2026, 2, 13);
        LocalDate dataB = LocalDate.of(2026, 2, 13);
        System.out.println("dataA.isEqual(dataB)? " + dataA.isEqual(dataB)); // true

        // Exemplo 9: comparando com equals()
        System.out.println("dataA.equals(dataB)? " + dataA.equals(dataB)); // true

        // Exemplo 10: mostrando diferença prática com LocalDateTime
        LocalDateTime dt1 = LocalDateTime.of(2026, 2, 13, 10, 0);
        LocalDateTime dt2 = LocalDateTime.of(2026, 2, 13, 10, 0);
        System.out.println("dt1.isEqual(dt2)? " + dt1.isEqual(dt2)); // true
        System.out.println("dt1.equals(dt2)? " + dt1.equals(dt2));   // true
    }
}