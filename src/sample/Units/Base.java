package sample.Units;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Base {
    protected String name;
    protected int hp;
    protected double gold;

    protected double chordX;
    protected double chordY;

    protected  ImageView baseImage;
    protected Group baseGroup;

    protected Rectangle HealthBar;

    protected Text baseName;
    protected Text baseGold;
    protected Text baseHp;

    private ArrayList<Warrior> Soldiers = new ArrayList<>();

    public void addToBase(Warrior w){
        Soldiers.add(w);
    }

    public ArrayList<Warrior> outputSoldiers(){
        return Soldiers;
    }

    public Base(double x,double y, String n, int h, double g){

        this.chordX = x;
        this.chordY = y;

        this.name = n;
        this.hp =h;
        this.gold = g;
        this.HealthBar = new Rectangle(100,10, Paint.valueOf("Blue"));
        this.HealthBar.setWidth(350);


        if (this.name == "France"){
            baseImage =new ImageView("/images/base2.png");
            baseImage.setPreserveRatio(true);
            baseImage.setFitHeight(441);
            this.baseName = new Text("France"+"\nGold "+this.gold+"\nHp "+this.hp+"\nDMG "+getBasePower());
            this.baseName.setX(70);
            this.baseName.setY(180);
            this.baseName.setFont(new Font("Tahoma", 40));
            /*this.baseGold = new Text("Gold");
            this.baseGold.setX(120);
            this.baseGold.setY(200);
            this.baseGold.setFont(new Font("Tahoma", 40));*/
            this.HealthBar.setLayoutX(-2);
            this.HealthBar.setLayoutY(-30);

            //this.baseName = new Text("France");
        } else {
            baseImage =new ImageView("/images/base1.png");
            baseImage.setPreserveRatio(true);
            baseImage.setFitHeight(360);
            this.baseName = new Text("Germany"+"\nGold "+this.gold+"\nHp "+this.hp+"\nDamage "+getBasePower());
            this.baseName.setX(200);
            this.baseName.setY(100);
            this.baseName.setFont(new Font("Tahoma", 40));
            this.HealthBar.setLayoutX(90);
            this.HealthBar.setLayoutY(380);
        }



        this.HealthBar.setY(4);


        this.baseName.setFill(Color.WHITE);

        //this.baseName = n;

        this.baseGroup = new Group(this.baseImage, this.baseName,this.HealthBar);

        baseGroup.setLayoutX(x);
        baseGroup.setLayoutY(y);

        Soldiers = new ArrayList<>();
    }

    //public String getName() {
        //return Name;
   // }



    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public double getGold() {
        return gold;
    }

    public double getChordX() {
        return chordX;
    }

    public double getChordY() {
        return chordY;
    }

    public ImageView getBaseImage() {
        return baseImage;
    }

    public Group getBaseGroup() {
        return baseGroup;
    }

    public Text getBaseName() {
        return baseName;
    }

    public void sellSoldier(String n, Base b, double g){
        int a=0;
        for(int i=0; i < Soldiers.size(); i++){
            if(n.equals(Soldiers.get(i).getName())){
                a=i;
            }
        }
        System.out.println(n);
        if(n==null)
            System.out.println("error");
        if(a>-1){
            if(Double.compare(gold,g)>0 && Soldiers.contains(Soldiers.get(a))){
                b.Soldiers.add(Soldiers.get(a));
                this.Soldiers.remove(Soldiers.get(a));
                this.gold+= g;
                b.gold-=g;
            } else System.out.println("Error");
        } else System.out.println("Error");

    }

    public void takeGold(Warrior w){
        this.gold += w.getGold();
        w.setGold(0);
    }

    public int getBasePower(){
        int power=0;
        for(int i=0; i < Soldiers.size(); i++){
            if(Soldiers.get(i).getClass() != Warrior.class && Soldiers.get(i) instanceof Warrior) {
                power += Soldiers.get(i).getDamage();
            }
        }
        return power;
    }

    /*public boolean attackBase(Base a, Base b){
        System.out.println("Атака бази "+ a.getName()+"="+getBasePower(a) +
                "\nАтака бази " + b.getName() + "="+ getBasePower(b));
        if(getBasePower(a)>getBasePower(b)){
            System.out.println("База " + a.getName() + " перемогла");
        } else {
            b.hp -= a.getBasePower(a);
            System.out.println("HP " + a.getName() + " = " + a.getHp() +
                    "\nHP " + b.getName() + " " + b.getHp());
        }
        return true;
    }*/

    /*public Base(){
        this("Country", 2000, 2000);
    }*/

    /*public Base createAllyBase(){
        System.out.println("Створення нової бази");
        System.out.println("Name: ");
        String name = Main.in.next();

        System.out.println("HP: ");
        String s = Main.in.nextLine();
        Integer hp = Integer.parseInt(s);

        System.out.println("Gold:");
        s = Main.in.nextLine();
        Double gold = Double.parseDouble(s);

        return new Base(name,hp,gold);

    }*/

    /*@Override
    public String toString() {
        return "AllyBase:" +
                "Name=" + Name +
                ", hp=" + hp +
                ", gold=" + gold +
                ", Soldiers=" + Soldiers //+
                //"\n"+"---------------------------------"
                ;
    }*/

    public void updateInfoBase(){
        baseName.setText("France"+"\nGold "+this.gold+"\nHp "+this.hp+"\nDamage "+getBasePower());
    }

    public String stringASoldiers(){
        return "Soldiers:" + Soldiers.toString();
    }
}

