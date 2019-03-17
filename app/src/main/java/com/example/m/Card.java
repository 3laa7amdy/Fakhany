package com.example.m;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Card extends AppCompatActivity {
    GridView listView;
    RequestQueue requestQueue;
    SharedPreferences shared;
    ArrayList<Model> card_listitems = new ArrayList<Model>();

    String userId;
    Intent card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        listView = (GridView) findViewById(R.id.card_list);
        getData();
    }

    public void getData() {
        SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        userId = shared.getString("id", "123456789");
        String uri_getData = "https://mindxx.000webhostapp.com/fakahany/temp_show.php?username=" + userId + "";
        requestQueue = Volley.newRequestQueue(this);
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, uri_getData,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String id = object.getString("id");
                                String user_id = object.getString("user_id");
                                String pro_name = object.getString("pro_name");
                                String pro_price = object.getString("pro_price");
                                String count = object.getString("count");
                                String image = object.getString("image_pro");


                                card_listitems.add(new Model(id, pro_name, Integer.parseInt(image), pro_price, count));
                                listAllitem();

                            }
                        } catch (JSONException e) {
                            Toast.makeText(Card.this, "line 80" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", "ERROR");

            }


        });

        requestQueue.add(jsonObjectRequest);


    }

    public void send_to_card(final String userId, final String pro_name, final String pro_price, final int count, final int image_pro) {
        String url = "https://mindxx.000webhostapp.com/fakahany/temp.php?userId=" + userId +
                "&pro_name=" + pro_name + "&pro_price=" + pro_price + "&count=" + count + "&image_pro=" + image_pro + "";

        RequestQueue requestQueue = Volley.newRequestQueue(Card.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Hitesh", "" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Hitesh", "" + error);
                Toast.makeText(Card.this, "error" + error, Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);

    }

    public void Delete(final String id) {
        String delete = "https://mindxx.000webhostapp.com/fakahany/DeleteProduct.php";
        RequestQueue requestQueue = Volley.newRequestQueue(Card.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, delete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Hitesh", "" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Hitesh", "" + error);
                Toast.makeText(Card.this, "" + error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mapData = new HashMap<>();
                mapData.put("user_id", id);

                return mapData;
            }
        };

        requestQueue.add(stringRequest);


    }

    public void send_to_pur(final String username, final String pro_id,
                            final String pro_name, final String pro_price, final String count, final int image_pro) {
        String url = "https://mindxx.000webhostapp.com/fakahany/ord.php";
        RequestQueue requestQueue = Volley.newRequestQueue(Card.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Hitesh", "" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Hitesh", "" + error);
                Toast.makeText(Card.this, "" + error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mapData = new HashMap<>();
                mapData.put("username", username);
                mapData.put("pro_id", pro_id);
                mapData.put("pro_name", pro_name);
                mapData.put("pro_price", pro_price);
                mapData.put("count", count);
                mapData.put("pro_image", String.valueOf(image_pro));
                return mapData;
            }
        };

        requestQueue.add(stringRequest);

    }
    public void listAllitem () {

        BaseAdapter Al = new BaseAdpter(card_listitems);
        listView.setAdapter(Al);


    }
    class BaseAdpter extends BaseAdapter {
        ArrayList<Model> list = new ArrayList<Model>();

        public BaseAdpter(ArrayList<Model> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i).name;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.row_item_card, null);
            TextView textname = (TextView) view1.findViewById(R.id.row_shopcard_name);
            TextView textprice = (TextView) view1.findViewById(R.id.row_shopcard_price);
            TextView txt_pro = (TextView) view1.findViewById(R.id.row_shopcard_count);
            ImageButton confrim = (ImageButton) view1.findViewById(R.id.card_confrim);
            final ImageButton delete = (ImageButton) view1.findViewById(R.id.card_delete);
            ImageView imageView = (ImageView) view1.findViewById(R.id.image);


            textname.setText(list.get(i).name);
            textprice.setText(list.get(i).text2);
            txt_pro.setText(list.get(i).Count);
            imageView.setImageResource(list.get(i).image);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Delete(list.get(i).id);
                    finish();
                    startActivity(getIntent());

                }
            });

            confrim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                    String username = shared.getString("username", "non");
                    String pro_id = list.get(i).id;
                    String pro_name = list.get(i).name;
                    String pro_price = list.get(i).text2;
                    String count = list.get(i).Count;
                    int pro_imge = list.get(i).image;
                    send_to_pur(username, pro_id, pro_name, pro_price, count, pro_imge);
                    Delete(pro_id);
                    //Toast.makeText(Card.this, "", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                }
            });

            return view1;
        }
    }
}

