package sample.Units;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import sample.App.Main;

public class UnitInfo {
    private static final Label mainLabel = new Label("Жоден юніт обраний");
    private static final Label label1 = new Label();
    private static final Rectangle border =new Rectangle();
    private Pane pane;


    public UnitInfo(){

        pane = new Pane();
        border.setHeight(40);
        border.setWidth(316);

        mainLabel.setLayoutY(0);
        mainLabel.setFont(Font.font("Verdana",28));

        label1.setFont(Font.font("Verdana",20));
        label1.setLayoutY(5);

        border.setFill(Color.TRANSPARENT);
        border.setStrokeWidth(2);
        border.setStroke(Color.BLUE);

        pane.getChildren().addAll(border, mainLabel,label1);

    }

    public Pane getPane() {
        return pane;
    }

    public void update(){
        int i = 0;
        String str = new String("");
        for(Warrior w : Main.world.outputSoldiers() ) {
            if (w.isActive()) {
                i++;
                str+= w.toString();
            }
        }
        if(i!=0) {
            mainLabel.setFont(Font.font("Verdana",20));
            if(i==1)
                mainLabel.setText(i+" юніт обраний");
            else mainLabel.setText(i+" юніта обрано");

            int j = 40+(35*i);
            border.setHeight(j);
            label1.setFont(Font.font("Verdana",13));
            label1.setText(str);
            //border.setArcHeight(j);

        } else if (i==0){
            mainLabel.setText("Жоден юніт обраний");
            mainLabel.setFont(Font.font("Verdana",28));
            label1.setText("");
            border.setHeight(40);
            border.setWidth(316);
        }
    }


}
