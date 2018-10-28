package com.example.lenovoq.skripsiq.Presensi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
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
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class PDCheck extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<NamaMhs_Obj> PDCheck_list;
    private PDCheck_list adapter;
    private Button btnselect, btnedit, btnsimpan;

    private String[] statusmhs;
    private int met_id;


    private String url = Server.URL + "nama_mhs.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdcheck);
        btnselect = (Button) findViewById(R.id.list_select_all);
        btnedit = (Button) findViewById(R.id.editstatusbtn);
        btnsimpan = (Button) findViewById(R.id.save_manual);

        rv = (RecyclerView) findViewById(R.id.recyclerview_checkbox);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        rv.addItemDecoration(itemDecoration);

        //dapet id pertemuan dari presensidosen
        met_id = getIntent().getIntExtra("Met_id", 0);
        Log.e("afis", "met_id" + met_id);

        PDCheck_list = new ArrayList<>();
        ambilnama(false);

        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Afis", "onClick: Tes");
                int size = PDCheck_list.size();
                for (int i = 0; i < size; i++) {
                    PDCheck_list.get(i).setSelected(true);
                    adapter.list_data.get(i).setSelected(true);
                    Log.d("Afis", "onClick: " + adapter.list_data.get(i).isSelected());
                }
                adapter.notifyDataSetChanged();
//                rv.setAdapter(adapter);

            }
        });
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(PDCheck.this);
                builder.setMessage("Anda yakin dengan ini?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                modelArrayList = getModel(true);
//                                customAdapter = new CustomAdapter(MainActivity.this,modelArrayList);
//                                recyclerView.setAdapter(customAdapter);
                                Intent o = new Intent(PDCheck.this, BeritaAcara.class);
                                startActivity(o);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
        });
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PDCheck.this);
                statusmhs = getResources().getStringArray(R.array.statusmhs_array);
                builder.setTitle(R.string.title)
                        .setItems(statusmhs, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int position) {
                                int size = PDCheck_list.size();
                                for (int i = 0; i < size; i++) {
                                    PDCheck_list.get(i).setStatus(statusmhs[position]);
                                }
                                adapter.notifyDataSetChanged();
//                              String clickedItemValue = Arrays.asList(statusmhs.array).get(which);
//                                //Set the TextView text color corresponded to the user selected color
//                                adapter.list_data.setStatus(clickedItemValue);
                            }

                        });
                builder.show();
            }
        });
    }


    public void ambilnama(final boolean check) {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        url = url + "?met_id=" + met_id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    progressBar.setVisibility(View.INVISIBLE);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("nama_mhs");
                    Log.e("nama diambil", jsonObject.toString());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject ob = array.getJSONObject(i);
                        NamaMhs_Obj list = new NamaMhs_Obj(ob.getString("Nama_Mhs"), ob.getString("NRP"));
                        list.setSelected(check);
                        list.setStatus("tidak hadir");
                        PDCheck_list.add(list);

                    }
                    adapter = new PDCheck_list(PDCheck_list);
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