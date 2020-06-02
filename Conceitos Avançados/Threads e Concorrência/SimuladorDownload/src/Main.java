public class Main {
    public static void main(String[] args) {
        // Cria uma instância da nossa thread simulando download de um arquivo
        SimuladorDownload download = new SimuladorDownload("video.mp4");

        // Inicia a execução da thread (chama internamente o método run())
        download.start();

        // Simulação: após 3 segundos, interrompe o download
        try {
            Thread.sleep(3000); // espera 3 segundos no programa principal
            download.interrupt(); // interrompe a thread de download
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
📚 O que você aprendeu aqui:
- Thread: unidade de execução paralela dentro de um programa.
- run(): método que define o que a thread vai fazer.
- start(): inicia a thread (não confundir com run()).
- sleep(): pausa a execução da thread por um tempo definido.
- interrupt(): forma de parar uma thread de maneira controlada.
- Simulação de processo: podemos usar loops e pausas para imitar tarefas reais (como download).
*/
