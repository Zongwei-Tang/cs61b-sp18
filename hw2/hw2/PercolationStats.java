package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] value;
    public PercolationStats(int N, int T, PercolationFactory pf){
        Percolation a=pf.make(N);
        value=new double [T];
        for (int i=0;i<T;i++) {
            while (!a.percolates()) {
                a.open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
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
