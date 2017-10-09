package com.example.pc.web;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.web.Adapters.MovieAdapter;
import com.example.pc.web.Models.MovieModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity{

    ListView listMovie;
    Button search;
    RadioGroup radioGroup;
    String sortByVariable;
    public static final String ReleaseDateAsc = "release_date.asc";
    public static final String PopularityAsc = "popularity.des";
    String year;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final TextView mTextView = (TextView) findViewById(R.id.textview);
//
//// Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=fe81cc86b1b198376a650a28075a13d4";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        //mTextView.setText("Response is: "+ response.substring(0,500));
////                        JsonObject jsonObject=new JsonObject(response);
////                        JsonArray jsonArray=jsonObject.getAsJsonArray("results");
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
//            }
//        });
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);

        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        listMovie = (ListView) findViewById(R.id.listMovie);
        search = (Button) findViewById(R.id.btnSearch);
        editText=(EditText) findViewById(R.id.editText) ;


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.popularAsc:
                        sortByVariable = PopularityAsc;
                        year=editText.getText().toString();
                        break;
                    case R.id.releaseAsc:
                        sortByVariable = ReleaseDateAsc;
                        year=editText.getText().toString();
                        break;
                }
                String url = "http://api.themoviedb.org/3/discover/movie?api_key=be32430c9f675ed7df41fbeda2a0525a&language=en-US&sort_by=" + sortByVariable + "&page=1&year="+year;
                executeWebService(url);
            }
        });
    }

    void executeWebService(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //      mTextView.setText("Response is: "+ response.substring(0,500));
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            //mTextView.setText(jsonArray.toString());

                            final MovieModel[] movieModel;
                            movieModel = new Gson().fromJson(jsonArray.toString(), MovieModel[].class);
//                            for (int i = 0; i < movieModel.length; i++) {
//                                Log.d("zamel test", "onResponse: ." + movieModel[i].getTitle());
//                            }
                            MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, movieModel);
                            listMovie.setAdapter(movieAdapter);
                            listMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//                                    Toast.makeText(MainActivity.this, movieModel[i].getTitle(), Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(MainActivity.this, Details.class);
                                    intent.putExtra("movieModel", (Serializable) movieModel[i]);
                                    startActivity(intent);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //       mTextView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }
}

