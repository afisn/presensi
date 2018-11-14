package com.example.lenovoq.skripsiq.History;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovoq.skripsiq.Login;
import com.example.lenovoq.skripsiq.Presensi.MatkulDosen_Obj;
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class History_Dsn extends AppCompatActivity {
    private List<MatkulDosen_Obj> list_jadwal;
    private RecyclerView rv;
    private History_Dsn_List adapter;

    private String url = Server.URL + "jadwal_dosen_history.php";

    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    String username ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        username =  sharedpreferences.getString(Login.TAG_USERNAME, null);

        rv = (RecyclerView) findViewById(R.id.recyclerview_his);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        rv.addItemDecoration(itemDecoration);

        list_jadwal=new ArrayList<>();
        JadwalMatkulList();
        //dapet id pertemuan dari presensi
//        met_id = getIntent().getIntExtra("Met_id", 0);
//        Log.e("afis", "met_id" + met_id);

    }

    public void JadwalMatkulList(){
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        url = url+"?username="+username;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.INVISIBLE);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("jadwaldosen_history");
                    Log.e("Successfully Login!", jsonObject.toString());
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        MatkulDosen_Obj listData=new MatkulDosen_Obj( ob.getInt("met_id"),ob.getString("Kode_MK")
                                ,ob.getString("Nama_MK"),ob.getString("Kelas"));
                        list_jadwal.add(listData);
                    }
                    //rv.setAdapter(adapter);
                    Log.d("list_data",String.valueOf(list_jadwal.size()));
                    adapter=new History_Dsn_List(list_jadwal);
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