package com.example.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.model.User;

import java.sql.SQLException;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    EditText login, password;
    Button btnRegister, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        login = (EditText) findViewById(R.id.edLoginForL);
        password = (EditText) findViewById(R.id.edPasswordForL);

        btnRegister = (Button) findViewById(R.id.login);
        btnRegister.setOnClickListener(this);

        btnCancel = (Button) findViewById(R.id.cancelLogIn);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login:
                if (login.getText().length()!=0 && password.getText().length()!=0) {
                    try {
                        HelperFactory.getHelper().getUserDAO().getAllUsers();
                        for (User user : HelperFactory.getHelper().getUserDAO().getAllUsers()){
                            if(user.getLogin().equals(login.getText().toString())){
                                if(user.getPassword().equals(password.getText().toString()))
                                {
                                    Intent intent = new Intent(this, MainActivity.class);
                                    intent.putExtra("userId", user.getId());
                                    startActivity(intent);
                                    break;
                                }
                                else {
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Неверный пароль!", Toast.LENGTH_SHORT);
                                toast.show();
                                break;
                                }
                            }
                        }

                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Пользователь с таким логином не существует!", Toast.LENGTH_SHORT);
                        toast.show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите все данные!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.cancelLogIn:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
