package com.example.m;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class Fruit extends AppCompatActivity {
    ArrayList<Model> arrayList3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        arrayList3 = new ArrayList<>();
        arrayList3.add(new Model("برتقال",R.drawable.a2,"50p"));
        arrayList3.add(new Model("برقوق",R.drawable.a3,"50p"));
        arrayList3.add(new Model("تفاح_احمر",R.drawable.a4,"50p"));
        arrayList3.add(new Model("تفاح_اخضر",R.drawable.a5,"50p"));
        arrayList3.add(new Model("تفاح_اصفر",R.drawable.a6,"50p"));
        arrayList3.add(new Model("جوافه",R.drawable.a7,"50p"));
        arrayList3.add(new Model("رمان",R.drawable.a8,"50p"));
        arrayList3.add(new Model("كمثري",R.drawable.a9,"50p"));
        arrayList3.add(new Model("كيوي",R.drawable.a10,"50p"));
        arrayList3.add(new Model("مانجو",R.drawable.a11,"50p"));
        arrayList3.add(new Model("موز",R.drawable.a12,"50p"));
        arrayList3.add(new Model("يوسفي",R.drawable.a13,"50p"));

        GridView gridView = (GridView) findViewById(R.id.grid2);
        AdapterClass myAdapter =new AdapterClass(this,arrayList3);

        gridView.setAdapter(myAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent card_gro = new Intent(Fruit.this, ShowItem.class);
                card_gro.putExtra("name",arrayList3.get(position).name);
                card_gro.putExtra("price",arrayList3.get(position).text2);
                card_gro.putExtra("image",arrayList3.get(position).image);
                startActivity(card_gro);
            }
        });

    }
}
