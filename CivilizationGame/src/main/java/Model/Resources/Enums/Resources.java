package Model.Resources.Enums;

import Model.Resources.Resource;

public enum Resources {
    ; //TODO fill enums
    private Resource resource;

    Resources(String name, int balance) {
        this.resource = new Resource(name, balance);
    }
}
