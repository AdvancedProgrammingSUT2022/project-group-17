package Model.Resources;

import Model.Resources.Enums.ResourceType;

public class Resource {
    protected int balance;
    protected ResourceType resourceType;

    public Resource(ResourceType resourceType){
        this.resourceType = resourceType;
        this.balance = 0;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
