package sut.civilization.View.Graphical;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import sut.civilization.Civilization;
import sut.civilization.Controller.GameControllers.*;
import sut.civilization.Enums.CommonIcons;
import sut.civilization.Enums.Consts;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.*;
import sut.civilization.Model.ModulEnums.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static javafx.scene.paint.Color.WHITE;

public class GamePlayController extends ViewController {
    @FXML
    private ScrollPane mapScrollPane;
    private final static LandGraphical[][] graphicalMap = new LandGraphical[Consts.MAP_SIZE.amount.x][Consts.MAP_SIZE.amount.y];
    public AnchorPane root = new AnchorPane();
    private final Popup infoPopup = new Popup();
    private final Popup unitPopup = new Popup();
    private final Popup cityPopup = new Popup();
    public Label inProgressTechnologyName = new Label();
    public ImageView inProgressTechnologyImage = new ImageView();
    public ProgressBar technologyProgressBar = new ProgressBar();
    public Label goldInfo = new Label();
    public Label scienceInfo = new Label();
    public Label happinessInfo = new Label();
    public Label turn = new Label();
    public Label yearLabel = new Label();
    private static GamePlayController gamePlayController;

    public static GamePlayController getInstance() {
        if (gamePlayController == null)
            gamePlayController = new GamePlayController();
        return gamePlayController;
    }

