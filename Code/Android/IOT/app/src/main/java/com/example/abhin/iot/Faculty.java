package com.example.abhin.iot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Faculty extends AppCompatActivity {

    private String username, password;

    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_FTABLE = "http://riyatalwar1697.000webhostapp.com/fpage.php";
    public static final String KEY_EXTRA = "com.example.yourapp.KEY_BOOK";
    public static final String KEY_EXTRA1 = "com.example.yourapp.KEY_BOOK1";

    //a list to store all the products
    List<FTable> productList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        if (getIntent().hasExtra(KEY_EXTRA)) {
            username = getIntent().getStringExtra(KEY_EXTRA);
            password = getIntent().getStringExtra(KEY_EXTRA1);
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras " + KEY_EXTRA);
        }

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
    }

    private void loadProducts() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FTABLE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response);

                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //Log.d("Response", response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {
                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                Log.d("List entries:", product.getString("attendance"));
                                //adding the product to product list
                                productList.add(new FTable(
                                        product.getString("course_id"),
                                        product.getString("course_name"),
                                        product.getString("attendance"),
                                        product.getString("students"),
                                        product.getString("date")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            ProductAdapter adapter = new ProductAdapter(Faculty.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //User u =new User();
                params.put("username", username);
                //params.put("password", password);
                Log.d("Ye error hai",username+password);
                return params;
            }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
