// Classe que representa uma Thread que simula um download
// Aqui estamos estendendo a classe Thread para criar nosso próprio comportamento
public class SimuladorDownload extends Thread {

    private String arquivo; // nome do arquivo que será "baixado"

    // Construtor para receber o nome do arquivo
    public SimuladorDownload(String arquivo) {
        this.arquivo = arquivo;
    }

    // O método run() é o que será executado quando a thread for iniciada
    @Override
    public void run() {
        try {
            System.out.println("Iniciando download do arquivo: " + arquivo);

            // Simula o progresso do download de 0% até 100%
            for (int progresso = 0; progresso <= 100; progresso += 10) {
                System.out.println("Download de " + arquivo + ": " + progresso + "% concluído");

                // Pausa de 500 milissegundos para simular tempo de download
                Thread.sleep(500);
            }

            System.out.println("Download do arquivo " + arquivo + " concluído!");
        } catch (InterruptedException e) {
            // Caso a thread seja interrompida, cai aqui
            System.out.println("Download interrompido!");
        }
    }
}
