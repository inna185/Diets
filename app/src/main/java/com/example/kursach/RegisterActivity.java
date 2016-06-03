package com.example.kursach;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.model.User;

import java.sql.SQLException;

public class RegisterActivity extends Activity implements View.OnClickListener{
    EditText etLogin, etPassword, etName, etAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.reg:
                Log.d("Register", " Click reg");
                if (etAge.getText().toString().length()>0 && Integer.valueOf(etAge.getText().toString()) < 0 ||
                        etAge.getText().toString().length()>0 && Integer.valueOf(etAge.getText().toString()) > 80){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Возраст неверен!", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }
                if (etLogin.getText().length()!=0 && etPassword.getText().length()!=0 && etName.getText().length()!=0) {
                    User user = new User();
                    user.setLogin(etLogin.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    user.setName(etName.getText().toString());
                    user.setAge(etAge.getText().toString());

                    Log.d("Register", " etLogin " + etLogin.getText().toString());

                    try {
                        HelperFactory.getHelper().getUserDAO().getAllUsers();
                        for (User user1 : HelperFactory.getHelper().getUserDAO().getAllUsers()){
                            if(user.getLogin().equals(user1.getLogin())){
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Пользователь с таким логином уже существует!", Toast.LENGTH_SHORT);
                                toast.show();
                                break;
                            }
                        }
                        HelperFactory.getHelper().getUserDAO().createUser(user);
                        Log.d("Register", "Reg success");
                        MainPage.user = user;
                        Intent intent = new Intent(this, TestActivity.class);
                        startActivity(intent);
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
            case R.id.cancelReg:
                Log.d("Register", " CLick cencal");

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}