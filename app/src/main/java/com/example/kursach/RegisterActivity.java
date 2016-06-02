package com.example.kursach;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.User;

import java.sql.SQLException;

/**
 * Created by 1 on 02.06.2016.
 */
public class RegisterActivity extends Activity implements View.OnClickListener{
    EditText etlogin, etpassword, etname;
    TextView textView;
    Button btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        etlogin = (EditText) findViewById(R.id.etlogin);
        etpassword = (EditText) findViewById(R.id.etpassword);
        etname = (EditText) findViewById(R.id.etname);
        textView = (TextView) findViewById(R.id.tvloginn);
        btnregister = (Button) findViewById(R.id.btnregister);

        btnregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnregister:
                if (etlogin.getText().length()!=0 && etpassword.getText().length()!=0 && etname.getText().length()!=0) {
                    User user = new User();
                    user.setLogin(etlogin.getText().toString());
                    user.setPassword(etpassword.getText().toString());
                    user.setName(etname.getText().toString());
                    try {
                        HelperFactory.getHelper().getUserDAO().createUser(user);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите все данные!", Toast.LENGTH_SHORT);
                    toast.show();
                }
        }
    }
}