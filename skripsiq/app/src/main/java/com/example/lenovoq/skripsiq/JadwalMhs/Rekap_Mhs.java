package com.example.lenovoq.skripsiq.JadwalMhs;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Rekap_Mhs extends AppCompatActivity {
    private List<RekapMhs_Obj> list_rekap;
    private RecyclerView rv;
    private Rekap_List_Mhs adapter;
    private int met_id;

    private String url = Server.URL + "history_mhs.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        rv = (RecyclerView) findViewById(R.id.recyclerview_his);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        rv.addItemDecoration(itemDecoration);

        met_id = getIntent().getIntExtra("Met_id", 0);
        Log.e("afis", "met_id" + met_id);

        list_rekap=new ArrayList<>();
        Rekapan();
    }

    public void Rekapan(){
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
//        url = url + "?met_id=" + met_id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.INVISIBLE);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("rekapan_mhs");
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        RekapMhs_Obj listData=new RekapMhs_Obj( ob.getInt("pertemuan_ke"),ob.getString("tanggal")
                                ,ob.getString("materi_bahasan"),ob.getString("Keterangan"));
                        list_rekap.add(listData);
                    }
                    //rv.setAdapter(adapter);
                    Log.d("list_data",String.valueOf(list_rekap.size()));
                    adapter=new Rekap_List_Mhs(list_rekap);
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
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}