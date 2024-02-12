package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    String url="https://www.google.com";
    String apiurl="https://jsonplaceholder.typicode.com/todos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//         queue=Volley.newRequestQueue(this);
        queue=MySingleton.getInstance(this.getApplicationContext())
                .getRequestQueue();
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET,apiurl, null,
                response -> {
            for(int i=0;i<response.length();i++){
                try {
                    JSONObject jsonObject=response.getJSONObject(i);
                    Log.d("JSON","onCreate: "+jsonObject.getString("title"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
            },
                error -> Log.d("JSON","onCreate: Failed!")
        );
           queue.add(jsonArrayRequest);
        getString(queue);

    }

    private void getString(RequestQueue queue) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,url,response -> {
            Log.d("Main","onCreate: " + response.substring(0,500));

        },error ->{
            Log.d("Main","Failed to get info");
        });
//        queue.add(stringRequest);
    }





}





//    RequestQueue requestQueue;
//        requestQueue=Volley.newRequestQueue(this);
//
//                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos/1",
//                null, response -> {
//                try {
//                Log.d("myapp","the response : "+ response.getString("title"));
//                } catch (JSONException e) {
//                throw new RuntimeException(e);
//                }
//
//                }, error -> Log.d("myapp","somethis went wrong"));
//                requestQueue.add(jsonObjectRequest);
