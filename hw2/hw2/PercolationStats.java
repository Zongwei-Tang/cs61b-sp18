package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PercolationStats {
    private double[] value;

    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N 和 T 必须是正整数");
        }
        value=new double [T];
        for (int i=0;i<T;i++) {
            Percolation a = pf.make(N);
            List<Integer> b= new ArrayList<>();
            for (int j=0;j<N*N;j++){
                b.add(j);
            }
            Collections.shuffle(b);
            int x=0;
            while (!a.percolates() && x<b.size()) {
                a.open((b.get(x))/N, (b.get(x))%N);
                x++;
            }
            value[i]=(double)a.numberOfOpenSites()/(N*N);
        }
    }
    public double mean(){
        return StdStats.mean(value);
    }
    public double stddev(){
        return StdStats.stddev(value);
    }
    public double confidenceLow(){
        return mean()-1.96*stddev()/Math.sqrt(value.length);
    }
    public double confidenceHigh(){
        return mean()+1.96*stddev()/Math.sqrt(value.length);
    }
}
