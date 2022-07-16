package sut.civilization.View.Graphical;

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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import sut.civilization.Civilization;
import sut.civilization.Controller.GameControllers.GameController;
import sut.civilization.Model.Classes.*;
import sut.civilization.Model.ModulEnums.CivilizedUnitType;
import sut.civilization.Model.ModulEnums.CurrencyType;
import sut.civilization.Model.ModulEnums.TechnologyType;

import static javafx.scene.paint.Color.WHITE;

public class GamePlayController extends ViewController {
    @FXML
    private ScrollPane root;
    public AnchorPane anchorPane;
    private final static LandGraphical[][] graphicalMap = new LandGraphical[20][20];
    private Popup infoPopup = new Popup();
    private Popup unitPopup = new Popup();
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
                final int finalI = i;
                final int finalJ = j;
                graphicalMap[i][j] = new LandGraphical(new Pair<>(i, j), pane);
//                graphicalMap[i][j].getCivilizedUnitImageView().x.setOnMouseClicked(mouseEvent -> {
//                    showSelectedCivilizedUnitInfo();
//                });
            }
        }
        pane.setStyle("-fx-background-position: center; -fx-background-size: auto; -fx-background-image: url(/sut/civilization/Images/BackGround/gameBackground.png);");
        root.setContent(pane);

        ((Stage) Game.getCurrentScene().getWindow()).setFullScreen(true);
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
        int nationNumber = Game.getPlayersInGame().size();
        HBox[] eachNationHBox = new HBox[nationNumber];
        int i = 0;
        for (User user : Game.getPlayersInGame()) {
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
        Window window = Game.getCurrentScene().getWindow();
        ImageView ex = new ImageView(new Image(
                Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/ex.png")
        ));
        ex.setFitWidth(20);
        ex.setFitHeight(20);
        ex.setOnMouseClicked(mouseEvent -> {
            infoPopup.hide();
            anchorPane.setEffect(null);
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

    }

    public void showSelectedCivilizedUnitInfo() {
        if (unitPopup.isShowing()) {
            unitPopup.hide();
            return;
        }
        CivilizedUnitType civilizedUnitType = GameController.getSelectedCivilizedUnit().getCivilizedUnitType();
        Window window = Game.getCurrentScene().getWindow();
        ImageView unitImage = new ImageView();
//        if (GameController.getSelectedCivilizedUnit() != null) {
        unitImage.setImage(new Image(
                Civilization.class.getResourceAsStream(civilizedUnitType.imageAddress)
        ));
//        } else if (GameController.getSelectedCombatUnit() != null) {
//            if (GameController.getSelectedCombatUnit() instanceof CloseCombatUnit) {
//                unitImage.setImage(new Image(
//                        Civilization.class.getResourceAsStream(((CloseCombatUnit) GameController.getSelectedCombatUnit()).getCloseCombatUnitType().imageAddress)
//                ));
//            } else {
//                unitImage.setImage(new Image(
//                        Civilization.class.getResourceAsStream(((RangedCombatUnit) GameController.getSelectedCombatUnit()).getRangedCombatUnitType().imageAddress)
//                ));
//            }
//        }
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
        VBox unitInfoVBox = new VBox(unitHP, unitMovement);
        unitInfoVBox.setAlignment(Pos.CENTER);
        HBox infoHBox = new HBox(unitImage, unitInfoVBox);
        infoHBox.setAlignment(Pos.CENTER_LEFT);
        VBox wholeUnit = new VBox(unitName, infoHBox);
        wholeUnit.setStyle("-fx-background-color: black; -fx-background-radius: 0 40 0 0;");
        wholeUnit.setAlignment(Pos.CENTER);
        wholeUnit.setPrefSize(380, 230);

        infoPopup.hide();
//        anchorPane.setEffect(null);
        unitPopup.getContent().clear();
        unitPopup.getContent().add(wholeUnit);
        unitPopup.setX(0);
        unitPopup.setY(570);
        unitPopup.show(window);
    }

    public void showSelectedCombatUnitInfo() {
        if (unitPopup.isShowing()) {
            unitPopup.hide();
            return;
        }
        CombatUnit combatUnit = GameController.getSelectedCombatUnit();
        Window window = Game.getCurrentScene().getWindow();
        ImageView unitImage = new ImageView();
        Label unitName = new Label();
        Label unitHP = new Label();
        Label unitMovement = new Label();


        if (GameController.getSelectedCombatUnit() instanceof CloseCombatUnit) {
            unitImage.setImage(new Image(
                    Civilization.class.getResourceAsStream(((CloseCombatUnit) combatUnit).getCloseCombatUnitType().imageAddress)
            ));
            unitName.setText(((CloseCombatUnit) combatUnit).getCloseCombatUnitType().name);
            unitHP.setText(String.valueOf(((CloseCombatUnit) combatUnit).getCloseCombatUnitType().hp));
            unitMovement.setText(String.valueOf(((CloseCombatUnit) combatUnit).getCloseCombatUnitType().MP));
        } else {
            unitImage.setImage(new Image(
                    Civilization.class.getResourceAsStream(((RangedCombatUnit) combatUnit).getRangedCombatUnitType().imageAddress)
            ));
            unitName.setText(((RangedCombatUnit) combatUnit).getRangedCombatUnitType().name);
            unitHP.setText(String.valueOf(((RangedCombatUnit) combatUnit).getRangedCombatUnitType().hp));
            unitMovement.setText(String.valueOf(((RangedCombatUnit) combatUnit).getRangedCombatUnitType().MP));
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
        VBox unitInfoVBox = new VBox(unitHP, unitMovement);
        unitInfoVBox.setAlignment(Pos.CENTER);
        HBox infoHBox = new HBox(unitImage, unitInfoVBox);
        infoHBox.setAlignment(Pos.CENTER_LEFT);
        VBox wholeUnit = new VBox(unitName, infoHBox);
        wholeUnit.setStyle("-fx-background-color: black; -fx-background-radius: 0 40 0 0;");
        wholeUnit.setAlignment(Pos.CENTER);
        wholeUnit.setPrefSize(380, 230);

        infoPopup.hide();
//        anchorPane.setEffect(null);
        unitPopup.getContent().clear();
        unitPopup.getContent().add(wholeUnit);
        unitPopup.setX(0);
        unitPopup.setY(570);
        unitPopup.show(window);
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
        anchorPane.getChildren().get(4).setVisible(false);
        anchorPane.getChildren().get(1).setVisible(false);

        AnchorPane CityAnchorPane = new AnchorPane();

        City city = GameController.getCurrentTurnUser().getNation().getCities().get(0);

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

        anchorPane.getChildren().add(wholeCurrenciesInfosVBox);


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

        anchorPane.getChildren().add(cityName);


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
            productTurnsLeft.setText(building.getTurns() + productTurnsLeft.getText());
            productCost.setText(productCost.getText() + building.getBuildingType().cost);
            productMaintenance.setText(productMaintenance.getText() + building.getBuildingType().maintenance);

            //TODO complete other products...
        } else if (city.getInProgressCivilizedUnit() != null) {
            productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                    city.getInProgressCivilizedUnit().imageAddress
            )));
        } else if (city.getInProgressCloseCombatUnit() != null) {
            productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                    city.getInProgressCloseCombatUnit().imageAddress
            )));
        } else if (city.getInProgressRangedCombatUnit() != null) {
            productImage.setImage(new Image(Civilization.class.getResourceAsStream(
                    city.getInProgressRangedCombatUnit().imageAddress
            )));
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
        wholeProduct.setStyle("-fx-background-color: black; -fx-background-radius: 0 40 0 0;");
        wholeProduct.setAlignment(Pos.CENTER);
        wholeProduct.setPrefSize(380, 230);
        wholeProduct.setLayoutY(538);

        anchorPane.getChildren().add(wholeProduct);

        // product buttons
        Button purchaseButton = new Button("Purchase");
        Button setProductButton = new Button("Set/Change Product");

        HBox productButtons = new HBox(purchaseButton, setProductButton);
        productButtons.setLayoutY(498);

        anchorPane.getChildren().add(productButtons);

        // two center buttons
        Button buyATile = new Button("Buy a tile");
        Button returnToMap = new Button("Return to map");
        returnToMap.setOnMouseClicked(mouseEvent -> {

        });

        VBox centerButtons = new VBox(buyATile, returnToMap);
        VBox.setMargin(buyATile, new Insets(0, 0, 20, 0));
        centerButtons.setLayoutX(593);
        centerButtons.setLayoutY(560);

        anchorPane.getChildren().add(centerButtons);

    }

}
