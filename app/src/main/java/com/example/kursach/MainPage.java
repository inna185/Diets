package com.example.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.model.User;

import java.sql.SQLException;

public class MainPage extends AppCompatActivity implements View.OnClickListener {
    public static User user;
    private TextView tvHeader, tvDescription, tvDescription2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        tvHeader = (TextView) findViewById(R.id.tvHeader);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvDescription2 = (TextView) findViewById(R.id.tvDescription2);
        tvDescription2.setMovementMethod(new ScrollingMovementMethod());
        tvHeader.setText("Здравствуйте, "+user.getName().toString());
        if (user.getDiet() == null)
        tvDescription.setText("Для того, чтобы определить подходящую для Вас диету, пройдите тест!");
        else {
            tvDescription.setText("Подобранная для Вас диета: " + user.getDiet().getName());
            tvDescription2.setText(user.getDiet().getDescription());
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.exit:
                try {
                    HelperFactory.getHelper().getUserDAO().updateUser(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                user = null;
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.diet:
                intent = new Intent(this, Diets.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(this, Profile.class);
                startActivity(intent);
                break;
        }
    }
}
