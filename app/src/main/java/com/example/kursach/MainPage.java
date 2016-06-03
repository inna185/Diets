package com.example.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.model.User;

import java.sql.SQLException;

public class MainPage extends AppCompatActivity implements View.OnClickListener {
    public static User user;
    private TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        editText = (TextView) findViewById(R.id.text);
        editText.setText(user.getName().toString());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.exit:
                user = null;
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
