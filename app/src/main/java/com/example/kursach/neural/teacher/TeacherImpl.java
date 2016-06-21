package com.example.kursach.neural.teacher;


import com.example.kursach.neural.Perseptron;

/**
 * Created by FSkulll on 15.05.2016.
 */
public class TeacherImpl implements Teacher {
    private Perseptron perseptron;
    private double[][] patterns = {
            {0,0,0,0},
            {0.12,0.12,0.12,1},
            {0.16,0.12,0.33,1},
            {0.16,0.12,0.33,0},
            {0.2,0,0.33, 0},
            {0.3,0.24,0.4,0},
            {0.35,0.24,0.4,1},
            {0.4,0.4,0.6,0},
            {0.4,0.4,0.7,1},
            {0.55,0.7,0.8,0},
            {0.7,0.8,1,1},
            {1,1,1,1}
    };
    private double[][] answers = {
            {1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0}
    };

    public TeacherImpl(Perseptron perseptron) {
        this.perseptron = perseptron;
    }

    @Override
    public void education() {
        perseptron.education(patterns,answers);
    }
}
