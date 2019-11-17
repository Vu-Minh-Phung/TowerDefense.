package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Mod extends Rectangle {

    public int xC, yC;

    public int modSize = 40;
    public int modId ;

    public boolean inGame = false;

    public int modWalk = 0;
    public enum Direction{UP, DOWN, LEFT, RIGHT};

    public Direction direction = Direction.UP;

    public boolean hasUpward = false;
    public boolean hasDown = false;
    public boolean hasLeft = false;
    public boolean hasRight = false;

    public Mod(){

    }
    public void spawnMob(Block[][] blocks, int modId){
        for(int x = 0; x < blocks[blocks.length - 1].length; x++){
            if(blocks[blocks.length - 1][x].groundId == Value.roadGrass){
                setX(blocks[blocks.length - 1][x].getX());
                setY(blocks[blocks.length - 1][x].getY());
                setWidth(modSize);
                setHeight(modSize);

                xC = x;
                yC = blocks.length - 1 ;
            }
        }
        this.modId = modId;
        inGame = true;
    }

    public void  deleteMob(){
        inGame = false;
    }
    public void looseHealth(){
        GameLaunch.health -= 1;
        Store.text1.setText("" + GameLaunch.health);
        //System.out.println(GameLaunch.health);
    }

    public void physic(Block[][] blocks){
        if(direction == Direction.UP){
            setY( getY() - 1);
        }
        if(direction == Direction.DOWN){
            setY( getY() + 1);
        }
        if(direction == Direction.RIGHT){
            setX( getX() + 1);
        }
        if(direction == Direction.LEFT){
            setX( getX() - 1);
        }

        modWalk += 1;

        //System.out.println("Direction" + modWalk);
        if(modWalk == Room.blockSize){


            if(direction == Direction.UP){
                yC -= 1;
                hasUpward = true;
            }
            if(direction == Direction.DOWN){
                yC += 1;
                hasUpward = true;
            }
            if(direction == Direction.LEFT){
                xC -= 1;
                hasUpward = true;
            }
            if(direction == Direction.RIGHT){
                xC += 1;
                hasUpward = true;
            }

            //System.out.println("yC: " + yC + " | xC" + xC);
            if(blocks[yC][xC].airId == 0){
                //System.out.println("zo");
                deleteMob();
                looseHealth();
            }
            try {
                if (!hasRight) {
                    if (blocks[yC][xC - 1].groundId == Value.roadGrass) {
                        direction = Direction.LEFT;
                    }
                }
            }
            catch (Exception e){ }
            try {
                if(!hasUpward){
                    if(blocks[yC + 1][xC].groundId == Value.roadGrass){
                        direction = Direction.DOWN;
                    }
                }
            }
            catch (Exception e){}
            try {
                if(!hasLeft){
                    if(blocks[yC][xC + 1].groundId == Value.roadGrass){
                        direction = Direction.RIGHT;
                    }
                }
            }
            catch (Exception e){ }
            try {
                if(!hasDown){
                    if(blocks[yC - 1][xC].groundId == Value.roadGrass){
                        direction = Direction.UP;
                    }
                }
            }
            catch (Exception e){ }

            modWalk = 0;
            hasUpward = false;
            hasDown = false;
            hasLeft = false;
            hasRight = false;
        }

    }

    public void draw(GraphicsContext graphicsContext){

            graphicsContext.drawImage(GameLaunch.titlesEnemy[0], getX(), getY(), getWidth(), getHeight());
    }

}
