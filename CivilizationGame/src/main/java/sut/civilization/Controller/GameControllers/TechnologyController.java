package sut.civilization.Controller.GameControllers;

import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.Nation;
import sut.civilization.Model.Classes.User;
import sut.civilization.Model.ModulEnums.TechnologyType;

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

    public static void updateNextAvailableTechnologies(){
        for (User user : Game.instance.getPlayersInGame()) {
            Nation nation = user.getNation();
            nation.resetNextTechnologies();
            Tech: for (TechnologyType technologyType : TechnologyType.values()) {
                if (!nation.hasTechnology(technologyType)){
                    for (TechnologyType father : technologyType.fathers) {
                        if (!nation.hasTechnology(father))
                            continue Tech;
                    }
                    nation.addNextTechnology(technologyType);
                }
            }
        }
    }

}
