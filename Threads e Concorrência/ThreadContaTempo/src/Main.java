//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            // Cria uma instância da nossa thread
            ContadorTempo contador = new ContadorTempo();

            // Inicia a execução da thread (chama internamente o método run())
            contador.start();

            // Simulação: após 10 segundos, interrompe a thread
            try {
                Thread.sleep(10000); // espera 10 segundos no programa principal
                contador.interrupt(); // interrompe a thread de contagem
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

/*
 O que você aprendeu aqui:
- Thread: unidade de execução paralela dentro de um programa.
- run(): método que define o que a thread vai fazer.
- start(): inicia a thread (não confundir com run()).
- sleep(): pausa a execução da thread por um tempo definido.
- interrupt(): forma de parar uma thread de maneira controlada.
*/
