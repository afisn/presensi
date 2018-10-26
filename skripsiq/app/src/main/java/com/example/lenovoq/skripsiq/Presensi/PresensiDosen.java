package com.example.lenovoq.skripsiq.Presensi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

public class PresensiDosen extends AppCompatActivity {
    private List<MatkulDosen_Obj> presensidosen_list;
    private RecyclerView rv;
    private PresensiDosenList adapter;

    private String url = Server.URL + "jadwal_dosen_aja.php";
    private static final String TAG = PresensiDosen.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presensidosen);
        ActionBar ac = getSupportActionBar();
        getSupportActionBar().setTitle("Presensi");
        ac.setDisplayHomeAsUpEnabled(true);

        rv=(RecyclerView)findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        presensidosen_list=new ArrayList<>();
        JadwalPengajarList();
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

    public void JadwalPengajarList(){
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
                    Log.e("Successfully Login!", jsonObject.toString());
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        MatkulDosen_Obj listData=new MatkulDosen_Obj( ob.getInt("met_id"),ob.getString("Kode_MK")
                                ,ob.getString("Nama_MK"),ob.getString("Kelas"),ob.getString("Hari")
                                ,ob.getString("jam_mulai"),ob.getString("jam_selesai"));
                        presensidosen_list.add(listData);
                    }
                    //rv.setAdapter(adapter);
                    Log.d("list_data",String.valueOf(presensidosen_list.size()));
                    adapter=new PresensiDosenList(presensidosen_list);
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
