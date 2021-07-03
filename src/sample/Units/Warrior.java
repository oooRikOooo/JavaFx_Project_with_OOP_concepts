package sample.Units;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.App.Main;

import java.util.*;

enum Status {
    WORKER,SOLDIER,ROYALSOLDIER
}

public class Warrior implements Comparable<Warrior>,Cloneable  {
    protected ImageView imgWarrior;
    protected Image imgNotActiveRed = new Image("/images/worker_redL_Big.png");
    protected Image imgActiveRed = new Image("/images/worker_redL_Big_Selected.png");
    protected Image imgActiveGreen = new Image("/images/worker_green_LightL_Selected.png");
    protected Image imgNotActiveGreen = new Image("/images/worker_green_lightL.png");

    private String rank;
    protected String name;
    protected Integer hp;
    protected int points;
    protected int damage;
    private Double gold;
    private String side;

    private int target1=0;
    private int target2=1;

    protected Text nameText;

    protected Group unitGroup;

    protected Rectangle rectangleActive;
    protected Rectangle HealthBar;

    private double chordX;
    private double chordY;

    protected int aimX = 0;
    protected int aimY = 0;

    protected boolean isAlive;

    protected boolean isMonster;


    protected byte autoMove;

    protected Deque<Warrior> targets = new ArrayDeque<>();


    protected double[] killList;
    protected boolean active=false;


