package sut.civilization.View.Graphical;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import sut.civilization.Civilization;
import sut.civilization.Controller.GameControllers.GameController;
import sut.civilization.Model.Classes.*;
import sut.civilization.Model.ModulEnums.*;

import java.util.Objects;

import static javafx.scene.paint.Color.WHITE;

public class GamePlayController extends ViewController {
    @FXML
    private ScrollPane root;

    private final static LandGraphical[][] graphicalMap = new LandGraphical[20][20];
    public AnchorPane anchorPane;
    private Popup infoPopup = new Popup();
    private Popup unitPopup = new Popup();
    private Popup cityPopup = new Popup();
    public Label inProgressTechnologyName;
    public ImageView inProgressTechnologyImage;
    private static GamePlayController gamePlayController;

    public static GamePlayController getInstance() {
        if (gamePlayController == null)
            gamePlayController = new GamePlayController();
        return gamePlayController;
    }

    public void initialize() {
        Pane pane = new Pane();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 10; j++) {
                graphicalMap[i][j] = new LandGraphical(new Pair<>(i, j), pane);
            }
        }
        pane.setStyle("-fx-background-position: center; -fx-background-size: auto; -fx-background-image: url(/sut/civilization/Images/BackGround/gameBackground.png);");
        root.setContent(pane);

        ((Stage) Game.instance.getCurrentScene().getWindow()).setFullScreen(true);
        root.setMaxHeight(768);
        root.setMaxWidth(1366);


        TechnologyType inProgressTechnology = GameController.getCurrentTurnUser().getNation().getInProgressTechnology();
        inProgressTechnologyName.setText(inProgressTechnology.name);
        inProgressTechnologyImage.setImage(new Image(Civilization.class.getResourceAsStream(
                inProgressTechnology.imageAddress
        )));
    }

    public void showUnits() {

        int unitsNumber = GameController.getCurrentTurnUser().getNation().getUnits().size();
        HBox[] eachUnitHBox = new HBox[unitsNumber];
        int i = 0;
        for (Unit unit : GameController.getCurrentTurnUser().getNation().getUnits()) {
            ImageView avatar = new ImageView();
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label unitType;
            if (unit instanceof RangedCombatUnit) {
                unitType = new Label(((RangedCombatUnit) unit).getRangedCombatUnitType().name);
                avatar.setImage(new Image(Civilization.class.getResourceAsStream(((RangedCombatUnit) unit).getRangedCombatUnitType().imageAddress)));
            } else if (unit instanceof CloseCombatUnit) {
                unitType = new Label(((CloseCombatUnit) unit).getCloseCombatUnitType().name);
                avatar.setImage(new Image(Civilization.class.getResourceAsStream(((CloseCombatUnit) unit).getCloseCombatUnitType().imageAddress)));
            } else {
                unitType = new Label(((CivilizedUnit) unit).getCivilizedUnitType().name);
                avatar.setImage(new Image(Civilization.class.getResourceAsStream(((CivilizedUnit) unit).getCivilizedUnitType().imageAddress)));
            }

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
        allUnitsVBox.setPrefWidth(800);
        allUnitsVBox.setPrefHeight(500);
        allUnitsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allUnitsVBox);

    }


    public void showCities() {
        int citiesNumber = GameController.getCurrentTurnUser().getNation().getCities().size();
        HBox[] eachCityHBox = new HBox[citiesNumber];
        int i = 0;
        for (City city : GameController.getCurrentTurnUser().getNation().getCities()) {
            Label cityName = new Label(city.getName());
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
        allUnitsVBox.setPrefWidth(800);
        allUnitsVBox.setPrefHeight(500);
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
                    Civilization.class.getResourceAsStream(nation.getNationType().nationImageAddress)
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
                    Civilization.class.getResourceAsStream(nation.getNationType().nationImageAddress)
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

        allNationsVBox.setPrefWidth(800);
        allNationsVBox.setPrefHeight(500);
        allNationsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allNationsVBox);
    }

    public void showDemographics() {
        int nationNumber = Game.instance.getPlayersInGame().size();
        HBox[] eachNationHBox = new HBox[nationNumber];
        int i = 0;
        for (User user : Game.instance.getPlayersInGame()) {
            ImageView avatar = new ImageView(new Image(user.getNation().getNationType().leaderImageAddress));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label nationName = new Label(user.getNation().getNationType().name);
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
        allUnitsVBox.setPrefWidth(800);
        allUnitsVBox.setPrefHeight(500);
        allUnitsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allUnitsVBox);
    }


    public void showEconomics() {
        int cityNumber = GameController.getCurrentTurnUser().getNation().getCities().size();
        HBox[] eachCityHBox = new HBox[cityNumber];

        int i = 0;
        for (City city : GameController.getCurrentTurnUser().getNation().getCities()) {
            Label cityName = new Label(city.getName());
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
        allUnitsVBox.setPrefWidth(800);
        allUnitsVBox.setPrefHeight(500);
        allUnitsVBox.getStyleClass().add("unitList");

        scrollPanePopup(allUnitsVBox);
    }


    private void scrollPanePopup(VBox vBox) {
        Window window = Game.instance.getCurrentScene().getWindow();
        ImageView ex = new ImageView(new Image(
                Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/ex.png")
        ));
        ex.setFitWidth(20);
        ex.setFitHeight(20);
        ex.setOnMouseClicked(mouseEvent -> {
            infoPopup.hide();
            anchorPane.setEffect(null);
            anchorPane.setDisable(false);
        });
        vBox.getChildren().add(0, ex);
        ScrollPane scrollPane = new ScrollPane(vBox);
//        scrollPane.setLayoutY(150);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        borderPane.setTop(ex);

        unitPopup.hide();
        infoPopup.getContent().clear();
        infoPopup.getContent().add(borderPane);
        infoPopup.show(window);
        Light light = new Light.Distant();
        light.setColor(new Color(0.4, 0.4, 0.4, 0.5));
        anchorPane.setEffect(new Lighting(light));
        anchorPane.setDisable(true);

    }

    public void showSelectedCivilizedUnitInfo() {
        if (GameController.getSelectedCivilizedUnit() != null) {

            CivilizedUnitType civilizedUnitType = GameController.getSelectedCivilizedUnit().getCivilizedUnitType();
            Window window = Game.instance.getCurrentScene().getWindow();
            ImageView unitImage = new ImageView();
            unitImage.setImage(new Image(
                    Civilization.class.getResourceAsStream(civilizedUnitType.imageAddress)
            ));
            unitImage.setFitWidth(180);
            unitImage.setFitHeight(180);


            Label unitName = new Label(civilizedUnitType.name);
            unitName.setTextFill(WHITE);
            unitName.setStyle("-fx-font-size: 24; -fx-label-padding: 10; -fx-font-weight: bold");
            Label unitHP = new Label("Health: " + civilizedUnitType.hp);
            unitHP.setTextFill(WHITE);
            unitHP.setStyle("-fx-label-padding: 10 0 0 30");
            //fixme MPs
            Label unitMovement = new Label("Movement: " + civilizedUnitType.MP);
            unitMovement.setTextFill(WHITE);
            unitMovement.setStyle("-fx-label-padding: 10 0 0 30");

            ImageView ex = new ImageView(new Image(
                    Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/ex.png")
            ));
            ex.setFitWidth(20);
            ex.setFitHeight(20);
            ex.setOnMouseClicked(mouseEvent -> {
                unitPopup.hide();
                GameController.setSelectedCivilizedUnit(null);
            });

            VBox unitInfoVBox = new VBox(unitHP, unitMovement);
            unitInfoVBox.setAlignment(Pos.CENTER);
            HBox infoHBox = new HBox(unitImage, unitInfoVBox);
            infoHBox.setAlignment(Pos.CENTER_LEFT);
            VBox wholeUnit = new VBox(ex, unitName, infoHBox);
            wholeUnit.setStyle("-fx-background-color: black; -fx-background-radius: 0 40 0 0;");
            wholeUnit.setAlignment(Pos.CENTER);
            wholeUnit.setPrefSize(380, 250);

//        anchorPane.setEffect(null);
            unitPopup.setX(0);
            unitPopup.setY(550);
            unitPopup.getContent().clear();
            unitPopup.getContent().add(wholeUnit);
            unitPopup.show(window);
        }
    }

    public void showSelectedCombatUnitInfo() {
        if (GameController.getSelectedCombatUnit() != null) {

            CombatUnit combatUnit = GameController.getSelectedCombatUnit();
            Window window = Game.instance.getCurrentScene().getWindow();
            ImageView unitImage = new ImageView();
            Label unitName = new Label();
            Label unitHP = new Label();
            Label unitMovement = new Label();


            if (GameController.getSelectedCombatUnit() instanceof CloseCombatUnit) {
                unitImage.setImage(new Image(
                        Civilization.class.getResourceAsStream(((CloseCombatUnit) combatUnit).getCloseCombatUnitType().imageAddress)
                ));
                unitName.setText(((CloseCombatUnit) combatUnit).getCloseCombatUnitType().name);
                unitHP.setText("Health: " + ((CloseCombatUnit) combatUnit).getCloseCombatUnitType().hp);
                unitMovement.setText("Movement: " + ((CloseCombatUnit) combatUnit).getCloseCombatUnitType().MP);
            } else {
                unitImage.setImage(new Image(
                        Civilization.class.getResourceAsStream(((RangedCombatUnit) combatUnit).getRangedCombatUnitType().imageAddress)
                ));
                unitName.setText(((RangedCombatUnit) combatUnit).getRangedCombatUnitType().name);
                unitHP.setText("Health: " + ((RangedCombatUnit) combatUnit).getRangedCombatUnitType().hp);
                unitMovement.setText("Movement: " + ((RangedCombatUnit) combatUnit).getRangedCombatUnitType().MP);
            }
            unitImage.setFitWidth(180);
            unitImage.setFitHeight(180);

            unitName.setTextFill(WHITE);
            unitName.setStyle("-fx-font-size: 24; -fx-label-padding: 10; -fx-font-weight: bold");

            unitHP.setTextFill(WHITE);
            unitHP.setStyle("-fx-label-padding: 10 0 0 30");
            //fixme MPs
            unitMovement.setTextFill(WHITE);
            unitMovement.setStyle("-fx-label-padding: 10 0 0 30");

            ImageView ex = new ImageView(new Image(
                    Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/ex.png")
            ));
            ex.setFitWidth(20);
            ex.setFitHeight(20);
            ex.setOnMouseClicked(mouseEvent -> {
                unitPopup.hide();
                GameController.setSelectedCombatUnit(null);
            });

            VBox unitInfoVBox = new VBox(unitHP, unitMovement);
            unitInfoVBox.setAlignment(Pos.CENTER);
            HBox infoHBox = new HBox(unitImage, unitInfoVBox);
            infoHBox.setAlignment(Pos.CENTER_LEFT);
            VBox wholeUnit = new VBox(ex, unitName, infoHBox);
            wholeUnit.setStyle("-fx-background-color: black; -fx-background-radius: 0 40 0 0;");
            wholeUnit.setAlignment(Pos.CENTER);
            wholeUnit.setPrefSize(380, 250);

//        anchorPane.setEffect(null);
            unitPopup.setX(0);
            unitPopup.setY(550);
            unitPopup.getContent().clear();
            unitPopup.getContent().add(wholeUnit);
            unitPopup.show(window);
        }
    }


    public void showTechnologyPanel() {
        HBox[] eachFloor = new HBox[12];
        VBox[] eachTechnology = new VBox[46];
        for (int i = 0; i < 12; i++) {
            eachFloor[i] = new HBox();
            eachFloor[i].setAlignment(Pos.CENTER);
        }
        for (int i = 0; i < 46; i++) {
            eachTechnology[i] = new VBox();
            eachTechnology[i].setAlignment(Pos.CENTER);
        }
        int i = 0;
        for (TechnologyType technologyType : TechnologyType.values()) {

            ImageView technologyImage = new ImageView(new Image(Civilization.class.getResourceAsStream(technologyType.imageAddress)));
            Label name = new Label(technologyType.name);
            eachTechnology[i].getChildren().add(technologyImage);
            eachTechnology[i].getChildren().add(name);
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            if (!GameController.getCurrentTurnUser().getNation().getTechnologies().get(technologyType)) {
                technologyImage.setEffect(colorAdjust);
            }

            if (i == 0) {
                eachFloor[0].getChildren().add(eachTechnology[i]);
            } else if (i < 5) {
                eachFloor[1].getChildren().add(eachTechnology[i]);
            } else if (i < 11) {
                eachFloor[2].getChildren().add(eachTechnology[i]);
            } else if (i < 16) {
                eachFloor[3].getChildren().add(eachTechnology[i]);
            } else if (i < 21) {
                eachFloor[4].getChildren().add(eachTechnology[i]);
            } else if (i < 26) {
                eachFloor[5].getChildren().add(eachTechnology[i]);
            } else if (i < 30) {
                eachFloor[6].getChildren().add(eachTechnology[i]);
            } else if (i < 33) {
                eachFloor[7].getChildren().add(eachTechnology[i]);
            } else if (i < 38) {
                eachFloor[8].getChildren().add(eachTechnology[i]);
            } else if (i < 40) {
                eachFloor[9].getChildren().add(eachTechnology[i]);
            } else if (i < 44) {
                eachFloor[10].getChildren().add(eachTechnology[i]);
            } else {
                eachFloor[11].getChildren().add(eachTechnology[i]);
            }
            i++;
        }

        VBox wholeTechTree = new VBox(eachFloor);
        wholeTechTree.setPrefWidth(1200);
        wholeTechTree.setPrefHeight(700);
        wholeTechTree.getStyleClass().add("unitList");
        wholeTechTree.setAlignment(Pos.CENTER);

        scrollPanePopup(wholeTechTree);
    }


    public void showCityPanel() {
//        anchorPane.getChildren().get(4).setVisible(false);
//        anchorPane.getChildren().get(1).setVisible(false);

//        AnchorPane cityAnchorPane = new AnchorPane();

        City city = GameController.getSelectedCity();

        // top-left box
        Label population = new Label(city.getCitizens() + " Citizens");
        population.setStyle("-fx-label-padding: 0 0 20 0; -fx-font-size: 18; -fx-font-weight: bold;");
        population.setTextFill(WHITE);

        ImageView[] currenciesIcons = new ImageView[5];
        Label[] currenciesNames = new Label[5];
        Label[] currenciesAmounts = new Label[5];

        int i = 0;
        for (CurrencyType currencyType : CurrencyType.values()) {
            currenciesIcons[i] = new ImageView(new Image(currencyType.imageAddress));
            VBox.setMargin(currenciesIcons[i], new Insets(5, 5, 5, 5));
            currenciesNames[i] = new Label(currencyType.name);
            currenciesNames[i].setTextFill(currencyType.color);
            VBox.setMargin(currenciesNames[i], new Insets(7, 7, 7, 7));
            switch (currencyType) {
                case FOOD:
                    currenciesAmounts[i] = new Label(String.format("%+d", city.getFoodGrowth()));
                    break;
                case GOLD:
                    currenciesAmounts[i] = new Label(String.format("%+d", city.getCoinGrowth()));
                    break;
                case SCIENCE:
                    currenciesAmounts[i] = new Label(String.format("%+d", city.getScienceGrowth()));
                    break;
                case HAPPINESS:
                    currenciesAmounts[i] = new Label(String.format("%+d", city.getHappinessGrowth()));
                    break;
                case PRODUCTION:
                    currenciesAmounts[i] = new Label(String.format("%+d", city.getProductionGrowth()));
                    break;
            }
            currenciesAmounts[i].setTextFill(currencyType.color);
            VBox.setMargin(currenciesAmounts[i], new Insets(7, 7, 7, 7));
            i++;
        }

        VBox currenciesIconsVBox = new VBox(currenciesIcons);
        VBox currenciesNamesVBox = new VBox(currenciesNames);
        VBox currenciesAmountsVBox = new VBox(currenciesAmounts);

        HBox currenciesInfosHBox = new HBox(currenciesIconsVBox, currenciesNamesVBox, currenciesAmountsVBox);

        VBox wholeCurrenciesInfosVBox = new VBox(population, currenciesInfosHBox);
        wholeCurrenciesInfosVBox.setAlignment(Pos.TOP_CENTER);
        wholeCurrenciesInfosVBox.setStyle("-fx-background-color: #212121; -fx-background-radius: 0 0 20 0;");
        wholeCurrenciesInfosVBox.setPadding(new Insets(10, 20, 20, 10));
        wholeCurrenciesInfosVBox.setLayoutX(0);
        wholeCurrenciesInfosVBox.setLayoutY(30);

        cityPopup.getContent().add(wholeCurrenciesInfosVBox);


        //city-name
        Label cityName = new Label(city.getName());
        cityName.setStyle(
                "-fx-background-color: #212121; -fx-background-radius: 20; -fx-font-size: 24; -fx-font-weight: bold"
        );
        cityName.setAlignment(Pos.CENTER);
        cityName.setTextFill(WHITE);
        cityName.setPadding(new Insets(10, 0, 10, 0));
        cityName.setPrefWidth(350);
        cityName.setLayoutX(508);
        cityName.setLayoutY(75);

        cityPopup.getContent().add(cityName);


        //bottom-left box
        ImageView productImage = new ImageView();
        Label productName = new Label("No product is creating!");
        Label productTurnsLeft = new Label(" Turns left...");
        Label productCost = new Label("Cost: ");
        Label productMaintenance = new Label("Maintenance: ");


        if (city.getInProgressBuilding() != null) {
            Building building = city.getInProgressBuilding();
            productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                    building.getBuildingType().imageAddress
            )));
            productName.setText(building.getBuildingType().name);
            productTurnsLeft.setText(building.getTurnsLeft() + productTurnsLeft.getText());
            productCost.setText(productCost.getText() + building.getBuildingType().cost);
            productMaintenance.setText(productMaintenance.getText() + building.getBuildingType().maintenance);

            //TODO complete other products...
        } else if (city.getInProgressCivilizedUnit() != null) {
            CivilizedUnit civilizedUnit = city.getInProgressCivilizedUnit();
            productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                    civilizedUnit.getCivilizedUnitType().imageAddress
            )));
            productName.setText(civilizedUnit.getCivilizedUnitType().name);
            productTurnsLeft.setText(civilizedUnit.getTurnsLeft() + productTurnsLeft.getText());
            productCost.setText(productCost.getText() + civilizedUnit.getCivilizedUnitType().cost);
            productMaintenance.setText("Movement: " + civilizedUnit.getCivilizedUnitType().MP);

        } else if (city.getInProgressCloseCombatUnit() != null) {
            CloseCombatUnit closeCombatUnit = city.getInProgressCloseCombatUnit();
            productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                    closeCombatUnit.getCloseCombatUnitType().imageAddress
            )));
            productName.setText(closeCombatUnit.getCloseCombatUnitType().name);
            productTurnsLeft.setText(closeCombatUnit.getTurnsLeft() + productTurnsLeft.getText());
            productCost.setText(productCost.getText() + closeCombatUnit.getCloseCombatUnitType().cost);
            productMaintenance.setText("Movement: " + closeCombatUnit.getCloseCombatUnitType().MP);

        } else if (city.getInProgressRangedCombatUnit() != null) {
            RangedCombatUnit rangedCombatUnit = city.getInProgressRangedCombatUnit();
            productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                    rangedCombatUnit.getRangedCombatUnitType().imageAddress
            )));
            productName.setText(rangedCombatUnit.getRangedCombatUnitType().name);
            productTurnsLeft.setText(rangedCombatUnit.getTurnsLeft() + productTurnsLeft.getText());
            productCost.setText(productCost.getText() + rangedCombatUnit.getRangedCombatUnitType().cost);
            productMaintenance.setText("Movement: " + rangedCombatUnit.getRangedCombatUnitType().MP);

        } else {
            productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                    "/sut/civilization/Images/productBorder.png"
            )));
        }
        productImage.setFitWidth(180);
        productImage.setFitHeight(180);

        productName.setTextFill(WHITE);
        productName.setStyle("-fx-font-size: 24; -fx-label-padding: 10; -fx-font-weight: bold");

        productTurnsLeft.setTextFill(WHITE);
        productTurnsLeft.setStyle("-fx-label-padding: 10 0 0 30");

        productCost.setTextFill(WHITE);
        productCost.setStyle("-fx-label-padding: 10 0 0 30");

        productMaintenance.setTextFill(WHITE);
        productMaintenance.setStyle("-fx-label-padding: 10 0 0 30");

        VBox productInfoVBox = new VBox(productTurnsLeft, productCost, productMaintenance);
        productInfoVBox.setAlignment(Pos.CENTER);
        HBox infoHBox = new HBox(productImage, productInfoVBox);
        infoHBox.setAlignment(Pos.CENTER_LEFT);
        VBox wholeProduct = new VBox(productName, infoHBox);
        wholeProduct.setStyle("-fx-background-color: #212121; -fx-background-radius: 0 40 0 0;");
        wholeProduct.setAlignment(Pos.CENTER);
        wholeProduct.setPrefSize(380, 230);
        wholeProduct.setLayoutY(538);

        cityPopup.getContent().add(wholeProduct);

        // product buttons
        Button purchaseButton = new Button("Purchase");
        Button setProductButton = new Button("Set/Change Product");
        setProductButton.setOnMouseClicked(mouseEvent -> {
            // top-right box
            Label unitsHeader = new Label("Units:");
            unitsHeader.setTextFill(WHITE);
            unitsHeader.setStyle("-fx-font-size: 18; -fx-label-padding: 10 0 10 10; -fx-font-weight: bold;");
            VBox listOfAvailableProducts = new VBox();
            listOfAvailableProducts.getChildren().add(unitsHeader);


            for (CivilizedUnitType civilizedUnitType : CivilizedUnitType.values()) {
                if ((civilizedUnitType.technologyType == null ||
                        GameController.getCurrentTurnUser().getNation().getTechnologies().get(civilizedUnitType.technologyType).equals(true)) &&
                        (civilizedUnitType.resourceType == null ||
                                GameController.getCurrentTurnUser().getNation().getResourceCellar().get(civilizedUnitType.resourceType) > 0)) {
                    ImageView unitImage = new ImageView(new Image(Civilization.class.getResourceAsStream(civilizedUnitType.imageAddress)));
                    Label unitName = new Label(civilizedUnitType.name);
                    Label unitTurns = new Label(civilizedUnitType.turns + " turns");
                    unitImage.setFitWidth(50);
                    unitImage.setFitHeight(50);
                    unitName.setTextFill(WHITE);
                    unitName.setStyle("-fx-label-padding: 0 0 5 0; -fx-font-weight: bold;");
                    unitTurns.setTextFill(WHITE);
                    VBox nameAndTurns = new VBox(unitName, unitTurns);
                    nameAndTurns.setAlignment(Pos.CENTER_LEFT);
                    HBox eachUnit = new HBox(unitImage, nameAndTurns);
                    eachUnit.setAlignment(Pos.CENTER_LEFT);
                    eachUnit.setPrefWidth(300);
                    eachUnit.setOnMouseClicked(mouseEvent1 -> {
                        CivilizedUnit rangedCombatUnit = new CivilizedUnit(
                                civilizedUnitType,
                                GameController.getCurrentTurnUser().getNation(),
                                new Pair<>(city.getMainLand().getI(), city.getMainLand().getJ())
                        );
                        city.setInProgressCivilizedUnit(rangedCombatUnit);
                        productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                                rangedCombatUnit.getCivilizedUnitType().imageAddress
                        )));
                        productName.setText(rangedCombatUnit.getCivilizedUnitType().name);
                        productTurnsLeft.setText(rangedCombatUnit.getTurnsLeft() + " Turns left...");
                        productCost.setText("Cost: " + rangedCombatUnit.getCivilizedUnitType().cost);
                        productMaintenance.setText("Maintenance: " + rangedCombatUnit.getCivilizedUnitType().MP);
                    });
                    listOfAvailableProducts.getChildren().add(eachUnit);
                }
            }

            for (CloseCombatUnitType closeCombatUnitType : CloseCombatUnitType.values()) {
                if ((closeCombatUnitType.technologyType == null ||
                        GameController.getCurrentTurnUser().getNation().getTechnologies().get(closeCombatUnitType.technologyType).equals(true)) &&
                        (closeCombatUnitType.resourceType == null ||
                                GameController.getCurrentTurnUser().getNation().getResourceCellar().get(closeCombatUnitType.resourceType) > 0)) {
                    ImageView unitImage = new ImageView(new Image(Civilization.class.getResourceAsStream(closeCombatUnitType.imageAddress)));
                    unitImage.setFitWidth(50);
                    unitImage.setFitHeight(50);
                    Label unitName = new Label(closeCombatUnitType.name);
                    unitName.setTextFill(WHITE);
                    unitName.setStyle("-fx-label-padding: 0 0 5 0; -fx-font-weight: bold;");
                    Label unitTurns = new Label(closeCombatUnitType.turns + " turns");
                    unitTurns.setTextFill(WHITE);
                    VBox nameAndTurns = new VBox(unitName, unitTurns);
                    nameAndTurns.setAlignment(Pos.CENTER_LEFT);
                    HBox eachUnit = new HBox(unitImage, nameAndTurns);
                    eachUnit.setAlignment(Pos.CENTER_LEFT);
                    eachUnit.setPrefWidth(300);
                    eachUnit.setOnMouseClicked(mouseEvent1 -> {
                        CloseCombatUnit closeCombatUnit = new CloseCombatUnit(
                                closeCombatUnitType,
                                GameController.getCurrentTurnUser().getNation(),
                                new Pair<>(city.getMainLand().getI(), city.getMainLand().getJ())
                        );
                        city.setInProgressCloseCombatUnit(closeCombatUnit);
                        productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                                closeCombatUnit.getCloseCombatUnitType().imageAddress
                        )));
                        productName.setText(closeCombatUnit.getCloseCombatUnitType().name);
                        productTurnsLeft.setText(closeCombatUnit.getTurnsLeft() + " Turns left...");
                        productCost.setText("Cost: " + closeCombatUnit.getCloseCombatUnitType().cost);
                        productMaintenance.setText("Maintenance: " + closeCombatUnit.getCloseCombatUnitType().MP);
                    });
                    listOfAvailableProducts.getChildren().add(eachUnit);
                }
            }

            for (RangedCombatUnitType rangedCombatUnitType : RangedCombatUnitType.values()) {
                if ((rangedCombatUnitType.technologyType == null ||
                        GameController.getCurrentTurnUser().getNation().getTechnologies().get(rangedCombatUnitType.technologyType).equals(true)) &&
                        (rangedCombatUnitType.resourceType == null ||
                                GameController.getCurrentTurnUser().getNation().getResourceCellar().get(rangedCombatUnitType.resourceType) > 0)) {
                    ImageView unitImage = new ImageView(new Image(Civilization.class.getResourceAsStream(rangedCombatUnitType.imageAddress)));
                    unitImage.setFitWidth(50);
                    unitImage.setFitHeight(50);
                    Label unitName = new Label(rangedCombatUnitType.name);
                    unitName.setTextFill(WHITE);
                    unitName.setStyle("-fx-label-padding: 0 0 5 0; -fx-font-weight: bold;");
                    Label unitTurns = new Label(rangedCombatUnitType.turns + " turns");
                    unitTurns.setTextFill(WHITE);
                    VBox nameAndTurns = new VBox(unitName, unitTurns);
                    nameAndTurns.setAlignment(Pos.CENTER_LEFT);
                    HBox eachUnit = new HBox(unitImage, nameAndTurns);
                    eachUnit.setAlignment(Pos.CENTER_LEFT);
                    eachUnit.setPrefWidth(300);
                    eachUnit.setOnMouseClicked(mouseEvent1 -> {
                        RangedCombatUnit rangedCombatUnit = new RangedCombatUnit(
                                rangedCombatUnitType,
                                GameController.getCurrentTurnUser().getNation(),
                                new Pair<>(city.getMainLand().getI(), city.getMainLand().getJ())
                        );
                        city.setInProgressRangedCombatUnit(rangedCombatUnit);
                        productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                                rangedCombatUnit.getRangedCombatUnitType().imageAddress
                        )));
                        productName.setText(rangedCombatUnit.getRangedCombatUnitType().name);
                        productTurnsLeft.setText(rangedCombatUnit.getTurnsLeft() + " Turns left...");
                        productCost.setText("Cost: " + rangedCombatUnit.getRangedCombatUnitType().cost);
                        productMaintenance.setText("Maintenance: " + rangedCombatUnit.getRangedCombatUnitType().MP);
                    });
                    listOfAvailableProducts.getChildren().add(eachUnit);
                }
            }

            Label buildingsHeader = new Label("Buildings:");
            buildingsHeader.setTextFill(WHITE);
            buildingsHeader.setStyle("-fx-font-size: 18; -fx-label-padding: 10 0 10 10; -fx-font-weight: bold;");
            listOfAvailableProducts.getChildren().add(new Separator());
            listOfAvailableProducts.getChildren().add(buildingsHeader);

            for (BuildingType buildingType : BuildingType.values()) {
                if (buildingType.technologyType == null ||
                        GameController.getCurrentTurnUser().getNation().getTechnologies().get(buildingType.technologyType).equals(true)) {
                    ImageView buildingImage = new ImageView(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(buildingType.imageAddress))));
                    buildingImage.setFitWidth(50);
                    buildingImage.setFitHeight(50);
                    Label buildingName = new Label(buildingType.name);
                    buildingName.setTextFill(WHITE);
                    buildingName.setStyle("-fx-label-padding: 0 0 5 0; -fx-font-weight: bold;");
                    Label buildingTurns = new Label(buildingType.initialTurns + " turns");
                    buildingTurns.setTextFill(WHITE);
                    VBox nameAndTurns = new VBox(buildingName, buildingTurns);
                    nameAndTurns.setAlignment(Pos.CENTER_LEFT);
                    HBox eachBuilding = new HBox(buildingImage, nameAndTurns);
                    eachBuilding.setAlignment(Pos.CENTER_LEFT);
                    eachBuilding.setPrefWidth(300);
                    eachBuilding.setOnMouseClicked(mouseEvent1 -> {
                        Building building = new Building(buildingType);
                        city.setInProgressBuilding(building);
                        productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                                building.getBuildingType().imageAddress
                        )));
                        productName.setText(building.getBuildingType().name);
                        productTurnsLeft.setText(building.getTurnsLeft() + " Turns left...");
                        productCost.setText("Cost: " + building.getBuildingType().cost);
                        productMaintenance.setText("Maintenance: " + building.getBuildingType().maintenance);
                    });
                    listOfAvailableProducts.getChildren().add(eachBuilding);
                }
            }


            listOfAvailableProducts.setStyle("-fx-background-color: #212121;");
            ScrollPane scrollPane = new ScrollPane(listOfAvailableProducts);
            scrollPane.setPrefWidth(300);
            scrollPane.setLayoutX(1066);
            scrollPane.setLayoutY(30);
            scrollPane.setMaxHeight(738);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            cityPopup.getContent().add(scrollPane);
        });

        HBox productButtons = new HBox(purchaseButton, setProductButton);
        productButtons.setLayoutY(498);
        productButtons.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        cityPopup.getContent().add(productButtons);

        // two center buttons
        Button buyATile = new Button("Buy a tile");
        Button returnToMap = new Button("Return to map");
        returnToMap.setOnMouseClicked(mouseEvent -> {
            cityPopup.getContent().remove(5);
            cityPopup.hide();
            GameController.setSelectedCity(null);
        });

        VBox centerButtons = new VBox(buyATile, returnToMap);
        VBox.setMargin(buyATile, new Insets(0, 0, 20, 0));
        centerButtons.setLayoutX(593);
        centerButtons.setLayoutY(560);
        centerButtons.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        cityPopup.getContent().add(centerButtons);
