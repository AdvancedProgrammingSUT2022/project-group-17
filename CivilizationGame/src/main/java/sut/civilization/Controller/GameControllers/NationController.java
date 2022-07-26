package sut.civilization.Controller.GameControllers;

import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.Nation;
import sut.civilization.Model.Classes.User;
import sut.civilization.Model.ModulEnums.NationType;
import sut.civilization.Model.ModulEnums.ResourceType;

public class NationController extends GameController {

    public String showHappiness(){
        return "" + currentTurnUser.getNation().getHappiness().getBalance();
    }

    public static String declareWar(Nation nation){
        if (currentTurnUser.getNation().getNationsAtWar().contains(nation))
            return "Already at war with this nation";
        currentTurnUser.getNation().addAtWarNation(nation);
        nation.addAtWarNation(currentTurnUser.getNation());
        return "Declared war against nation " + nation.getNationType().name;
    }

    public static String initiatePeace(Nation nation){
        if (!currentTurnUser.getNation().getNationsAtWar().contains(nation))
            return "You are not at war with this nation";
        currentTurnUser.getNation().removeAtWarNation(nation);
        nation.removeAtWarNation(currentTurnUser.getNation());
        return "Made peace with nation " + nation.getNationType().name;
    }

    public static String sendTradeRequest(ResourceType resourceType, int amount, Nation nation1 ,Nation nation2){
        if (nation2.getTrade().containsKey("nation"))
            return "This nation is already trading with a nation";
        if (nation1.getResourceCellar().get(resourceType) <= amount)
            return "You have enough of this resourceType";
        if (nation2.getCoin().getBalance() < amount * 2)
            return "The chosen nation doesn't have enough coins for this trade";
        nation2.putTrade("ResourceType", resourceType.name);
        nation2.putTrade("amount", String.valueOf(amount));
        nation2.putTrade("nation", nation1.getNationType().name);
        return "Trade request sent successfully";
    }

    public static String showTradeRequest(Nation nation, int answer){
        if (!nation.getTrade().containsKey("nation"))
            return "This nation doesn't have any trade requests";
        ResourceType resourceType = GameController.getResourceType(nation.getTrade().get("ResourceType"));
        int amount = Integer.parseInt(nation.getTrade().get("amount"));
        Nation nation2 = getNation(nation.getTrade().get("nation"));

        if (answer == 1){
            nation.getCoin().addBalance(-Integer.parseInt(nation.getTrade().get("amount")));
            for (int i = 0; i < amount; i++) {
                nation.removeResource(resourceType);
                nation2.addResource(resourceType);
            }

            nation.getTrade().clear();
            return "Traded successfully";
        }

        nation.getTrade().clear();
        return "Canceled trade successfully";

    }

    public static Nation getNation(String name){
        for (User user : Game.instance.getPlayersInGame()) {
            Nation nation = user.getNation();
            if (nation.getNationType().name.equals(nation))
                return nation;
        }

        return null;
    }



}
