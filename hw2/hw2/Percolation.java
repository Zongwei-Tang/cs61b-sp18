package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private class block {
        boolean color;

        public block(boolean a) {
            color = a;
        }
    }
    private int top;
    private int bottom;
    private block[][] world;
    private int size;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufFull;

    private int index(int a, int b) {
        return a * size + b;
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        world = new block[N][N];
        size = N;
        uf=new WeightedQuickUnionUF(N * N+2);
        ufFull=new WeightedQuickUnionUF(N*N+1);
        top=size*size;
        bottom=size*size+1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                world[i][j] = new block(false);
            }
        }
    }

    public void open(int row, int col) {
        if (row > size - 1 || col > size - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (world[row][col].color != true) {
            world[row][col].color = true;
            int index = index(row, col);
            if (row==0){
                uf.union(top,index);
                ufFull.union(top,index);
            }
            if (row==size-1){
                uf.union(bottom,index);
            }
            if (row > 0 && isOpen(row - 1, col)) {
                uf.union(index, index(row - 1, col));
                ufFull.union(index, index(row - 1, col));
            }

            // 检查并连接下方相邻位置
            if (row < size - 1 && isOpen(row + 1, col)) {
                uf.union(index, index(row + 1, col));
                ufFull.union(index, index(row + 1, col));
            }

            // 检查并连接左侧相邻位置
            if (col > 0 && isOpen(row, col - 1)) {
                uf.union(index, index(row, col - 1));
                ufFull.union(index, index(row, col - 1));
            }

            // 检查并连接右侧相邻位置
            if (col < size - 1 && isOpen(row, col + 1)) {
                uf.union(index, index(row, col + 1));
                ufFull.union(index, index(row, col + 1));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row > size - 1 || col > size - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return world[row][col].color;
    }

    public boolean isFull(int row, int col) {
        if (row > size - 1 || col > size - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row,col)){
            return false;
        }
        if (ufFull.connected(top,index(row,col))) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites(){
        int count=0;
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if (world[i][j].color){
                    count++;
                }
            }
        }
        return count;
    }

    public boolean percolates(){
        return uf.connected(top,bottom);
    }

    public static void main(String[] args){

    }
}
