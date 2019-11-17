package sample;

import com.sun.prism.Graphics;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;

public class GameLaunch {

    public static int myWidth = 1120;
    public static int myHeight = 600;

    public boolean isFirst = true;

    public static Image[] titlesGround = new Image[10];
    public static Image[] titlesAir = new Image[10];
    public static Image[] titlesIcon = new Image[10];
    public static Image[] titlesEnemy = new Image[10];

    public Room room;
    public Save save;

    public Store store;
    public static Mod[] enemy = new Mod[5];

    public static int health = 1;
    public static int coinage = 100;

    public static Point2D mse;

    public GameLaunch() throws FileNotFoundException, InterruptedException {
        define();

    }

    public void define() {
        try {
            titlesGround[0] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\IceMap2.jpg");
            titlesGround[1] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\road1.png");
            titlesGround[2] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\background.png");

            titlesAir[0] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\target.png");
            titlesAir[1] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\recycle.png");
            titlesAir[2] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\tower1.png");
            titlesAir[3] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\tower2.png");
            titlesAir[4] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\tower3.png");
            titlesAir[5] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\tower4.png");
            titlesAir[6] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\tower5.png");
            titlesAir[7] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\tower6.png");
            titlesAir[8] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\tower7.png");

            titlesIcon[0] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\cell.png");
            titlesIcon[3] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\button.png");
            titlesIcon[1] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\coin.png");
            titlesIcon[2] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\health.png");



            titlesEnemy[0] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\5.png");
            titlesEnemy[1] = Draw.loadImage("C:\\Users\\QD\\Documents\\TowerDefense(Demo)\\4.png");
        }
        catch (Exception e){
            System.out.println("Canot load Image");
        }
        }

    public void drawGameLaunch(Group group, GraphicsContext graphicsContext) throws FileNotFoundException {

        room = new Room();
        save = new Save();

        save.loadSave(new File("Map1"), room.blocks);

        System.out.println(room.blocks[3][27].airId);

        store = new Store();
        store.draw(group, room.blocks);



        for(int i = 0; i < enemy.length; i++) {
            enemy[i] = new Mod();
            //enemy[i].spawnMob(room.blocks, 0);
        }

    }

    public void paintComponent(Group group, GraphicsContext graphicsContext) {
        try {

            if (isFirst) {
                drawGameLaunch(group, graphicsContext);
                isFirst = false;

            }
            graphicsContext.clearRect((GameStart.WIDTH - myWidth) / 2, 0 , myWidth, myHeight);

            graphicsContext.drawImage(titlesGround[0], (GameStart.WIDTH - myWidth) / 2, 0, myWidth, myHeight);
            room.drawRoom(graphicsContext);
            for (int i = 0; i < enemy.length; i++) {
                if (enemy[i].inGame) {
                    enemy[i].draw(graphicsContext);
                    //System.out.println("draw" + i);
                }
            }

            if(health < 1){
                graphicsContext.drawImage(titlesGround[2], (GameStart.WIDTH - myWidth) / 2, 0, myWidth, myHeight);
                graphicsContext.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                graphicsContext.strokeText("You were lose!", 20, 20);
                System.out.println("zo");
            }
        }
        catch (Exception e){
            System.out.println("Error: Paint Component");
        }
    }

    public int spawnTime = 100, spawnFrame = 0;
    boolean create = true;
    public void modSpawner(){
        if(spawnFrame >= spawnTime){
            for(int i =0; i < enemy.length; i++){
                if(!enemy[i].inGame) {
                    enemy[i].spawnMob(room.blocks, Value.modGreeny);
                    break;
                }
                if(enemy[4].inGame){
                    create = false;
                }
            }

            spawnFrame = 0;
        }
        else{
            spawnFrame += 1;
        }
        //System.out.println(spawnFrame);
    }



    public void start(Group group,GraphicsContext graphicsContext){

        try {
            paintComponent(group, graphicsContext);
            room.physic();
            if(create && health > 0){
                modSpawner();
            }
            for(int i=0;i<enemy.length;i++){
                if(enemy[i].inGame){
                    enemy[i].physic(room.blocks);
                }
            }




        }
        catch (Exception e){
            System.out.println("Error: start GameLaunch");
        }
    }
}