    public Warrior( String n, int hp, double gold, int damage,boolean isActive, String side,String rank) {
        this.side = side;
        if (this.side.equals("Monster1")){
            imgWarrior = new ImageView(new Image("/images/monster1.png"));
        } else if(this.side.equals("Monster2")){
            imgWarrior = new ImageView(new Image("/images/monster2.png"));
        }
        else if(this.side.equals("France")) {
            imgWarrior = new ImageView(new Image("/images/worker_redL_Big.png"));
        } else {
            imgWarrior = new ImageView(new Image("/images/worker_green_lightL.png"));
        }

        this.name=n;
        this.hp=hp;
        this.gold=gold;
        this.damage=damage;
        this.rank = rank;
        this.isAlive = true;

        if (this.side.equals("Monster1")){
            killList = new double[0];
        } else if(this.side.equals("Monster2")){
            killList = new double[3];
        } else killList = new double[0];

        this.active = isActive;

        this.nameText = new Text(name);
        this.nameText.setFont(new Font(("Tahoma"),24.0D));
        this.nameText.setX(30);


        this.rectangleActive = new Rectangle(350,250);
        this.rectangleActive.setStrokeWidth(3);
        this.rectangleActive.setStroke(Color.YELLOW);
        this.rectangleActive.setFill(Color.TRANSPARENT);

        this.HealthBar = new Rectangle(100,10, Paint.valueOf("Blue"));
        this.HealthBar.setX(20);
        this.HealthBar.setY(5);

        this.unitGroup = new Group(imgWarrior,this.nameText,this.HealthBar);

        if(side.equals("Germany")){
            this.unitGroup.setLayoutX(100);
            this.unitGroup.setLayoutY(670);
        } else if(side.equals("France")){
            this.unitGroup.setLayoutX(1000);
            this.unitGroup.setLayoutY(200);
        }

        unitGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
            if(!this.side.equals("Monster1") && !this.side.equals("Monster2")) {
                this.active = !this.active;

                double x = this.imgWarrior.getX();
                double y = this.imgWarrior.getY();

                //System.out.println("**");
                //System.out.println(imgWarrior.getX());
                //System.out.println(imgWarrior.getY());
                //System.out.println("**");
                System.out.println("**");
                System.out.println(getChordX());
                System.out.println(getChordY());
                System.out.println("**");

                this.getUnitGroup().getChildren().remove(this.getImgWarrior());

                if (this.side.equals("France")) {
                    imgWarrior.setImage(imgNotActiveRed);
                } else {
                    imgWarrior.setImage(imgNotActiveGreen);
                }
                //System.out.println("not active");
                this.rectangleActive.setOpacity(0);

                if (this.active) {
                    //System.out.println("Active");
                    if (this.side.equals("France")) {
                        imgWarrior.setImage(imgActiveRed);
                    } else {
                        imgWarrior.setImage(imgActiveGreen);
                    }

                    this.rectangleActive.setOpacity(1);
                } else {
                    if (this.side.equals("France")) {
                        imgWarrior.setImage(imgNotActiveRed);
                    } else {
                        imgWarrior.setImage(imgNotActiveGreen);
                    }
                    //System.out.println("not active");
                    this.rectangleActive.setOpacity(0);
                }
                this.getUnitGroup().getChildren().add(this.getImgWarrior());


                this.imgWarrior.setX(x);
                this.imgWarrior.setY(y);
            }

        });

        //System.out.println("Конструктор з параметрами");
    }

    public Warrior(String n, int hp, double gold, int damage,boolean isActive, String side,String rank,double chrdX, double chrdY){
        this(n,hp,gold,damage,isActive,side,rank);
        /*this.chordX = chrdX;
        this.chordY = chrdY;*/
        this.unitGroup.setLayoutX(chrdX);
        this.unitGroup.setLayoutY(chrdY);
    }

    public Warrior(String n,int hp,double gold,int damage,String side,String rank,double chrdX, double chrdY){
        this.name=n;
        this.hp=hp;
        this.gold=gold;
        this.damage=damage;

        if(this.side.equals("France")) {
            imgWarrior = new ImageView(new Image("/images/monster2.png"));
        } else {
            imgWarrior = new ImageView(new Image("/images/monster1.png"));
        }

        this.unitGroup = new Group(imgWarrior);

        this.unitGroup.setLayoutX(chrdX);
        this.unitGroup.setLayoutY(chrdY);

    }

    public Warrior(){

    }

    public void setUnitChord(){
        /*this.imgWarrior.setX(this.chordX);
        this.imgWarrior.setY(this.chordY);

        this.nameText.setX(this.chordX+8);
        this.nameText.setY(this.chordY-5);

        this.rectangleActive.setX(this.chordX);
        this.rectangleActive.setY(this.chordY);*/

        this.unitGroup.setLayoutX(this.chordX);
        this.unitGroup.setLayoutY(this.chordY);

        if(this.active){
            rectangleActive.setOpacity(1);
        } else {
            rectangleActive.setOpacity(0);
        }
    }

    public void up(double d){
        if(chordY>0) {
            chordY = unitGroup.getLayoutY();
            this.unitGroup.setLayoutY(this.chordY - d);
        }
        /*this.chordY -= d;
        this.setUnitChord();*/
        //unitGroup.setLayoutX(unitGroup.getLayoutY()-d);
    }

    public void down(double d){
        if(chordY<2300) {
            chordY = unitGroup.getLayoutY();
            this.unitGroup.setLayoutY(this.chordY + d);
        }
        /*this.chordY += d;
        this.setUnitChord();*/
        //unitGroup.setLayoutX(unitGroup.getLayoutY()+d);
    }

    public void left(double d){
        if(chordX>0) {
            chordX = unitGroup.getLayoutX();
            this.unitGroup.setLayoutX(this.chordX - d);
        }
        /*this.chordX -= d;
        this.setUnitChord();*/
        //unitGroup.setLayoutX(unitGroup.getLayoutX()-d);
    }

    public void right(double d){
        if(chordX<3700) {
            chordX = unitGroup.getLayoutX();
            this.unitGroup.setLayoutX(this.chordX + d);
        }

        /*this.chordX += d;
        this.setUnitChord();*/
        //unitGroup.setLayoutX(unitGroup.getLayoutX()+d);
    }

    public void autoMove(){
        if(!this.side.equals("Monster1") && !this.side.equals("Monster2")) {
            if (aimX == unitGroup.getLayoutX() && aimY == unitGroup.getLayoutY()) {
                if (this.getClass() == Warrior.class && this instanceof Warrior && this.gold > 3000) {
                    if (this.side.equals("Germany")) {
                        aimX = 100;
                        aimY = 1600;
                    } else {
                        aimX = 2900;
                        aimY = 240;
                    }
                } else {
                    Random rnd = new Random();
                    aimX = rnd.nextInt(World.getRootWidth() - (int) unitGroup.getBoundsInParent().getWidth());
                    aimY = rnd.nextInt(World.getRootHeight() - (int) unitGroup.getBoundsInParent().getHeight());
                }

            }

            double dx = aimX - unitGroup.getLayoutX();
            double dy = aimY - unitGroup.getLayoutY();
            dx = Math.abs(dx) > 1 ? Math.signum(dx) * 1 : dx;
            dy = Math.abs(dy) > 1 ? Math.signum(dy) * 1 : dy;

            unitGroup.setLayoutX(unitGroup.getLayoutX() + dx);
            unitGroup.setLayoutY(unitGroup.getLayoutY() + dy);
        } /*else {
            //aimX = Main.getWorld().outputSoldiers().get(Main.getWorld().outputSoldiers().get(this.target)).getChordX();
            //aimY =  Main.getWorld().outputSoldiers().get(Main.getWorld().outputSoldiers().size()).getAimY();


        }*/
    }

    public void updateAim(){

        this.targets.clear();
        for (Warrior w : Main.world.outputSoldiers()){
            if(w.getHp()>0) {
                this.targets.add(w);
            }
        }

        if(this.side.equals("Monster1")) {

            this.aimX = (int) this.targets.peekFirst().getChordX();
            this.aimY = (int) this.targets.peekFirst().getChordY();
        } else if(this.side.equals("Monster2")){
            this.aimX = (int) this.targets.peekLast().getChordX();
            this.aimY = (int) this.targets.peekLast().getChordY();
        }

        double dx = aimX - unitGroup.getLayoutX();
        double dy = aimY - unitGroup.getLayoutY();
        dx = Math.abs(dx) > 1 ? Math.signum(dx) * 2 : dx;
        dy = Math.abs(dy) > 1 ? Math.signum(dy) * 2 : dy;

        unitGroup.setLayoutX(unitGroup.getLayoutX() + dx);
        unitGroup.setLayoutY(unitGroup.getLayoutY() + dy);
    }

    public Deque<Warrior> getTargets() {
        return targets;
    }

    public int getAimX() {
        return aimX;
    }

    public int getAimY() {
        return aimY;
    }

    public void setAimX(int aimX) {
        this.aimX = aimX;
    }

    /*public int getTarget() {
        return target;
    }*/

    public void setAimY(int aimY) {
        this.aimY = aimY;
    }

    public void moveToBase(){
        if(this.side.equals("Germany")){
            aimX = 100;
            aimY = 1600;
        } else {
            aimX = 2900;
            aimY = 240;
        }

    }

    public void moveAllUnits(){
        if(this.side.equals("Germany")){
            aimX = 100;
            aimY = 1600;
        } else {
            aimX = 2900;
            aimY = 240;
        }

    }

    public void moveAllUnits(int x, int y){
        aimX = x;
        aimY = y;
    }



    public String getSide() {
        return side;
    }

    public double[] getKillList() {
        return killList;
    }

    public ImageView getImgWarrior() {
        return imgWarrior;
    }

    public Group getUnitGroup() {
        return unitGroup;
    }

    public void setX(double x){
        imgWarrior.setX(x);
    }

    public void setY(double y){
        imgWarrior.setY(y);
    }

    public double getChordX(){
        //return imgWarrior.getX();
        return  chordX;
    }

    public double getChordY(){
        //return imgWarrior.getY();
        return chordY;
    }

    public String getRank() {
        return rank;
    }

    public ImageView getIV(){
        return imgWarrior;
    }

    /*public EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            activate();
        }
    };*/

    public void activate(){
        if(active){
            active = !active;
            if(this.side.equals("France")){
                imgWarrior.setImage(imgNotActiveRed);
            }

            else {
                imgWarrior.setImage(imgNotActiveGreen);
            }
        } else {
            active = !active;
            if(this.side.equals("France")) {
                imgWarrior.setImage(imgActiveRed);
            }
            else {
                imgWarrior.setImage(imgActiveGreen);
            }
        }
    }


    public void keyMove(double dx, double dy){
        double x = imgWarrior.getLayoutX()+dx;
        double y = imgWarrior.getLayoutY()+dy;

        imgWarrior.setLayoutX(x);
        imgWarrior.setLayoutY(y);
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return  hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getGold(){
        return gold;
    }

    public void setGold(double gold){
        this.gold=gold;
    }


    public void setPoints(int points){
        this.points=points;
    }

    public int getPoints(){
        return points;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isActive(){ return active; }

    public void setActive(){
        if(this.active) {
            this.active = !this.active;

            double x = this.imgWarrior.getX();
            double y = this.imgWarrior.getY();



            this.getUnitGroup().getChildren().remove(this.getImgWarrior());

            if (this.side.equals("France")) {
                imgWarrior.setImage(imgNotActiveRed);
            } else {
                imgWarrior.setImage(imgNotActiveGreen);
            }
            System.out.println("not active");
            this.rectangleActive.setOpacity(0);

            if (this.active) {
                System.out.println("Active");
                if (this.side.equals("France")) {
                    imgWarrior.setImage(imgActiveRed);
                } else {
                    imgWarrior.setImage(imgActiveGreen);
                }

                this.rectangleActive.setOpacity(1);
            }
            this.getUnitGroup().getChildren().add(this.getImgWarrior());

            this.imgWarrior.setX(x);
            this.imgWarrior.setY(y);
        }
    }

    Status status;
    {
        status=Status.WORKER;
    }

    public void addToKillList(int kill){
        if(killList==null)
            killList = new double[1];
        else killList = Arrays.copyOf(
                killList,killList.length+1);
        killList[killList.length-1] = kill;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        Warrior tmp = (Warrior) super.clone();

        if(tmp.side.equals("France")) {
            tmp.imgWarrior = new ImageView(new Image("/images/worker_redL_Big.png"));
        } else {
            tmp.imgWarrior = new ImageView(new Image("/images/worker_green_lightL.png"));
        }

        tmp.active = false;

        tmp.nameText = new Text(tmp.name);
        tmp.nameText.setFont(new Font(("Tahoma"),24.0D));
        tmp.nameText.setX(30);

        tmp.rectangleActive = new Rectangle(350,250);
        tmp.rectangleActive.setStrokeWidth(3);
        tmp.rectangleActive.setStroke(Color.YELLOW);
        tmp.rectangleActive.setFill(Color.TRANSPARENT);

        tmp.HealthBar = new Rectangle(100,10, Paint.valueOf("Blue"));
        tmp.HealthBar.setX(20);
        tmp.HealthBar.setY(5);

        tmp.unitGroup = new Group(tmp.imgWarrior,tmp.nameText,tmp.HealthBar);
        if(tmp.side.equals("Germany")){
            tmp.unitGroup.setLayoutX(100);
            tmp.unitGroup.setLayoutY(670);
        } else if(tmp.side.equals("France")){
            tmp.unitGroup.setLayoutX(1000);
            tmp.unitGroup.setLayoutY(200);
        }
        tmp.unitGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
            tmp.active = !tmp.active;

            double x = tmp.imgWarrior.getX();
            double y = tmp.imgWarrior.getY();

            tmp.getUnitGroup().getChildren().remove(tmp.getImgWarrior());

            if(tmp.side.equals("France")) {
                tmp.imgWarrior.setImage(tmp.imgNotActiveRed);
            }
            else {
                tmp.imgWarrior.setImage(tmp.imgNotActiveGreen);
            }
            //System.out.println("not active");
            tmp.rectangleActive.setOpacity(0);

            if(tmp.active){
                //System.out.println("Active");
                if (tmp.side.equals("France")) {
                    tmp.imgWarrior.setImage(tmp.imgActiveRed);
                }
                else {
                    tmp.imgWarrior.setImage(tmp.imgActiveGreen);
                }

                tmp.rectangleActive.setOpacity(1);
            } else {
                if(tmp.side.equals("France")) {
                    tmp.imgWarrior.setImage(tmp.imgNotActiveRed);
                }
                else {
                    tmp.imgWarrior.setImage(tmp.imgNotActiveGreen);
                }
                //System.out.println("not active");
                tmp.rectangleActive.setOpacity(0);
            }
            tmp.getUnitGroup().getChildren().add(tmp.getImgWarrior());

            tmp.imgWarrior.setX(x);
            tmp.imgWarrior.setY(y);

        });

        if(this.killList == null) tmp.killList = new double[0];
        else tmp.killList =Arrays.copyOf(
                this.killList,
                this.killList.length);



        return (Warrior) tmp;
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        Warrior warrior=(Warrior) o;
        return
                name.equals(warrior.name) &&
                        status==warrior.status;
    }
    //lvl==warrior.lvl &&

    @Override
    public String toString() {
        return "\nІмя:" + name +
                " Здоровя: "+ hp +
                " Золото: " + gold +
                "\n Урон: " + damage +
                " Країна: " + side +
                " Вбивств: " + killList.length;
        /*Arrays.toString(killList);*/
    }

    public static int lineforUp;
    static {
        lineforUp=5;
    }

    public void work(){
        this.gold++;
    }

    public boolean promote(Warrior w) {
        if(points==0){
            System.out.println("0 балів");
            return false;
        }
        if(points<lineforUp) return false;
        switch (w.status) {
            case WORKER -> w.status = status.ROYALSOLDIER;
            case ROYALSOLDIER -> w.status = status.SOLDIER;
            case SOLDIER -> {
                System.out.println("Максимальній рівень");
                break;
            }
            default -> System.out.println("Помилка! Неіснуючий статус");
        }
        return true;
    }

    /*public void addToKillList(double kills){
        if(killList == null) killList = new double[1];
        else killList =Arrays.copyOf(
                killList,killList.length+1);
        killList[killList.length-1]=kills;
    }*/

    public void printInfo(Warrior w) {
        System.out.println("Воїн: "+w.name+" ХП:"+w.hp+" Золото: "+w.gold+" Урон: "+w.damage);
        //" Рівень: "+w.lvl+
    }


    /* public void move(){
         System.out.println("Рух");
     }*/
    public void move( double dx, double dy )
    {
       /*double x=+ dx;
       double y=+ dy;*/

        imgWarrior.setX(this.imgWarrior.getX()+dx);
        imgWarrior.setY(this.imgWarrior.getY()+dy);

    }
    public void moveMouse( double dx, double dy )
    {
        imgWarrior.setX(dx);
        imgWarrior.setY(dy);

    }

    public void attack(Warrior w1, Warrior w2){
        w2.hp = w2.hp-w1.damage;
        w1.hp = w1.hp-w2.damage;
        if(w2.hp<=0) {
            w1.addToKillList(1);
        }
        if(w1.hp<=0) {
            w2.addToKillList(1);
        }
    }

    public void shareMoney(Warrior w1, Warrior w2,double n){
        if(w2.gold>=n) {
            w1.gold += n;
            w2.gold -= n;
            System.out.println(w2.name + " передав"+ n +"золота " + w1.name);
            System.out.println(w1.toString());
            System.out.println(w2.toString());
        } else System.out.println("Недостатньо золота у "+ w2.name);

    }

    public void build(Warrior w){
        System.out.println(w+" побудував будівлю");
    }

    public void die(Warrior w){
        System.out.println(w.name+" помер");
        w.hp=0;
        isAlive = false;
    }

    public static Comparator<Warrior> nameComparator = new Comparator<Warrior>() {
        @Override
        public int compare(Warrior o1, Warrior o2) {
            return o1.name.compareTo(o2.name);
        }
    };

    public static Comparator<Warrior > hpComparator = new Comparator<Warrior>() {
        @Override
        public int compare(Warrior o1, Warrior o2) {
            if(o1.hp < o2.hp) return 1;
            if(o1.hp > o2.hp) return -1;
            return 0;
        }
    };

    public static int compareHp(Warrior w1,Warrior w2){
        if(w1.hp < w2.hp) return 1;
        if(w1.hp > w2.hp) return -1;
        return 0;
    }

    public static int compareDamage(Warrior w1,Warrior w2){
        if(w1.damage < w2.damage) return 1;
        if(w1.damage > w2.damage) return -1;
        return 0;
    }

    public static Comparator<Warrior> goldComparator = new Comparator<Warrior>() {
        @Override
        public int compare(Warrior o1, Warrior o2) {
            if(o1.gold < o2.gold) return 1;
            if(o1.gold > o2.gold) return -1;
            return 0;
        }
    };
    public static Comparator<Warrior> nameComparatorToHigh = new Comparator<Warrior>() {
        @Override
        public int compare(Warrior o1, Warrior o2) {
            return o2.name.compareTo(o1.name);
        }
    };

    public static Comparator<Warrior > hpComparatorToHigh = new Comparator<Warrior>() {
        @Override
        public int compare(Warrior o1, Warrior o2) {
            if(o1.hp < o2.hp) return -1;
            if(o1.hp > o2.hp) return 1;
            return 0;
        }
    };

    public static Comparator<Warrior > damageComparatorToHigh = new Comparator<Warrior>() {
        @Override
        public int compare(Warrior o1, Warrior o2) {
            if(o1.damage < o2.damage) return -1;
            if(o1.damage > o2.damage) return 1;
            return 0;
        }
    };

    public static Comparator<Warrior> goldComparatorToHigh = new Comparator<Warrior>() {
        @Override
        public int compare(Warrior o1, Warrior o2) {
            if(o1.gold < o2.gold) return -1;
            if(o1.gold > o2.gold) return 1;
            return 0;
        }
    };



    @Override
    public int compareTo(Warrior o) {

        if(this.name.equals(o.name)==false){
            if(o.hp!=0) {
                if (this.hp < o.hp)
                    return -1;
                if (this.hp > o.hp)
                    return 1;
                return 0;
            }
            if(o.gold!=0) {
                if (this.gold < o.gold)
                    return -1;
                if (this.gold > o.gold)
                    return 1;
                return 0;
            }
            if(o.damage!=0){
                if(this.damage < o.damage)
                    return -1;
                if(this.damage > o.damage)
                    return 1;
                return 0;
            }

            return this.name.compareTo(o.name);
        }
        else return 0 ;

    }

}

