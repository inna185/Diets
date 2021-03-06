package com.example.kursach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.model.User;

import java.sql.SQLException;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    EditText login, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        login = (EditText) findViewById(R.id.edLoginForL);
        password = (EditText) findViewById(R.id.edPasswordForL);
    }

    @Override
    public void onClick(View v) {
        A: switch(v.getId()){
            case R.id.login:
                if(login.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Intent intent = new Intent(this, Admin.class);
                    startActivity(intent);
                }
                if (login.getText().length()!=0 && password.getText().length()!=0) {
                    try {
                        HelperFactory.getHelper().getUserDAO().getAllUsers();
                        for (User user : HelperFactory.getHelper().getUserDAO().getAllUsers()){
                            if(user.getLogin().equals(login.getText().toString())){
                                Log.d("Register", " etLogin " + login.getText().toString());

                                if(user.getPassword().equals(password.getText().toString()))
                                {
                                    Log.d("Register", " pass " + password.getText().toString());

                                    MainPage.user = user;
                                    Intent intent = new Intent(this, MainPage.class);
                                    startActivity(intent);
                                    break A;
                                }
                                else {
                                    Log.d("Register", " passU " + user.getPassword().toString());
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Неверный пароль!", Toast.LENGTH_SHORT);
                                toast.show();
                                break A;
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
