package sample.Units;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sample.App.Main;

import java.util.HashMap;

public class Minimap {
    final static private double SCALE = 0.15;
    private Pane pane;
    private HashMap<Warrior, ImageView> unitsMap;
    private HashMap<Base, Group> baseMap;

    private final Rectangle mapArea;
    private boolean mapOpacity;

    //private boolean mapOpacity;

    private Image miniMapBackground;
    //private Image miniMapBorder;

    //private final Rectangle border;

    public Pane getPane() {
        return pane;
    }
    public Rectangle getMapArea() {
        return mapArea;
    }
    public static double getSCALE() {
        return SCALE;
    }

    public Minimap(){
        this.pane = new Pane();

        this.pane.setMinHeight(World.getRootHeight() * Minimap.SCALE);
        this.pane.setMinWidth(World.getRootWidth() * Minimap.SCALE);

        unitsMap = new HashMap<>();
        this.baseMap = new HashMap<>();

        miniMapBackground = new Image("/images/FullMap.png");

        this.mapOpacity = false;

        Rectangle rectangle = new Rectangle(0, 0, pane.getMinWidth(), pane.getMinHeight());
        rectangle.setFill(new ImagePattern(miniMapBackground));

        mapArea = new Rectangle(0, 0, Main.getSceneWidth() * Minimap.SCALE, Main.getSceneHeight() * Minimap.SCALE);
        mapArea.setFill(Color.TRANSPARENT);
        mapArea.setStrokeWidth(2);
        mapArea.setStroke(Color.BLUE);

        this.pane.getChildren().addAll(rectangle, mapArea);

        /*pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getButton() == MouseButton.PRIMARY){
                //if(!mapOpacity)
                double x = event.getX();
                double y = event.getY();
                moveTo(x, y);
                System.out.println("active");
            }
        });*/

        pane.setOnMousePressed(mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.PRIMARY)
                moveTo(mouseEvent.getX(), mouseEvent.getY());
            if (mouseEvent.getButton() == MouseButton.SECONDARY){
                mapOpacity=!mapOpacity;
                if (mapOpacity){
                    this.pane.setOpacity(0);
                    //this.pane.setDisable(true);
                }else {
                    this.pane.setOpacity(1);
                    //this.pane.setDisable(false);
                }
            }
        });
    }

    public void moveTo(double x, double y) {
        /*Main.getScrollPane().setHvalue(x/pane.getWidth());
        Main.getScrollPane().setVvalue(y/pane.getWidth());*/
        if (x < mapArea.getWidth() / 2) {
            Main.getScrollPane().setHvalue(0);
        } else if (x > pane.getWidth() - mapArea.getWidth() / 2) {
            Main.getScrollPane().setHvalue(1);
        } else Main.getScrollPane().setHvalue(x / pane.getWidth());

        if (y < mapArea.getHeight() / 2) {
            Main.getScrollPane().setVvalue(0);
        } else if (y > pane.getHeight() - mapArea.getHeight() / 2) {
            Main.getScrollPane().setVvalue(1);
        } else Main.getScrollPane().setVvalue(y / pane.getHeight());
    }



    public void addUnit(Warrior w){
        ImageView imageView;

        switch (w.getRank()) {
            case "Worker" -> {
                if(w.getSide().equals("France")) {
                    imageView = new ImageView(new Image("/images/worker_redL_Big.png"));
                } else imageView = new ImageView(new Image("/images/worker_green_lightL.png"));
            }

            case "SwordMan" -> {
                if(w.getSide().equals("France")) {
                    imageView = new ImageView(new Image("/images/swordman_redL.png"));
                } else imageView = new ImageView(new Image("/images/swordman_green_lightL.png"));
            }

            case "RoyalSwordMan" -> {
                if(w.getSide().equals("France")) {
                    imageView = new ImageView(new Image("/images/soldier_redR.png"));
                } else imageView = new ImageView(new Image("/images/soldier_green_lightR.png"));
            }


            default -> throw new IllegalStateException("Unexpected value: " + w.getRank());
        }

        imageView.setLayoutX(w.getChordX() * SCALE);
        imageView.setLayoutY(w.getChordY() * SCALE);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(190 * SCALE);
        unitsMap.put(w, imageView);
        pane.getChildren().add(imageView);
    }


    public void addBase(Base b){
        ImageView imageView;
        Group group;

        switch (b.getName()){
            case "France" ->{
                imageView = new ImageView(new Image("images/base2.png"));
                imageView.setPreserveRatio(true);
                imageView.setFitHeight(b.getBaseImage().getFitHeight() * Minimap.SCALE);
            }

            case "Germany" -> {
                imageView = new ImageView(new Image("images/base1.png"));
                imageView.setPreserveRatio(true);
                imageView.setFitHeight(b.getBaseImage().getFitHeight() * Minimap.SCALE);
            }
            default -> throw new IllegalStateException("Unexpected value: " + b.getName());
        }

        imageView.setLayoutX(b.getChordX() * Minimap.SCALE);
        imageView.setLayoutY(b.getChordY() * Minimap.SCALE);
        group = new Group(imageView);

        baseMap.put(b, group);
        pane.getChildren().add(imageView);
    }

    public void deleteUnit(Warrior w){
        pane.getChildren().remove(unitsMap.get(w));
    }

    public void updateMap(){
        for(Warrior w: Main.world.outputSoldiers()){
            // new ImageView(this.unitsMap.get(w).getImage());
            ImageView imageview = unitsMap.get(w);
            imageview.setLayoutX(w.getUnitGroup().getLayoutX()*Minimap.SCALE);
            imageview.setLayoutY(w.getUnitGroup().getLayoutY()* Minimap.SCALE);
        }
    }

    public HashMap<Warrior, ImageView> getUnitsMap() {
        return unitsMap;
    }

    public double getLayoutx(){
        return pane.getLayoutX();
    }

    public double getLayouty(){
        return pane.getLayoutY();
    }


}
