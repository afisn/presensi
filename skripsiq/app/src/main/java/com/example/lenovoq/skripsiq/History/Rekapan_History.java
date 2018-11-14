package com.example.lenovoq.skripsiq.History;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovoq.skripsiq.Presensi.MatkulDosen_Obj;
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Rekapan_History extends AppCompatActivity {
    private List<Rekapan_Obj> list_rekap;
    private RecyclerView rv;
    private Rekap_List adapter;

    private String url = Server.URL + "history_dosen.php";
    private int met_id;

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

        //dapet met id dari history dsn list
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
        url = url + "?met_id=" + met_id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.INVISIBLE);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("rekapan");
                    Log.e("Successfully Login!", jsonObject.toString());
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        Rekapan_Obj listData=new Rekapan_Obj( ob.getInt("pertemuan_ke"),ob.getString("materi_bahasan")
                                ,ob.getString("metode"),ob.getString("catatan"), ob.getInt("Hadir"),ob.getInt("Sakit")
                                ,ob.getInt("Ijin"),ob.getInt("Tugas"),ob.getInt("Tidak_hadir"));
                        list_rekap.add(listData);
                    }
                    //rv.setAdapter(adapter);
                    Log.d("list_data",String.valueOf(list_rekap.size()));
                    adapter=new Rekap_List(list_rekap);
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