//        cityAnchorPane.setStyle("Button:hover{-fx-background-radius: 20;-fx-border-radius: 20;-fx-background-color: rgb(19, 135, 135);-fx-text-fill: white;-fx-border-color: white;-fx-background-color: white;-fx-text-fill: rgb(33, 33, 33);}");
//        cityAnchorPane.setPrefWidth(1366);
//        cityAnchorPane.setPrefHeight(768);
//        cityAnchorPane.setBackground(Background.EMPTY);

        Window window = Game.instance.getCurrentScene().getWindow();

        GameController.setSelectedCivilizedUnit(null);
        GameController.setSelectedCombatUnit(null);
        unitPopup.hide();
        cityPopup.setX(0);
        cityPopup.show(window);

    }

    public ScrollPane getRoot() {
        return root;
    }

    public void setRoot(ScrollPane root) {
        this.root = root;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Popup getInfoPopup() {
        return infoPopup;
    }

    public void setInfoPopup(Popup infoPopup) {
        this.infoPopup = infoPopup;
    }

    public Popup getUnitPopup() {
        return unitPopup;
    }

    public void setUnitPopup(Popup unitPopup) {
        this.unitPopup = unitPopup;
    }

    public Label getInProgressTechnologyName() {
        return inProgressTechnologyName;
    }

    public void setInProgressTechnologyName(Label inProgressTechnologyName) {
        this.inProgressTechnologyName = inProgressTechnologyName;
    }

    public ImageView getInProgressTechnologyImage() {
        return inProgressTechnologyImage;
    }

    public void setInProgressTechnologyImage(ImageView inProgressTechnologyImage) {
        this.inProgressTechnologyImage = inProgressTechnologyImage;
    }

    public void nextTurn(MouseEvent mouseEvent) {
        System.out.println(new Gson().toJson(Game.instance));

    }
}
