package org.example;


import org.example.dao.GenericDAO;
import org.example.domain.ClientDomain;
import org.example.domain.UserDomain;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static GenericDAO<Integer, UserDomain> userDAO = new GenericDAO();
    private static GenericDAO<Integer, ClientDomain> clientDAO = new GenericDAO();


    public static void main(String[] args) {

    var user = (new UserDomain(1,"Joao", 36));
    var cliente = (new ClientDomain(1,"Joao", 36));

        System.out.println("\n================ USER =================");
        System.out.println("Count: " + userDAO.count());
        System.out.println("Saved: " + userDAO.save(2,user, new UserDomain(2, "Maria", 28)));
        System.out.println("All Users: " + userDAO.findAll());
        System.out.println("Find by ID=1: " + userDAO.find(d -> d.getId().equals(1)));
        System.out.println("Find by ID=2: " + userDAO.find(d -> d.getId().equals(2)));
        System.out.println("Deleted: " + userDAO.delete(user));
        System.out.println("Final Users: " + userDAO.findAll());
        System.out.println("=======================================\n");

        System.out.println("\n================ CLIENT ================");
        System.out.println("Count: " + clientDAO.count());
        System.out.println("Saved: " + clientDAO.save(1,cliente));
        System.out.println("All Clients: " + clientDAO.findAll());
        System.out.println("Find by ID=1: " + clientDAO.find(d -> d.getId().equals(1)));
        System.out.println("Find by ID=2: " + clientDAO.find(d -> d.getId().equals(2)));
        System.out.println("Deleted: " + clientDAO.delete(cliente));
        System.out.println("Final Clients: " + clientDAO.findAll());
        System.out.println("=======================================\n");



    }
}