package com.example.kursach.neural;

/**
 * Created by apple on 02.06.2016.
 */
public class DataNormalization {

    public static double normalizeMassIndex(double index){
        return (index-16)/24;
    }

    public static double normalizePeriod(double period){
        return (period-1)/7;
    }

    public static double normalizeWeight(double weight){
        return (weight-2)/18;
    }

    public static double getMassIndex(int weight, int growth){
        return weight/(growth*growth/10000);
    }
}
