package sut.civilization.Controller.GameControllers;

public class NationController extends GameController {

    public String showHappiness(){
        return "" + currentTurnUser.getNation().getHappiness().getBalance();
    }

}
