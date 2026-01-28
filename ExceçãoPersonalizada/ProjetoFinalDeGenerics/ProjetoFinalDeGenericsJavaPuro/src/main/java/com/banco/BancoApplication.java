package com.banco;

// Classe principal para rodar a aplicação
import com.banco.controller.BancoController;

public class BancoApplication {
    // Método main: ponto de entrada da aplicação
    public static void main(String[] args) {
        BancoController controller = new BancoController();
        controller.executar(); // executa o fluxo principal
    }
}