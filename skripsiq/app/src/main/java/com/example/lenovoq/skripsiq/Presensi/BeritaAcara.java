package com.example.lenovoq.skripsiq.Presensi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.lenovoq.skripsiq.MainActivityPengajar;
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.AppController;
import com.example.lenovoq.skripsiq.Volley.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeritaAcara extends AppCompatActivity {

    ProgressDialog pDialog;
    private TextView tgltxt, metodetxt, materitxt, kettxt;
    private Button btn_simpan;
    private EditText txttgl, txtmetode, txtmateri, txtket;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    private String url = Server.URL + "berita_acara.php";
    ConnectivityManager conMgr;
    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.berita_acara);

        btn_simpan = (Button) findViewById(R.id.btnSimpanBA);
        txttgl = (EditText) findViewById(R.id.txttgl);
        txtmateri = (EditText) findViewById(R.id.txtmateri);
        txtmetode = (EditText) findViewById(R.id.txtmetode);
        txtket = (EditText) findViewById(R.id.txtket);

        kettxt = (TextView) findViewById(R.id.kettxt);
        materitxt = (TextView) findViewById(R.id.materitxt);
        metodetxt = (TextView) findViewById(R.id.metodetxt);
        tgltxt = (TextView) findViewById(R.id.tgltxt);

//        calendar = Calendar.getInstance();
//        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        date = simpleDateFormat.format(calendar.getTime());
//        txttgl.setText(date);

        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        txttgl.setText(currentDateTimeString);

        //data yang dipilih di hal sebelum
        for (int i = 0; i < PDCheck_list.list_data.size(); i++) {
            if (PDCheck_list.list_data.get(i).isSelected()) {
//                JSONObject jsonObject = array.get(i);
            }
        }

        btn_simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final AlertDialog.Builder builder = new AlertDialog.Builder(BeritaAcara.this);
                builder.setMessage("Anda yakin dengan ini?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                modelArrayList = getModel(true);
//                                customAdapter = new CustomAdapter(MainActivity.this,modelArrayList);
//                                recyclerView.setAdapter(customAdapter);
                                // mengecek kolom yang kosong
                                String tgl = txttgl.getText().toString();
                                String metode = txtmetode.getText().toString();
                                String materi = txtmateri.getText().toString();
                                String ket = txtket.getText().toString();
                                if (metode.trim().length() > 0 && materi.trim().length() > 0) {
                                    simpanData(tgl,metode,materi,ket);
                                } else {
                                    // Prompt user to enter credentials
                                    Toast.makeText(getApplicationContext(), "Data belum terisi", Toast.LENGTH_LONG).show();
                                }
                                Intent o = new Intent(BeritaAcara.this, MainActivityPengajar.class);
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
    }

    public void simpanData(final String tgl,final String metode, final String materi, final String ket) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response.toString());
                hideDialog();

                try {
                    JSONObject jObject = new JSONObject(response);
                    String pesan = jObject.getString("pesan");
                    String hasil = jObject.getString("result");
                    if (hasil.equalsIgnoreCase("true")) {
                        Toast.makeText(BeritaAcara.this, pesan, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BeritaAcara.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(BeritaAcara.this, "Error JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ERROR", error.getMessage());
                Toast.makeText(BeritaAcara.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tgl", tgl);
                params.put("metode", metode);
                params.put("materi", materi);
                params.put("ket", ket);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}