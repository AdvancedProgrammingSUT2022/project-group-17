package sut.civilization.View.Graphical;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import sut.civilization.Civilization;
import sut.civilization.Controller.GameControllers.GameController;
import sut.civilization.Model.Classes.*;

import java.util.Stack;

import static javafx.scene.paint.Color.WHITE;

public class GamePlayController extends ViewController{
    @FXML
    private ScrollPane root;
    public AnchorPane anchorPane;
    private Popup popup = new Popup();

    public void initialize(){
        Pane pane = new Pane();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 10; j++) {
                LandGraphical landGraphical = new LandGraphical(new Pair<>(i, j),pane);
            }
        }
        pane.setStyle("-fx-background-position: center; -fx-background-size: auto; -fx-background-image: url(/sut/civilization/Images/BackGround/gameBackground.png);");
        root.setContent(pane);

        ((Stage) Game.getCurrentScene().getWindow()).setFullScreen(true);
        root.setMaxHeight(Game.getCurrentScene().getWindow().getHeight());
        root.setMaxWidth(Game.getCurrentScene().getWindow().getWidth());
    }

    public void showUnits() {

        int unitsNumber = GameController.getCurrentTurnUser().getNation().getUnits().size();
        HBox[] eachUnitHBox = new HBox[unitsNumber];
        int i = 0;
        for (Unit unit : GameController.getCurrentTurnUser().getNation().getUnits()) {
            ImageView avatar = new ImageView(new Image(
                    Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/user-profile.png")
            ));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label unitType;
            if (unit instanceof RangedCombatUnit)
                unitType  = new Label(((RangedCombatUnit)unit).getRangedCombatUnitType().name);
            else if (unit instanceof CloseCombatUnit)
                unitType  = new Label(((CloseCombatUnit)unit).getCloseCombatUnitType().name);
            else unitType  = new Label(((CivilizedUnit)unit).getCivilizedUnitType().name);

            Label unitLocation = new Label("(" + unit.getLocation().x + "," + unit.getLocation().y + ")");
            Label unitStatus = new Label(unit.getUnitStatus().name());
            unitType.setPrefWidth(200);
            unitType.setPrefHeight(100);
            unitType.getStyleClass().add("unitInfo");
            unitType.setAlignment(Pos.CENTER);
            unitLocation.setPrefHeight(100);
            unitLocation.setPrefWidth(120);
            unitLocation.getStyleClass().add("unitInfo");
            unitLocation.setAlignment(Pos.CENTER);
            unitStatus.setPrefHeight(100);
            unitStatus.setPrefWidth(200);
            unitStatus.getStyleClass().add("unitInfo");
            unitStatus.setAlignment(Pos.CENTER);
            eachUnitHBox[i] = new HBox(avatar, unitType, unitLocation, unitStatus);
            eachUnitHBox[i].setPrefHeight(100);

            i++;
        }

        VBox allUnitsVBox = new VBox(eachUnitHBox);
        allUnitsVBox.setPrefWidth(620);
        allUnitsVBox.setPrefHeight(420);
        allUnitsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allUnitsVBox);

    }


    public void showCities() {
        int citiesNumber = GameController.getCurrentTurnUser().getNation().getCities().size();
        HBox[] eachCityHBox = new HBox[citiesNumber];
        int i = 0;
        for (City city : GameController.getCurrentTurnUser().getNation().getCities()) {
            Label cityName= new Label(city.getName());
            cityName.setPrefWidth(200);
            cityName.setPrefHeight(100);
            cityName.getStyleClass().add("unitInfo");
            cityName.setAlignment(Pos.CENTER);
            Label citySize = new Label(String.valueOf(city.getLands().size()));
            citySize.setPrefHeight(100);
            citySize.setPrefWidth(120);
            citySize.getStyleClass().add("unitInfo");
            citySize.setAlignment(Pos.CENTER);
            Label cityPopulation = new Label(String.valueOf(city.getCitizens()));
            cityPopulation.setPrefHeight(100);
            cityPopulation.setPrefWidth(200);
            cityPopulation.getStyleClass().add("unitInfo");
            cityPopulation.setAlignment(Pos.CENTER);
            eachCityHBox[i] = new HBox(cityName, citySize, cityPopulation);
            eachCityHBox[i].setPrefHeight(100);

            i++;
        }

        VBox allUnitsVBox = new VBox(eachCityHBox);
        allUnitsVBox.setPrefWidth(620);
        allUnitsVBox.setPrefHeight(420);
        allUnitsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allUnitsVBox);
    }


    public void showDiplomacies() {
        int friendsNumber = GameController.getCurrentTurnUser().getNation().getFriends().size();
        int enemiesNumber = GameController.getCurrentTurnUser().getNation().getEnemies().size();
        HBox[] eachFriendHBox = new HBox[friendsNumber];
        HBox[] eachEnemyHBox = new HBox[enemiesNumber];

        VBox allNationsVBox = new VBox();
        int i = 0;
        for (Nation nation : GameController.getCurrentTurnUser().getNation().getFriends()) {
            ImageView avatar = new ImageView(new Image(
                    Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/user-profile.png")
            ));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label NationName = new Label(nation.getNationType().name);
            NationName.setPrefWidth(200);
            NationName.setPrefHeight(100);
            NationName.getStyleClass().add("unitInfo");
            NationName.setAlignment(Pos.CENTER);
            eachFriendHBox[i] = new HBox(avatar, NationName);
            eachFriendHBox[i].setPrefHeight(100);

            allNationsVBox.getChildren().add(eachFriendHBox[i]);
            i++;
        }

        allNationsVBox.getChildren().add(new Separator());

        i = 0;
        for (Nation nation : GameController.getCurrentTurnUser().getNation().getEnemies()) {
            ImageView avatar = new ImageView(new Image(
                    Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/user-profile.png")
            ));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label NationName = new Label(nation.getNationType().name);
            NationName.setPrefWidth(200);
            NationName.setPrefHeight(100);
            NationName.getStyleClass().add("unitInfo");
            NationName.setAlignment(Pos.CENTER);
            eachEnemyHBox[i] = new HBox(avatar, NationName);
            eachEnemyHBox[i].setPrefHeight(100);

            allNationsVBox.getChildren().add(eachEnemyHBox[i]);
            i++;
        }

        allNationsVBox.setPrefWidth(620);
        allNationsVBox.setPrefHeight(420);
        allNationsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allNationsVBox);
    }

    public void showDemographics() {
        int nationNumber = Game.getPlayersInGame().size();
        HBox[] eachNationHBox = new HBox[nationNumber];
        int i = 0;
        for (User user : Game.getPlayersInGame()) {
            ImageView avatar = new ImageView(new Image(user.getAvatarLocation()));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label nationName= new Label(user.getNation().getNationType().name);
            nationName.setPrefWidth(200);
            nationName.setPrefHeight(100);
            nationName.getStyleClass().add("unitInfo");
            nationName.setAlignment(Pos.CENTER);
            int population = 0;
            int landNum = 0;
            for (City city : user.getNation().getCities()) {
                population += city.getCitizens();
                landNum += city.getLands().size();
            }
            Label Population = new Label("Population: " + population);
            Population.setPrefHeight(100);
            Population.setPrefWidth(200);
            Population.getStyleClass().add("unitInfo");
            Population.setAlignment(Pos.CENTER);
            Label units = new Label("units: " + user.getNation().getUnits().size());
            units.setPrefHeight(100);
            units.setPrefWidth(200);
            units.getStyleClass().add("unitInfo");
            units.setAlignment(Pos.CENTER);

            Label lands = new Label("lands: " + landNum);
            lands.setPrefHeight(100);
            lands.setPrefWidth(200);
            lands.getStyleClass().add("unitInfo");
            lands.setAlignment(Pos.CENTER);
            eachNationHBox[i] = new HBox(avatar, nationName, Population, units, lands);
            eachNationHBox[i].setPrefHeight(100);

            i++;
        }

        VBox allUnitsVBox = new VBox(eachNationHBox);
        allUnitsVBox.setPrefWidth(620);
        allUnitsVBox.setPrefHeight(420);
        allUnitsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allUnitsVBox);
    }


    public void showEconomics() {
        int cityNumber = GameController.getCurrentTurnUser().getNation().getCities().size();
        HBox[] eachCityHBox = new HBox[cityNumber];

        int i = 0;
        for (City city : GameController.getCurrentTurnUser().getNation().getCities()) {
            Label cityName= new Label(city.getName());
            cityName.setPrefWidth(100);
            cityName.setPrefHeight(100);
            cityName.getStyleClass().add("unitInfo");
            cityName.setAlignment(Pos.CENTER);
            Label level = new Label("Level: " + city.getLevel());
            level.setPrefHeight(100);
            level.setPrefWidth(100);
            level.getStyleClass().add("unitInfo");
            level.setAlignment(Pos.CENTER);
            Label strength = new Label("strength: " + city.getCombatStrength());
            strength.setPrefHeight(100);
            strength.setPrefWidth(100);
            strength.getStyleClass().add("unitInfo");
            strength.setAlignment(Pos.CENTER);
            Label coin = new Label("coin: " + city.getCoinGrowth());
            coin.setPrefHeight(100);
            coin.setPrefWidth(100);
            coin.getStyleClass().add("unitInfo");
            coin.setAlignment(Pos.CENTER);
            Label production = new Label("production: " + city.getProductionGrowth());
            production.setPrefHeight(100);
            production.setPrefWidth(100);
            production.getStyleClass().add("unitInfo");
            production.setAlignment(Pos.CENTER);
            Label food = new Label("food: " + city.getFoodGrowth());
            food.setPrefHeight(100);
            food.setPrefWidth(100);
            food.getStyleClass().add("unitInfo");
            food.setAlignment(Pos.CENTER);
            eachCityHBox[i] = new HBox(cityName, level, strength, coin, production, food);
            eachCityHBox[i].setPrefHeight(100);
            eachCityHBox[i].setMinHeight(100);

            i++;
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
                Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/ex.png")
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

        popup.getContent().clear();
        popup.getContent().add(scrollPane);
        popup.setX(200);
        popup.setY(0);
        popup.show(window);
        anchorPane.setEffect(new Lighting());

    }

    public void showSelectedUnitInfo() {
        Window window = Game.getCurrentScene().getWindow();
        ImageView unitBox = new ImageView(new Image(
                Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/unitBox.png")
        ));
        unitBox.setLayoutX(15);
        unitBox.setLayoutY(580);
        unitBox.setFitWidth(300);
        unitBox.setFitHeight(200);
        ImageView unit = new ImageView(new Image(
                Civilization.class.getResourceAsStream("/sut/civilization/Images/Icons/UnitIcons/Archer.png")
        ));
        unit.setLayoutX(25);
        unit.setLayoutY(640);
        unit.setFitWidth(70);
        unit.setFitHeight(70);


        Label unitName = new Label("Archer");
        unitName.setTextFill(WHITE);
        unitName.setStyle("-fx-font-size: 24; -fx-label-padding: 5 0 20; -fx-font-weight: bold");
        Label unitStrength = new Label("Strength: 12");
        unitStrength.setTextFill(WHITE);
        unitStrength.setStyle("-fx-label-padding: 10");
        Label unitMovement = new Label("Movement: 2/2");
        unitMovement.setTextFill(WHITE);
        unitMovement.setStyle("-fx-label-padding: 10");
        VBox unitInfoVBox = new VBox(unitName, unitStrength, unitMovement);
        unitInfoVBox.setAlignment(Pos.CENTER);
        HBox infoHBox = new HBox(unit, unitInfoVBox);
        infoHBox.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(unitBox, infoHBox);

        popup.getContent().clear();
        popup.getContent().add(stackPane);
        popup.setX(15);
        popup.setY(580);
        popup.show(window);
    }

}
