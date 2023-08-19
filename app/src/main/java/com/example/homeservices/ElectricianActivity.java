package com.example.homeservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

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
import java.util.List;

public class ElectricianActivity extends AppCompatActivity {

    private final String JSON_URL = "https://run.mocky.io/v3/301384c5-7009-419f-9b93-95cab341472d";

    private StringRequest request;
    private RequestQueue requestQueue;
    private List<worker_model> model;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrician);

        model = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        jsonrequest();

        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton profileButton = (ImageButton) findViewById(R.id.profileButton);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileButton = new Intent(ElectricianActivity.this,ProfileActivity.class);
                startActivity(profileButton);
            }
        });

    }

    private void jsonrequest() {

        request = new StringRequest(Request.Method.GET,JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("electrician");


                    for (int i=0; i<jsonArray.length();i++){


                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        worker_model workerModel = new worker_model();
                        workerModel.setName(jsonObject1.getString("name"));
                        workerModel.setField(jsonObject1.getString("field"));
                        workerModel.setRating(jsonObject1.getString("rating"));
                        workerModel.setPrice(jsonObject1.getString("price"));
                        workerModel.setImage(jsonObject1.getString("image"));
                        model.add(workerModel);



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setuprecyclerview(model);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse:"+error.getMessage());
            }

        });

        requestQueue = Volley.newRequestQueue(ElectricianActivity.this);
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<worker_model> model) {

        workerAdapter adapter = new workerAdapter(this,model);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}