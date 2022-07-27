package sut.civilization.Controller.GameControllers;

import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.Nation;
import sut.civilization.Model.Classes.User;
import sut.civilization.Model.ModulEnums.ResourceType;

public class NationController extends GameController {

    public String showHappiness() {
        return "" + currentTurnUser.getNation().getHappiness().getBalance();
    }

    public static String declareWar(Nation nation) {
        if (currentTurnUser.getNation().getEnemies().contains(nation))
            return "Already at war with this nation";
        currentTurnUser.getNation().addEnemy(nation);
        currentTurnUser.getNation().removeFriend(nation);
        nation.addEnemy(currentTurnUser.getNation());
        nation.removeFriend(currentTurnUser.getNation());
        return "Declared war against nation " + nation.getNationType().name;
    }

    public static String initiatePeace(Nation nation) {
        if (!currentTurnUser.getNation().getEnemies().contains(nation))
            return "You are not at war with this nation";
        currentTurnUser.getNation().removeEnemy(nation);
        currentTurnUser.getNation().addFriend(nation);
        nation.removeEnemy(currentTurnUser.getNation());
        nation.addFriend(currentTurnUser.getNation());
        return "Made peace with nation " + nation.getNationType().name;
    }

    public static String sendTradeRequest(ResourceType resourceType, int amount, Nation nation2) {
        Nation nation1 = currentTurnUser.getNation();
        if (nation2.getTrade().containsKey("nation"))
            return "This nation is already trading with a nation";
        if (nation1.getResourceCellar().get(resourceType) < amount)
            return "You don't have enough of this resourceType";
        if (nation2.getCoin().getBalance() < amount * 5)
            return "The chosen nation doesn't have enough coins for this trade";
        nation2.putTrade("ResourceType", resourceType.name);
        nation2.putTrade("amount", String.valueOf(amount));
        nation2.putTrade("nation", nation1.getNationType().name);
        return "Trade request sent successfully";
    }

    public static String showTradeRequest(int answer) {
        Nation nation = currentTurnUser.getNation();
        if (!nation.getTrade().containsKey("nation"))
            return "This nation doesn't have any trade requests";
        ResourceType resourceType = GameController.getResourceType(nation.getTrade().get("ResourceType"));
        int amount = Integer.parseInt(nation.getTrade().get("amount"));
        Nation nation2 = getNation(nation.getTrade().get("nation"));

        if (answer == 1) {
            nation.getCoin().addBalance(-Integer.parseInt(nation.getTrade().get("amount")));
            for (int i = 0; i < amount; i++) {
                nation2.removeResource(resourceType);
                nation.addResource(resourceType);
            }

            nation.getTrade().clear();
            return "Traded successfully";
        }

        nation.getTrade().clear();
        return "Canceled trade successfully";

    }

    public static Nation getNation(String name) {
        for (User user : Game.instance.getPlayersInGame()) {
            Nation nation = user.getNation();
            if (nation.getNationType().name.equals(name))
                return nation;
        }

        return null;
    }

    public static void changeSpeed(int amount){
        for (User user : Game.instance.getPlayersInGame()) {
            Nation nation = user.getNation();
            nation.setSpeed(nation.getSpeed() + amount);
        }

    }


}
