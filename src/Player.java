import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 * ------------   Thor 1 -------------------------
 **/
class Player {

    static int MAX_X = 39;
    static int MAX_Y = 17;

    int currentThorX = 0;
    int currentThorY = 0;

    int lightX;
    int lightY;

    public Player(int initialTX,int initialTY,int lightX,int lightY){
        currentThorX = initialTX;
        currentThorY = initialTY;
        this.lightX = lightX;
        this.lightY = lightY;
    }

    public String findVerticalOrientation(){
        if(currentThorY < lightY){
            return "S";
        }else if (currentThorY > lightY){
            return "N";
        }else{
            return "";
        }
    }

    public String findHorizontalOrientation(){
        if(currentThorX < lightX){
            return "E";
        }else if (currentThorX > lightX){
            return "W";
        }else{
            return "";
        }

    }

    public void moveThor(String vertical,String horizontal){
        if(vertical.equals("S")){
            currentThorY++;
        }else if(vertical.equals("N")){
            currentThorY--;
        }

        if(horizontal.equals("E")) {
            currentThorX++;
        }else if(horizontal.equals("W")) {
            currentThorX--;
        }
    }


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTX = in.nextInt(); // Thor's starting X position
        int initialTY = in.nextInt(); // Thor's starting Y position

        Player player = new Player(initialTX,initialTY,lightX,lightY);

        // game loop
        while (true) {

            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            String vertical = player.findVerticalOrientation();
            String horizontal = player.findHorizontalOrientation();
            player.moveThor(vertical,horizontal);


            // A single line providing the move to be made: N NE E SE S SW W or NW
            System.out.println(vertical+horizontal);
        }
    }
}