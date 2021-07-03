package sample.App;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.Units.*;
import sample.Windows.CompareManager;
import sample.Windows.UnitAddManager;
import sample.Windows.UnitFightsManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Main extends Application {

    private static final int sceneWidth = 1920;
    private static final int sceneHeight = 1080;

    private static final Label lbl = new Label();
    private static final Rectangle rct = new Rectangle();

    public static Warrior w1;
    public static Warrior w2;
    public static SwordMan sw1;
    public static SwordMan sw2;
    public static RoyalSwordMan rsm1;
    public static RoyalSwordMan rsm2;
    public static Base base1;
    public static Base base2;
    public static final World world = new World();
    private static final ScrollPane scrollPane =new ScrollPane(world.getGameRoot());
    public static BorderPane pane = new BorderPane();
    public static final MenuBar menuBar = new MenuBar();
    public static final Menu  menuGame = new Menu("Game");
    public static final MenuItem menuGameInfo = new MenuItem("Info");
    public static final MenuItem menuGameCompare = new MenuItem("Comparing");
    public static final MenuItem menuGameMonsters = new MenuItem("Monsters");
    public static final MenuItem menuGameExit = new MenuItem("Exit");
    public static final Scene scene = new Scene(pane, sceneWidth,sceneHeight);
    public static final Button buttonToBase = new Button("На базу");
    private UnitInfo uI;

    public static Random random = new Random(new Date().getTime());

    private ImageView imgMap = new ImageView();

    private static double scrollX;
    private static double scrollY;

    private static Stage pStage;

    private void setPrimaryStage(Stage pStage){
        Main.pStage = pStage;
    }

    public static int getSceneWidth() {
        return sceneWidth;
    }

    public static int getSceneHeight() {
        return sceneHeight;
    }

    public static Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        UnitAddManager uam = new UnitAddManager();
        InfoManager im = new InfoManager();
        UnitFightsManager ufm = new UnitFightsManager();

        setPrimaryStage(pStage);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        menuBar.getMenus().add(menuGame);

        uI = new UnitInfo();


        w1 = new Warrior( "Tom",200,50,40,false, "France","Worker");


        w2 = new Warrior( "Tim",200,50,40,false, "Germany","Worker");

        sw1 = new SwordMan(1, "John", 500, 100, 150,false, "Germany","SwordMan");

        sw2 = new SwordMan(1, "Ragnar", 500, 100, 150,false, "France","SwordMan");

        rsm1 = new RoyalSwordMan(50,1,"Ragnar", 1000,500,200,false, "Germany","RoyalSwordMan");

        rsm2 = new RoyalSwordMan(50,1,"Ragnar", 1000,500,201,false, "France","RoyalSwordMan");

        base1 = new Base(48,1551,"Germany",10000,5000);
        base2 = new Base(2866,190,"France",10000,5000);
        world.addBase(base1);
        world.addBase(base2);
        world.addUnit(w1);
        world.addUnit(w2);
        world.addUnit(sw1);
        world.addUnit(sw2);
        world.addUnit(rsm1);
        world.addUnit(rsm2);

        CompareManager cm = new CompareManager();
        menuGame.getItems().addAll(menuGameInfo,menuGameCompare,menuGameMonsters,menuGameExit);

        world.miniMapInFront();

        pane.setTop(menuBar);
        pane.setCenter(scrollPane);
        pane.getChildren().addAll(uI.getPane());


        AnimationTimer timer =new AnimationTimer() {
            @Override
            public void handle(long l) {

                ArrayList<Warrior> tmp = new ArrayList();
                for(Warrior w: world.outputSoldiers()){
                    tmp.add(w);
                }

                for (Warrior w : tmp){
                    for(Warrior w1: tmp){
                        if(!w.getSide().equals(w1.getSide())){
                            if(w.getUnitGroup().getBoundsInParent().intersects(w1.getUnitGroup().getBoundsInParent())){
                                if(w.getClass() != Warrior.class && w1.getClass() != Warrior.class &&
                                        w1 instanceof Warrior && w instanceof Warrior) {
                                    w.attack(w, w1);
                                    if (w1.getHp() <= 0) {
                                        world.outputSoldiers().removeIf(i -> w1 == w);
                                        w1.getUnitGroup().getChildren().clear();
                                        world.getMinimap().deleteUnit(w1);
                                        w.work();
                                        if(w1.getSide().equals("Germany")){
                                            base1.outputSoldiers().remove(w1);
                                        } else {
                                            base2.outputSoldiers().remove(w1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                for (Warrior w : tmp){
                    if(w.getClass() == Warrior.class && w instanceof Warrior ) {
                        if (w.getSide().equals("Germany")){
                            if(w.getUnitGroup().getBoundsInParent().intersects(base1.getBaseGroup().getBoundsInParent())){
                                base1.takeGold(w);
                            }
                        } else {
                            if(w.getUnitGroup().getBoundsInParent().intersects(base2.getBaseGroup().getBoundsInParent())) {
                                base2.takeGold(w);
                            }
                        }
                    }
                }




                scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->{
                    if (event.getCode() == KeyCode.DELETE){
                        for(Warrior w : tmp){
                            if(w.isActive()) {
                                world.outputSoldiers().remove(w);
                                w.getUnitGroup().getChildren().clear();
                                System.out.println(w.getName()+" Видалено");
                                world.getMinimap().deleteUnit(w);
                                if(w.getSide().equals("Germany")){
                                    base1.outputSoldiers().remove(w);
                                } else {
                                    base2.outputSoldiers().remove(w);
                                }
                            }
                        }
                    }
                    if(event.getCode() == KeyCode.X){
                        for (Warrior w: world.outputSoldiers()){
                            w.moveAllUnits();
                        }


                    }


                });

                //moveMonsters();

                /*for (Warrior w : world.outputSoldiers()) {
                    if (w.getSide().equals("Monster1")) {
                        w.updateAim();
                    } else if (w.getSide().equals("Monster2")){
                        w.updateAim();
                    }
                }*/


                farmGold();

                uI.update();

                //System.out.println(w1.getChordX());

                base1.updateInfoBase();
                base2.updateInfoBase();

                cm.updateCompareManager();

                world.lifeCycle();

                world.getMinimap().updateMap();

                world.updateMap();

            }
        };

        menuGameExit.setOnAction(event ->{
            System.exit(0);
        });

        menuGameInfo.setOnAction(event ->{
            im.createInfoScene();
        });

        menuGameCompare.setOnAction(event ->{
            cm.createCompareScene();
        });

        menuGameMonsters.setOnAction(event ->{
            ufm.crateUnitFightManager();
        });


        scrollPane.viewportBoundsProperty().addListener((observable, oldBounds, bounds) -> {

            Main.scrollX = -1 * (int) bounds.getMinX();
            Main.scrollY = -1 * (int) bounds.getMinY();

            world.getMinimap().getPane().setLayoutX(scrollX + 1320 );
            world.getMinimap().getPane().setLayoutY(scrollY);
            uI.getPane().setLayoutX(1590);
            uI.getPane().setLayoutY(390);

            world.getMinimap().getMapArea().setLayoutX(scrollX* Minimap.getSCALE());
            world.getMinimap().getMapArea().setLayoutY(scrollY*Minimap.getSCALE());
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->{
            if(event.getCode() == KeyCode.INSERT){
                try {
                    uam.createUnitAddScene();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            if (event.getCode() == KeyCode.C){
                ArrayList<Warrior> tmp = new ArrayList();

                for(Warrior w: world.outputSoldiers()){
                    tmp.add(w);
                }

                for(Warrior w : tmp) {
                    if (w.isActive()) {
                        System.out.println("c");
                        try {
                            Warrior cloned = (Warrior) w.clone();
                            world.addUnit(cloned);

                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            if(event.getCode() == KeyCode.Z){
                for (Warrior w: world.outputSoldiers()){
                    if(w.isActive())
                        w.moveToBase();
                }
            }

            if(event.getCode() == KeyCode.B){
                for (Warrior w: world.outputSoldiers()){
                    if(w.getClass() != Warrior.class && w instanceof Warrior ) {
                        w.moveAllUnits(1000, 1000);
                    }
                }
            }



            if (event.getCode() == KeyCode.ESCAPE){
                for (Warrior w: world.outputSoldiers()){
                    if(w.isActive())
                        w.setActive();
                }
            }

            if (event.getCode() == KeyCode.I){
                //System.out.println(world.outputSoldiers());
                for(Warrior w : world.outputSoldiers()){
                    if(w.getSide().equals("Monster1"))
                    System.out.println(w.getTargets());
                }
            }

        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->{
            for (Warrior w: world.outputSoldiers()){
                if(w.isActive()){
                    if(event.getCode() == KeyCode.W){
                            w.up(15);
                    }
                    if(event.getCode() == KeyCode.S){
                            w.down(15);
                    }
                    if(event.getCode() == KeyCode.A){
                            w.left(15);
                    }
                    if(event.getCode() == KeyCode.D){
                            w.right(15);
                    }


                }
            }
        });

        primaryStage.setMaximized(true);
        primaryStage.setTitle("***Age Wars***");
        primaryStage.setScene(scene);
        timer.start();
        primaryStage.show();
    }

    public static World getWorld() {
        return world;
    }

    public void makeInfoLabels(){
        for (Warrior w: world.outputSoldiers()){
            if(w.isActive()){
                lbl.setText("Name "+ w.getName() + " Country " + w.getSide()  );
            }
        }
    }

    public static ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void farmGold(){
        for(Warrior w: world.outputSoldiers()){
            if(w.getClass() == Warrior.class && w instanceof Warrior){
                //w.setGold(w.getGold()+1);
                w.work();
            }
        }
    }

    /*public void moveMonsters(){
        for(Warrior w: world.outputSoldiers()){
            if(w.getSide().equals("Monster1")){
                w.setAimX((int) world.outputSoldiers().get(w.getTarget()).getChordX());
                w.setAimY((int) world.outputSoldiers().get(w.getTarget()).getChordY());
                double dx = w.getAimX() - w.getUnitGroup().getLayoutX();
                double dy = w.getAimY() - w.getUnitGroup().getLayoutY();
                dx = Math.abs(dx) > 1 ? Math.signum(dx) * 1 : dx;
                dy = Math.abs(dy) > 1 ? Math.signum(dy) * 1 : dy;

                w.getUnitGroup().setLayoutX(w.getUnitGroup().getLayoutX() + dx);
                w.getUnitGroup().setLayoutY(w.getUnitGroup().getLayoutY() + dy);
            } else if(w.getSide().equals("Monster2")){
                w.setAimX((int) world.outputSoldiers().get(w.getTarget()+1).getChordX());
                w.setAimY((int) world.outputSoldiers().get(w.getTarget()+1).getChordY());
                double dx = w.getAimX() - w.getUnitGroup().getLayoutX();
                double dy = w.getAimY() - w.getUnitGroup().getLayoutY();
                dx = Math.abs(dx) > 1 ? Math.signum(dx) * 1 : dx;
                dy = Math.abs(dy) > 1 ? Math.signum(dy) * 1 : dy;

                w.getUnitGroup().setLayoutX(w.getUnitGroup().getLayoutX() + dx);
                w.getUnitGroup().setLayoutY(w.getUnitGroup().getLayoutY() + dy);
            }

        }
    }*/




    public static void main(String[] args) {
        launch(args);
    }
}
