package sample.Units;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sample.App.Main;

import java.util.ArrayList;

public class World {
    /*private final static int rootHeight = 1348;
    private final static int rootWidth = 2160;*/

    private final static int rootHeight = 2426;
    private final static int rootWidth = 3888;
    //private final static int rootHeight = 2000;
    //private final static int rootWidth = 3000;

    private String Name;
    private static final Pane gameRoot = new Pane();
    private ArrayList<Warrior> ObjectsW = new ArrayList<>();
    private ArrayList<Base> ObjectsB = new ArrayList<>();
    //private ArrayList<Warrior> ObjectsWorld = new ArrayList<>();

    private Minimap minimap;
    //private final
    //ImageView img;
    //private ArrayList<EnemyBase> ObjectsEB = new ArrayList<>();

    public void addObjectsW (Warrior w){
        ObjectsW.add(w);
    }

    public World(){

        gameRoot.setMinWidth(rootWidth);
        gameRoot.setMinHeight(rootHeight);

        Rectangle bg = new Rectangle(rootWidth,rootHeight);
        Image img = new Image("/images/FullMap.png");
        bg.setFill(new ImagePattern(img));
        gameRoot.getChildren().add(bg);

        this.ObjectsW = new ArrayList<>();

        this.minimap = new Minimap();
        gameRoot.getChildren().addAll(minimap.getPane());
    }

    public void addUnit(Warrior w){
        ObjectsW.add(w);
        gameRoot.getChildren().add(w.getUnitGroup());
        minimap.addUnit(w);
        if(w.getSide().equals("Germany")){
            Main.base1.outputSoldiers().add(w);
        } else {
            Main.base2.outputSoldiers().add(w);
        }
    }

    public void  miniMapInFront(){
        gameRoot.getChildren().remove(minimap.getPane());
        gameRoot.getChildren().add(minimap.getPane());
    }

    public void addBase(Base b){
        gameRoot.getChildren().add(b.getBaseGroup());
        minimap.addBase(b);
    }

    public Warrior pickChosenUnit(){
        int i = 0;
        if(i<1) {
            for (Warrior w : ObjectsW) {
                if (w.isActive()) {
                    i++;
                    return w;
                }
            }
        }
        return null;
    }

    public double getLayoutY(){
        return gameRoot.getLayoutY();
    }

    public void unitMove(double dx, double dy){
        for(Warrior w: ObjectsW){
            if(w.isActive())
                w.keyMove(dx,dy);
        }
    }

    public void lifeCycle(){
        for (Warrior w: outputSoldiers()){
            if(!w.isActive())
                w.autoMove();
            if (w.getSide().equals("Monster1")) {
                w.updateAim();
            } else if (w.getSide().equals("Monster2")){
                w.updateAim();
            }
        }

    }

    public void addObjectsB (Base a){
        ObjectsB.add(a);
    }

    public ArrayList<Base> getObjectsB() {
        return ObjectsB;
    }

    public String getName() {
        return Name;
    }

    public Minimap getMinimap() {
        return minimap;
    }

    public ArrayList<Warrior> outputSoldiers(){
        return ObjectsW;
    }


    public World(String n){
        this.Name = n;
    }

    public static Pane getGameRoot() {
        return gameRoot;
    }

    public static int getRootHeight() {
        return rootHeight;
    }

    public static int getRootWidth() {
        return rootWidth;
    }

    public void updateMap(){
        gameRoot.getChildren().remove(minimap.getPane());
        gameRoot.getChildren().add(minimap.getPane());
    }
}
