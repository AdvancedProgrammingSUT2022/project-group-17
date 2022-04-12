package Resources;

public class Resource {
    private String name;
    private int balance;
    private int growthRate;

    public Resource(String name, int balance){
        this.name = name;
        this.balance = balance;
        this.growthRate = 0;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public int getGrowthRate() {
        return growthRate;
    }

    public void addGrowth(){
        this.balance += this.growthRate;
    }

    public void setGrowthRate(int growthRate) {
        this.growthRate = growthRate;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
