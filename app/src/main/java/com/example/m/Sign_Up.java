package com.example.m;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Sign_Up extends AppCompatActivity {

    public static final String url = "https://mindxx.000webhostapp.com/fakahany/reg.php";
    EditText res_name, res_username, res_address, res_phone, res_email, res_password, res_re_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);


        res_name = (EditText) findViewById(R.id.res_name);
        res_username = (EditText) findViewById(R.id.res_username);
        res_email = (EditText) findViewById(R.id.res_email);
        res_address = (EditText) findViewById(R.id.res_address);
        res_phone = (EditText) findViewById(R.id.res_phoneunmber);
        res_password = (EditText) findViewById(R.id.res_password);
        res_re_password = (EditText) findViewById(R.id.res_re_password);
        Button btn_reg = (Button) findViewById(R.id.btn_signup);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = res_name.getText().toString().trim();
                String username = res_username.getText().toString().trim();
                String email = res_email.getText().toString().trim();
                String address = res_address.getText().toString().trim();
                String phone = res_phone.getText().toString().trim();
                String pass = res_password.getText().toString().trim();
                String re_pass = res_re_password.getText().toString().trim();
                if (name.isEmpty() || username.isEmpty() || pass.isEmpty() || address.isEmpty()
                        || phone.isEmpty() || email.isEmpty() || re_pass.isEmpty()) {

                    Toast.makeText(Sign_Up.this, "Fill all details", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(re_pass)) {
                    Toast.makeText(Sign_Up.this, "Please check Password and Re_Password", Toast.LENGTH_SHORT).show();
                } else {


                    signup(name, username, pass, address, phone, email);
                    SharedPreferences preferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.putString("name", name);
                    editor.putString("username", username);
                    editor.putString("password", pass);
                    editor.putString("address", address);
                    editor.putString("phone", phone);
                    editor.putString("email", email);
                    editor.commit();
                    show();

                }

            }
        });

    }

    public void signup ( final String name, final String username, final String pass,
                         final String address, final String phone, final String email){

        RequestQueue requestQueue = Volley.newRequestQueue(Sign_Up.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Hitesh", "" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Hitesh", "" + error);
                Toast.makeText(Sign_Up.this, "" + error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mapData = new HashMap<>();
                mapData.put("name", name);
                mapData.put("username", username);
                mapData.put("password", pass);
                mapData.put("address", address);
                mapData.put("phone", phone);
                mapData.put("email", email);
                return mapData;
            }
        };
        requestQueue.add(stringRequest);

    }




    public void show() {

        Intent intent = new Intent(Sign_Up.this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }




}
