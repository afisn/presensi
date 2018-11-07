package com.example.lenovoq.skripsiq.History;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.lenovoq.skripsiq.Presensi.MatkulDosen_Obj;
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.Server;

import java.util.ArrayList;
import java.util.List;

public class History_Dsn extends AppCompatActivity {
    private List<MatkulDosen_Obj> list_jadwal;
    private RecyclerView rv;
    private History_Dsn_List adapter;

    private String url = Server.URL + "jadwa_dosen_history.php";

    public static TextView txtContent;
    private Animation animationUp;
    private Animation animationDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presensi);

        rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        rv.addItemDecoration(itemDecoration);

        list_jadwal=new ArrayList<>();
//        JadwalPengajarList();
        //dapet id pertemuan dari presensi
//        met_id = getIntent().getIntExtra("Met_id", 0);
//        Log.e("afis", "met_id" + met_id);

//        txtContent = (TextView) findViewById(R.id.title_text);
////        TextView txtTitle = (TextView) findViewById(R.id.content_text);
//        txtContent.setVisibility(View.GONE);
//
//        animationUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
//        animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

//        txtTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(txtContent.isShown()){
//                    txtContent.setVisibility(View.GONE);
//                    txtContent.startAnimation(animationUp);
//                }
//                else{
//                    txtContent.setVisibility(View.VISIBLE);
//                    txtContent.startAnimation(animationDown);
//                }
//            }
//        });
    }

//    public void JadwalPengajarList(){
//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        //making the progressbar visible
//        progressBar.setVisibility(View.VISIBLE);
//
//        //creating a string request to send request to the url
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                progressBar.setVisibility(View.INVISIBLE);
//
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
//                    JSONArray array=jsonObject.getJSONArray("jadwaldosen_history");
//                    Log.e("Successfully Login!", jsonObject.toString());
//                    for (int i=0; i<array.length(); i++ ){
//                        JSONObject ob=array.getJSONObject(i);
//                        MatkulDosen_Obj listData=new MatkulDosen_Obj( ob.getInt("met_id"),ob.getString("Kode_MK")
//                                ,ob.getString("Nama_MK"),ob.getString("Kelas"));
//                        list_jadwal.add(listData);
//                    }
//                    //rv.setAdapter(adapter);
//                    Log.d("list_data",String.valueOf(list_jadwal.size()));
//                    adapter=new History_Dsn_List(list_jadwal);
//                    rv.setAdapter(adapter);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
}