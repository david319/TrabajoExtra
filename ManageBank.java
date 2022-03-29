package Extra;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Calendar;

public class ManageBank {

    private static RandomAccessFile cuentas;

    public ManageBank() throws Exception {
        File file = new File("banco");
        if (!file.exists()) {
            file.mkdir();
        }
        cuentas = new RandomAccessFile("banco/cuentas.bnk", "rw");
        cuentas.seek(cuentas.length());
        cuentas.writeInt(0);
        cuentas.writeUTF("");
        cuentas.writeLong(0);
        cuentas.writeDouble(0);
        cuentas.writeUTF("");
        cuentas.close();
    }

    public static int buscar(int cod) throws Exception {
        cuentas = new RandomAccessFile("banco/cuentas.bnk", "rw");
        for (int i = 0; i < cuentas.length(); i++) {
            cuentas.seek(i);
            if (cuentas.readInt() == cod) {
                return i;
            }
        }
        return 1;
    }

    public static void addCuenta(int cod, String nombre, TipoCuenta tipo) throws Exception {
        cuentas = new RandomAccessFile("banco/cuentas.bnk", "rw");
        for (int i = 0; i < cuentas.length(); i++) {
            cuentas.seek(i);
            if (cuentas.readInt() == cod) {
                throw new AccountAlreadyExist(cod);
            } else {
                cuentas.writeInt(cod);
                cuentas.writeUTF(nombre);
                cuentas.writeLong(Calendar.getInstance().getTimeInMillis());
                cuentas.writeDouble(0);
                cuentas.writeUTF(tipo.toString());
                cuentas.close();
            }
        }
    }

    public static void deposito(int cod, double monto) throws Exception {
        cuentas = new RandomAccessFile("banco/cuentas.bnk", "rw");
        cuentas.seek(buscar(cod));
        cuentas.readUTF();
        cuentas.writeLong(Calendar.getInstance().getTimeInMillis());
        cuentas.writeDouble(cuentas.readDouble() + monto);
        System.out.println("Deposito realizado con éxito saldo actual: " + cuentas.readDouble() + " Lps.");
        cuentas.close();
    }

    public static void retiro(int cod, double monto) throws Exception {
        cuentas = new RandomAccessFile("banco/cuentas.bnk", "rw");
        cuentas.seek(buscar(cod));
        cuentas.readUTF();
        cuentas.writeLong(Calendar.getInstance().getTimeInMillis());
        double saldoActual = cuentas.readDouble();
        if (saldoActual < monto) {
            System.out.println("Saldo insuficiente");
        } else {
            cuentas.writeDouble(saldoActual - monto);
            System.out.println("Retiro realizado con éxito saldo actual: " + cuentas.readDouble() + " Lps.");
        }
        cuentas.close();
    }

    public static void registrarIntereses() throws Exception {
        cuentas = new RandomAccessFile("banco/cuentas.bnk", "rw");
        for (int i = 0; i < cuentas.length(); i++) {
            cuentas.seek(i);
            cuentas.readUTF();
            cuentas.readLong();
            cuentas.readDouble();
            TipoCuenta tipo = TipoCuenta.valueOf(cuentas.readUTF());
            cuentas.writeDouble(cuentas.readDouble() + cuentas.readDouble() * tipo.getTasaInteres());
            cuentas.close();
        }
    }

    public static void Import(String filename) throws Exception {
        File listC = new File(filename + ".txt");
        FileWriter fw = new FileWriter(listC, false);
        StringBuilder info = new StringBuilder();
        cuentas = new RandomAccessFile("banco/cuentas.bnk", "rw");
        for (int i = 0; i < cuentas.length(); i++) {
            cuentas.seek(i);
            info.append("Código: ").append(cuentas.readInt()).append("\n");
            info.append("Nombre: ").append(cuentas.readUTF()).append("\n");
            cuentas.readLong();
            info.append("Saldo: ").append(cuentas.readDouble()).append("Lps.\n");
            info.append("Tipo: ").append(cuentas.readUTF()).append("\n");
            info.append("\n");
        }
        fw.write(info.toString());
        fw.close();
        System.out.println("Archivo exportado");
        System.out.println("Información de las cuentas: " + info);
    }
}
