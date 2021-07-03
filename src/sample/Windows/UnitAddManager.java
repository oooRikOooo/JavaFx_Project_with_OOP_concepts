package sample.Windows;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.App.Main;
import sample.Units.RoyalSwordMan;
import sample.Units.SwordMan;
import sample.Units.Warrior;

public class UnitAddManager {
    private AnchorPane pane;
    public static Scene unitScene;
    private Stage unitStage;

    private Label name = new Label("Імя");
    private TextField nameField= new TextField();
    private Label country = new Label("Країна");
    private TextField countryField= new TextField();
    private RadioButton germanyButton = new RadioButton("Germany");
    private RadioButton franceButton = new RadioButton("France");
    private Label hp = new Label("Здоровя");
    private TextField hpField= new TextField();
    private Label gold = new Label("Золото");
    private TextField goldField= new TextField();
    private Label damage = new Label("Урон");
    private TextField damageField= new TextField();
    private ChoiceBox choiceBox = new ChoiceBox();
    private Label armor = new Label("Броня");
    private TextField armorField= new TextField();
    private Label lvl = new Label("Рівень");
    private TextField lvlField= new TextField();
    private Label id = new Label("ID");
    private TextField idField= new TextField();

    private Button addUnit = new Button("Додати юніта");

    private Group rButtons = new Group();

    private static final int WIDTH=400;
    private static final int HEIGHT=500;

    public UnitAddManager(){
        pane = new AnchorPane();
        unitScene = new Scene(pane,WIDTH,HEIGHT);
        unitStage =new Stage();
        name.setLayoutX(15);
        name.setLayoutY(20);
        name.setFont(new Font("Verdana", 17));
        nameField.setLayoutX(110);
        nameField.setLayoutY(20);

        country.setLayoutX(15);
        country.setLayoutY(60);
        country.setFont(new Font("Verdana", 17));
        countryField.setLayoutX(110);
        countryField.setLayoutY(60);
        germanyButton.setLayoutX(110);
        germanyButton.setLayoutY(60);
        franceButton.setLayoutX(210);
        franceButton.setLayoutY(60);

        rButtons.getChildren().addAll(germanyButton,franceButton);

        hp.setLayoutX(15);
        hp.setLayoutY(100);
        hp.setFont(new Font("Verdana", 17));
        hpField.setLayoutX(110);
        hpField.setLayoutY(100);

        gold.setLayoutX(15);
        gold.setLayoutY(140);
        gold.setFont(new Font("Verdana", 17));
        goldField.setLayoutX(110);
        goldField.setLayoutY(140);

        damage.setLayoutX(15);
        damage.setLayoutY(180);
        damage.setFont(new Font("Verdana", 17));
        damageField.setLayoutX(110);
        damageField.setLayoutY(180);

        choiceBox.getItems().add("Worker");
        choiceBox.getItems().add("SwordMan");
        choiceBox.getItems().add("RoyalSwordMan");
        choiceBox.setLayoutX(5);
        choiceBox.setLayoutY(220);

        lvl.setLayoutX(15);
        lvl.setLayoutY(260);
        lvl.setFont(new Font("Verdana", 17));
        lvlField.setLayoutX(110);
        lvlField.setLayoutY(260);

        armor.setLayoutX(15);
        armor.setLayoutY(300);
        armor.setFont(new Font("Verdana", 17));
        armorField.setLayoutX(110);
        armorField.setLayoutY(300);

        id.setLayoutX(15);
        id.setLayoutY(340);
        id.setFont(new Font("Verdana", 17));
        idField.setLayoutX(110);
        idField.setLayoutY(340);

        addUnit.setPrefHeight(30);
        addUnit.setPrefWidth(200);
        addUnit.setLayoutX(100);
        addUnit.setLayoutY(460);
        addUnit.setFont(new Font("Verdana", 17));

        addUnit.setOnMouseClicked(event ->{
            String name = new String(nameField.getText());
            int hp = Integer.parseInt(hpField.getText());
            //String country = new String(countryField.getText());
            String country = new String();
            if (germanyButton.isSelected() && franceButton.isSelected()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!!!");

                alert.setHeaderText(null);
                alert.setContentText("Choose only one side!!!");

                alert.showAndWait();
            }
            if(germanyButton.isSelected()){
                country = "Germany";
            }
            if (franceButton.isSelected()){
                country = "France";
            }

            double gold = Double.parseDouble(goldField.getText());
            int damage = Integer.parseInt(damageField.getText());
            int lvl = Integer.parseInt(lvlField.getText());
            int armor = Integer.parseInt(armorField.getText());
            String str = (String) choiceBox.getValue();
            /*String strr = new String(idField.getText());*/
            System.out.println(str);
            if(str.equals("Worker")) {
                Main.getWorld().addUnit(new Warrior(name, hp, gold, damage, false, country, str,1080,675));
                System.out.println("Unit added");
            } else if(str.equals("SwordMan")){
                Main.getWorld().addUnit(new SwordMan(lvl,name, hp, gold, damage, false, country,str,1080,675));
            } else if(str.equals("RoyalSwordMan")){
                Main.getWorld().addUnit(new RoyalSwordMan(armor,lvl,name, hp, gold, damage, false, country,str,1080,675));
            }

        });
//,countryField
        pane.getChildren().addAll(name,nameField,country,rButtons,hp,hpField,gold,goldField,
                damage,damageField,choiceBox,addUnit,armor,armorField,lvl,lvlField,id,idField);
        unitStage.setScene(unitScene);
    }

    public void createUnitAddScene(){
        //this.unitStage = stg;
        unitStage.show();
    }

    public Stage getUnitStage() {
        return unitStage;
    }




}
