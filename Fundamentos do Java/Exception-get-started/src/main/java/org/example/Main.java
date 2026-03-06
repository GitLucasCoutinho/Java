package org.example;

import org.example.br.com.ocooldev.dao.UserDAO;
import org.example.br.com.ocooldev.exception.UserNotFundException;
import org.example.br.com.ocooldev.model.MenuOption;
import org.example.br.com.ocooldev.model.UserModel;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("Bem vindo ao cadastro de músicas, selecione a operação desejada:");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Buscar por identificador");
            System.out.println("5 - Listar");
            System.out.println("6 - Sair");

            int selectOption = scanner.nextInt();

            MenuOption option = switch (selectOption) {
                case 1 -> MenuOption.SAVE;
                case 2 -> MenuOption.UPDATE;
                case 3 -> MenuOption.DELETE;
                case 4 -> MenuOption.FIND_BY_ID;
                case 5 -> MenuOption.FIND_ALL;
                case 6 -> MenuOption.EXIT;
                default -> null;
            };

            if (option == null) {
                System.out.println("Opção inválida, tente novamente.");
                continue;
            }

            switch (option) {
                case SAVE -> {
                    var user = requestUserInfo();
                    userDAO.save(user);
                    System.out.println("Usuário cadastrado com sucesso: " + user);
                }
                case UPDATE -> {
                    System.out.println("Informe o ID do usuário para atualizar:");
                    long id = scanner.nextLong();
                    var user = requestUserInfo();
                    user.setId(id);
                    try {
                        userDAO.update(user);
                        System.out.println("Usuário atualizado com sucesso: " + user);
                    } catch (UserNotFundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case DELETE -> {
                    System.out.println("Informe o ID do usuário para excluir:");
                    long id = scanner.nextLong();
                    try {
                        userDAO.delete(id);
                        System.out.println("Usuário excluído com sucesso!");
                    } catch (UserNotFundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case FIND_BY_ID -> {
                    System.out.println("Informe o ID do usuário para buscar:");
                    long id = scanner.nextLong();
                    try {
                        var user = userDAO.findById(id);
                        System.out.println("Usuário encontrado: " + user);
                    } catch (UserNotFundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case FIND_ALL -> {
                    var users = userDAO.findAll();
                    if (users.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        users.forEach(System.out::println);
                    }
                }
                case EXIT -> {
                    System.out.println("Saindo...");
                    running = false;
                }
            }
        }
    }

    private static UserModel requestUserInfo() {
        System.out.println("Informe o nome do usuário");
        var name = scanner.next();
        System.out.println("Informe o e-mail do usuário");
        var email = scanner.next();
        System.out.println("Informe a data de nascimento do usuário (dd/MM/yyyy)");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Faz o parse como LocalDate
            var localDate = java.time.LocalDate.parse(birthdayString, formatter);

            // Converte para OffsetDateTime assumindo fuso horário de Brasília (-03:00)
            var birthday = localDate.atStartOfDay().atOffset(java.time.ZoneOffset.ofHours(-3));

            return new UserModel(0, name, email, birthday);
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Data inválida: " + birthdayString + ". Use o formato dd/MM/yyyy");
            return requestUserInfo(); // pede novamente se o usuário errou
        }
    }
}
