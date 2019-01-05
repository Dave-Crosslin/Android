package com.example.slothofdoom.jsonsender;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.List;
import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Context mContext;
    private String mUrlString = "http://192.168.0.13:8081/Home/Json";
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        mActivity = MainActivity.this;
        final Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        final List<String> spinnerNames = new ArrayList<String>();
                spinnerNames.add("hello");
               JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                        Request.Method.GET,
                        mUrlString,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                               for(int i = 0; i < response.length(); i++) {
                                   try {
                                       spinnerNames.add(response.getString(i));
                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                            }
                         },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                               spinnerNames.add(error.toString());
                            }
                        }
                );

        RequestHandler.getInstance(mContext).addToRequestQueue(jsonArrReq);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

