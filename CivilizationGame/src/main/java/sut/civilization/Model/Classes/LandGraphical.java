package sut.civilization.Model.Classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import sut.civilization.Civilization;
import sut.civilization.Model.ModulEnums.LandType;

import java.util.Objects;

public class LandGraphical extends Polygon {

    private static final Pair<Integer,Integer> tileCount = new Pair<>(10,10);
    private static final double tileRadius = 100;
    private final Land land;
    private Pair<Integer,Integer> coordinate;
    private final Pair<Double,Double> centerCoordinate = new Pair<>();
    private final Double[] pointCoords = new Double[12];

    private final ImageView resourceImageView = new ImageView();
    private final ImageView landFeatureImageView = new ImageView();
    private final ImageView civilizedUnitImageView = new ImageView();
    private final ImageView combatUnitImageView = new ImageView();
    private final ImageView OwnerNationImageView = new ImageView();

    public LandGraphical(Pair<Integer,Integer> coordinate, Pane pane){

        setCoordinates(coordinate);

        land = Land.map[coordinate.x][coordinate.y];

        this.setFill(new ImagePattern(new Image("sut/civilization/Images/tiles/" + land.getLandType().name + ".png")));

        if (land.getLandFeature() != null) {
            landFeatureImageView.setImage(new Image(Objects.requireNonNull(Civilization.class.getResource("Images/tiles/" + land.getLandFeature().landFeatureType.name + ".png")).toExternalForm()));
        }
        if (land.getResource() != null) {
            resourceImageView.setImage(new Image(Objects.requireNonNull(Civilization.class.getResource("Images/Icons/ResourceIcons/" + land.getResource().resourceType.name + ".png")).toExternalForm()));
        }
        if (land.getCombatUnit() != null){
            combatUnitImageView.setImage(new Image(Objects.requireNonNull(Civilization.class.getResource("Images/Icons/UnitIcons/" + land.getCombatUnit().name + ".png")).toExternalForm()));
        }
        if (land.getCivilizedUnit() != null){
            civilizedUnitImageView.setImage(new Image(Objects.requireNonNull(Civilization.class.getResource("Images/Icons/UnitIcons/" + land.getCivilizedUnit().name + ".png")).toExternalForm()));
        }

        landFeatureImageView.setFitWidth(tileRadius*1.5);
        landFeatureImageView.setFitHeight(tileRadius*1.5);
        landFeatureImageView.setX(this.centerCoordinate.x - tileRadius*0.75);
        landFeatureImageView.setY(this.centerCoordinate.y - tileRadius*0.75);
//        landFeatureImageView.setOnMouseEntered();

        resourceImageView.setFitWidth(tileRadius*0.5);
        resourceImageView.setFitHeight(tileRadius*0.5);
        resourceImageView.setX(this.centerCoordinate.x - tileRadius*0.25);
        resourceImageView.setY(this.centerCoordinate.y - tileRadius*0.75);

        combatUnitImageView.setFitWidth(tileRadius*0.5);
        combatUnitImageView.setFitHeight(tileRadius*0.5);
        combatUnitImageView.setX(this.centerCoordinate.x + tileRadius*0.25);
        combatUnitImageView.setY(this.centerCoordinate.y - tileRadius*0.25);

        civilizedUnitImageView.setFitWidth(tileRadius*0.5);
        civilizedUnitImageView.setFitHeight(tileRadius*0.5);
        civilizedUnitImageView.setX(this.centerCoordinate.x - tileRadius*0.75);
        civilizedUnitImageView.setY(this.centerCoordinate.y - tileRadius*0.25);

        if (land.getLandType() != LandType.OCEAN)
            pane.getChildren().add(this);

        pane.getChildren().add(landFeatureImageView);
        pane.getChildren().add(resourceImageView);
        pane.getChildren().add(civilizedUnitImageView);
        pane.getChildren().add(combatUnitImageView);
    }


    private void showSelectDialog(){

    }

    public Land getLand() {
        return land;
    }

    public ImageView getResourceImageView() {
        return resourceImageView;
    }

    private void setCoordinates(Pair<Integer, Integer> coordinate) {
        this.coordinate = coordinate;

        if (coordinate.x % 2 == 0){
            this.centerCoordinate.x = (coordinate.x/2)*3*tileRadius + tileRadius;
            this.centerCoordinate.y = coordinate.y*2*tileRadius*Math.sin(Math.toRadians(60)) + tileRadius*Math.sin(Math.toRadians(60));

        }
        else {
            this.centerCoordinate.x = ((coordinate.x-1)/2)*3*tileRadius + 2*tileRadius + Math.cos(Math.toRadians(60)) * tileRadius;
            this.centerCoordinate.y = coordinate.y*2*tileRadius*Math.sin(Math.toRadians(60)) + 2*tileRadius*Math.sin(Math.toRadians(60));
        }

        pointCoords[0] = this.centerCoordinate.x - (tileRadius * Math.cos(Math.toRadians(60)));
        pointCoords[2] = this.centerCoordinate.x + (tileRadius * Math.cos(Math.toRadians(60)));
        pointCoords[4] = this.centerCoordinate.x + tileRadius;
        pointCoords[6] = this.centerCoordinate.x + (tileRadius * Math.cos(Math.toRadians(60)));
        pointCoords[8] = this.centerCoordinate.x - (tileRadius * Math.cos(Math.toRadians(60)));
        pointCoords[10] = this.centerCoordinate.x - tileRadius;

        pointCoords[1] = this.centerCoordinate.y - (tileRadius * Math.sin(Math.toRadians(60)));
        pointCoords[3] = this.centerCoordinate.y - (tileRadius * Math.sin(Math.toRadians(60)));
        pointCoords[5] = this.centerCoordinate.y;
        pointCoords[7] = this.centerCoordinate.y + (tileRadius * Math.sin(Math.toRadians(60)));
        pointCoords[9] = this.centerCoordinate.y + (tileRadius * Math.sin(Math.toRadians(60)));
        pointCoords[11] = this.centerCoordinate.y;

        this.getPoints().addAll(pointCoords);
    }

    public Pair<Integer, Integer> getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Pair<Integer, Integer> coordinate) {
        this.coordinate = coordinate;
    }

    public Pair<Double, Double> getCenterCoordinate() {
        return centerCoordinate;
    }

    public Double[] getPointCoords() {
        return pointCoords;
    }

    public ImageView getLandFeatureImageView() {
        return landFeatureImageView;
    }

    public ImageView getCivilizedUnitImageView() {
        return civilizedUnitImageView;
    }

    public ImageView getCombatUnitImageView() {
        return combatUnitImageView;
    }

    public ImageView getOwnerNationImageView() {
        return OwnerNationImageView;
    }
}
