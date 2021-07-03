package sample.Units;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class SwordMan extends Warrior  {
    private int lvl;
    //private String RANK = "SwordMan";
    private ArrayList<Warrior> SwordManSquad = new ArrayList<>();

    private String rank;
    private String side;

    private double chordX;
    private double chordY;

    private boolean isAlive;

    //protected boolean active=false;

    protected ImageView imgSwordMan;
    protected Image imgActiveRed = new Image("/images/swordman_redL_Selected.png");
    protected Image imgNotActiveRed = new Image("/images/swordman_redL.png");
    protected Image imgActiveGreen = new Image("/images/swordman_green_lightL_Selected.png");
    protected Image imgNotActiveGreen = new Image("/images/swordman_green_lightL.png");

    protected Rectangle HealthBar;


    //Image m,m,, double x,double yx,y,
    public SwordMan(int l, String n, int hp, double g, int dam, boolean isActive,String side,String rank ) {
        super(n, hp, g, dam, false,side,rank);
        this.side = side;
        if (this.side.equals("Monster1")){
            imgSwordMan = new ImageView(new Image("/images/monster1.png"));
        } else if(this.side.equals("Monster2")){
            imgSwordMan = new ImageView(new Image("/images/monster2.png"));
        }
        else if (this.side.equals("France")) {
            imgSwordMan = new ImageView("/images/swordman_redL.png");
        }
        else {
            imgSwordMan = new ImageView("/images/swordman_green_lightL.png");
        }
        lvl = l;
        this.rank = rank;
        this.isAlive = true;

        //imgWarrior

        killList = new double[0];

        this.active = isActive;

        this.nameText = new Text(name);
        this.nameText.setFont(new Font(("Tahoma"), 20.0D));
        this.nameText.setX(30);

        this.rectangleActive = new Rectangle(350, 250);
        this.rectangleActive.setStrokeWidth(3);
        this.rectangleActive.setStroke(Color.YELLOW);
        this.rectangleActive.setFill(Color.TRANSPARENT);

        this.HealthBar = new Rectangle(100,10, Paint.valueOf("Blue"));
        this.HealthBar.setX(20);
        this.HealthBar.setY(5);

        this.unitGroup = new Group(imgSwordMan, this.nameText,this.HealthBar);

        if(side.equals("Germany")){
            this.unitGroup.setLayoutX(100);
            this.unitGroup.setLayoutY(670);
        } else if(side.equals("France")){
            this.unitGroup.setLayoutX(1000);
            this.unitGroup.setLayoutY(200);
        }

        unitGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(!this.side.equals("Monster1") && !this.side.equals("Monster2")) {
                this.active = !this.active;

                double x = this.imgSwordMan.getX();
                double y = this.imgSwordMan.getY();

                System.out.println("**");
                System.out.println(imgSwordMan.getX());
                System.out.println(imgSwordMan.getY());
                System.out.println("**");

                this.getUnitGroup().getChildren().remove(this.getImgSwordMan());

                if (this.active) {
                    System.out.println("Active");
                    if (this.side.equals("France")) {
                        imgSwordMan.setImage(imgActiveRed);
                    } else {
                        imgSwordMan.setImage(imgActiveGreen);
                    }
                    int z = this.unitGroup.getChildren().indexOf(rectangleActive);
                    //this.unitGroup.getChildren().get(z).toFront();
                    this.rectangleActive.setOpacity(1);
                } else {
                    if (this.side.equals("France")) {
                        imgSwordMan.setImage(imgNotActiveRed);
                    } else {
                        imgSwordMan.setImage(imgNotActiveGreen);
                    }
                    System.out.println("not active");
                    this.rectangleActive.setOpacity(0);
                }
                this.getUnitGroup().getChildren().add(this.getImgSwordMan());

                this.rectangleActive.setX(x - 25.0D);
                this.rectangleActive.setY(y + 25.0D);

                this.imgSwordMan.setX(x);
                this.imgSwordMan.setY(y);
            }
        });

    }

    public SwordMan(int l, String n, int hp, double g, int dam, boolean isActive,String side,String rank,double chrdX, double chrdY){
        this(l,n,hp,g,dam,isActive,side,rank);
        this.unitGroup.setLayoutX(chrdX);
        this.unitGroup.setLayoutY(chrdY);
    }

    public ImageView getImgSwordMan() {
        return imgSwordMan;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        SwordMan tmp = (SwordMan) super.clone();

        if (tmp.side.equals("France")) {
            tmp.imgSwordMan = new ImageView("/images/swordman_redL.png");
        }
        else {
            tmp.imgSwordMan = new ImageView("/images/swordman_green_lightL.png");
        }

        tmp.active = false;

        tmp.nameText = new Text(tmp.name);
        tmp.nameText.setFont(new Font(("Tahoma"), 20.0D));
        tmp.nameText.setX(30);

        tmp.rectangleActive = new Rectangle(350, 250);
        tmp.rectangleActive.setStrokeWidth(3);
        tmp.rectangleActive.setStroke(Color.YELLOW);
        tmp.rectangleActive.setFill(Color.TRANSPARENT);

        tmp.HealthBar = new Rectangle(110,10, Paint.valueOf("Blue"));
        tmp.HealthBar.setX(20);
        tmp.HealthBar.setY(5);

        tmp.unitGroup = new Group(tmp.imgSwordMan, tmp.nameText,tmp.HealthBar);

        if(tmp.side.equals("Germany")){
            tmp.unitGroup.setLayoutX(100);
            tmp.unitGroup.setLayoutY(670);
        } else if(tmp.side.equals("France")){
            tmp.unitGroup.setLayoutX(1000);
            tmp.unitGroup.setLayoutY(200);
        }

        tmp.unitGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            tmp.active = !tmp.active;

            double x = tmp.imgSwordMan.getX();
            double y = tmp.imgSwordMan.getY();

            /*System.out.println("**");
            System.out.println(imgSwordMan.getX());
            System.out.println(imgSwordMan.getY());
            System.out.println("**");*/

            tmp.getUnitGroup().getChildren().remove(tmp.getImgSwordMan());

            if (tmp.active) {
                System.out.println("Active");
                if (tmp.side.equals("France")) {
                    tmp.imgSwordMan.setImage(tmp.imgActiveRed);
                }
                else {
                    tmp.imgSwordMan.setImage(tmp.imgActiveGreen);
                }
                /*int z = this.unitGroup.getChildren().indexOf(rectangleActive);*/
                //this.unitGroup.getChildren().get(z).toFront();
                tmp.rectangleActive.setOpacity(1);
            } else {
                if (tmp.side.equals("France")) {
                    tmp.imgSwordMan.setImage(tmp.imgNotActiveRed);
                }
                else {
                    tmp.imgSwordMan.setImage(tmp.imgNotActiveGreen);
                }
                System.out.println("not active");
                tmp.rectangleActive.setOpacity(0);
            }
            tmp.getUnitGroup().getChildren().add(tmp.getImgSwordMan());

            tmp.rectangleActive.setX(x - 25.0D);
            tmp.rectangleActive.setY(y + 25.0D);

            tmp.imgSwordMan.setX(x);
            tmp.imgSwordMan.setY(y);
        });

        if(this.killList == null) tmp.killList = new double[0];
        else tmp.killList = Arrays.copyOf(
                this.killList,
                this.killList.length);

        return (SwordMan) tmp;
    }

    public void setUnitChord(){
        this.imgSwordMan.setX(this.chordX);
        this.imgSwordMan.setY(this.chordY);

        this.nameText.setX(this.chordX+8);
        this.nameText.setY(this.chordY-5);

        this.rectangleActive.setX(this.chordX);
        this.rectangleActive.setY(this.chordY);

        if(this.active){
            rectangleActive.setOpacity(1);
        } else {
            rectangleActive.setOpacity(0);
        }


    }


    public void up(double d) {
        //this.unitGroup.setLayoutY(this.unitGroup.getLayoutY() -d );
        /*this.chordY -= d;
        this.setUnitChord();*/

        if (chordY > 0) {
            chordY = unitGroup.getLayoutY();
            this.unitGroup.setLayoutY(this.chordY - d);
        }
    }

    public void down(double d){
        if(chordY<2300) {
            chordY = unitGroup.getLayoutY();
            this.unitGroup.setLayoutY(this.chordY + d);
        /*this.chordY += d;
        this.setUnitChord();*/
        }
    }

    public void left(double d){
        if(chordX>0) {
            chordX = unitGroup.getLayoutX();
            this.unitGroup.setLayoutX(this.chordX - d);
        }
        /*this.chordX -= d;
        this.setUnitChord();*/
    }

    public void right(double d){
        if(chordX<3700) {
            chordX = unitGroup.getLayoutX();
            this.unitGroup.setLayoutX(this.chordX + d);
        }
        /*this.chordX += d;
        this.setUnitChord();*/
    }

    public void addWarriorToSquad(Warrior w){
        SwordManSquad.add(w);
    }

    @Override
    public void work(){
        int i= killList.length+1;
        setGold(getGold()+500*i);
    }

    public void setActive(){
        if(this.active) {
            this.active = !this.active;

            double x = this.imgSwordMan.getX();
            double y = this.imgSwordMan.getY();


            this.nameText.setFont(new Font(("Tahoma"),24.0D));

            this.getUnitGroup().getChildren().remove(this.getImgSwordMan());

            if (this.side.equals("France")) {
                imgSwordMan.setImage(imgNotActiveRed);
            } else {
                imgSwordMan.setImage(imgNotActiveGreen);
            }
            System.out.println("not active");
            this.rectangleActive.setOpacity(0);

            if (this.active) {
                System.out.println("Active");
                if (this.side.equals("France")) {
                    imgSwordMan.setImage(imgActiveRed);
                } else {
                    imgSwordMan.setImage(imgActiveGreen);
                }

                this.rectangleActive.setOpacity(1);
            }
            this.getUnitGroup().getChildren().add(this.getImgSwordMan());

            this.imgSwordMan.setX(x);
            this.imgSwordMan.setY(y);
        }
    }

    @Override
    public String getSide() {
        return side;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getLvl() {
        return lvl;
    }

    /*@Override
    public String toString(){
        return   "Мечник "+lvl+"-го рівня "+super.toString();
        //String rez=
        *//*rez+="\nОтряд:\n-------------------------\n";
        for(Warrior w: SwordManSquad){
            rez += w.toString();
            rez +="\n-------------------------";
        }
        return rez;*//*
    }*/

    public void print(){
        System.out.println(toString());
    }
}
