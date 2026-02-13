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
    // Classe de demonstração: mostra como trabalhar com datas em Java
    // - Mostramos três "eras"/APIs: a antiga (Date), a intermediária (Calendar)
    //   e a moderna (java.time, introduzida no Java 8).
    // - Os exemplos são educativos: eles imprimem valores e comparam
    //   comportamentos típicos de cada API.

    public static void main(String[] args) {
        // ============================================================
        // BLOCO 1 - API ANTIGA (Date, SimpleDateFormat)
        // ============================================================
        System.out.println("=== BLOCO 1 - API ANTIGA ===");

        // Date representa um instante em milissegundos desde a epoch (1970-01-01T00:00:00Z)
        // Construtor a partir de System.currentTimeMillis() é comum para criar instantes "agora".
        var date1 = new Date(System.currentTimeMillis() - 999999999L);
        System.out.println("Data calculada: " + date1);

        // Cria uma data com o instante atual
        var date2 = new Date();
        // SimpleDateFormat formata/analisa datas seguindo um padrão.
        // Padrão: dd = dia, MM = mês, yyyy = ano, HH = hora (0-23), mm = minutos, ss = segundos
        // Atenção: SimpleDateFormat NÃO é thread-safe — cuidado em ambientes concorrentes.
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        System.out.println("Data atual: " + date2);
        System.out.println("Formatado: " + formatter.format(date2));

        // Uma forma explícita de trabalhar com milissegundos
        var milliseconds = System.currentTimeMillis();
        var date3 = new Date(milliseconds);
        // novaData será alguns milissegundos à frente de date3
        var novaData = new Date(milliseconds + 99999);
        // Date fornece métodos utilitários como after() e before() para comparação
        System.out.println("Nova data é depois da base? " + novaData.after(date3));
        System.out.println("Nova data é antes da base? " + novaData.before(date3));

        // Observação: a API Date tem várias limitações (mutabilidade, pouca granularidade)
        // por isso recomenda-se usar a API java.time quando possível.

        // ============================================================
        // BLOCO 2 - API INTERMEDIÁRIA (Java 7 - Calendar)
        // ============================================================
        System.out.println("\n=== BLOCO 2 - API INTERMEDIÁRIA (Calendar) ===");

        // Calendar é uma API que permite manipular campos (dia, mês, ano) de forma mais
        // clara do que Date. Ainda assim, é mutável e mais verbosa comparada à java.time.
        Calendar calendario = Calendar.getInstance(); // usa o timezone e locale padrão
        System.out.println("Data atual (Calendar): " + calendario.getTime());

        // Adiciona 5 dias ao objeto Calendar (mutação do próprio objeto)
        calendario.add(Calendar.DAY_OF_MONTH, 5);
        System.out.println("Data +5 dias: " + calendario.getTime());

        // Subtrai 2 meses do calendário (note que é alteração no mesmo objeto)
        calendario.add(Calendar.MONTH, -2);
        System.out.println("Data -2 meses: " + calendario.getTime());

        // Criamos outra instância de Calendar para comparar datas
        Calendar outraData = Calendar.getInstance();
        outraData.add(Calendar.DAY_OF_MONTH, 10);
        // Aqui usamos after/before, mas atenção: estamos comparando com uma nova instância de agora
        // (Calendar.getInstance()) — chamadas rápidas a getInstance() retornam instantes muito
        // parecidos, mas não necessariamente exatamente iguais.
        System.out.println("Outra data é depois da base? " + outraData.after(Calendar.getInstance()));
        System.out.println("Outra data é antes da base? " + outraData.before(Calendar.getInstance()));

        // ============================================================
        // BLOCO 3 - API MODERNA (Java 8+ - java.time)
        // ============================================================
        System.out.println("\n=== BLOCO 3 - API MODERNA ===");

        // java.time é a API recomendada desde Java 8. As classes são imutáveis,
        // mais expressivas e tratam melhor fusos horários e precisão temporal.
        LocalDateTime agora = LocalDateTime.now(); // data e hora local (sem info de fuso)

        // Exemplo 1: manipulando datas com minusDays()
        // Note: LocalDateTime é imutável — minusDays() retorna uma nova instância
        LocalDateTime data1 = agora.minusDays(11);
        System.out.println("Data calculada: " + data1);

        // Exemplo 2: formatando a data atual com padrão customizado
        // DateTimeFormatter é thread-safe e recomendado em aplicações concorrentes
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        System.out.println("Data atual: " + agora);
        System.out.println("Formatado: " + agora.format(formato));

        // Exemplo 3: comparando datas com isAfter() e isBefore()
        // isAfter/isBefore comparam instantes temporais (ou seja, levam em conta a hora)
        LocalDateTime base = LocalDateTime.now();
        LocalDateTime novaData2 = base.plusSeconds(99999);
        System.out.println("Nova data é depois da base? " + novaData2.isAfter(base));
        System.out.println("Nova data é antes da base? " + novaData2.isBefore(base));

        // Exemplo 4: usando LocalDate e plusYears()
        // LocalDate representa uma data (ano/mês/dia) sem hora — útil para aniversários
        LocalDate hoje = LocalDate.now();
        LocalDate daqui20Anos = hoje.plusYears(20);
        System.out.println("Data atual (LocalDate): " + hoje);
        System.out.println("Data daqui a 20 anos: " + daqui20Anos);

        // Exemplo 5: calculando diferença entre duas datas (Period)
        // Period representa uma quantidade de tempo na unidade de datas (anos, meses, dias)
        LocalDate aniversario = LocalDate.of(2000, 2, 13);
        Period idade = Period.between(aniversario, hoje);
        System.out.println("Idade: " + idade.getYears() + " anos, " + idade.getMonths() + " meses, " + idade.getDays() + " dias");

        // Exemplo 6: calculando duração entre dois horários (Duration)
        // Duration é usado para quantidades de tempo em horas/minutos/segundos (tempo baseado em instantes)
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusHours(5).plusMinutes(30);
        Duration duracao = Duration.between(inicio, fim);
        // para obter as partes em minutos/segundos use os métodos apropriados (toHours(), toMinutesPart())
        System.out.println("Duração entre horários: " + duracao.toHours() + " horas e " + duracao.toMinutesPart() + " minutos");

        // Exemplo 7: manipulando semanas e meses — operações encadeáveis e imutáveis
        System.out.println("Daqui 2 semanas: " + hoje.plusWeeks(2));
        System.out.println("Mês passado: " + hoje.minusMonths(1));

        // Exemplo 8: comparando com isEqual() e equals()
        // isEqual compara a representação temporal (útil para comparar datas sem se preocupar com
        // a implementação), equals também funciona porque LocalDate implementa equals corretamente.
        LocalDate dataA = LocalDate.of(2026, 2, 13);
        LocalDate dataB = LocalDate.of(2026, 2, 13);
        System.out.println("dataA.isEqual(dataB)? " + dataA.isEqual(dataB));
        System.out.println("dataA.equals(dataB)? " + dataA.equals(dataB));

        // Exemplo 9: mostrando diferença prática com LocalDateTime
        // Para instantes com hora, isEqual é equivalente a equals quando os campos são os mesmos
        LocalDateTime dt1 = LocalDateTime.of(2026, 2, 13, 10, 0);
        LocalDateTime dt2 = LocalDateTime.of(2026, 2, 13, 10, 0);
        System.out.println("dt1.isEqual(dt2)? " + dt1.isEqual(dt2));
        System.out.println("dt1.equals(dt2)? " + dt1.equals(dt2));

        // Exemplo 10: usando formatadores ISO — formatos padronizados para interoperabilidade
        System.out.println("BASIC_ISO_DATE: " + agora.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("ISO_DATE: " + agora.format(DateTimeFormatter.ISO_DATE));
        System.out.println("ISO_DATE_TIME: " + agora.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println("ISO_LOCAL_DATE: " + agora.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("ISO_LOCAL_DATE_TIME: " + agora.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}