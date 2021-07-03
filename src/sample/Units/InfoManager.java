package sample.Units;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InfoManager {
    private AnchorPane pane;
    public static Scene infoScene;
    private Stage infoStage;
    private Label mainLbl;

    private static final int WIDTH=400;
    private static final int HEIGHT=500;

    public InfoManager(){
        pane = new AnchorPane();
        infoScene = new Scene(pane,WIDTH,HEIGHT);
        infoStage =new Stage();

        mainLbl = new Label("W - рух вверх\nA - рух вліво\nS - рух вниз\nD - рух вправо\nЛКМ - виділити об'єкт\n" +
                "Insert - вікно для створення об'єкта\nDelete - видалення активного об'єкта" +
                "\nC - глибинне копіювання"+"\nZ - Відправити юніта до своєї бази" + "\nX - Відправити усіх юнітів до своїх баз" +
                "\nB - надіслати усіх боєспроможніх у одну точку");
        mainLbl.setFont(new Font("Verdana", 17));

        pane.getChildren().addAll(mainLbl);

        infoStage.setScene(infoScene);
    }

    public void createInfoScene(){
        infoStage.show();
    }

    public Stage getUnitStage() {
        return infoStage;
    }
}


