package sample;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;

public class Room {

    public static int worldWidth = 28;
    public static int worldHeight =15;

    public static int blockSize = 40;

    public Block[][] blocks = new Block[worldHeight][worldWidth];

    public Room(){
        createBlock();
    }

    public void createBlock() {
        for (int y = 0; y < blocks.length; y++)
            for (int x = 0; x < blocks[y].length; x++) {
                blocks[y][x] = new Block(x * blockSize + (GameStart.WIDTH - GameLaunch.myWidth) / 2, y * blockSize, blockSize, blockSize, Value.groundGrass, Value.airAir);
            }
    }

    public void drawRoom(GraphicsContext graphicsContext) throws FileNotFoundException {
            for(int y = 0; y < blocks.length; y++)
                for(int x = 0; x < blocks[y].length; x++){
                    blocks[y][x].drawBlock(graphicsContext);
                }
        }
    public void physic(){
        for(int y = 0; y < blocks.length; y++)
            for(int x = 0; x < blocks[y].length; x++){
                blocks[y][x].physic();
            }
    }
}
