package Extra;

public class AccountAlreadyExist extends Exception {

    public AccountAlreadyExist(int id) {
        super("Cuenta " + id + " ya esta agregada en el sistema");
    }
}
