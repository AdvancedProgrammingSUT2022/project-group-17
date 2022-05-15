package Controller.GameControllers;

import Controller.Controller;
import Model.Game;
import Model.Nations.Nation;
import Model.Technologies.TechnologyType;

import java.util.regex.Matcher;

public class TechnologyController extends GameController {

    public String addTechnology(Matcher matcher){
        String type = matcher.group("type");
        Nation nation = currentTurnUser.getNation();
        for (TechnologyType technologyType : TechnologyType.values()) {
            if (technologyType.name.equals(type)){
                if (technologyType.fathers != null){
                    for (TechnologyType father : technologyType.fathers) {
                        if (!nation.hasTechnology(father)){
                            return "You don't have the required technologies";
                        }
                    }
                }
                nation.setInProgressTechnology(technologyType);
                nation.setTechnologyTurns(technologyType.turns);
                return "Technology added successfully";
            }
        }
        return "invalid command";
    }

    public static void activateTechnology(Nation nation){
        nation.addTechnology(nation.getInProgressTechnology());
        nation.setInProgressTechnology(null);
        nation.setTechnologyTurns(-1);
    }
}
