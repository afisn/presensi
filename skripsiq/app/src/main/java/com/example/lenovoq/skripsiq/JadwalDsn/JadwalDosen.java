package com.example.lenovoq.skripsiq.JadwalDsn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
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

public class JadwalDosen extends AppCompatActivity {
    private List<JDosen>list_data;
    private RecyclerView rv;
    private JadwalDosenList adapter;

    private String url = Server.URL + "jadwal_dosen.php";

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
        ListJadwalPengajar();



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

    public void ListJadwalPengajar(){
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    progressBar.setVisibility(View.INVISIBLE);

                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        JSONArray array=jsonObject.getJSONArray("jadwaldosen");
                        for (int i=0; i<array.length(); i++ ){
                            JSONObject ob=array.getJSONObject(i);
                            JDosen listData=new JDosen(ob.getInt("id")
                                    ,ob.getInt("Tahun"),ob.getString("Periode_Sem"),ob.getString("Kode_MK")
                                    ,ob.getString("Nama_MK"),ob.getString("Kelas"),ob.getString("hari")
                                    ,ob.getString("jam_mulai"),ob.getString("jam_selesai"),ob.getString("pengajar"));
                            list_data.add(listData);
                        }
                        //rv.setAdapter(adapter);
                        Log.d("list_data",String.valueOf(list_data.size()));
                        adapter=new JadwalDosenList(list_data);
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