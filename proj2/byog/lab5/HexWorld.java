package byog.lab5;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t){
        for (int y = 0; y < 2 * s; y++) {
            // 确定当前行的宽度
            int rowWidth;

            // 确定当前行的起始x坐标偏移量
            int xOffset;

            // 上半部分六边形（包括中间最宽的两行）
            if (y < s) {
                rowWidth = s + 2 * y;
                xOffset = s - 1 - y;
            }
            // 下半部分六边形
            else {
                rowWidth = 3 * s - 2 * (y - s + 1);
                xOffset = y - s + 1;
            }

            // 填充当前行
            for (int x = 0; x < rowWidth; x++) {
                // 计算在世界中的坐标位置
                int worldX = p.x + xOffset + x;
                int worldY = p.y + y;

                // 确保坐标在世界范围内
                if (worldX >= 0 && worldX < world.length &&
                        worldY >= 0 && worldY < world[0].length) {
                    world[worldX][worldY] = t;
                }
            }
        }
    }
    public static Position getTop(Position p,int s){
        return new Position(p.x,p.y+2*s);
    }
    public static Position getTopRight(Position p, int s){
        return new Position(p.x+2*s-1,p.y+s);
    }
    public static Position getBottomRight(Position p,int s){
        return new Position(p.x+2*s-1,p.y-s);
    }
    public static TETile getrandomtile(){
        Random rand=new Random();
        TETile tile;
        switch (StdRandom.uniform(0,5)){
            case 0:
                tile = Tileset.GRASS;
                break;
            case 1:
                tile = Tileset.FLOWER;
                break;
            case 2:
                tile = Tileset.MOUNTAIN;
                break;
            case 3:
                tile = Tileset.TREE;
                break;
            case 4:
                tile = Tileset.SAND;
                break;
            default:
                tile = Tileset.NOTHING;
                break;
        }
        return TETile.colorVariant(tile, 25, 25, 25, rand);
    }
    public static void drawvertex(TETile[][] world,Position p,int n,int s){
        Position a=p;
        for (int i=0;i<n;i++){
            TETile t=getrandomtile();
            addHexagon(world,a,s,t);
            a=getTop(a,s);
        }
    }
    public static void buildworld(TETile[][]world,int s){
        Position start=new Position(20,10);
        for (int i=3;i<6;i++){
            start=getBottomRight(start,s);
            drawvertex(world,start,i,s);
        }
        start=getTopRight(start,s);
        int j;
        for (j = 4; j>=3; j--) {
            drawvertex(world,start,j,s);
            start=getTopRight(start,s);
        }
    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(80, 60);
        TETile[][] world = new TETile[80][60];
        for (int x = 0; x < 80; x++) {
            for (int y = 0; y < 60; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        buildworld(world,3);
        ter.renderFrame(world);
    }
}
