package com.example.slothofdoom.jsonsender;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private String mUrlString = "https://localhost:8080/TestService/HomeController/ReturnJson/";
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        Button button = findViewById(R.id.button);
        final TextView textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");


                String str = "SELECT name FROM Teams";
                JSONObject query = new JSONObject();
                try {
                    query.put(str, "SELECT name FROM Teams");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                        Request.Method.POST,
                        mUrlString,
                        query,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                textView.setText(response.toString());
                            }

                         },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Do something when get error
                                textView.setText("Error");
                            }
                        }
                );

                MySingleton.getInstance(mContext).addToRequestQueue(jsonObjReq);

            }
        });

    }
}

