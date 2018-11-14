package com.example.lenovoq.skripsiq.Presensi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BeritaAcara extends AppCompatActivity {

    ProgressDialog pDialog;
    private TextView tgltxt, metodetxt, materitxt, kettxt;
    private Button btn_simpan;
    private EditText txttgl, txtmetode, txtmateri, txtket,jamawaltxt, jamakhirtxt;
    private Calendar myCalendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat;
    private String date;
    public static ArrayList<NamaMhs_Obj> kump_mhs;
    private int met_id;

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
        jamawaltxt = (EditText) findViewById(R.id.jamAwaltxt);
        jamakhirtxt = (EditText) findViewById(R.id.jamAkhirtxt);

        met_id = getIntent().getIntExtra("Met_id", 0);
        Log.e("afis", "met_id" + met_id);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        txttgl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(BeritaAcara.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

//        calendar = Calendar.getInstance();
//        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        date = simpleDateFormat.format(calendar.getTime());
//        txttgl.setText(date);

//        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
//        txttgl.setText(currentDateTimeString);

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
                                String cat = txtket.getText().toString();
                                if (metode.trim().length() > 0 && materi.trim().length() > 0) {
                                    simpanData(tgl,metode,materi,cat, kump_mhs);
                                } else {
                                    // Prompt user to enter credentials
                                    Toast.makeText(getApplicationContext(), "Data belum terisi", Toast.LENGTH_LONG).show();
                                }
//                                Intent o = new Intent(BeritaAcara.this, MainActivityPengajar.class);
//                                startActivity(o);
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

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txttgl.setText(sdf.format(myCalendar.getTime()));
    }

    public void simpanData(final String tgl,final String metode, final String materi, final String cat, final ArrayList<NamaMhs_Obj> kump_mhs) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Processing ...");
        showDialog();

//        url = url + "?met_id=" + met_id;
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
                params.put("tanggal", tgl);
                params.put("metode", metode);
                params.put("materi_bahasan", materi);
                params.put("catatan", cat);
                Log.d("Afis", "getParams: "+kump_mhs);
                //JSONObject jsonObject = new JSONObject();
                /*

*/
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<NamaMhs_Obj>>() {}.getType();
                String json = gson.toJson(kump_mhs, type);
                System.out.println("Afis Json"+json);
                //Merubah JSON String ke Objectya (ArrayList)
//                ArrayList<NamaMhs_Obj> fromJson = gson.fromJson(json, type);
                //Log.d("Afis", "Json Array: "+jsArray.toString());
                params.put("kump_mhs",json );
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