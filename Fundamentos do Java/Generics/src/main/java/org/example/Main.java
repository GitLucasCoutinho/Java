package org.example;


import org.example.dao.GenericDAO;
import org.example.domain.UserDomain;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static GenericDAO<UserDomain> dao = new GenericDAO<>() {
    };

    public static void main(String[] args) {

    var user = (new UserDomain(2,"Joao", 36));
        System.out.println(dao.count());
        System.out.println(dao.save(user));
        System.out.println(dao.count());
        System.out.println(dao.findAll());
        //  var d = new UserDomain("Joso", 38);
        //  System.out.println(dao.update(d));
        System.out.println(dao.findAll());
        System.out.println(dao.delete(user));
        System.out.println(dao.delete(new UserDomain(1,"Lucas", -1)));
        System.out.println("ultimo"+dao.findAll());

    }
}