package com.example.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kursach.adapters.AdapterForDiet;
import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.model.Diet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DietActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        String idDiet = getIntent().getStringExtra("idNote");
        Diet diet = null;
        try {
            diet = HelperFactory.getHelper().getDietDAO().getDietByNeuron(idDiet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TextView n = (TextView) findViewById(R.id.named);
        TextView d = (TextView) findViewById(R.id.dd);
        TextView kg = (TextView) findViewById(R.id.kg);
        TextView sr = (TextView) findViewById(R.id.sr);

        n.setText(diet.getName());
        d.setText(diet.getDescription());
        kg.setText(diet.getWeight());
        sr.setText(diet.getTime());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.br) {
            Intent intent = new Intent(this, Diets.class);
            startActivity(intent);
        }
    }
}
