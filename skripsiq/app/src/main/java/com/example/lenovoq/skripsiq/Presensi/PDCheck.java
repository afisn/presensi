package com.example.lenovoq.skripsiq.Presensi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovoq.skripsiq.Coba.NextActivity;
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PDCheck extends AppCompatActivity {
    private RecyclerView rv;
    private List<NamaMhs_Obj> PDCheck_list;
    private PDCheck_list adapter;
    private Button btnselect, btnnext, btndeselect;

    private String url = Server.URL + "nama_mhs.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdcheck);
        btnselect = (Button) findViewById(R.id.list_select_all);
        btnnext = (Button) findViewById(R.id.next);
        btndeselect = (Button) findViewById(R.id.list_remove_selected_rows);

        rv=(RecyclerView)findViewById(R.id.recyclerview_checkbox);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        PDCheck_list =new ArrayList<>();
       // ambilnama(false);

//        btnselect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                modelArrayList = getModel(true);
//                customAdapter = new CustomAdapter(MainActivity.this,modelArrayList);
//                recyclerView.setAdapter(customAdapter);
//            }
//        });
//        btndeselect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                modelArrayList = getModel(false);
//                customAdapter = new CustomAdapter(MainActivity.this,modelArrayList);
//                recyclerView.setAdapter(customAdapter);
//            }
//        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PDCheck.this,NextActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ambilnama(final boolean isSelect) {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("nama_mhs");
                    Log.e("nama diambil", jsonObject.toString());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject ob = array.getJSONObject(i);
                        NamaMhs_Obj list =new NamaMhs_Obj( ob.getString("Nama_Mhs"),ob.getString("NRP"));
                        list.setSelected(isSelect);
                        PDCheck_list.add(list);
                    }
                    adapter=new PDCheck_list (PDCheck_list);
                    rv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}