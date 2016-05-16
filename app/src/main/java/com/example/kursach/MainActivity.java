package com.example.kursach;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.kursach.orm.Diet;
import com.example.kursach.orm.DietDAO;
import com.example.kursach.orm.HelperFactory;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button b, b2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        b = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        b.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Diet diet = new Diet();
                diet.setName("Цветная диета");
                diet.setTime("2 недели");
                diet.setWeight("15 кг");
                diet.setDescription("бла бла бла");
                try {
                    DietDAO dietDAO = HelperFactory.getHelper().getDietDAO();
                    dietDAO.create(diet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            case R.id.button2:
                try {
                    DietDAO dietDAO = HelperFactory.getHelper().getDietDAO();
                    List<Diet> diet2 = dietDAO.getAllDiets();
                    textView.setText(diet2.get(0).getDescription());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
