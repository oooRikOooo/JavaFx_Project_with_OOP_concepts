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

public class RoyalSwordMan extends  SwordMan{
    private int armor;
    //private String RANK = "RoyalSwordMan";
    //Image m,,m,  double x, double y,x,y

    //protected boolean active=false;

    private ArrayList<Warrior> SwordManSquad = new ArrayList<>();

    private double chordX;
    private double chordY;

    private String rank;
    private String side;

    protected boolean isAlive;

    protected ImageView imgRoyalSwordMan;
    protected Image imgActiveRed = new Image("/images/soldier_redR_Selected.png");
    protected Image imgNotActiveRed = new Image("/images/soldier_redR.png");
    protected Image imgActiveGreen = new Image("/images/soldier_green_lightR_Selected.png");
    protected Image imgNotActiveGreen = new Image("/images/soldier_green_lightR.png");

    protected Rectangle HealthBar;

    public RoyalSwordMan (int n, int l, String nm, int hp, double g, int dam,boolean isActive, String side,String rank ){
        super(l,nm,hp,g,dam,false,side,rank);
        this.side = side;
        if (this.side.equals("Monster1")){
            imgRoyalSwordMan = new ImageView(new Image("/images/monster1.png"));
        } else if(this.side.equals("Monster2")){
            imgRoyalSwordMan = new ImageView(new Image("/images/monster2.png"));
        }
        else if (this.side.equals("France")) {
            imgRoyalSwordMan = new ImageView("/images/soldier_redR.png");
        } else {
            imgRoyalSwordMan = new ImageView("/images/soldier_green_lightR.png");
        }
        armor = n;
        this.rank = rank;


        killList = new double[0];

        this.active = isActive;
        this.isAlive = true;

        this.nameText = new Text(name);
        this.nameText.setFont(new Font(("Tahoma"), 20.0D));
        this.nameText.setX(30);

        this.rectangleActive = new Rectangle(350, 250);
        this.rectangleActive.setStrokeWidth(3);
        this.rectangleActive.setStroke(Color.YELLOW);
        this.rectangleActive.setFill(Color.TRANSPARENT);

        this.HealthBar = new Rectangle(120,10, Paint.valueOf("Blue"));
        this.HealthBar.setX(20);
        this.HealthBar.setY(5);

        this.unitGroup = new Group(imgRoyalSwordMan, this.nameText,this.HealthBar);

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

                double x = this.imgRoyalSwordMan.getX();
                double y = this.imgRoyalSwordMan.getY();

                System.out.println("**");
                System.out.println(imgRoyalSwordMan.getX());
                System.out.println(imgRoyalSwordMan.getY());
                System.out.println("**");

                this.getUnitGroup().getChildren().remove(this.getImgRoyalSwordMan());

                if (this.active) {
                    System.out.println("Active");
                    if (this.side.equals("France")) {
                        imgRoyalSwordMan.setImage(imgActiveRed);
                    } else {
                        imgRoyalSwordMan.setImage(imgActiveGreen);
                    }
                    int z = this.unitGroup.getChildren().indexOf(rectangleActive);
                    //this.unitGroup.getChildren().get(z).toFront();
                    this.rectangleActive.setOpacity(1);
                } else {
                    if (this.side.equals("France")) {
                        imgRoyalSwordMan.setImage(imgNotActiveRed);
                    } else {
                        imgRoyalSwordMan.setImage(imgNotActiveGreen);
                    }

                    System.out.println("not active");
                    this.rectangleActive.setOpacity(0);
                }
                this.getUnitGroup().getChildren().add(this.getImgRoyalSwordMan());

                this.rectangleActive.setX(x - 25.0D);
                this.rectangleActive.setY(y + 25.0D);

                this.imgRoyalSwordMan.setX(x);
                this.imgRoyalSwordMan.setY(y);
            }
        });
    }

    public RoyalSwordMan(int n, int l, String nm, int hp, double g, int dam,boolean isActive, String side,String rank,double chrdX,double chrdY){
        this(n,l,nm,hp,g,dam,isActive,side,rank);
        this.unitGroup.setLayoutX(chrdX);
        this.unitGroup.setLayoutY(chrdY);
    }

    public ImageView getImgRoyalSwordMan() {
        return imgRoyalSwordMan;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        RoyalSwordMan tmp = (RoyalSwordMan) super.clone();

        if (tmp.side.equals("France")) {
            tmp.imgRoyalSwordMan = new ImageView("/images/soldier_redR.png");
        } else {
            tmp.imgRoyalSwordMan = new ImageView("/images/soldier_green_lightR.png");
        }

        tmp.active = false;

        tmp.nameText = new Text(tmp.name);
        tmp.nameText.setFont(new Font(("Tahoma"), 20.0D));
        tmp.nameText.setX(30);

        tmp.rectangleActive = new Rectangle(350, 250);
        tmp.rectangleActive.setStrokeWidth(3);
        tmp.rectangleActive.setStroke(Color.YELLOW);
        tmp.rectangleActive.setFill(Color.TRANSPARENT);

        tmp.HealthBar = new Rectangle(100,10, Paint.valueOf("Blue"));
        tmp.HealthBar.setX(20);
        tmp.HealthBar.setY(5);

        tmp.unitGroup = new Group(tmp.imgRoyalSwordMan, tmp.nameText,tmp.HealthBar);

        if(tmp.side.equals("Germany")){
            tmp.unitGroup.setLayoutX(100);
            tmp.unitGroup.setLayoutY(670);
        } else if(tmp.side.equals("France")){
            tmp.unitGroup.setLayoutX(1000);
            tmp.unitGroup.setLayoutY(200);
        }

        tmp.unitGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            tmp.active = !tmp.active;

            double x = tmp.imgRoyalSwordMan.getX();
            double y = tmp.imgRoyalSwordMan.getY();

            tmp.getUnitGroup().getChildren().remove(tmp.getImgRoyalSwordMan());

            if (tmp.active) {
                System.out.println("Active");
                if(tmp.side.equals("France")) {
                    tmp.imgRoyalSwordMan.setImage(tmp.imgActiveRed);
                }
                else {
                    tmp.imgRoyalSwordMan.setImage(tmp.imgActiveGreen);
                }
                //int z = this.unitGroup.getChildren().indexOf(rectangleActive);
                //this.unitGroup.getChildren().get(z).toFront();
                tmp.rectangleActive.setOpacity(1);
            } else {
                if(tmp.side.equals("France")) {
                    tmp.imgRoyalSwordMan.setImage(tmp.imgNotActiveRed);
                }
                else {
                    tmp.imgRoyalSwordMan.setImage(tmp.imgNotActiveGreen);
                }

                System.out.println("not active");
                tmp.rectangleActive.setOpacity(0);
            }
            tmp.getUnitGroup().getChildren().add(tmp.getImgRoyalSwordMan());

            tmp.rectangleActive.setX(x - 25.0D);
            tmp.rectangleActive.setY(y + 25.0D);

            tmp.imgRoyalSwordMan.setX(x);
            tmp.imgRoyalSwordMan.setY(y);
        });

        if(this.killList == null) tmp.killList = new double[0];
        else tmp.killList = Arrays.copyOf(
                this.killList,
                this.killList.length);

        return (RoyalSwordMan) tmp;
    }

    public void setUnitChord(){
        this.imgRoyalSwordMan.setX(this.chordX);
        this.imgRoyalSwordMan.setY(this.chordY);

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

    @Override
    public void work(){
        int i= (killList.length+1)*getLvl();
        setGold(getGold()+500*i);

    }

    public void up(double d){
        //this.unitGroup.setLayoutY(this.unitGroup.getLayoutY() -d );
        /*this.chordY -= d;
        this.setUnitChord();*/
        if(chordY>0) {
            chordY = unitGroup.getLayoutY();
            this.unitGroup.setLayoutY(this.chordY - d);
        }
    }

    public void down(double d){
        /*this.chordY += d;
        this.setUnitChord();*/
        if(chordY<2300) {
            chordY = unitGroup.getLayoutY();
            this.unitGroup.setLayoutY(this.chordY + d);
        }
    }

    public void left(double d){
        /*this.chordX -= d;
        this.setUnitChord();*/
        if(chordX>0) {
            chordX = unitGroup.getLayoutX();
            this.unitGroup.setLayoutX(this.chordX - d);
        }
    }

    public void right(double d){
        /*this.chordX += d;
        this.setUnitChord();*/
        if(chordX<3700) {
            chordX = unitGroup.getLayoutX();
            this.unitGroup.setLayoutX(this.chordX + d);
        }
    }

    /*public String getRSRank(){
        return RANK;
    }*/

    public void die(Warrior w){
        System.out.println(w.name+" помер");
        w.hp=0;
        isAlive = false;
    }

    @Override
    public String getSide() {
        return side;
    }

    /*@Override
    public String toString(){
        return " Страж з "+armor+" захистом. " + super.toString();
    }*/

    public void setActive(){
        if(this.active) {
            this.active = !this.active;

            double x = this.imgRoyalSwordMan.getX();
            double y = this.imgRoyalSwordMan.getY();

            this.nameText.setFont(new Font(("Tahoma"),24.0D));

            this.getUnitGroup().getChildren().remove(this.getImgRoyalSwordMan());

            if (this.side.equals("France")) {
                imgRoyalSwordMan.setImage(imgNotActiveRed);
            } else {
                imgRoyalSwordMan.setImage(imgNotActiveGreen);
            }
            System.out.println("not active");
            this.rectangleActive.setOpacity(0);

            if (this.active) {
                System.out.println("Active");
                if (this.side.equals("France")) {
                    imgRoyalSwordMan.setImage(imgActiveRed);
                } else {
                    imgRoyalSwordMan.setImage(imgActiveGreen);
                }

                this.rectangleActive.setOpacity(1);
            }
            this.getUnitGroup().getChildren().add(this.getImgRoyalSwordMan());

            this.imgRoyalSwordMan.setX(x);
            this.imgRoyalSwordMan.setY(y);
        }
    }

    public void print(){
        System.out.println(toString());
    }
}

