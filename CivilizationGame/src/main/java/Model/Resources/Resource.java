package Model.Resources;

public class Resource {
    protected String name;
    protected int balance;

    public Resource(String name, int balance){
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
