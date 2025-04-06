package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public class block {
        boolean color;

        public block(boolean a) {
            color = a;
        }
    }

    block[][] world;
    int size;
    WeightedQuickUnionUF uf;

    public int index(int a, int b) {
        return a * size + b;
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        world = new block[N][N];
        size = N;
        uf=new WeightedQuickUnionUF(N * N);
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
            if (row > 0 && isOpen(row - 1, col)) {
                uf.union(index, index(row - 1, col));
            }

            // 检查并连接下方相邻位置
            if (row < size - 1 && isOpen(row + 1, col)) {
                uf.union(index, index(row + 1, col));
            }

            // 检查并连接左侧相邻位置
            if (col > 0 && isOpen(row, col - 1)) {
                uf.union(index, index(row, col - 1));
            }

            // 检查并连接右侧相邻位置
            if (col < size - 1 && isOpen(row, col + 1)) {
                uf.union(index, index(row, col + 1));
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
        for (int i = 0; i < size; i++) {
            if (uf.connected(index(0,i),index(row,col))){
                return true;
            }
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
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if (uf.connected(index(size-1,i),index(0,j))){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){

    }
}
