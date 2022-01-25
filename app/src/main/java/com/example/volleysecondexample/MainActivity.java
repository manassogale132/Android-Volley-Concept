package com.example.volleysecondexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fabButton;
    int switchCount = 0;
    ImageView refreshIcon;
    TextView noDataTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fabButton = (FloatingActionButton) findViewById(R.id.fabButton);
        refreshIcon = (ImageView) findViewById(R.id.refreshIcon);
        noDataTV = (TextView) findViewById(R.id.noDataTV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchData();

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchCount == 0) {
                    //recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    fabButton.setImageResource(R.drawable.ic_baseline_grid_off_24);
                    switchCount++;
                } else if (switchCount == 1) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    fabButton.setImageResource(R.drawable.ic_baseline_grid_on_24);
                    switchCount = 0;
                }

            }
        });

        refreshIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                fetchData();
            }
        });

    }

//    private void fetchData() {
//        String apikey = "109a996d712d49cc87ac1b9c369ef8ec";
//        String apiurl = "https://newsapi.org/v2/top-headlines?country=in&pageSize=40&apiKey=" + apikey;
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        StringRequest jsonObjectRequest = new StringRequest(
//                Request.Method.GET,
//                apiurl,
//                response -> {
//                    ArrayList<Articles> articlesArrayList = new ArrayList<Articles>();
//                    try {
//                        JSONObject articleObj = new JSONObject(response);   //outer object
//                        JSONArray articleArray = articleObj.getJSONArray("articles");  // inner array
//                        for (int i = 0; i < articleArray.length(); i++) {
//                            JSONObject jasonObject1 = articleArray.getJSONObject(i);  //inner object
//                            JSONObject jasonObject2 = jasonObject1.getJSONObject("source"); //inner inner object
//                            Articles articles1 = new Articles(
//                                    jasonObject1.getString("author"),
//                                    jasonObject1.getString("urlToImage"),
//                                    jasonObject1.getString("title"),
//                                    jasonObject1.getString("description"),
//                                    jasonObject1.getString("url"),
//                                    jasonObject1.getString("content"),
//                                    jasonObject2.getString("name")
//                            );
//                            articlesArrayList.add(articles1);
//                        }
//                        recyclerView.setAdapter(new NewsAdapter(getApplicationContext(), articlesArrayList));
//
//                        if(articlesArrayList.isEmpty()){
//                            recyclerView.setVisibility(View.GONE);
//                            noDataTV.setVisibility(View.VISIBLE);
//                        }else {
//                            recyclerView.setVisibility(View.VISIBLE);
//                            noDataTV.setVisibility(View.GONE);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                },
//                error -> {
//                    Toast.makeText(MainActivity.this, "Something went wrong!",
//                            Toast.LENGTH_SHORT).show();
//                }
//        ) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("User-Agent", "Mozilla/5.0");
//                return headers;
//            }
//        };
//        queue.add(jsonObjectRequest);
//    }

    private void fetchData() {
        String apikey = "109a996d712d49cc87ac1b9c369ef8ec";
        String apiurl = "https://newsapi.org/v2/top-headlines?country=in&pageSize=40&apiKey=" + apikey;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                apiurl,
                null,
                response -> {
                    ArrayList<Articles> articlesArrayList = new ArrayList<Articles>();
                    try {
                        JSONArray articleArray = response.getJSONArray("articles");
                        for (int i = 0; i < articleArray.length(); i++) {
                            JSONObject articleObject = articleArray.getJSONObject(i);
                            JSONObject jasonObject2 = articleObject.getJSONObject("source");
                            try {
                                Articles articles1 = new Articles(
                                        articleObject.getString("author"),
                                        articleObject.getString("urlToImage"),
                                        articleObject.getString("title"),
                                        articleObject.getString("description"),
                                        articleObject.getString("url"),
                                        articleObject.getString("content"),
                                        jasonObject2.getString("name")
                                );
                                articlesArrayList.add(articles1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            recyclerView.setAdapter(new NewsAdapter(getApplicationContext(), articlesArrayList));
                        }
                        Log.d("****fetched api data", response.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(MainActivity.this, "Something went wrong!",
                            Toast.LENGTH_LONG).show();
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };

        queue.add(jsonObjectRequest);
    }
}