    public void initialize() {

        Pane pane = new Pane();

        for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                graphicalMap[i][j] = new LandGraphical(new Pair<>(i, j), pane);
            }
        }
        pane.setStyle(
                "-fx-background-position: center; " +
                        "-fx-background-size: auto; " +
                        "-fx-background-image: " +
                        "url(/sut/civilization/Images/BackGround/gameBackground.png);"
        );
        mapScrollPane.setContent(pane);

        ((Stage) Game.instance.getCurrentScene().getWindow()).setFullScreen(true);
        mapScrollPane.setMaxHeight(Consts.SCREEN_SIZE.amount.x);
        mapScrollPane.setMaxWidth(Consts.SCREEN_SIZE.amount.y);

        updateTechnologyBox();

        updateCurrencyBar();

        turn.setText("Turn " + Game.instance.getTurn());
        yearLabel.setText("AD " + Game.instance.getTurn() * 50);

        infoPopup.setHideOnEscape(true);

        infoPopup.setOnHiding(e -> {
            Game.instance.getCurrentScene().getRoot().setEffect(null);
            Game.instance.getCurrentScene().getRoot().setDisable(false);
        });


        infoPopup.setOnHidden(e -> {
            Game.instance.getCurrentScene().getRoot().setEffect(null);
            Game.instance.getCurrentScene().getRoot().setDisable(false);
        });
    }

    public void showInfoPanel(HBox[] eachUnitHBox) {
        VBox allUnitsVBox = new VBox(eachUnitHBox);
        allUnitsVBox.setMaxHeight(500);
        allUnitsVBox.getStyleClass().add("infoList");
        allUnitsVBox.setPadding(new Insets(0, 30, 0, 30));

        scrollPanePopup(allUnitsVBox);
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
                avatar.setImage(new Image(
                        Objects.requireNonNull(Civilization.class.getResourceAsStream(((RangedCombatUnit) unit).getRangedCombatUnitType().imageAddress))
                ));
            } else if (unit instanceof CloseCombatUnit) {
                unitType = new Label(((CloseCombatUnit) unit).getCloseCombatUnitType().name);
                avatar.setImage(new Image(
                        Objects.requireNonNull(Civilization.class.getResourceAsStream(((CloseCombatUnit) unit).getCloseCombatUnitType().imageAddress)))
                );
            } else {
                unitType = new Label(((CivilizedUnit) unit).getCivilizedUnitType().name);
                avatar.setImage(new Image(
                        Objects.requireNonNull(Civilization.class.getResourceAsStream(((CivilizedUnit) unit).getCivilizedUnitType().imageAddress))
                ));
            }

            Label unitLocation = new Label("(" + unit.getLocation().x + "," + unit.getLocation().y + ")");
            Label unitStatus = new Label(unit.getUnitStatus().name());
            unitType.getStyleClass().add("infoBoldColumn");
            unitLocation.getStyleClass().add("infoColumns");
            unitStatus.getStyleClass().add("infoColumns");
            eachUnitHBox[i] = new HBox(avatar, unitType, unitLocation, unitStatus);
            eachUnitHBox[i].setPrefHeight(100);
            eachUnitHBox[i].setAlignment(Pos.CENTER_LEFT);

            i++;
        }

        showInfoPanel(eachUnitHBox);

    }


    public void showCities() {
        int citiesNumber = GameController.getCurrentTurnUser().getNation().getCities().size();
        HBox[] eachCityHBox = new HBox[citiesNumber];
        int i = 0;
        for (City city : GameController.getCurrentTurnUser().getNation().getCities()) {
            Label cityName = new Label(city.getName());
            cityName.getStyleClass().add("infoBoldColumn");
            Label citySize = new Label(String.valueOf(city.getLands().size()));
            citySize.getStyleClass().add("infoColumns");
            Label cityPopulation = new Label(String.valueOf(city.getCitizens()));
            cityPopulation.getStyleClass().add("infoColumns");
            eachCityHBox[i] = new HBox(cityName, citySize, cityPopulation);
            eachCityHBox[i].setPrefHeight(100);
            eachCityHBox[i].setAlignment(Pos.CENTER_LEFT);

            i++;
        }

        showInfoPanel(eachCityHBox);
    }


    public void showDiplomacies() {
        int friendsNumber = GameController.getCurrentTurnUser().getNation().getFriends().size();
        int enemiesNumber = GameController.getCurrentTurnUser().getNation().getEnemies().size();
        HBox[] eachFriendHBox = new HBox[friendsNumber];
        HBox[] eachEnemyHBox = new HBox[enemiesNumber];

        Label friends = new Label("Friends");
        Label enemies = new Label("Enemies");
        friends.getStyleClass().add("header");
        enemies.getStyleClass().add("header");

        VBox allNationsVBox = new VBox();

        allNationsVBox.getChildren().add(friends);

        int i = 0;
        for (Nation nation : GameController.getCurrentTurnUser().getNation().getFriends()) {
            ImageView avatar = new ImageView(new Image(
                    Objects.requireNonNull(Civilization.class.getResourceAsStream(nation.getNationType().nationImageAddress))
            ));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label NationName = new Label(nation.getNationType().name);
            NationName.getStyleClass().add("infoBoldColumn");
            eachFriendHBox[i] = new HBox(avatar, NationName);
            eachFriendHBox[i].setPrefHeight(100);
            eachFriendHBox[i].setAlignment(Pos.CENTER_LEFT);

            allNationsVBox.getChildren().add(eachFriendHBox[i]);
            i++;
        }

        allNationsVBox.getChildren().add(new Separator());
        allNationsVBox.getChildren().add(enemies);

        i = 0;
        for (Nation nation : GameController.getCurrentTurnUser().getNation().getEnemies()) {
            ImageView avatar = new ImageView(new Image(
                    Objects.requireNonNull(Civilization.class.getResourceAsStream(nation.getNationType().nationImageAddress))
            ));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label NationName = new Label(nation.getNationType().name);
            NationName.getStyleClass().add("infoBoldColumn");
            eachEnemyHBox[i] = new HBox(avatar, NationName);
            eachEnemyHBox[i].setPrefHeight(100);
            eachEnemyHBox[i].setAlignment(Pos.CENTER_LEFT);

            allNationsVBox.getChildren().add(eachEnemyHBox[i]);
            i++;
        }

        allNationsVBox.setMaxHeight(500);
        allNationsVBox.getStyleClass().add("infoList");
        allNationsVBox.setPadding(new Insets(0, 30, 0, 30));

        scrollPanePopup(allNationsVBox);
    }

    public void showDiplomacyPanel(Nation nation) {
        Label diplomacyWith = new Label("Diplomacy with " + nation.getNationType().name);
        diplomacyWith.getStyleClass().add("header");
        ImageView hisLeaderImage = new ImageView(new Image(
                Objects.requireNonNull(Civilization.class.getResourceAsStream(nation.getNationType().leaderImageAddress))
        ));
        hisLeaderImage.setFitWidth(100);
        hisLeaderImage.setFitHeight(100);

        VBox mySuppliesVBox = new VBox();
        for (ResourceType resourceType : ResourceType.values()) {
            if (GameController.getCurrentTurnUser().getNation().getResourceCellar().get(resourceType) > 0) {
                ImageView resourceImage = new ImageView(resourceType.image);
                resourceImage.setFitWidth(40);
                resourceImage.setFitHeight(40);
                Label resourceName = new Label(resourceType.name);
                resourceName.setTextFill(WHITE);
                resourceName.setPrefWidth(70);
                Label resourceNumber = new Label(String.valueOf(
                        GameController.getCurrentTurnUser().getNation().getResourceCellar().get(resourceType)
                ));
                resourceNumber.setPrefWidth(50);
                resourceNumber.setTextFill(WHITE);
                resourceName.setStyle("-fx-label-padding: 0 10 0 0;");
                resourceNumber.setStyle("-fx-label-padding: 0 10 0 0;");
                TextField numberTextField = new TextField();
                numberTextField.setPrefWidth(50);
                HBox eachResource = new HBox(resourceImage, resourceName, resourceNumber, numberTextField);
                eachResource.setAlignment(Pos.CENTER_LEFT);
                mySuppliesVBox.getChildren().add(eachResource);
            }
        }
        ScrollPane mySuppliesScrollPane = new ScrollPane(mySuppliesVBox);
        mySuppliesScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mySuppliesScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mySuppliesVBox.setPrefHeight(300);
        mySuppliesVBox.setPrefWidth(300);
        VBox myVBox = new VBox(hisLeaderImage, mySuppliesScrollPane);
        myVBox.setMaxHeight(500);
        myVBox.setAlignment(Pos.CENTER);

        HBox tradesHBox = new HBox(myVBox);
        tradesHBox.setAlignment(Pos.CENTER);

        Button demandButton = new Button("Demand");
        VBox.setMargin(demandButton, new Insets(10));
        demandButton.setOnMouseClicked(mouseEvent -> {
            for (Node child : mySuppliesVBox.getChildren()) {
                TextField textField;
                if (!(textField = (TextField) ((HBox) child).getChildren().get(3)).getText().equals("")) {
                    for (ResourceType resourceType : ResourceType.values()) {
                        if (((Label) ((HBox) child).getChildren().get(1)).getText().equals(resourceType.name)) {
                            String message = NationController.sendTradeRequest(
                                    resourceType, Integer.parseInt(textField.getText()), nation);
                            showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                        }
                    }
                }
            }
        });
        Button declareWarButton = new Button("Declare War!");
        VBox.setMargin(declareWarButton, new Insets(10));
        declareWarButton.setStyle("-fx-background-color: red;");
        declareWarButton.setOnMouseClicked(mouseEvent -> {
            String message = NationController.declareWar(nation);
            showPopUp(Game.instance.getCurrentScene().getWindow(), message);
        });
        Button peaceButton = new Button("Peace");
        peaceButton.setStyle("-fx-background-color: green;");
        VBox.setMargin(peaceButton, new Insets(10));
        peaceButton.setOnMouseClicked(mouseEvent -> {
            String message = NationController.initiatePeace(nation);
            showPopUp(Game.instance.getCurrentScene().getWindow(), message);
        });
        VBox wholeDiplomacy = new VBox(diplomacyWith, tradesHBox, demandButton, declareWarButton, peaceButton);
        wholeDiplomacy.setAlignment(Pos.TOP_CENTER);
        wholeDiplomacy.setMaxHeight(700);
        wholeDiplomacy.getStyleClass().add("infoList");
        wholeDiplomacy.setPadding(new Insets(20));

        infoPopup.hide();
        scrollPanePopup(wholeDiplomacy);
    }

    public void showDemographics() {
        int nationNumber = Game.instance.getPlayersInGame().size();
        HBox[] eachNationHBox = new HBox[nationNumber];
        int i = 0;
        for (User user : Game.instance.getPlayersInGame()) {
            Nation nation = user.getNation();
            ImageView avatar = new ImageView(new Image(nation.getNationType().leaderImageAddress));
            avatar.setFitWidth(70);
            avatar.setFitHeight(70);
            Label nationName = new Label(nation.getNationType().name);
            nationName.getStyleClass().add("infoBoldColumn");
            int population = 0;
            int landNum = 0;
            for (City city : nation.getCities()) {
                population += city.getCitizens();
                landNum += city.getLands().size();
            }
            Label Population = new Label("Population: " + population);
            Population.getStyleClass().add("infoColumns");
            Label units = new Label("units: " + nation.getUnits().size());
            units.getStyleClass().add("infoColumns");
            Label lands = new Label("lands: " + landNum);
            lands.getStyleClass().add("infoColumns");
            eachNationHBox[i] = new HBox(avatar, nationName, Population, units, lands);
            eachNationHBox[i].setPrefHeight(100);
            eachNationHBox[i].setAlignment(Pos.CENTER_LEFT);
            if (nation != GameController.getCurrentTurnUser().getNation()) {
                eachNationHBox[i].setOnMouseClicked(mouseEvent -> showDiplomacyPanel(nation));
            }

            i++;
        }

        showInfoPanel(eachNationHBox);
    }

    public void showTradePanel() {
        if (!GameController.getCurrentTurnUser().getNation().getTrade().isEmpty()) {
            HashMap<String, String> trade = GameController.getCurrentTurnUser().getNation().getTrade();
            ImageView nationImage = new ImageView(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                    Objects.requireNonNull(NationController.getNation(trade.get("nation"))).getNationType().nationImageAddress
            ))));
            nationImage.setFitWidth(50);
            nationImage.setFitHeight(50);
            Label nationName = new Label(trade.get("nation"));
            nationName.setTextFill(WHITE);
            nationName.setStyle("-fx-font-size: 18; -fx-label-padding: 10 30 10 10;");
            Label resourceName = new Label(trade.get("ResourceType"));
            resourceName.setTextFill(WHITE);
            resourceName.setStyle("-fx-font-size: 18; -fx-label-padding: 10;");
            Label resourceAmount = new Label(trade.get("amount"));
            resourceAmount.setTextFill(WHITE);
            resourceAmount.setStyle("-fx-font-size: 18; -fx-label-padding: 10;");

            HBox tradesHBox = new HBox(nationImage, nationName, resourceName, resourceAmount);
            tradesHBox.setAlignment(Pos.CENTER_LEFT);
            Button accept = new Button("Accept");
            accept.setStyle("-fx-background-color: green;");
            accept.setOnMouseClicked(mouseEvent -> {
                String message = NationController.showTradeRequest(1);
                showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                updateCurrencyBar();
            });
            Button reject = new Button("Reject");
            reject.setStyle("-fx-background-color: red;");
            reject.setOnMouseClicked(mouseEvent -> {
                String message = NationController.showTradeRequest(0);
                showPopUp(Game.instance.getCurrentScene().getWindow(), message);
            });
            HBox buttons = new HBox(accept, reject);

            VBox wholeTrade = new VBox(tradesHBox, buttons);
            wholeTrade.setAlignment(Pos.TOP_CENTER);
            wholeTrade.getStyleClass().add("infoList");
            wholeTrade.setPadding(new Insets(20));

            scrollPanePopup(wholeTrade);
        }
    }


    public void showEconomics() {
        int cityNumber = GameController.getCurrentTurnUser().getNation().getCities().size();
        HBox[] eachCityHBox = new HBox[cityNumber];
        int i = 0;
        for (City city : GameController.getCurrentTurnUser().getNation().getCities()) {
            Label cityName = new Label(city.getName());
            cityName.getStyleClass().add("infoBoldColumn");
            Label level = new Label("Level: " + city.getLevel());
            level.getStyleClass().add("infoColumns");
            Label strength = new Label("strength: " + city.getCombatStrength());
            strength.getStyleClass().add("infoColumns");
            Label coin = new Label("coin: " + city.getCoinGrowth());
            coin.getStyleClass().add("infoColumns");
            Label production = new Label("production: " + city.getProductionGrowth());
            production.getStyleClass().add("infoColumns");
            Label food = new Label("food: " + city.getFoodGrowth());
            food.getStyleClass().add("infoColumns");
            eachCityHBox[i] = new HBox(cityName, level, strength, coin, production, food);
            eachCityHBox[i].setPrefHeight(100);
            eachCityHBox[i].setAlignment(Pos.CENTER_LEFT);

            i++;
        }

        showInfoPanel(eachCityHBox);
    }


    private void scrollPanePopup(Node node) {
        Window window = Game.instance.getCurrentScene().getWindow();
        ImageView ex = exCreator();
        ex.setOnMouseClicked(mouseEvent -> {
            infoPopup.hide();
            root.setEffect(null);
            root.setDisable(false);
        });
        ScrollPane scrollPane = new ScrollPane(node);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setMaxHeight(700);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        borderPane.setTop(ex);
        borderPane.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        unitPopup.hide();
        infoPopup.getContent().clear();
        infoPopup.getContent().add(borderPane);
        infoPopup.show(window);
        lightenRoot();
        root.setDisable(true);

    }

    public void showUnitInfo(Label unitName, Label unitMovement, Label unitHP, ImageView unitImage, VBox unitActions, HBox actionsAndImprovementsHBox) {

        ImageView ex = exCreator();
        ex.setOnMouseClicked(mouseEvent -> {
            unitPopup.hide();
            GameController.setSelectedCivilizedUnit(null);
        });

        VBox unitInfoVBox = new VBox(unitHP, unitMovement);
        unitInfoVBox.setAlignment(Pos.CENTER);
        HBox infoHBox = new HBox(unitImage, unitInfoVBox);
        infoHBox.setAlignment(Pos.CENTER_LEFT);
        VBox wholeUnit = new VBox(unitName, infoHBox);
        wholeUnit.setStyle("-fx-background-color: #212121;");
        wholeUnit.setAlignment(Pos.CENTER);
        wholeUnit.setPrefSize(380, 230);

        ScrollPane actionsScrollPane = new ScrollPane(unitActions);
        actionsScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        actionsScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        actionsScrollPane.setStyle("-fx-background-color: #212121; -fx-background-radius: 0 30 0 0;");
        actionsScrollPane.setPrefHeight(230);

        actionsAndImprovementsHBox.getChildren().add(actionsScrollPane);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(ex);
        borderPane.setCenter(wholeUnit);
        borderPane.setRight(actionsAndImprovementsHBox);
        borderPane.setStyle("-fx-background-color: #212121; -fx-background-radius: 0 30 0 0;");
        borderPane.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        unitPopup.setX(0);
        unitPopup.setY(570);
        unitPopup.getContent().clear();
        unitPopup.getContent().add(borderPane);
        unitPopup.show(Game.instance.getCurrentScene().getWindow());
    }

    public void showSelectedCivilizedUnitInfo() {
        unitPopup.hide();
        VBox unitActions = new VBox();
        HBox actionsAndImprovementsHBox = new HBox();
        CivilizedUnit civilizedUnit = GameController.getSelectedCivilizedUnit();
        CivilizedUnitType civilizedUnitType = civilizedUnit.getCivilizedUnitType();

        ImageView unitImage = new ImageView(new Image(
                Objects.requireNonNull(Civilization.class.getResourceAsStream(civilizedUnitType.imageAddress))
        ));
        unitImage.setFitWidth(180);
        unitImage.setFitHeight(180);

        Label unitName = new Label(civilizedUnitType.name);
        unitName.getStyleClass().add("header");

        Label unitMovement = new Label("Movement: " + civilizedUnit.getMP());
        unitMovement.setTextFill(WHITE);
        unitMovement.setStyle("-fx-label-padding: 10 0 0 30");

        Label unitHP = new Label("Health: " + civilizedUnit.getHp());
        unitHP.setTextFill(WHITE);
        unitHP.setStyle("-fx-label-padding: 10 0 0 30");

        for (UnitActions action : civilizedUnit.getCivilizedUnitType().actions) {
            ImageView actionImage = new ImageView(action.image);

            handleCommonActions(action, civilizedUnit, unitMovement, 0, actionImage);
            handleCivilizedUnitActions(action, actionImage, actionsAndImprovementsHBox);

            actionImage.setFitWidth(40);
            actionImage.setFitHeight(40);
            Tooltip.install(actionImage, new Tooltip(action.toString()));
            VBox.setMargin(actionImage, new Insets(0, 0, 5, 0));
            unitActions.getChildren().add(actionImage);
        }

        unitActions.getStyleClass().add("thinVBox");

        showUnitInfo(unitName, unitMovement, unitHP, unitImage, unitActions, actionsAndImprovementsHBox);

    }

    public void showSelectedCombatUnitInfo() {
        unitPopup.hide();
        VBox unitActions = new VBox();
        HBox actionsAndImprovementsHBox = new HBox();
        Unit combatUnit = GameController.getSelectedCombatUnit();

        ImageView unitImage;
        Label unitName;
        Label unitMovement = new Label("Movement: " + combatUnit.getMP());
        Label unitHP = new Label("Health: " + combatUnit.getHp());

        if (combatUnit instanceof CloseCombatUnit) {
            CloseCombatUnitType closeCombatUnitType = ((CloseCombatUnit) combatUnit).getCloseCombatUnitType();
            unitImage = new ImageView(new Image(
                    Objects.requireNonNull(Civilization.class.getResourceAsStream(closeCombatUnitType.imageAddress))
            ));
            unitName = new Label(closeCombatUnitType.name);
            for (UnitActions action : ((CloseCombatUnit) combatUnit).getCloseCombatUnitType().actions) {
                ImageView actionImage = new ImageView(action.image);

                handleCommonActions(action, combatUnit, unitMovement, 1, actionImage);
                handleCombatUnitActions(action, actionImage);

                actionImage.setFitWidth(40);
                actionImage.setFitHeight(40);
                Tooltip.install(actionImage, new Tooltip(action.toString()));
                VBox.setMargin(actionImage, new Insets(0, 0, 5, 0));
                unitActions.getChildren().add(actionImage);
            }

        } else {
            RangedCombatUnitType rangedCombatUnitType = ((RangedCombatUnit) combatUnit).getRangedCombatUnitType();
            unitImage = new ImageView(new Image(
                    Objects.requireNonNull(Civilization.class.getResourceAsStream(rangedCombatUnitType.imageAddress))
            ));
            unitName = new Label(rangedCombatUnitType.name);
            for (UnitActions action : ((RangedCombatUnit) combatUnit).getRangedCombatUnitType().actions) {
                ImageView actionImage = new ImageView(action.image);

                handleCommonActions(action, combatUnit, unitMovement, 1, actionImage);
                handleCombatUnitActions(action, actionImage);

                actionImage.setFitWidth(40);
                actionImage.setFitHeight(40);
                Tooltip.install(actionImage, new Tooltip(action.toString()));
                VBox.setMargin(actionImage, new Insets(0, 0, 5, 0));
                unitActions.getChildren().add(actionImage);
            }

        }

//        unitHP.setText("Health: " + combatUnit.getHp());

        unitImage.setFitWidth(180);
        unitImage.setFitHeight(180);
        unitName.getStyleClass().add("header");

        unitMovement.setTextFill(WHITE);
        unitMovement.setStyle("-fx-label-padding: 10 0 0 30");

        unitHP.setTextFill(WHITE);
        unitHP.setStyle("-fx-label-padding: 10 0 0 30");

        unitActions.getStyleClass().add("thinVBox");

        showUnitInfo(unitName, unitMovement, unitHP, unitImage, unitActions, actionsAndImprovementsHBox);

    }

    private void handleCombatUnitActions(UnitActions action, ImageView actionImage) {
        switch (action) {
            case FORTIFY:
            case FORTIFY_UNTIL_HEAL:
            case ALERT:
                actionImage.setOnMouseClicked(mouseEvent -> {
                    String message = UnitController.combatUnitAction(action.toString());
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                });
                break;
            case RANGED_ATTACK:
            case ATTACK:
                actionImage.setOnMouseClicked(mouseEvent -> {
                    for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
                        for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                            if (Game.instance.map[i][j].getCombatUnit() != null &&
                                    Game.instance.map[i][j].getCombatUnit().getOwnerNation() != GameController.getCurrentTurnUser().getNation()) {
                                int finalI = i;
                                int finalJ = j;
                                graphicalMap[i][j].getCombatUnitImageView().x.setOnMouseClicked(mouseEvent1 -> {
                                    String message = UnitController.unitSetCombatUnitTarget(Game.instance.map[finalI][finalJ].getCombatUnit());
                                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                                    updateWholeMap();
                                    graphicalMap[finalI][finalJ].getCombatUnitImageView().x.setOnMouseClicked(mouseEvent2 -> {
                                        if (graphicalMap[finalI][finalJ].getLand().getCombatUnit().getOwnerNation() == GameController.getCurrentTurnUser().getNation()) {
                                            GameController.setSelectedCombatUnit(graphicalMap[finalI][finalJ].getLand().getCombatUnit());
                                            GameController.setSelectedCivilizedUnit(null);
                                            GamePlayController.getInstance().showSelectedCombatUnitInfo();
                                        }
                                    });
                                });
                            }
                            if (Game.instance.map[i][j].isACityMainLand()) {
                                int finalI = i;
                                int finalJ = j;
                                graphicalMap[i][j].getCityImageView().setOnMouseClicked(mouseEvent1 -> {
                                    City city = Game.instance.map[finalI][finalJ].getOwnerCity();
                                    String message = UnitController.unitSetCityTarget(city);
                                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                                    if (city.getHP() <= 0) {
                                        Button destroyCity = new Button("Destroy city");
                                        destroyCity.setOnMouseClicked(mouseEvent2 -> {
                                            UnitController.decideCityFate(city, GameController.getSelectedCombatUnit(), 1);
                                            updateWholeMap();
                                            infoPopup.hide();
                                        });
                                        VBox.setMargin(destroyCity, new Insets(5));
                                        Button takeOverCity = new Button("Take over city");
                                        takeOverCity.setOnMouseClicked(mouseEvent2 -> {
                                            UnitController.decideCityFate(city, GameController.getSelectedCombatUnit(), 2);
                                            updateWholeMap();
                                            infoPopup.hide();
                                        });
                                        VBox.setMargin(takeOverCity, new Insets(5));
                                        Button annexCity = new Button("Annex city");
                                        annexCity.setOnMouseClicked(mouseEvent2 -> {
                                            UnitController.decideCityFate(city, GameController.getSelectedCombatUnit(), 3);
                                            updateWholeMap();
                                            infoPopup.hide();
                                        });
                                        VBox.setMargin(annexCity, new Insets(5));

                                        VBox vBox = new VBox(destroyCity, takeOverCity, annexCity);
                                        vBox.setPadding(new Insets(20));

                                        scrollPanePopup(vBox);
                                        ((BorderPane)infoPopup.getContent().get(0)).setTop(null);
                                    }
                                    updateWholeMap();
                                    graphicalMap[finalI][finalJ].getCityImageView().setOnMouseClicked(mouseEvent2 -> {
                                        if (city.getOwnerNation() == GameController.getCurrentTurnUser().getNation()) {
                                            GameController.setSelectedCity(city);
                                            GamePlayController.getInstance().showCityPanel();
                                        }
                                    });
                                });
                            }
                        }
                    }
                });
        }
    }

    private void handleCommonActions(UnitActions action, Unit unit, Label unitMovement, int selection, ImageView actionImage) {
        switch (action) {
            case DELETE:
                actionImage.setOnMouseClicked(mouseEvent -> {
                    String message = unitDelete();
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    if (message.equals("removed successfully!")) unitPopup.hide();
                });
                break;
            case SLEEP:
                actionImage.setOnMouseClicked(mouseEvent -> {
                    String message = UnitController.unitSleep();
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                });
                break;
            case WAKE:
                actionImage.setOnMouseClicked(mouseEvent -> {
                    String message = UnitController.unitWake();
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                });
                break;
            case MOVE:
                actionImage.setOnMousePressed(mouseEvent -> unitMove(unitMovement, unit, selection));
                break;
        }
    }

    private void handleCivilizedUnitActions(UnitActions action, ImageView actionImage, HBox actionsAndImprovementsHBox) {
        switch (action) {
            case FOUND_CITY:
                actionImage.setOnMouseClicked(mouseEvent -> {
                    String message = CityController.buildCity("Tehran");
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    updateWholeMap();
                    if (message.equals("City built successfully")) unitPopup.hide();
                });
                break;
            case BUILD_IMPROVEMENT:
                actionImage.setOnMouseClicked(mouseEvent -> {
                    ScrollPane improvementsScrollPane = buildImprovement();
                    actionsAndImprovementsHBox.getChildren().add(improvementsScrollPane);
                });
                break;
            case REPAIR:
                actionImage.setOnMouseClicked(mouseEvent -> {
                    String message = WorkerController.setWorkerToRepair();
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                });
                break;
            case REMOVE_FEATURE:
                actionImage.setOnMouseClicked(mouseEvent -> {
                    String message = WorkerController.setWorkerToRemoveFeature();
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                });
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
            ImageView technologyImage = new ImageView(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(technologyType.imageAddress))));
            HBox technologyImageHBox = new HBox(technologyImage);
            Label name = new Label(technologyType.name);
            name.setTextFill(WHITE);
            name.setStyle("-fx-label-padding: 0 0 20 0;");
            Tooltip.install(technologyImage, new Tooltip(technologyType.name + "\nCost: " + technologyType.cost +
                    "\nTurns: " + technologyType.turns + "\nFathers:\n" + Arrays.toString(technologyType.fathers)));
            eachTechnology[i].getChildren().add(technologyImageHBox);
            eachTechnology[i].getChildren().add(name);
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            if (GameController.getCurrentTurnUser().getNation().getInProgressTechnology() == technologyType) {
                technologyImageHBox.setStyle("-fx-background-color: blue; -fx-background-radius: 100;");
            } else if (GameController.getCurrentTurnUser().getNation().getNextTechnologies().contains(technologyType)) {
                technologyImageHBox.setStyle("-fx-background-radius: 100;");
                technologyImage.setOnMouseClicked(mouseEvent -> {
                    String message = TechnologyController.addTechnology(technologyType.name);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    if (message.equals("Technology added successfully")) {
                        technologyImageHBox.setStyle("-fx-background-color: blue; -fx-background-radius: 100;");
                        for (VBox vBox : eachTechnology)
                            if (vBox.getChildren().get(0).getStyle().equals(technologyImageHBox.getStyle()) &&
                                    vBox.getChildren().get(0) != technologyImageHBox)
                                vBox.getChildren().get(0).setStyle(null);
                        updateTechnologyBox();
                    }
                });
            } else if (!GameController.getCurrentTurnUser().getNation().getTechnologies().get(technologyType)) {
                technologyImage.setEffect(colorAdjust);
            } else {
                technologyImageHBox.setStyle("-fx-background-color: green; -fx-background-radius: 100;");
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
        wholeTechTree.setPadding(new Insets(20, 50, 20, 50));
        wholeTechTree.setStyle("-fx-background-color: transparent;");
        wholeTechTree.setAlignment(Pos.CENTER);

        AnchorPane lineAnchorPane = drawTechnologiesLines();

        StackPane stackPane = new StackPane(lineAnchorPane, wholeTechTree);

        scrollPanePopup(stackPane);
    }

    private AnchorPane drawTechnologiesLines() {
        Line[] lines = new Line[63];

        int[] rowNum = new int[]{1, 4, 6, 5, 5, 5, 4, 3, 5, 2, 4, 2};
        final int eachTechWidth = 188;
        final int eachTechHeight = 224;
        final int centerX = 614;
        int y1 = 114;
        int y2 = 114 + eachTechHeight;
        int j = 0;

        for (int i = 0; i < 11; i++) {
            int x1 = centerX - ((rowNum[i] - 1) * (eachTechWidth / 2));
            int x2 = centerX - ((rowNum[i + 1] - 1) * (eachTechWidth / 2));
            if (i == 0) {
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
            } else if (i == 1) {
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 -= (eachTechWidth / 2);
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2 + eachTechHeight);
                j++;
                x2 += (eachTechWidth / 2) + eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
            } else if (i == 2) {
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2 + eachTechHeight);
                j++;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 -= (eachTechWidth * 2);
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2 + eachTechHeight);
                j++;
                x2 += (eachTechWidth * 3);
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
            } else if (i == 3) {
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 -= eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2 + eachTechHeight);
                j++;
                x2 += (eachTechWidth * 2);
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
            } else if (i == 4) {
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 -= eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += (eachTechWidth * 2);
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
            } else if (i == 5) {
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
            } else if (i == 6) {
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 -= eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2 + eachTechHeight);
                j++;
                x2 += (eachTechWidth * 4);
                lines[j] = new Line(x1, y1, x2, y2 + eachTechHeight);
                j++;
                x2 -= (eachTechWidth * 3);
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
            } else if (i == 7) {
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
            } else if (i == 8) {
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2 + eachTechHeight);
                j++;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2 + eachTechHeight);
                j++;
                x2 -= eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
            } else if (i == 9) {
                x1 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += (eachTechWidth * 2);
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x1 -= eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
            } else {
                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
                j++;
                x2 += eachTechWidth;
                x1 += eachTechWidth;

                lines[j] = new Line(x1, y1, x2, y2);
            }

            y1 += eachTechHeight;
            y2 += eachTechHeight;
        }

        for (Line line : lines) {
            line.setStroke(WHITE);
            line.setStrokeWidth(2);
        }

        AnchorPane anchorPane = new AnchorPane(lines);
        anchorPane.setPrefWidth(1228);
        anchorPane.setPrefHeight(2448);
        anchorPane.setStyle("-fx-background-color: #212121;");

        return anchorPane;

    }

    private void showCityInfoBox(City city) {
        Label population = new Label(city.getCitizens() + " Citizens");
        population.setStyle("-fx-label-padding: 0 0 20 0; -fx-font-size: 18; -fx-font-weight: bold;");
        population.setTextFill(WHITE);

        ImageView[] currenciesIcons = new ImageView[5];
        Label[] currenciesNames = new Label[5];
        Label[] currenciesAmounts = new Label[5];

        int i = 0;
        for (CurrencyType currencyType : CurrencyType.values()) {
            currenciesIcons[i] = new ImageView(currencyType.image);
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
        wholeCurrenciesInfosVBox.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        cityPopup.getContent().add(wholeCurrenciesInfosVBox);
    }

    private void showProductBox(City city) {
        ImageView productImage = new ImageView();
        Label productName = new Label("No product is creating!");
        Label productTurnsLeft = new Label(" Turns left...");
        Label productCost = new Label("Cost: ");
        Label productMaintenance = new Label("Maintenance: ");


        if (city.getInProgressBuilding() != null) {
            Building building = city.getInProgressBuilding();
            productImage.setImage(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                    building.getBuildingType().imageAddress
            ))));
            productName.setText(building.getBuildingType().name);
            productTurnsLeft.setText(city.getNextProductTurns() + productTurnsLeft.getText());
            productCost.setText(productCost.getText() + building.getBuildingType().cost);
            productMaintenance.setText(productMaintenance.getText() + building.getBuildingType().maintenance);

        } else if (city.getInProgressCivilizedUnit() != null) {
            CivilizedUnit civilizedUnit = city.getInProgressCivilizedUnit();
            productImage.setImage(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                    civilizedUnit.getCivilizedUnitType().imageAddress
            ))));
            productName.setText(civilizedUnit.getCivilizedUnitType().name);
            productTurnsLeft.setText(city.getNextProductTurns() + productTurnsLeft.getText());
            productCost.setText(productCost.getText() + civilizedUnit.getCivilizedUnitType().cost);
            productMaintenance.setText("Movement: " + civilizedUnit.getCivilizedUnitType().MP);

        } else if (city.getInProgressCloseCombatUnit() != null) {
            CloseCombatUnit closeCombatUnit = city.getInProgressCloseCombatUnit();
            productImage.setImage(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                    closeCombatUnit.getCloseCombatUnitType().imageAddress
            ))));
            productName.setText(closeCombatUnit.getCloseCombatUnitType().name);
            productTurnsLeft.setText(city.getNextProductTurns() + productTurnsLeft.getText());
            productCost.setText(productCost.getText() + closeCombatUnit.getCloseCombatUnitType().cost);
            productMaintenance.setText("Movement: " + closeCombatUnit.getCloseCombatUnitType().MP);

        } else if (city.getInProgressRangedCombatUnit() != null) {
            RangedCombatUnit rangedCombatUnit = city.getInProgressRangedCombatUnit();
            productImage.setImage(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                    rangedCombatUnit.getRangedCombatUnitType().imageAddress
            ))));
            productName.setText(rangedCombatUnit.getRangedCombatUnitType().name);
            productTurnsLeft.setText(city.getNextProductTurns() + productTurnsLeft.getText());
            productCost.setText(productCost.getText() + rangedCombatUnit.getRangedCombatUnitType().cost);
            productMaintenance.setText("Movement: " + rangedCombatUnit.getRangedCombatUnitType().MP);

        } else {
            productImage.setImage(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                    "/sut/civilization/Images/productBorder.png"
            ))));
        }
        productImage.setFitWidth(180);
        productImage.setFitHeight(180);

        productName.getStyleClass().add("header");

        String style = "-fx-label-padding: 10 0 0 30";

        productTurnsLeft.setTextFill(WHITE);
        productTurnsLeft.setStyle(style);

        productCost.setTextFill(WHITE);
        productCost.setStyle(style);

        productMaintenance.setTextFill(WHITE);
        productMaintenance.setStyle(style);

        VBox productInfoVBox = new VBox(productTurnsLeft, productCost, productMaintenance);
        productInfoVBox.setAlignment(Pos.CENTER);
        HBox infoHBox = new HBox(productImage, productInfoVBox);
        infoHBox.setAlignment(Pos.CENTER_LEFT);
        VBox wholeProduct = new VBox(productName, infoHBox);
        wholeProduct.setStyle("-fx-background-color: #212121; -fx-background-radius: 0 40 0 0;");
        wholeProduct.setAlignment(Pos.CENTER);
        wholeProduct.setPrefSize(380, 230);
        wholeProduct.setLayoutY(538);
        wholeProduct.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        cityPopup.getContent().add(wholeProduct);


        // product buttons
        Button purchaseButton = new Button("Purchase");
        purchaseButton.setOnMouseClicked(mouseEvent -> showListOfPurchasableProducts(city));
        Button setProductButton = new Button("Set/Change Product");
        setProductButton.setOnMouseClicked(mouseEvent ->
                showListOfCreatableProducts(city, productImage, productName, productCost, productTurnsLeft, productMaintenance)
        );

        HBox productButtons = new HBox(purchaseButton, setProductButton);
        productButtons.setLayoutY(498);
        productButtons.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        cityPopup.getContent().add(productButtons);
    }


    private void showListOfPurchasableProducts(City city) {
        // top-right box
        Label unitsHeader = new Label("Units:");
        unitsHeader.getStyleClass().add("header");
        VBox listOfAvailableProducts = new VBox();
        listOfAvailableProducts.getChildren().add(unitsHeader);

        for (CivilizedUnitType civilizedUnitType : CivilizedUnitType.values()) {
            if ((civilizedUnitType.technologyType == null ||
                    GameController.getCurrentTurnUser().getNation().getTechnologies().get(civilizedUnitType.technologyType).equals(true)) &&
                    (civilizedUnitType.resourceType == null ||
                            GameController.getCurrentTurnUser().getNation().getResourceCellar().get(civilizedUnitType.resourceType) > 0)) {
                ImageView unitImage = new ImageView(new Image(
                        Objects.requireNonNull(Civilization.class.getResourceAsStream(civilizedUnitType.imageAddress))
                ));
                unitImage.setFitWidth(50);
                unitImage.setFitHeight(50);
                Label unitName = new Label(civilizedUnitType.name);
                Label unitCost = new Label(String.valueOf(civilizedUnitType.cost));
                unitName.setTextFill(WHITE);
                unitName.setStyle("-fx-label-padding: 0 0 5 0; -fx-font-weight: bold;");
                unitCost.setTextFill(WHITE);
                ImageView goldImage = new ImageView(CurrencyType.GOLD.image);
                HBox costHBox = new HBox(goldImage, unitCost);
                VBox nameAndCost = new VBox(unitName, costHBox);
                nameAndCost.setAlignment(Pos.CENTER_LEFT);
                HBox eachUnit = new HBox(unitImage, nameAndCost);
                eachUnit.setAlignment(Pos.CENTER_LEFT);
                eachUnit.setPrefWidth(300);
                eachUnit.setOnMouseClicked(mouseEvent1 -> {
                    String message = UnitController.purchaseProduct("civilized unit", civilizedUnitType.name);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    updateWholeMap();
                    updateCurrencyBar();
                });
                listOfAvailableProducts.getChildren().add(eachUnit);
            }
        }

        for (CloseCombatUnitType closeCombatUnitType : CloseCombatUnitType.values()) {
            if ((closeCombatUnitType.technologyType == null ||
                    GameController.getCurrentTurnUser().getNation().getTechnologies().get(closeCombatUnitType.technologyType).equals(true)) &&
                    (closeCombatUnitType.resourceType == null ||
                            GameController.getCurrentTurnUser().getNation().getResourceCellar().get(closeCombatUnitType.resourceType) > 0)) {
                ImageView unitImage = new ImageView(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(closeCombatUnitType.imageAddress))));
                unitImage.setFitWidth(50);
                unitImage.setFitHeight(50);
                Label unitName = new Label(closeCombatUnitType.name);
                Label unitCost = new Label(String.valueOf(closeCombatUnitType.cost));
                unitName.setTextFill(WHITE);
                unitName.setStyle("-fx-label-padding: 0 0 5 0; -fx-font-weight: bold;");
                unitCost.setTextFill(WHITE);
                ImageView goldImage = new ImageView(CurrencyType.GOLD.image);
                HBox costHBox = new HBox(goldImage, unitCost);
                VBox nameAndCost = new VBox(unitName, costHBox);
                nameAndCost.setAlignment(Pos.CENTER_LEFT);
                HBox eachUnit = new HBox(unitImage, nameAndCost);
                eachUnit.setAlignment(Pos.CENTER_LEFT);
                eachUnit.setPrefWidth(300);
                eachUnit.setOnMouseClicked(mouseEvent1 -> {
                    String message = UnitController.purchaseProduct("close combat unit", closeCombatUnitType.name);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    updateWholeMap();
                    updateCurrencyBar();
                });
                listOfAvailableProducts.getChildren().add(eachUnit);
            }
        }

        for (RangedCombatUnitType rangedCombatUnitType : RangedCombatUnitType.values()) {
            if ((rangedCombatUnitType.technologyType == null ||
                    GameController.getCurrentTurnUser().getNation().getTechnologies().get(rangedCombatUnitType.technologyType).equals(true)) &&
                    (rangedCombatUnitType.resourceType == null ||
                            GameController.getCurrentTurnUser().getNation().getResourceCellar().get(rangedCombatUnitType.resourceType) > 0)) {
                ImageView unitImage = new ImageView(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(rangedCombatUnitType.imageAddress))));
                unitImage.setFitWidth(50);
                unitImage.setFitHeight(50);
                Label unitName = new Label(rangedCombatUnitType.name);
                Label unitCost = new Label(String.valueOf(rangedCombatUnitType.cost));
                unitName.setTextFill(WHITE);
                unitName.setStyle("-fx-label-padding: 0 0 5 0; -fx-font-weight: bold;");
                unitCost.setTextFill(WHITE);
                ImageView goldImage = new ImageView(CurrencyType.GOLD.image);
                HBox costHBox = new HBox(goldImage, unitCost);
                VBox nameAndCost = new VBox(unitName, costHBox);
                nameAndCost.setAlignment(Pos.CENTER_LEFT);
                HBox eachUnit = new HBox(unitImage, nameAndCost);
                eachUnit.setAlignment(Pos.CENTER_LEFT);
                eachUnit.setPrefWidth(300);
                eachUnit.setOnMouseClicked(mouseEvent1 -> {
                    String message = UnitController.purchaseProduct("ranged combat unit", rangedCombatUnitType.name);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    updateWholeMap();
                    updateCurrencyBar();
                });
                listOfAvailableProducts.getChildren().add(eachUnit);
            }
        }

        Label buildingsHeader = new Label("Buildings:");
        buildingsHeader.getStyleClass().add("header");
        listOfAvailableProducts.getChildren().add(new Separator());
        listOfAvailableProducts.getChildren().add(buildingsHeader);

        for (BuildingType buildingType : BuildingType.values()) {
            if ((buildingType.technologyType == null ||
                    GameController.getCurrentTurnUser().getNation().getTechnologies().get(buildingType.technologyType).equals(true)) &&
                    !city.getBuildings().contains(buildingType)) {
                ImageView buildingImage = new ImageView(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(buildingType.imageAddress))));
                buildingImage.setFitWidth(50);
                buildingImage.setFitHeight(50);
                Label buildingName = new Label(buildingType.name);
                Label buildingCost = new Label(String.valueOf(buildingType.cost));
                buildingName.setTextFill(WHITE);
                buildingName.setStyle("-fx-label-padding: 0 0 5 0; -fx-font-weight: bold;");
                buildingCost.setTextFill(WHITE);
                ImageView goldImage = new ImageView(CurrencyType.GOLD.image);
                HBox costHBox = new HBox(goldImage, buildingCost);
                VBox nameAndCost = new VBox(buildingName, costHBox);
                nameAndCost.setAlignment(Pos.CENTER_LEFT);
                HBox eachUnit = new HBox(buildingImage, nameAndCost);
                eachUnit.setAlignment(Pos.CENTER_LEFT);
                eachUnit.setPrefWidth(300);
                eachUnit.setOnMouseClicked(mouseEvent1 -> {
                    String message = UnitController.purchaseProduct("building", buildingType.name);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    updateWholeMap();
                    updateCurrencyBar();
                });
                listOfAvailableProducts.getChildren().add(eachUnit);
            }
        }

        listOfAvailableProducts.setStyle("-fx-background-color: #212121;");
        ScrollPane scrollPane = new ScrollPane(listOfAvailableProducts);
