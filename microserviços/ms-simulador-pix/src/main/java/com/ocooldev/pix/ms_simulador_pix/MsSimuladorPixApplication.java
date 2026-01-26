package com.ocooldev.pix.ms_simulador_pix;
// Pacote raiz da aplicação. Certifique-se que o arquivo está dentro da pasta src/main/java/com/ocooldev/pix/ms_simulador_pix/

import org.springframework.boot.SpringApplication;
// Classe utilitária que inicializa o Spring Boot.
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Anotação que configura automaticamente o projeto Spring Boot (auto-configuração, scan de componentes, etc).

/**
 * Classe principal que inicializa a aplicação Spring Boot.
 * SRP: única responsabilidade é iniciar o contexto da aplicação.
 */
@SpringBootApplication
// Indica que esta é a classe principal da aplicação Spring Boot.
public class MsSimuladorPixApplication {
    // Nome da classe pública deve ser idêntico ao nome do arquivo: MsSimuladorPixApplication.java

    public static void main(String[] args) {
        // Método principal, chamado quando você executa o programa.
        SpringApplication.run(MsSimuladorPixApplication.class, args);
        // Inicia o servidor embutido (Tomcat) e carrega todo o contexto Spring.
    }
}
