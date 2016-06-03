package com.example.kursach;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.kursach.orm.DatabaseHelper;
import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.model.User;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnlogin, btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        setSupportActionBar(toolbar);

        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnreg = (Button) findViewById(R.id.btnreg);

        btnreg.setOnClickListener(this);
        btnlogin.setOnClickListener(this);

        try {
            for (User user1 : HelperFactory.getHelper().getUserDAO().getAllUsers()){
                if(!"admin".equals(user1.getLogin())){
                    User user = new User();
                    user.setLogin("admin");
                    user.setPassword("admin");
                    HelperFactory.getHelper().getUserDAO().createUser(user);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.btnreg:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btnlogin:
                intent = new Intent(this, LogIn.class);
                startActivity(intent);
                break;
        }
    }
}
