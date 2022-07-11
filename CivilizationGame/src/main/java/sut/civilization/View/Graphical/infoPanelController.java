package sut.civilization.View.Graphical;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Window;
import sut.civilization.Civilization;
import sut.civilization.Model.Classes.*;

public class infoPanelController extends ViewController{

    public AnchorPane anchorPane;
    private Popup popup = new Popup();

    public void showUnits() {

//        int unitsNumber = GameController.getCurrentTurnUser().getNation().getUnits().size();
        int unitsNumber = 15;
        HBox[] eachUnitHBox = new HBox[unitsNumber];
//        int i = 0;
//        for (Unit unit : GameController.getCurrentTurnUser().getNation().getUnits()) {
        for (int j = 0; j < unitsNumber; j++) {
            ImageView avatar = new ImageView(new Image(
                    Civilization.class.getResourceAsStream("/sut/civilization/Images/user-profile.png")
            ));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label unitType= new Label("Warrior");
            unitType.setPrefWidth(200);
            unitType.setPrefHeight(100);
            unitType.getStyleClass().add("unitInfo");
            unitType.setAlignment(Pos.CENTER);
            Label unitLocation = new Label("(12,25)");
            unitLocation.setPrefHeight(100);
            unitLocation.setPrefWidth(120);
            unitLocation.getStyleClass().add("unitInfo");
            unitLocation.setAlignment(Pos.CENTER);
            Label unitStatus = new Label("AWAKE");
            unitStatus.setPrefHeight(100);
            unitStatus.setPrefWidth(200);
            unitStatus.getStyleClass().add("unitInfo");
            unitStatus.setAlignment(Pos.CENTER);
            eachUnitHBox[j] = new HBox(avatar, unitType, unitLocation, unitStatus);
            eachUnitHBox[j].setPrefHeight(100);

        }
//            if (unit instanceof RangedCombatUnit)
//                unitType  = new Label(((RangedCombatUnit)unit).getRangedCombatUnitType().name);
//            else if (unit instanceof CloseCombatUnit)
//                unitType  = new Label(((CloseCombatUnit)unit).getCloseCombatUnitType().name);
//            else unitType  = new Label(((CivilizedUnit)unit).getCivilizedUnitType().name);

//            Label unitLocation = new Label("(" + unit.getLocation().x + "," + unit.getLocation().y + ")");
//            Label unitStatus = new Label(unit.getUnitStatus().name());
//            eachUnitHBox[i] = new HBox(unitType, unitLocation, unitStatus);

//            i++;
//        }

        VBox allUnitsVBox = new VBox(eachUnitHBox);
        allUnitsVBox.setPrefWidth(620);
        allUnitsVBox.setPrefHeight(420);
        allUnitsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allUnitsVBox);

    }


    public void showCities() {
        int citiesNumber = 10;
        HBox[] eachCityHBox = new HBox[citiesNumber];
        for (int j = 0; j < citiesNumber; j++) {
            Label cityName= new Label("Tehran");
            cityName.setPrefWidth(200);
            cityName.setPrefHeight(100);
            cityName.getStyleClass().add("unitInfo");
            cityName.setAlignment(Pos.CENTER);
            Label citySize = new Label("14");
            citySize.setPrefHeight(100);
            citySize.setPrefWidth(120);
            citySize.getStyleClass().add("unitInfo");
            citySize.setAlignment(Pos.CENTER);
            Label cityPopulation = new Label("200");
            cityPopulation.setPrefHeight(100);
            cityPopulation.setPrefWidth(200);
            cityPopulation.getStyleClass().add("unitInfo");
            cityPopulation.setAlignment(Pos.CENTER);
            eachCityHBox[j] = new HBox(cityName, citySize, cityPopulation);
            eachCityHBox[j].setPrefHeight(100);

        }

        VBox allUnitsVBox = new VBox(eachCityHBox);
        allUnitsVBox.setPrefWidth(620);
        allUnitsVBox.setPrefHeight(420);
        allUnitsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allUnitsVBox);
    }


    public void showDiplomacies() {
        int friendsNumber = 3;
        int enemiesNumber = 5;
        HBox[] eachFriendHBox = new HBox[friendsNumber];
        HBox[] eachEnemyHBox = new HBox[enemiesNumber];

        VBox allNationsVBox = new VBox();
        for (int i = 0; i < friendsNumber; i++) {
            ImageView avatar = new ImageView(new Image(
                    Civilization.class.getResourceAsStream("/sut/civilization/Images/user-profile.png")
            ));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label NationName = new Label("Doost");
            NationName.setPrefWidth(200);
            NationName.setPrefHeight(100);
            NationName.getStyleClass().add("unitInfo");
            NationName.setAlignment(Pos.CENTER);
            eachFriendHBox[i] = new HBox(avatar, NationName);
            eachFriendHBox[i].setPrefHeight(100);

            allNationsVBox.getChildren().add(eachFriendHBox[i]);
        }

        allNationsVBox.getChildren().add(new Separator());

        for (int i = 0; i < enemiesNumber; i++) {
            ImageView avatar = new ImageView(new Image(
                    Civilization.class.getResourceAsStream("/sut/civilization/Images/user-profile.png")
            ));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label NationName = new Label("Doshman");
            NationName.setPrefWidth(200);
            NationName.setPrefHeight(100);
            NationName.getStyleClass().add("unitInfo");
            NationName.setAlignment(Pos.CENTER);
            eachEnemyHBox[i] = new HBox(avatar, NationName);
            eachEnemyHBox[i].setPrefHeight(100);

            allNationsVBox.getChildren().add(eachEnemyHBox[i]);
        }

        allNationsVBox.setPrefWidth(620);
        allNationsVBox.setPrefHeight(420);
        allNationsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allNationsVBox);
    }

    public void showDemographics() {
        int nationNumber = 10;
        HBox[] eachNationHBox = new HBox[nationNumber];
        for (int j = 0; j < nationNumber; j++) {
            ImageView avatar = new ImageView(new Image(
                    Civilization.class.getResourceAsStream("/sut/civilization/Images/user-profile.png")
            ));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label nationName= new Label("Nation " + j);
            nationName.setPrefWidth(200);
            nationName.setPrefHeight(100);
            nationName.getStyleClass().add("unitInfo");
            nationName.setAlignment(Pos.CENTER);
            Label Population = new Label("Population: 10002");
            Population.setPrefHeight(100);
            Population.setPrefWidth(200);
            Population.getStyleClass().add("unitInfo");
            Population.setAlignment(Pos.CENTER);
            Label units = new Label("units: 36");
            units.setPrefHeight(100);
            units.setPrefWidth(200);
            units.getStyleClass().add("unitInfo");
            units.setAlignment(Pos.CENTER);
            Label lands = new Label("lands: 71");
            lands.setPrefHeight(100);
            lands.setPrefWidth(200);
            lands.getStyleClass().add("unitInfo");
            lands.setAlignment(Pos.CENTER);
            eachNationHBox[j] = new HBox(avatar, nationName, Population, units, lands);
            eachNationHBox[j].setPrefHeight(100);

        }

        VBox allUnitsVBox = new VBox(eachNationHBox);
        allUnitsVBox.setPrefWidth(620);
        allUnitsVBox.setPrefHeight(420);
        allUnitsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allUnitsVBox);
    }
    
    
    public void showEconomics() {
        int cityNumber = 30;
        HBox[] eachCityHBox = new HBox[cityNumber];
        for (int j = 0; j < cityNumber; j++) {
            Label cityName= new Label("City " + j);
            cityName.setPrefWidth(100);
            cityName.setPrefHeight(100);
            cityName.getStyleClass().add("unitInfo");
            cityName.setAlignment(Pos.CENTER);
            Label level = new Label("Level: 23");
            level.setPrefHeight(100);
            level.setPrefWidth(100);
            level.getStyleClass().add("unitInfo");
            level.setAlignment(Pos.CENTER);
            Label strength = new Label("strength: 36");
            strength.setPrefHeight(100);
            strength.setPrefWidth(100);
            strength.getStyleClass().add("unitInfo");
            strength.setAlignment(Pos.CENTER);
            Label coin = new Label("coin: +7");
            coin.setPrefHeight(100);
            coin.setPrefWidth(100);
            coin.getStyleClass().add("unitInfo");
            coin.setAlignment(Pos.CENTER);
            Label production = new Label("production: +7");
            production.setPrefHeight(100);
            production.setPrefWidth(100);
            production.getStyleClass().add("unitInfo");
            production.setAlignment(Pos.CENTER);
            Label food = new Label("food: +7");
            food.setPrefHeight(100);
            food.setPrefWidth(100);
            food.getStyleClass().add("unitInfo");
            food.setAlignment(Pos.CENTER);
            eachCityHBox[j] = new HBox(cityName, level, strength, coin, production, food);
            eachCityHBox[j].setPrefHeight(100);
            eachCityHBox[j].setMinHeight(100);

        }

        VBox allUnitsVBox = new VBox(eachCityHBox);
        allUnitsVBox.setPrefWidth(620);
        allUnitsVBox.setPrefHeight(420);
        allUnitsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allUnitsVBox);
    }


    private void scrollPanePopup(VBox vBox) {
        Window window = Game.getCurrentScene().getWindow();
        ImageView ex = new ImageView(new Image(
                Civilization.class.getResourceAsStream("/sut/civilization/Images/ex.png")
        ));
        ex.setFitWidth(20);
        ex.setFitHeight(20);
        ex.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                popup.hide();
                anchorPane.setEffect(null);
            }
        });
        vBox.getChildren().add(0, ex);
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setLayoutY(150);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        popup.getContent().add(scrollPane);
        popup.show(window);
        anchorPane.setEffect(new Lighting());

    }


}
