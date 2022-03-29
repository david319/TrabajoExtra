package Extra;

import java.util.Scanner;

import static Extra.ManageBank.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        int option;

        do {
            System.out.println("""
                    Menu de opciones:
                    0. Iniciar
                    1. AddCuenta
                    2. Deposito
                    3. Retiro
                    4. Registrar Intereses
                    5. Importar Cuentas
                    6. Salir
                    Seleccione una opción:""");
            option = leer.nextInt();

            switch (option) {
                case 0 -> {
                    System.out.println("Iniciando...");
                    ManageBank bank = new ManageBank();
                }
                case 1 -> {
                    System.out.println("AddCuenta");
                    System.out.println("Ingrese un código de cuenta:");
                    int codeC = leer.nextInt();
                    System.out.println("Ingrese su nombre:");
                    String nombre = leer.next();
                    System.out.println("""
                            Ingrese el tipo de cuenta:
                            1. Normal
                            2. Planilla
                            3. VIP
                            Seleccione una opción:""");
                    int tipo = leer.nextInt();
                    if (tipo == 1) {
                        addCuenta(codeC, nombre, TipoCuenta.NORMAL);
                    } else if (tipo == 2) {
                        addCuenta(codeC, nombre, TipoCuenta.PLANILLA);
                    } else if (tipo == 3) {
                        addCuenta(codeC, nombre, TipoCuenta.VIP);
                    }
                }
                case 2 -> {
                    System.out.println("Deposito");
                    System.out.println("Ingrese el código de la cuenta:");
                    int code = leer.nextInt();
                    System.out.println("Ingrese el monto a depositar:");
                    double monto = leer.nextDouble();
                    deposito(code, monto);
                }
                case 3 -> {
                    System.out.println("Retiro");
                    System.out.println("Ingrese el código de la cuenta:");
                    int codeR = leer.nextInt();
                    System.out.println("Ingrese el monto a retirar:");
                    double montoRetiro = leer.nextDouble();
                    retiro(codeR, montoRetiro);
                }
                case 4 -> {
                    System.out.println("Registrar Intereses");
                    registrarIntereses();
                }
                case 5 -> {
                    System.out.println("Importar Cuentas");
                    System.out.println("Ingrese el nombre del archivo:");
                    String nameF = leer.next();
                    Import(nameF);
                }
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no valida");
            }
        } while (option != 6);
    }
}
