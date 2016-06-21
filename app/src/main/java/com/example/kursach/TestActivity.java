package com.example.kursach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.kursach.neural.DataNormalization;
import com.example.kursach.neural.Perseptron;
import com.example.kursach.neural.activation.SigmoidFunc;
import com.example.kursach.neural.teacher.Teacher;
import com.example.kursach.neural.teacher.TeacherImpl;
import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.model.Diet;

import java.sql.SQLException;

/**
 * Created by 1 on 03.06.2016.
 */
public class TestActivity extends Activity implements View.OnClickListener{
    EditText etWeight, etGrouth, etGoal, etTime;
    Button btnResult;
    RadioButton rbYes, rbNo;
    double severity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etGrouth = (EditText) findViewById(R.id.etGrouth);
        etGoal = (EditText) findViewById(R.id.etGoal);
        etTime = (EditText) findViewById(R.id.etTime);
        btnResult = (Button) findViewById(R.id.btnResult);

        rbYes = (RadioButton) findViewById(R.id.rbYes);
        rbNo = (RadioButton) findViewById(R.id.rbNo);

        btnResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnResult:
                Perseptron perseptron = new Perseptron(new SigmoidFunc(),4,9,9);
                Teacher teacher = new TeacherImpl(perseptron);
                teacher.education();
                Integer weight = Integer.valueOf(etWeight.getText().toString());
                Integer grouth = Integer.valueOf(etGrouth.getText().toString());
                Double massIndex = DataNormalization.normalizeMassIndex(DataNormalization.getMassIndex(weight, grouth));
                Double goal = DataNormalization.normalizeWeight(Double.valueOf(etGoal.getText().toString()));
                Double time = DataNormalization.normalizePeriod(Double.valueOf(etTime.getText().toString()));
                double[] d = {massIndex, time, goal, severity};
                Integer winner = perseptron.findMax(perseptron.process(d));
                try {
                    Diet diet = HelperFactory.getHelper().getDietDAO().getDietByNeuron(winner.toString()).get(0);
                    MainPage.user.setDiet(diet);
                    MainPage.user.setWeight(etWeight.getText().toString());
                    MainPage.user.setGrowth(etGrouth.getText().toString());
                    Intent intent = new Intent(this, MainPage.class);
                    startActivity(intent);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rbYes: severity = 1; break;
            case R.id.rbNo: severity = 0; break;
        }
    }
}