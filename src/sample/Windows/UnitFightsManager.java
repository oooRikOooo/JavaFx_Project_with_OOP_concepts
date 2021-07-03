package sample.Windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.App.Main;
import sample.Units.RoyalSwordMan;
import sample.Units.SwordMan;
import sample.Units.Warrior;

public class UnitFightsManager {

    private AnchorPane pane;
    public static Scene unitScene;
    private Stage unitStage;


    private Label lblMonster1 = new Label("Перший монстр");
    private Label lblMonster2 = new Label("Другий монстр");

    private ChoiceBox choiceBoxMonster1 = new ChoiceBox();
    private ChoiceBox choiceBoxMonster2 = new ChoiceBox();

    private Button addUnit = new Button("Додати юніта");
    private Button cancel = new Button("Cancel");

    private static final int WIDTH=400;
    private static final int HEIGHT=500;

    private String monster1;
    private String monster2;

    public UnitFightsManager(){
        pane = new AnchorPane();
        unitScene = new Scene(pane,WIDTH,HEIGHT);
        unitStage =new Stage();

        lblMonster1.setLayoutX(30);
        lblMonster1.setLayoutY(100);
        lblMonster1.setFont(new Font("Verdana", 17));

        choiceBoxMonster1.getItems().add("Worker");
        choiceBoxMonster1.getItems().add("SwordMan");
        choiceBoxMonster1.getItems().add("RoyalSwordMan");
        choiceBoxMonster1.setLayoutX(30);
        choiceBoxMonster1.setLayoutY(150);

        lblMonster2.setLayoutX(200);
        lblMonster2.setLayoutY(100);
        lblMonster2.setFont(new Font("Verdana", 17));

        choiceBoxMonster2.getItems().add("Worker");
        choiceBoxMonster2.getItems().add("SwordMan");
        choiceBoxMonster2.getItems().add("RoyalSwordMan");
        choiceBoxMonster2.setLayoutX(200);
        choiceBoxMonster2.setLayoutY(150);

        /*addUnit.setPrefHeight(30);
        addUnit.setPrefWidth(200);*/
        addUnit.setLayoutX(30);
        addUnit.setLayoutY(350);
        addUnit.setFont(new Font("Verdana", 17));

        cancel.setLayoutX(200);
        cancel.setLayoutY(350);
        cancel.setFont(new Font("Verdana", 17));



        cancel.setOnAction(actionEvent -> {
            unitStage.close();
        });

        addUnit.setOnAction(event ->{
            monster1 = (String) choiceBoxMonster1.getValue();
            monster2 = (String) choiceBoxMonster2.getValue();

            if(monster1.equals("Worker")) {
                Main.getWorld().addUnit(new Warrior("Monster1", 10000, 10000, 10000, false, "Monster1", "Worker",1080,675));
                System.out.println("Unit added");
            } else if(monster1.equals("SwordMan")){
                Main.getWorld().addUnit(new SwordMan(1,"Monster1", 10000, 10000, 10000, false, "Monster1", "SwordMan",1080,675));
            } else if(monster1.equals("RoyalSwordMan")){
                Main.getWorld().addUnit(new RoyalSwordMan(1,1,"Monster1", 10000, 10000, 10000, false, "Monster1", "RoyalSwordMan",1080,675));
            }

            if(monster2.equals("Worker")) {
                Main.getWorld().addUnit(new Warrior("Monster2", 10000, 10000, 10000, false, "Monster2", "Worker",1080,675));
                System.out.println("Unit added");
            } else if(monster2.equals("SwordMan")){
                Main.getWorld().addUnit(new SwordMan(1,"Monster2", 10000, 10000, 10000, false, "Monster2", "SwordMan",1080,675));
            } else if(monster2.equals("RoyalSwordMan")){
                Main.getWorld().addUnit(new RoyalSwordMan(1,1,"Monster2", 10000, 10000, 10000, false, "Monster2", "RoyalSwordMan",1080,675));
            }
        });

        pane.getChildren().addAll(lblMonster1,lblMonster2,choiceBoxMonster1,choiceBoxMonster2,addUnit,cancel);

        unitStage.setTitle("Оберіть двух монстрів");
        unitStage.setScene(unitScene);
    }

    public void crateUnitFightManager(){
        unitStage.show();
    }
}
