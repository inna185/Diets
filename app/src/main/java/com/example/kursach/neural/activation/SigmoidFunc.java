package com.example.kursach.neural.activation;

import com.example.kursach.neural.activation.ActivationFunc;

/**
 * Created by FSkulll on 15.05.2016.
 */
public class SigmoidFunc implements ActivationFunc {
    @Override
    public double process(double value) {
        return 1/(1+Math.exp(-value));
    }
}
