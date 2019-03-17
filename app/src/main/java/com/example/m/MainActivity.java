package com.example.m;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText username , password ;
    Button login , signUp;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById( R.id.username);
        password = (EditText) findViewById(R.id.password);
        login =(Button) findViewById(R.id.logein);
        signUp = (Button) findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Sign_Up.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInMethod();

            }
        });

    }

    public void signInMethod() {
        String user,pass;
        user = username.getText().toString();
        pass = password.getText().toString();

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(MainActivity.this, "fill details", Toast.LENGTH_SHORT).show();

        } else {

            login(user, pass);

        }
    }

    public void login(final String user, final String pass){

        url = "https://mindxx.000webhostapp.com/fakahany/log.php?username="+user+"&password="+pass+"";
        Log.i("Hiteshurl",""+url);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    String id = jsonObject1.getString("id");
                    String username = jsonObject1.getString("username");
                    String pass = jsonObject1.getString("password");

                    SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putString("id",id);
                    editor.putString("username", username);
                    editor.putString("password", pass);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);


                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this,"Please ckeck Username Or Password", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("HiteshURLerror",""+error);
                Toast.makeText(MainActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }

}