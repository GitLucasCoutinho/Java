package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println(soma(2,3));
        System.out.println(soma(1,2,3));
        System.out.println(soma(2.5,3.5));
        System.out.println(soma("O resultado é: ",5));

    }

    // Método soma com dois inteiros
    public static int soma(int a, int b){
        return a + b;
    }

    // Sobrecarga: soma com três inteiros
    public static int soma ( int a, int b, int c){
        return a + b + c;
    }
    // Sobrecarga: soma com dois números decimais
    public static double soma ( double a, double b){
        return a + b;
    }

    // Sobrecarga: soma com ordem diferente de parâmetros
    public static String soma (String a,int b){
        return a + b;
    }

}


