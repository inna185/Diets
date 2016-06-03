package com.example.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.model.Diet;

import java.sql.SQLException;

public class Admin extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.save:
                EditText name = (EditText) findViewById(R.id.aName);
                EditText time = (EditText) findViewById(R.id.aNeuron);
                EditText desc = (EditText) findViewById(R.id.aDescription);
                EditText width = (EditText) findViewById(R.id.aWidth);
                EditText neuron = (EditText) findViewById(R.id.aNeuron);
                Diet diet = new Diet(
                        name.getText().toString(),
                        time.getText().toString(),
                        width.getText().toString(),
                        desc.getText().toString(),
                        neuron.getText().toString()
                );

                try {
                    HelperFactory.getHelper().getDietDAO().createDiet(diet);
                    startAct();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.exitAdm:
                startAct();
                break;
        }
    }

    private void startAct() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
