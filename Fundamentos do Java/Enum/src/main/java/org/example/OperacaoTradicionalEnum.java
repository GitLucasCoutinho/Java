package org.example;

/**
 * Enum que representa operações matemáticas básicas.
 *
 * Cada constante tem sua própria implementação, seja via lambda ou sobrescrita.
 */
public enum OperacaoTradicionalEnum {

    // -------------------------------
    // Estilo TRADICIONAL (em português)
    // -------------------------------

        SOMA {
            @Override
            public double executar(double a, double b) {
                return a + b;
            }
        },
        SUBTRACAO {
            @Override
            public double executar(double a, double b) {
                return a - b;
            }
        },
        MULTIPLICACAO {
            @Override
            public double executar(double a, double b) {
                return a * b;
            }
        },
        DIVISAO {
            @Override
            public double executar(double a, double b) {
                if (b == 0) throw new ArithmeticException("Divisão por zero não permitida");
                return a / b;
            }
        };

        public abstract double executar(double a, double b);
    }