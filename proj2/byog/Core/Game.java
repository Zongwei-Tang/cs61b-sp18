package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private Random b;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {

    }



    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        ArrayList<room> rooms=new ArrayList<>();
        TETile[][] world=new TETile[WIDTH][HEIGHT];
        if (input.equals("l")){
            return world;
        }
        if ((input.length() > 0) && (input.charAt(0) == 'N' || input.charAt(0) == 'n')){
            long seed=0;
            int abc=input.indexOf('s');
            if (abc>1){
                seed= Long.parseLong(input.substring(1,abc));
            }
            b=new Random(seed);
        }
        ter.initialize(WIDTH,HEIGHT);
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int i=0;i<WIDTH;i++){
            for (int j=0;j<HEIGHT;j++){
                finalWorldFrame[i][j]= Tileset.NOTHING;
            }
        }
        int maxattempt=50;
        int current=0;
        int desireattempt=15;
        boolean d=true;
        while (current<maxattempt && rooms.size()<desireattempt){
            d=true;
            room a=getrandomroom();
            for (room room : rooms) {
                if (a.overlap(room)){
                    d=false;
                }
            }
            if (d){
                rooms.add(a);
                drawroom(a,finalWorldFrame);
                current++;
            }
        }
        for (int i=0;i<rooms.size()-1;i++){
            connectrooms(rooms.get(i),rooms.get(i+1),finalWorldFrame);
        }
        if (input.indexOf(":q")!=-1){
            world=finalWorldFrame;
        }
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    public void connectrooms(room a,room c,TETile[][]d){
        int x1=RandomUtils.uniform(b,a.p.x+1,a.p.x+a.width);
        int y1=RandomUtils.uniform(b,a.p.y+1,a.p.y+a.height);
        int x2=RandomUtils.uniform(b,c.p.x+1,c.p.x+c.width);
        int y2=RandomUtils.uniform(b,c.p.y+1,c.p.y+c.height);
        while (x1!=x2){
            if (d[x1][y1]!=Tileset.FLOOR){
                d[x1][y1]=Tileset.FLOOR;
            }
            if (d[x1][y1+1]==Tileset.NOTHING && y1+1<HEIGHT){
                d[x1][y1+1]=Tileset.WALL;
            }
            if (d[x1][y1-1]==Tileset.NOTHING && y1-1>=0){
                d[x1][y1-1]=Tileset.WALL;
            }
            if (x1<x2){
                x1++;
            }
            else{
                x1--;
            }
            if (x1==x2){
                if (d[x1][y1]!=Tileset.FLOOR){
                    d[x1][y1]=Tileset.FLOOR;
                }
                if (d[x1][y1+1]==Tileset.NOTHING && y1+1<HEIGHT){
                    d[x1][y1+1]=Tileset.WALL;
                }
                if (d[x1][y1-1]==Tileset.NOTHING && y1-1>=0){
                    d[x1][y1-1]=Tileset.WALL;
                }
            }
        }
        while (y1!=y2){
            if (d[x1][y1]!=Tileset.FLOOR){
                d[x1][y1]=Tileset.FLOOR;
            }
            if (d[x1+1][y1]==Tileset.NOTHING && x1+1<WIDTH){
                d[x1+1][y1]=Tileset.WALL;
            }
            if (d[x1-1][y1]==Tileset.NOTHING && x1-1>=0){
                d[x1-1][y1]=Tileset.WALL;
            }
            if (y1<y2){
                y1++;
            }
            else{
                y1--;
            }
            if (y1==y2){
                if (d[x1][y1]!=Tileset.FLOOR){
                    d[x1][y1]=Tileset.FLOOR;
                }
                if (d[x1+1][y1]==Tileset.NOTHING && x1+1<WIDTH){
                    d[x1+1][y1]=Tileset.WALL;
                }
                if (d[x1-1][y1]==Tileset.NOTHING && x1-1>=0){
                    d[x1-1][y1]=Tileset.WALL;
                }
            }
        }
    }

    public room getrandomroom(){
        int width=RandomUtils.uniform(b,3,10);
        int height=RandomUtils.uniform(b,3,10);
        int x=RandomUtils.uniform(b,0,WIDTH-width);
        int y=RandomUtils.uniform(b,0,HEIGHT-height);
        return new room(new position(x,y),width,height);
    }

    public void drawroom(room a, TETile[][] b){
        for (int i=0;i<a.height;i++){
            for (int j=0;j<a.width;j++){
                if ((i == 0 || i==a.height-1 || j==0 || j==a.width-1)) {
                    b[a.p.x + j][a.p.y + i] = Tileset.WALL;
                } else {
                    b[a.p.x + j][a.p.y + i] = Tileset.FLOOR;
                }
            }
        }
    }

    public class position{
        int x;
        int y;
        public position(int a, int b){
            x=a;
            y=b;
        }
    }

    public class room{
        position p;
        int width;
        int height;
        public room(position a,int b,int c){
            p=a;
            width=b;
            height=c;
        }
        public boolean overlap(room a){
            return ((p.x<a.p.x+a.width && a.p.x<p.x+width) && (p.y<a.p.y+height && a.p.y<p.y+height));
        }
    }

    public static void main(String[] args){
        Game a=new Game();
        a.playWithInputString("N73786S");
        StdDraw.pause(10000);
    }
}
