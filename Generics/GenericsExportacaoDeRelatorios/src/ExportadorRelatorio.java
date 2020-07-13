import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

// Classe utilitária para exportar relatórios
public class ExportadorRelatorio {

    // Exporta lista de contas para CSV
    public static void exportarContasParaCSV(Collection<? extends Conta<?>> contas, String nomeArquivo) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            // Cabeçalho
            writer.write("Titular,Saldo\n");

            // Percorre todas as contas e escreve no arquivo
            for (Conta<?> conta : contas) {
                writer.write(conta.getTitular() + "," + conta.getSaldo() + "\n");
            }

            System.out.println("📄 Relatório exportado para " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao exportar relatório: " + e.getMessage());
        }
    }

    // Exporta saldo total para CSV
    public static void exportarSaldoTotalParaCSV(Collection<? extends Conta<? extends Number>> contas, String nomeArquivo) {
        double total = 0;
        for (Conta<? extends Number> conta : contas) {
            total += conta.getSaldo().doubleValue();
        }

        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write("Saldo Total\n");
            writer.write(total + "\n");
            System.out.println("📄 Relatório de saldo total exportado para " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao exportar relatório: " + e.getMessage());
        }
    }
}