//        scrollPane.setPrefWidth(300);
//        scrollPane.setLayoutX(1066);
//        scrollPane.setLayoutY(30);
//        scrollPane.setMaxHeight(738);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        borderPane.setCenter(scrollPane);
        borderPane.setPrefWidth(300);
        borderPane.setLayoutX(1066);
        borderPane.setLayoutY(10);
        borderPane.setMaxHeight(738);
        ImageView ex = exCreator();
        ex.setOnMouseClicked(mouseEvent -> cityPopup.getContent().remove(cityPopup.getContent().size() - 1));
        borderPane.setTop(ex);

        cityPopup.getContent().add(borderPane);
    }


    private void showListOfCreatableProducts(City city, ImageView productImage, Label productName, Label productCost,
                                             Label productTurnsLeft, Label productMaintenance) {
        // top-right box
        Label unitsHeader = new Label("Units:");
        unitsHeader.getStyleClass().add("header");
        VBox listOfAvailableProducts = new VBox();
        listOfAvailableProducts.getChildren().add(unitsHeader);

        for (CivilizedUnitType civilizedUnitType : CivilizedUnitType.values()) {
            if ((civilizedUnitType.technologyType == null ||
                    GameController.getCurrentTurnUser().getNation().getTechnologies().get(civilizedUnitType.technologyType).equals(true)) &&
                    (civilizedUnitType.resourceType == null ||
                            GameController.getCurrentTurnUser().getNation().getResourceCellar().get(civilizedUnitType.resourceType) > 0)) {
                ImageView unitImage = new ImageView(new Image(
                        Objects.requireNonNull(Civilization.class.getResourceAsStream(civilizedUnitType.imageAddress))
                ));
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
                    String message = UnitController.productStartCreation("civilized unit", civilizedUnitType.name);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    CivilizedUnit civilizedUnit = city.getInProgressCivilizedUnit();

                    productImage.setImage(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                            civilizedUnit.getCivilizedUnitType().imageAddress
                    ))));
                    productName.setText(civilizedUnit.getCivilizedUnitType().name);
                    productTurnsLeft.setText(city.getNextProductTurns() + " Turns left...");
                    productCost.setText("Cost: " + civilizedUnit.getCivilizedUnitType().cost);
                    productMaintenance.setText("Maintenance: " + civilizedUnit.getCivilizedUnitType().MP);
                });
                listOfAvailableProducts.getChildren().add(eachUnit);
            }
        }

        for (CloseCombatUnitType closeCombatUnitType : CloseCombatUnitType.values()) {
            if ((closeCombatUnitType.technologyType == null ||
                    GameController.getCurrentTurnUser().getNation().getTechnologies().get(closeCombatUnitType.technologyType).equals(true)) &&
                    (closeCombatUnitType.resourceType == null ||
                            GameController.getCurrentTurnUser().getNation().getResourceCellar().get(closeCombatUnitType.resourceType) > 0)) {
                ImageView unitImage = new ImageView(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(closeCombatUnitType.imageAddress))));
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
                    String message = UnitController.productStartCreation("close combat unit", closeCombatUnitType.name);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    CloseCombatUnit closeCombatUnit = city.getInProgressCloseCombatUnit();

                    productImage.setImage(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                            closeCombatUnit.getCloseCombatUnitType().imageAddress
                    ))));
                    productName.setText(closeCombatUnit.getCloseCombatUnitType().name);
                    productTurnsLeft.setText(city.getNextProductTurns() + " Turns left...");
                    productCost.setText("Cost: " + closeCombatUnit.getCloseCombatUnitType().cost);
                    productMaintenance.setText("Maintenance: " + closeCombatUnit.getCloseCombatUnitType().MP);
                });
                StringBuilder info = new StringBuilder();
                if (closeCombatUnitType.resourceType != null)
                    info.append("Resource required: ").append(closeCombatUnitType.resourceType.name);
                if (closeCombatUnitType.technologyType != null)
                    info.append("\nTechnology required: ").append(closeCombatUnitType.technologyType.name);
                Tooltip.install(eachUnit, new Tooltip(info.toString()));
                listOfAvailableProducts.getChildren().add(eachUnit);
            }
        }

        for (RangedCombatUnitType rangedCombatUnitType : RangedCombatUnitType.values()) {
            if ((rangedCombatUnitType.technologyType == null ||
                    GameController.getCurrentTurnUser().getNation().getTechnologies().get(rangedCombatUnitType.technologyType).equals(true)) &&
                    (rangedCombatUnitType.resourceType == null ||
                            GameController.getCurrentTurnUser().getNation().getResourceCellar().get(rangedCombatUnitType.resourceType) > 0)) {
                ImageView unitImage = new ImageView(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(rangedCombatUnitType.imageAddress))));
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
                    String message = UnitController.productStartCreation("ranged combat unit", rangedCombatUnitType.name);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    RangedCombatUnit rangedCombatUnit = city.getInProgressRangedCombatUnit();

                    productImage.setImage(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                            rangedCombatUnit.getRangedCombatUnitType().imageAddress
                    ))));
                    productName.setText(rangedCombatUnit.getRangedCombatUnitType().name);
                    productTurnsLeft.setText(city.getNextProductTurns() + " Turns left...");
                    productCost.setText("Cost: " + rangedCombatUnit.getRangedCombatUnitType().cost);
                    productMaintenance.setText("Maintenance: " + rangedCombatUnit.getRangedCombatUnitType().MP);
                });
                StringBuilder info = new StringBuilder();
                if (rangedCombatUnitType.resourceType != null)
                    info.append("Resource required: ").append(rangedCombatUnitType.resourceType.name);
                if (rangedCombatUnitType.technologyType != null)
                    info.append("\nTechnology required: ").append(rangedCombatUnitType.technologyType.name);
                Tooltip.install(eachUnit, new Tooltip(info.toString()));
                listOfAvailableProducts.getChildren().add(eachUnit);
            }
        }

        Label buildingsHeader = new Label("Buildings:");
        buildingsHeader.getStyleClass().add("header");
        listOfAvailableProducts.getChildren().add(new Separator());
        listOfAvailableProducts.getChildren().add(buildingsHeader);

        for (BuildingType buildingType : BuildingType.values()) {
            if ((buildingType.technologyType == null ||
                    GameController.getCurrentTurnUser().getNation().getTechnologies().get(buildingType.technologyType).equals(true)) &&
                    !city.getBuildings().contains(buildingType)) {
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
                    String message = UnitController.productStartCreation("building", buildingType.name);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    Building building = city.getInProgressBuilding();

                    productImage.setImage(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                            building.getBuildingType().imageAddress
                    ))));
                    productName.setText(building.getBuildingType().name);
                    productTurnsLeft.setText(city.getNextProductTurns() + " Turns left...");
                    productCost.setText("Cost: " + building.getBuildingType().cost);
                    productMaintenance.setText("Maintenance: " + building.getBuildingType().maintenance);
                });
                StringBuilder info = new StringBuilder();
                if (buildingType.technologyType != null)
                    info.append("\nTechnology required: " + buildingType.technologyType.name);
                Tooltip.install(eachBuilding, new Tooltip(info.toString()));
                listOfAvailableProducts.getChildren().add(eachBuilding);
            }
        }

        listOfAvailableProducts.setStyle("-fx-background-color: #212121;");
        ScrollPane scrollPane = new ScrollPane(listOfAvailableProducts);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        borderPane.setPrefWidth(300);
        borderPane.setLayoutX(1066);
        borderPane.setLayoutY(10);
        borderPane.setMaxHeight(738);
        ImageView ex = exCreator();
        ex.setOnMouseClicked(mouseEvent -> cityPopup.getContent().remove(cityPopup.getContent().size() - 1));
        borderPane.setTop(ex);

        cityPopup.getContent().add(borderPane);
    }

    public void showListOfBuildings(City city) {
        VBox listOfBuildings = new VBox();
        Label buildingsHeader = new Label("Buildings:");
        buildingsHeader.getStyleClass().add("header");
        listOfBuildings.getChildren().add(buildingsHeader);

        for (BuildingType buildingType : BuildingType.values()) {
            if (city.getBuildings().contains(buildingType)) {
                ImageView buildingImage = new ImageView(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(buildingType.imageAddress))));
                buildingImage.setFitWidth(50);
                buildingImage.setFitHeight(50);
                Label buildingName = new Label(buildingType.name);
                buildingName.setTextFill(WHITE);
                buildingName.setStyle("-fx-label-padding: 0 0 5 0; -fx-font-weight: bold;");
                HBox eachBuilding = new HBox(buildingImage, buildingName);
                eachBuilding.setAlignment(Pos.CENTER_LEFT);
                eachBuilding.setPrefWidth(300);
                listOfBuildings.getChildren().add(eachBuilding);
            }
        }

        listOfBuildings.setStyle("-fx-background-color: #212121;");
        ScrollPane scrollPane = new ScrollPane(listOfBuildings);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefWidth(300);
        scrollPane.setLayoutX(1066);
        scrollPane.setLayoutY(30);
        scrollPane.setMaxHeight(738);
        scrollPane.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        cityPopup.getContent().add(scrollPane);
    }


    public void showCityPanel() {

        City city = GameController.getSelectedCity();

        // top-left box
        showCityInfoBox(city);

        //city-name
        Label cityName = new Label(city.getName());
        cityName.setStyle("-fx-background-color: #212121; -fx-background-radius: 20;");
        cityName.getStyleClass().add("header");
        cityName.setPadding(new Insets(10, 0, 10, 0));
        cityName.setPrefWidth(350);
        cityName.setLayoutX(508);
        cityName.setLayoutY(75);
        cityName.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        cityPopup.getContent().add(cityName);

        //right-box: list of buildings
        showListOfBuildings(city);

        //bottom-left box
        showProductBox(city);

        // two center buttons
        Button buyATile = new Button("Buy a tile");
        Button returnToMap = new Button("Return to map");
        returnToMap.setOnMouseClicked(mouseEvent -> {
            cityPopup.getContent().remove(cityPopup.getContent().size() - 1);
            cityPopup.hide();
            GameController.setSelectedCity(null);
        });

        VBox centerButtons = new VBox(buyATile, returnToMap);
        VBox.setMargin(buyATile, new Insets(0, 0, 20, 0));
        centerButtons.setLayoutX(593);
        centerButtons.setLayoutY(560);
        centerButtons.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");

        cityPopup.getContent().add(centerButtons);

        GameController.setSelectedCivilizedUnit(null);
        GameController.setSelectedCombatUnit(null);
        unitPopup.hide();
        cityPopup.setX(0);
        cityPopup.show(Game.instance.getCurrentScene().getWindow());

    }

    public void showMenu() {
        Button continueButton = new Button("Continue");
        continueButton.setOnMouseClicked(mouseEvent -> {
            root.setEffect(null);
            root.setDisable(false);
            infoPopup.hide();
        });
        Button saveGame = new Button("Save Game");
        //TODO Save button is here, Ravan!
        saveGame.setOnMouseClicked(mouseEvent -> {
//            String temp = Game.instance.saveGame("save");
//            if (temp != null)
//                showPopUp(Game.instance.getCurrentScene().getWindow(),"saved as Name " + temp);
//            else showPopUp(Game.instance.getCurrentScene().getWindow(),"cannot save Game !");
//            root.setEffect(null);
//            root.setDisable(false);
//            infoPopup.hide();
        });
        Button returnToMainMenu = new Button("Return To Main Menu");
        returnToMainMenu.setOnMouseClicked(mouseEvent -> {
            root.setEffect(null);
            root.setDisable(false);
            infoPopup.hide();
            ((Stage) Game.instance.getCurrentScene().getWindow()).setFullScreen(false);
            Game.instance.changeScene(Menus.MAIN_MENU);
        });
        Button exit = new Button("Exit");
        exit.setOnMouseClicked(mouseEvent -> ((Stage) Game.instance.getCurrentScene().getWindow()).close());

        VBox menuVBox = new VBox(continueButton, saveGame, returnToMainMenu, exit);
        menuVBox.getStylesheets().add("/sut/civilization/StyleSheet/LoginMenu.css");
        for (Node child : menuVBox.getChildren()) {
            VBox.setMargin(child, new Insets(0, 0, 20, 0));
        }

        GameController.setSelectedCivilizedUnit(null);
        GameController.setSelectedCombatUnit(null);
        unitPopup.hide();
        infoPopup.getContent().clear();
        infoPopup.getContent().add(menuVBox);
        infoPopup.show(Game.instance.getCurrentScene().getWindow());
        Light light = new Light.Distant();
        light.setColor(new Color(0.4, 0.4, 0.4, 0.5));
        root.setEffect(new Lighting(light));
        root.setDisable(true);
    }

    public String unitDelete() {
        Unit unit;
        if ((unit = GameController.getSelectedCivilizedUnit()) != null || (unit = GameController.getSelectedCombatUnit()) != null) {
            GameController.getCurrentTurnUser().getNation().getUnits().remove(unit);
            if (unit instanceof CivilizedUnit) {
                Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCivilizedUnit(null);
                GameController.setSelectedCivilizedUnit(null);
            } else {
                Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCombatUnit(null);
                GameController.setSelectedCombatUnit(null);
            }
            graphicalMap[unit.getLocation().x][unit.getLocation().y].updateMap();
            return "removed successfully!";
        }
        return "Select a unit first!";
    }


    public void updateWholeMap() {
        for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                graphicalMap[i][j].updateMap();
            }
        }
    }

    public void updateTechnologyBox() {
        TechnologyType inProgressTechnology = GameController.getCurrentTurnUser().getNation().getInProgressTechnology();
        if (inProgressTechnology != null) {
            inProgressTechnologyName.setText(inProgressTechnology.name);
            inProgressTechnologyImage.setImage(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                    inProgressTechnology.imageAddress
            ))));
            technologyProgressBar.setProgress(1 - ((double) GameController.getCurrentTurnUser().getNation().getTechnologyTurns() /
                    (double) inProgressTechnology.turns));
        } else {
            inProgressTechnologyName.setText("No technology");
            inProgressTechnologyImage.setImage(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
                    "/sut/civilization/Images/productBorder.png"
            ))));
            technologyProgressBar.setProgress(0);
        }
    }

    private void updateCurrencyBar() {
        Currency coin = GameController.getCurrentTurnUser().getNation().getCoin();
        Currency science = GameController.getCurrentTurnUser().getNation().getScience();
        Currency happiness = GameController.getCurrentTurnUser().getNation().getHappiness();
        goldInfo.setText(String.format("%d (%+d)", coin.getBalance(), coin.getGrowthRate()));
        scienceInfo.setText(String.format("%+d", science.getBalance()));
        happinessInfo.setText(String.valueOf(happiness.getBalance()));
    }

    public void lightenRoot() {
        Light light = new Light.Distant();
        light.setColor(new Color(1, 1, 1, 0.5));
        root.setEffect(new Lighting(light));
    }

    public ImageView exCreator() {
        ImageView ex = new ImageView(CommonIcons.EX.image);
        ex.setFitWidth(20);
        ex.setFitHeight(20);
        return ex;
    }

    public void unitMove(Label unitMovement, Unit unit, int selection) {
        for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                int finalI = i;
                int finalJ = j;
                graphicalMap[i][j].setOnMouseClicked(mouseEvent1 -> {
                    String message = UnitController.unitSetPath(finalI, finalJ, selection);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    unitMovement.setText("Movement: " + unit.getMP());
                    for (int k = 0; k < Consts.MAP_SIZE.amount.x; k++) {
                        for (int l = 0; l < Consts.MAP_SIZE.amount.y; l++) {
                            graphicalMap[k][l].setOnMouseClicked(null);
                        }
                    }
                });
                graphicalMap[i][j].getFogOfWarImageView().setOnMouseClicked(mouseEvent1 -> {
                    String message = UnitController.unitSetPath(finalI, finalJ, selection);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    unitMovement.setText("Movement: " + unit.getMP());
                    for (int k = 0; k < Consts.MAP_SIZE.amount.x; k++) {
                        for (int l = 0; l < Consts.MAP_SIZE.amount.y; l++) {
                            graphicalMap[k][l].getFogOfWarImageView().setOnMouseClicked(null);
                        }
                    }
                });
                graphicalMap[i][j].getLandFeatureImageView().setOnMouseClicked(mouseEvent1 -> {
                    String message = UnitController.unitSetPath(finalI, finalJ, selection);
                    showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    unitMovement.setText("Movement: " + unit.getMP());
                    for (int k = 0; k < Consts.MAP_SIZE.amount.x; k++) {
                        for (int l = 0; l < Consts.MAP_SIZE.amount.y; l++) {
                            graphicalMap[k][l].getLandFeatureImageView().setOnMouseClicked(null);
                        }
                    }
                });
            }
        }
    }

    public ScrollPane buildImprovement() {
        VBox improvements = new VBox();
        for (ImprovementType improvementType : ImprovementType.values()) {
            switch (improvementType) {
                case FOREST_FARM:
                case MARSH_FARM:
                case MARSH_MINE:
                case FOREST_MINE:
                case JUNGLE_FARM:
                case JUNGLE_MINE:
                    break;
                default:
                    ImageView improvementImage = new ImageView(new Image(
                            Objects.requireNonNull(Civilization.class.getResourceAsStream(improvementType.imageAddress))
                    ));
                    improvementImage.setOnMouseClicked(mouseEvent1 -> {
                        String message = WorkerController.setWorkerToBuildImprovement(improvementType.name);
                        showPopUp(Game.instance.getCurrentScene().getWindow(), message);
                    });
                    Tooltip.install(improvementImage, new Tooltip(improvementType.name));
                    improvementImage.setFitWidth(40);
                    improvementImage.setFitHeight(40);
                    VBox.setMargin(improvementImage, new Insets(0, 0, 5, 0));
                    improvements.getChildren().add(improvementImage);
            }
        }
        improvements.getStyleClass().add("thinVBox");

        ScrollPane improvementsScrollPane = new ScrollPane(improvements);
        improvementsScrollPane.setPrefHeight(230);
        improvementsScrollPane.setPrefWidth(50);
        improvementsScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        improvementsScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        improvementsScrollPane.setStyle("-fx-background-color: #212121; -fx-background-radius: 0 30 0 0;");

        return improvementsScrollPane;
    }

    public void nextTurn() {
//        System.out.println(new Gson().toJson(Game.instance));
        String message = GameController.nextPlayerTurn();
        showPopUp(Game.instance.getCurrentScene().getWindow(), message);

        hideUnitPopup();
        updateWholeMap();
        updateTechnologyBox();
        updateCurrencyBar();
        turn.setText("Turn " + Game.instance.getTurn());
        yearLabel.setText("AD " + Game.instance.getTurn() * 50);
    }

    public void hideUnitPopup() {
        GameController.setSelectedCivilizedUnit(null);
        GameController.setSelectedCombatUnit(null);
        unitPopup.hide();
    }

//    public static void main(String[] args) {
//        System.out.println(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
//    }


}
