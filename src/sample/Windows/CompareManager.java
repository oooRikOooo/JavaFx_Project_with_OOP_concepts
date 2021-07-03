package sample.Windows;

import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.App.Main;
import sample.Units.Warrior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CompareManager {
    private AnchorPane pane;
    public static Scene unitScene;
    private Stage unitStage;
    private Label lbl;
    private Label lbl1 = new Label("Сортування за уроном");
    private Warrior tmp[] = new Warrior[Main.world.outputSoldiers().size()];
    //private String str = new String("Name");

    private static final int WIDTH=400;
    private static final int HEIGHT=500;

    public CompareManager(){
        pane = new AnchorPane();
        unitScene = new Scene(pane,WIDTH,HEIGHT);
        unitStage =new Stage();

        lbl1.setLayoutX(5);
        lbl1.setLayoutY(20);
        lbl1.setFont(new Font("Verdana", 17));

        int i =0;
        for(Warrior w: Main.world.outputSoldiers()){
            //tmp.add(w);
            tmp[i] = w;
            i++;
        }

        Arrays.sort(tmp, Warrior.damageComparatorToHigh);


        lbl = new Label(Arrays.toString(tmp));

        lbl.setFont(new Font("Verdana", 17));
        lbl.setLayoutX(5);
        lbl.setLayoutY(50);

        pane.getChildren().addAll(lbl,lbl1);

        unitStage.setScene(unitScene);
    }

    public void updateCompareManager(){
        int i =0;
        tmp = new Warrior[Main.world.outputSoldiers().size()];
        for(Warrior w: Main.world.outputSoldiers()){
            tmp[i] = w;
            i++;

        }
        Arrays.sort(tmp, Warrior.damageComparatorToHigh);

        lbl.setText(Arrays.toString(tmp));
    }

    public void createCompareScene(){
        unitStage.show();
    }

}
