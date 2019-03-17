package com.example.m;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ShowItem extends AppCompatActivity {
    TextView txt_name ,txt_price , quantityView ;
    Button add,sub ;
    ImageView img_card ;
    FloatingActionButton addToCart ,shoppingCart ;
    Intent card_gro ;
    int quantity=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        txt_name = (TextView)findViewById(R.id.cart_gro_name);
        txt_price = (TextView)findViewById(R.id.cart_gro_price);
        quantityView = (TextView)findViewById(R.id.quantityProductPage);
        quantityView.setText(String.valueOf(quantity));
        img_card = (ImageView)findViewById(R.id.profileimage);
        card_gro = getIntent() ;
        txt_name.setText(card_gro.getExtras().getString("name").trim());
        txt_price.setText(card_gro.getExtras().getString("price").trim());
        img_card.setImageResource(card_gro.getExtras().getInt("image"));
        add = (Button) findViewById(R.id.incrementQuantity);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment();
            }
        });

        sub = (Button) findViewById(R.id.decrementQuantity);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrement();
            }
        });
        addToCart = (FloatingActionButton) findViewById(R.id.addToCartProductPage);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShowItem.this, "ADD TO Card", Toast.LENGTH_LONG).show();
                SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                String userId = shared.getString("id","123456789");
                shared.edit().putInt("count",quantity);
                String pro_name = card_gro.getExtras().getString("name").trim();
                String pro_price = card_gro.getExtras().getString("price").trim();
                int image_pro = card_gro.getExtras().getInt("image");
                send_to_card(userId,pro_name,pro_price,quantity,image_pro);

            }
        });
        shoppingCart = (FloatingActionButton) findViewById(R.id.cartProductPage);
        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent card = new Intent(ShowItem.this,Card.class);
                startActivity(card);
            }
        });
    }

    void increment(){
        if (quantity < 5){
            quantity++;
            quantityView.setText(String.valueOf(quantity));
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Limit of 5 products only",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    void decrement(){
        if (quantity > 1){
            quantity--;
            quantityView.setText(String.valueOf(quantity));
        }
    }

    public void send_to_card ( final String userId, final String pro_name,final String pro_price,final int count,final int image_pro){
        String url = "https://mindxx.000webhostapp.com/fakahany/temp.php?userId="+userId+
                "&pro_name="+pro_name+"&pro_price="+pro_price+"&count="+count+"&image_pro="+image_pro+"";

        RequestQueue requestQueue = Volley.newRequestQueue(ShowItem.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Hitesh", "" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Hitesh", "" + error);
                Toast.makeText(ShowItem.this, "error" + error, Toast.LENGTH_SHORT).show();

            }
        }) ;

        requestQueue.add(stringRequest);

    }

}