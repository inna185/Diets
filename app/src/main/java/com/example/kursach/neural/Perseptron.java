package com.example.kursach.neural;


import com.example.kursach.neural.activation.ActivationFunc;

/**
 * Created by FSkulll on 19.05.2016.
 */
public class Perseptron {

    private double[][][] weights;
    private double[][] layers;
    private double[][] errors;
    private ActivationFunc activationFunc;

    public Perseptron(ActivationFunc activation,int...n) {
        layers = new double[n.length][];
        for(int i = 0; i< layers.length; i++){
            layers[i] = new double[n[i]];
        }
        this.activationFunc = activation;
        initWeights(n);
    }

    private void initWeights(int...n){
        weights = new double[n.length - 1][][];

        for (int i = 0; i < weights.length; i++)
        {
            weights[i]=new double[n[i+1]][];

            for(int j = 0; j < weights[i].length;j++)
            {
                weights[i][j] = new double[n[i]];

                for(int k = 0; k < weights[i][j].length;k++)
                {
                    weights[i][j][k] = Math.random() - 0.5;
                }
            }
        }
        errors = new double[n.length][];
        for(int i = 0; i < n.length;i++)
        {
            errors[i] = new double[n[i]];
        }
    }

    private  void summator(int layer, int neuron){
        double sum = 0;

        for (int i = 0; i < layers[layer-1].length; i++)
        {
            sum+= layers[layer-1][i] * weights[layer-1][neuron][i];
        }

        layers[layer][neuron] = activationFunc.process(sum);
    }

    private double getDerivative(int layer,int neuron){
        double sum = 0;
        double[] inputLinks=weights[layer-1][neuron];
        for(int i = 0;i<inputLinks.length;i++)
        {
            sum+=inputLinks[i]*layers[layer-1][i];
        }

        return activationFunc.process(sum)*(1-activationFunc.process(sum));
    }

    public double[] process(double[] input) {
        layers[0] = input.clone();
        for(int i = 1; i < layers.length;i++)
        {
            for(int j = 0; j < layers[i].length;j++)
            {
                summator(i,j);
            }
        }
        return layers[layers.length-1].clone();
    }

    public int findMax(double[] input){
        int max = 0;
        double temp = 0;
        for (int i=0; i<input.length; i++){
            if (temp<input[i]){max=i; temp=input[i];}
        }
        return max;
    }

    public void education(double[][] patterns, double[][] answers) {
        for(int i = 0;i < 1000; i++)
        {
            for(int j=0; j < patterns.length;j++)
            {
                study(patterns[j], answers[j]);
            }
        }
    }

    private void study(double[] input, double[] answer) {

        double[] actualOutput = process(input);
        double[] errors = new double[actualOutput.length];
        for (int i = 0; i < errors.length; i++)
        {
            errors[i] = (answer[i] - actualOutput[i]) * getDerivative(2,i);
            this.errors[2][i] = errors[i];
            double[] inputLinks = weights[1][i];

            for(int j = 0; j < inputLinks.length; j++)
            {
                weights[1][i][j] = weights[1][i][j] + errors[i] * layers[1][j] * 0.1;
            }
        }

        double[] hiddenLayer = layers[1];

        for(int i = 0; i < hiddenLayer.length; i++)
        {
            double errorSum = 0;

            for (int j = 0; j < layers[2].length; j++)
            {
                errorSum += weights[1][j][i] * this.errors[2][j];
            }

            double error = errorSum * getDerivative(1, i);
            for(int j = 0; j < layers[0].length; j++)
            {
                weights[0][i][j] = weights[0][i][j] + error * layers[0][j] * 0.1;
            }

        }
    }


}
