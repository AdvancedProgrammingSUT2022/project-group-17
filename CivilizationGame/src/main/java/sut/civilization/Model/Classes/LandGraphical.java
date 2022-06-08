package sut.civilization.Model.Classes;

import javafx.scene.shape.Polygon;

public class LandGraphical extends Polygon {

    private static final Pair<Integer,Integer> tileCount = new Pair<>(10,10);
    private static final double tileRadius = 100;

    private Pair<Integer,Integer> coordinate;
    private final Pair<Double,Double> centerCoordinate = new Pair<>();
    private final Double[] pointCoords = new Double[12];

    public LandGraphical(Pair<Integer,Integer> coordinate){

        setCoordinates(coordinate);

        this.getStyleClass().add("polygon");
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
}
