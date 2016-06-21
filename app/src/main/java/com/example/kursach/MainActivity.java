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

import com.example.kursach.orm.HelperFactory;
import com.example.kursach.orm.model.Diet;

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

        Diet diet = new Diet("Протасова",
               "8 недель",
                "10-20 кг",
                "Диета Кима Протасова построена по принципу низкокалорийной диеты, но хитрого устройства. Среднесуточное меню на этой диете обеспечивает организм энергией в количестве приблизительно 1200-1500 калорий. Рацион состоит из большого количества растительной клетчатки (некрахмалистые овощи и разрешенные фрукты), а также из белка (первые две недели протеин поступает из молочных продуктов и отварного яйца, позже добавляется мясо",
                "2");
        Diet diet1 = new Diet("Гречневая",
               "1-2 недели",
                "5-10кг",
                "В течении дня вы не едите ничего, кроме гречи, приготовленной на воде. Диетическая норма - 1 стакан гречи, этот объем нужно съесть в течении дня небольшими порциями. Но она не является строгой - если вам мало, сделайте еще такую же порцию. .",
                "1");
        Diet diet2 = new Diet("Цветная диета",
               "1 неделя",
                "2кг",
                "Свое название цветная диета получила по градации потребляемых продуктов по их цвету. Предполагается что, разделив все продукты по дням недели и потребляя их с большим временным промежутком, чем в раздельном питании можно привести свой вес к нормальному.",
                "0");
        Diet diet3 = new Diet("Английская диета ",
               "3 недели",
                "10кг",
                "Основные трудности при английской диете возникают в первые три дня диеты, которые кажутся очень голодными. Но если вы  выдержите их, то в дальнейшем диета достаточно легко переносится и некоторые блюда, особенно после голодных дней, даже могут показаться чрезмерно сытными.",
                "5");
        Diet diet4 = new Diet("Японская диета",
               "4 недели",
                "15кг",
                "Куриные яйца — основной продукт в первые две недели диеты, следующие две недели направлены на закрепление результата (меню преимущественно низкоуглеводное растительное с небольшими порциями белка)",
                "6");
        Diet diet5 = new Diet("Немецкая диета ",
               "7 недели",
                "18кг",
                "Все продукты должны быть свежими и натуральными. В сутки нельзя употреблять более 1600 ккал – это действительно немного. Категорически запрещено спиртное – даже слабоалкогольные напитки, и вообще пить можно только негазированную воду – чай и кофе исключаются.",
                "2");
        Diet diet6 = new Diet("Диета Магги ",
               "4 недели",
                "15кг",
                "Режим питания при этой диете опирается не на калорийность продуктов, а на химические реакции, которые протекают в нашем организме. Режим питания следует соблюдать строго, иначе данная диета не принесет никакой пользы.",
                "8");
        Diet diet7 = new Diet("Китайская диета",
               "3 недели",
                "10кг",
                "В основе китайской диеты заложена низкая калорийность употребляемых продуктов, а во вторых она очень жёсткая, поэтому ведущими диетологами рекомендуется пить по одному литру в сутки кипячённой или минеральной воды без газа. ",
                "4");
        Diet diet8 = new Diet("Японская диета",
               "2 недели",
                "7кг",
                "Основное насыщающее вещество в японской диете — белок, получаемый с куриными яйцами, курицей, говядиной, рыбой и молочными продуктами. Углеводы представлены в сухариках и некоторых из разрешенных овощей, жиры — в оливковом масле, которое разрешается использовать для приготовления пищи и заправки салата, а также в составе мяса и рыбы.",
                "7");

        try {
            HelperFactory.getHelper().getDietDAO().createDiet(diet);
            HelperFactory.getHelper().getDietDAO().createDiet(diet1);
            HelperFactory.getHelper().getDietDAO().createDiet(diet2);
            HelperFactory.getHelper().getDietDAO().createDiet(diet3);
            HelperFactory.getHelper().getDietDAO().createDiet(diet4);
            HelperFactory.getHelper().getDietDAO().createDiet(diet5);
            HelperFactory.getHelper().getDietDAO().createDiet(diet6);
            HelperFactory.getHelper().getDietDAO().createDiet(diet7);
            HelperFactory.getHelper().getDietDAO().createDiet(diet8);
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
