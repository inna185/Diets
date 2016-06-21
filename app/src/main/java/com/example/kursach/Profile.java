package com.example.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kursach.orm.HelperFactory;

import java.sql.SQLException;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText age;
    private EditText width;
    private EditText growth;
    private EditText login;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = (EditText) findViewById(R.id.uName);
        age = (EditText) findViewById(R.id.uAge);
        width = (EditText) findViewById(R.id.uWeight);
        growth = (EditText) findViewById(R.id.uGrowth);
        login = (EditText) findViewById(R.id.uLogin);
        password = (EditText) findViewById(R.id.uPassword);

        name.setText(MainPage.user.getName().toString());
        age.setText(MainPage.user.getAge().toString());
        if (MainPage.user.getWeight() != null) {
            width.setText(MainPage.user.getWeight().toString());
        }
        if (MainPage.user.getGrowth() != null) {
            growth.setText(MainPage.user.getGrowth().toString());
        }
        login.setText(MainPage.user.getLogin().toString());
        password.setText(MainPage.user.getPassword().toString());
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.update:
                MainPage.user.setName(name.getText().toString());
                MainPage.user.setAge(age.getText().toString());
                MainPage.user.setPassword(password.getText().toString());
                MainPage.user.setGrowth(growth.getText().toString());
                MainPage.user.setWeight(width.getText().toString());
                MainPage.user.setLogin(login.getText().toString());

                try {
                    HelperFactory.getHelper().getUserDAO().updateUser(MainPage.user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Профиль обновлен!", Toast.LENGTH_SHORT);
                toast.show();
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
            case R.id.diet:
                intent = new Intent(this, Diets.class);
                startActivity(intent);
                break;
            case R.id.home:
                intent = new Intent(this, MainPage.class);
                startActivity(intent);
                break;
        }
    }
}
