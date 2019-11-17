package sample;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;


public class Block extends Rectangle {
    Rectangle displayAreaTower;
    public int areaTower = 60;
    public int groundId;
    public int airId;

    public int shotMob = 0;
    public boolean shoting = false;

    public Block(int positionX, int positionY, int width, int height, int groundId, int airId){
        super(positionX, positionY, width, height);

        displayAreaTower = new Rectangle(getX() - areaTower/2, getY() - areaTower/2,width + areaTower, height + areaTower);

        this.groundId = groundId;
        this.airId = airId;
    }

    public void physic(){
        for(int i = 0; i < GameLaunch.enemy.length; i++){
            if(GameLaunch.enemy[i].inGame ) {
                if (GameLaunch.enemy[i].contains(new Point2D(displayAreaTower.getX(), displayAreaTower.getY())))
                {
                    System.out.println("getX: " + displayAreaTower.getX() + " | get Y: " + displayAreaTower.getY());;
                }
            }
        }
    }

    public void drawArea(GraphicsContext graphicsContext){
        graphicsContext.beginPath();
        graphicsContext.moveTo(displayAreaTower.getX(), displayAreaTower.getY());
        graphicsContext.lineTo(displayAreaTower.getX() + displayAreaTower.getWidth(), displayAreaTower.getY());
        graphicsContext.lineTo(displayAreaTower.getX() + displayAreaTower.getWidth(), displayAreaTower.getY() + displayAreaTower.getHeight());
        graphicsContext.lineTo(displayAreaTower.getX(), displayAreaTower.getY() + displayAreaTower.getHeight());
        graphicsContext.lineTo(displayAreaTower.getX(), displayAreaTower.getY());
        graphicsContext.stroke();
        //System.out.println("zo");;
    }

    public void drawBlock(GraphicsContext graphicsContext) {

        if(groundId == Value.roadGrass){
            graphicsContext.drawImage(GameLaunch.titlesGround[Value.roadGrass], getX(), getY(), getWidth(), getHeight());
            //System.out.println("?");
        }
        if(airId != Value.airAir){
            //System.out.println("zo");
            graphicsContext.drawImage(GameLaunch.titlesAir[airId], getX(), getY(), getWidth(), getHeight());
            if(airId == Value.airTowerLaser){
                drawArea(graphicsContext);
            }
        }
    }
}
