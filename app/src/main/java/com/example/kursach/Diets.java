package com.example.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.kursach.adapters.AdapterForDiet;
import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.model.Diet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Diets extends AppCompatActivity implements View.OnClickListener {
    private List<Diet> diets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diets);

        try {
            diets = HelperFactory.getHelper().getDietDAO().getAllDiets();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        AdapterForDiet adapterNote = new AdapterForDiet(this, (ArrayList<Diet>) diets);
        listView.setAdapter(adapterNote);
        findViewById(R.id.exit).setOnClickListener(this);
        findViewById(R.id.home).setOnClickListener(this);
        findViewById(R.id.profile).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case  R.id.test:
                intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                try {
                    HelperFactory.getHelper().getUserDAO().updateUser(MainPage.user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                MainPage.user = null;
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(this, Profile.class);
                startActivity(intent);
                break;
            case R.id.home:
                intent = new Intent(this, MainPage.class);
                startActivity(intent);
                break;
        }
    }
}
