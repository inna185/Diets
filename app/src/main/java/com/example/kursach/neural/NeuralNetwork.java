package com.example.kursach.neural;

public class NeuralNetwork {

    double[] inputLayer;
    double[] outputLayer;
    double[][] weight = {{0.2,0.2,0.3},
            {0.2,0.8,0.7},
            {1,1,1},
            {0.9,0.8,0.7},
            {0.1,0.2,0.7},
            {0.5,0.5,0.4},
            {0.4,0.5,0.6},
            {0.2,0.9,0.1},
            {0.8,0.2,0.7}
    };
    double[][] trainingInput = {{0, 0, 0},
            {0.05, 0, 0},
            {0.08, 0, 0},
            {0.12, 0, 0},
            {0.12, 0.12, 0.16},
            {0.16, 0, 0},
            {0.16, 0.12, 0.16},
            {0.16, 0.12, 0.33},
            {0.2, 0, 0},
            {0.2, 0.12, 0.16},
            {0.2, 0.12, 0.33},
            {0.24, 0, 0},
            {0.24, 0.12, 0.16},
            {0.24, 0.12, 0.33},
            {0.28, 0, 0},
            {0.28, 0.12, 0.16},
            {0.28, 0.12, 0.33},
            {0.32, 0, 0},
            {0.32, 0.12, 0.16},
            {0.32, 0.12, 0.33},
            {0.36, 0, 0},
            {0.36, 0.12, 0.16},
            {0.36, 0.12, 0.33},
            {0.41, 0.12, 0.16},
            {0.41, 0.12, 0.33},
            {0.41, 0.28, 0.5},
            {0.45, 0.12, 0.16},
            {0.45, 0.12, 0.33},
            {0.45, 0.28, 0.5},
            {0.49, 0.12, 0.16},
            {0.49, 0.12, 0.33},
            {0.49, 0.28, 0.5},
            {0.53, 0.12, 0.16},
            {0.53, 0.12, 0.33},
            {0.53, 0.28, 0.5},
            {0.57, 0.12, 0.16},
            {0.57, 0.12, 0.33},
            {0.57, 0.28, 0.5},
            {0.62, 0.12, 0.33},
            {0.62, 0.28, 0.5},
            {0.62, 0.42, 0.75},
            {0.64, 0.12, 0.33},
            {0.64, 0.28, 0.5},
            {0.64, 0.42, 0.75},
            {0.68, 0.12, 0.33},
            {0.68, 0.28, 0.5},
            {0.68, 0.42, 0.75},
            {0.72, 0.12, 0.33},
            {0.72, 0.28, 0.5},
            {0.72, 0.42, 0.75},
            {0.76, 0.12, 0.33},
            {0.76, 0.28, 0.5},
            {0.76, 0.42, 0.75},
            {0.83, 0.28, 0.5},
            {0.83, 0.42, 0.75},
            {0.83, 0.85, 0.88},
            {0.83, 1, 1},
            {0.87, 0.28, 0.5},
            {0.87, 0.42, 0.75},
            {0.87, 0.85, 0.88},
            {0.87, 1, 1},
            {0.92, 0.28, 0.5},
            {0.92, 0.42, 0.75},
            {0.92, 0.85, 0.88},
            {0.92, 1, 1},
            {0.96, 0.28, 0.5},
            {0.96, 0.42, 0.75},
            {0.96, 0.85, 0.88},
            {0.96, 1, 1},
            {1, 0.28, 0.5},
            {1, 0.42, 0.75},
            {1, 0.85, 0.88},
            {1, 1, 1}};

    public NeuralNetwork(){
        inputLayer = new double[trainingInput[0].length];
        outputLayer = new double[9];
    }

    public void init(){
        for (int i=0; i<weight.length; i++){
            for (int j=0; j<weight[i].length; j++){
                weight[i][j] = Math.random()*0.2+0.2;
            }
        }
    }

    public void study(){
        //init();
        double v = 0.3;
        for(int i=0; i<6; i++){

            for(int j=0; j<trainingInput.length; j++){
                studyOne(trainingInput[j], v);
            }
            //v-=0.01;
        }

    }

    public void studyOne(double[] t, double v){
        double res=0;
        for (int j=0; j<t.length; j++){
            res += (t[j]-weight[0][j])*(t[j]-weight[0][j]);
        }

        res = Math.sqrt(res);
        int winner=0;
        double res2;

        for (int i=0; i<outputLayer.length; i++){
            res2=0;
            for (int j=0; j<t.length; j++){
                res2 += (t[j]-weight[i][j])*(t[j]-weight[i][j]);
            }
            res2 = Math.sqrt(res2);
            if (res2<res) {res=res2; winner=i;}
        }

        for (int i=0; i<weight[winner].length; i++){
            weight[winner][i] = weight[winner][i]+v*(t[i]-weight[winner][i]);
        }
    }

    public int getWinner(double[] t){
        double res=0;
        for (int j=0; j<t.length; j++){
            res += (t[j]-weight[0][j])*(t[j]-weight[0][j]);
        }
        res = Math.sqrt(res);
        int winner=0;
        double res2;

        for (int i=0; i<outputLayer.length; i++){
            res2=0;
            for (int j=0; j<t.length; j++){
                res2 += (t[j]-weight[i][j])*(t[j]-weight[i][j]);
            }
            res2 = Math.sqrt(res2);
            if (res2<res) {res=res2; winner=i;}
        }
        if (t[2]>=0 && t[2] <0.16) winner = 0;
        if (t[2]>=0.16 && t[2] < 0.33) winner = 1;
        if (t[2]>=0.33 && t[2] <0.45) winner = 7;
        return winner;
    }
}



