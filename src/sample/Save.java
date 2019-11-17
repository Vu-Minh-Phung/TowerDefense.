package sample;

import java.io.File;
import java.util.Scanner;

public class Save {
    public Save(){}

    public void loadSave(File loadPath, Block[][] blocks){

        try{
            Scanner loadScaner = new Scanner(loadPath);

            while (loadScaner.hasNext()) {
                for(int y = 0; y < blocks.length; y++)
                    for(int x = 0; x < blocks[y].length; x++){
                        blocks[y][x].groundId = loadScaner.nextInt();
                    }
                for(int y = 0; y < blocks.length; y++)
                    for(int x = 0; x < blocks[y].length; x++){
                        blocks[y][x].airId = loadScaner.nextInt();
                    }
            }
            loadScaner.close();
        }
        catch (Exception e){
            System.out.println("Error: Load file text!");
        }
    }

}
