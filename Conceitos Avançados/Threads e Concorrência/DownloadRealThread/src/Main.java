//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Exemplo com um PDF de dados abertos do governo
        String url = "https://www.gov.br/planejamento/pt-br/acesso-a-informacao/dados-abertos/dados-abertos.pdf";
        String destino = "dados_abertos.pdf";

        // Cria e inicia a thread de download
        DownloadRealThread download = new DownloadRealThread(url, destino);
        download.start();
    }
}

/*
 O que você aprendeu aqui:
- Thread: unidade de execução paralela dentro de um programa.
- run(): método que define o que a thread vai fazer.
- start(): inicia a thread (não confundir com run()).
- BufferedInputStream: usado para ler dados da rede em blocos.
- FileOutputStream: grava os dados recebidos em um arquivo local.
- Loop de leitura: lê pedaços do arquivo até terminar (read() retorna -1).
- Tratamento de exceções: sempre necessário em operações de rede/arquivos.
*/
