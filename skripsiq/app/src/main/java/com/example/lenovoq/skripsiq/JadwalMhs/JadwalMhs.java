package com.example.lenovoq.skripsiq.JadwalMhs;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovoq.skripsiq.Login;
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.AppController;
import com.example.lenovoq.skripsiq.Volley.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JadwalMhs extends AppCompatActivity {
    private List<JMahasiswa> list_data;
    private RecyclerView rv;
    private JadwalMhsList adapter;

    private String url = Server.URL + "jadwal_mahasiswa.php";
    private static final String TAG = JadwalMhs.class.getSimpleName();
    String username ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jadwal);
        ActionBar ac = getSupportActionBar();
        getSupportActionBar().setTitle("Jadwal Perkuliahan");
        ac.setDisplayHomeAsUpEnabled(true);



        rv=(RecyclerView)findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        list_data=new ArrayList<>();

        if(getIntent()!=null){
            username = getIntent().getStringExtra("username");
            Log.d("Afis", "onCreate: "+username);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    ListJadwalMhs(username);
                }
            }, 3000);

        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void ListJadwalMhs(final String username){
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            public void onResponse(String response) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d("Afis", "onResponse: "+response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("jadwalmahasiswa");
//                    Log.d("Afis", "Data: "+array.length());
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        JMahasiswa listData=new JMahasiswa(ob.getString("Kode_MK")
                                ,ob.getString("Nama_MK"),ob.getString("Kelas"),ob.getString("dayname")
                                ,ob.getString("jam_mulai"),ob.getString("jam_selesai"),ob.getString("pengajar"));
                        list_data.add(listData);
                    }
                    //rv.setAdapter(adapter);
                    Log.d("list_data",String.valueOf(list_data.size()));
                    adapter=new JadwalMhsList(list_data);
                    rv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();



            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                return params;
            }

        };

        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(strReq );
    }

    String tag_json_obj = "json_obj_req";
}