package com.example.kursach.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.kursach.DietActivity;
import com.example.kursach.Diets;
import com.example.kursach.R;
import com.example.kursach.orm.model.Diet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 22.06.2016.
 */
public class AdapterForDiet extends BaseAdapter{
        Context ctx;
        LayoutInflater lInflater;
        List<Diet> objects;

        public AdapterForDiet(Context context, ArrayList<Diet> notes) {
            ctx = context;
            objects = notes;
            lInflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public Object getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // используем созданные, но не используемые view
            View view = convertView;
            if (view == null) {
                view = lInflater.inflate(R.layout.list_diets, parent, false);
            }

            final Diet p = getNote(position);

            // заполняем View в пункте списка данными из товаров: наименование, цена
            // и картинка
            ((TextView) view.findViewById(R.id.h)).setText(p.getName());
            ((TextView) view.findViewById(R.id.d)).setText(p.getWeight());
            ((Button) view.findViewById(R.id.upNote)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, DietActivity.class);
                    intent.putExtra("idNote", p.getNeuron());
                    ctx.startActivity(intent);
                }
            });



            return view;
        }

        // товар по позиции
        Diet getNote(int position) {
            return ((Diet) getItem(position));
        }

}
