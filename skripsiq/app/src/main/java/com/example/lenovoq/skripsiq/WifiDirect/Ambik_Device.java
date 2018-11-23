package com.example.lenovoq.skripsiq.WifiDirect;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.lenovoq.skripsiq.Login;
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.AppController;
import com.example.lenovoq.skripsiq.Volley.Server;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Ambik_Device extends AppCompatActivity{
    private Button btn_simpan;
    private EditText txttgl, txtmetode, txtmateri, txtket,txtjamawaltxt, txtjamakhirtxt, txtperke;
    private Calendar myCalendar = Calendar.getInstance();
    public static List<WifiP2pDevice> kump_peers;

    private String url = Server.URL + "ambil_device.php";
    String tag_json_obj = "json_obj_req";
    ProgressDialog pDialog;
    private int met_id;

    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    String username ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.berita_acara);

        btn_simpan = (Button) findViewById(R.id.btnSimpanBA);
        txttgl = (EditText) findViewById(R.id.txttgl);
        txtmateri = (EditText) findViewById(R.id.txtmateri);
        txtmetode = (EditText) findViewById(R.id.txtmetode);
        txtket = (EditText) findViewById(R.id.txtket);
        txtjamawaltxt = (EditText) findViewById(R.id.jamAwaltxt);
        txtjamakhirtxt = (EditText) findViewById(R.id.jamAkhirtxt);
        txtperke = (EditText) findViewById(R.id.txtper);

        kump_peers = Wifi_Main.peers;
        met_id = getIntent().getIntExtra("Met_id", 0);
        Log.e("afis", "met_id" + met_id);


        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        username =  sharedpreferences.getString(Login.TAG_USERNAME, null);

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
                new DatePickerDialog(Ambik_Device.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btn_simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final AlertDialog.Builder builder = new AlertDialog.Builder(Ambik_Device.this);
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
                                int perke = Integer.parseInt(txtperke.getText().toString());
                                String jamawal = txtjamawaltxt.getText().toString();
                                String jamakhir = txtjamakhirtxt.getText().toString();
                                if (metode.trim().length() > 0 && materi.trim().length() > 0) {
                                    simpanData(tgl,metode,materi,cat, kump_peers, perke, jamawal,jamakhir);
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
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txttgl.setText(sdf.format(myCalendar.getTime()));
    }

    public void simpanData(final String tgl, final String metode, final String materi, final String cat, final List<WifiP2pDevice> kump_peers,
                           final int perke, final String jamawal, final String jamakhir) {

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Processing ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response.toString());

                try {
                    JSONObject jObject = new JSONObject(response);
                    String pesan = jObject.getString("pesan");
                     String hasil = jObject.getString("result");
                    if (hasil.equalsIgnoreCase("true")) {
                        Toast.makeText(Ambik_Device.this, pesan, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Ambik_Device.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Ambik_Device.this, "Error JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ERROR", error.getMessage());
                Toast.makeText(Ambik_Device.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                Log.d("Afis", "getParams: "+kump_peers);
                //JSONObject jsonObject = new JSONObject();
                /*

                 */
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<WifiP2pDevice>>() {}.getType();
                String json = gson.toJson(kump_peers, type);
                System.out.println("Afis Json"+json);
                //Merubah JSON String ke Objectya (ArrayList)
//                ArrayList<NamaMhs_Obj> fromJson = gson.fromJson(json, type);
                //Log.d("Afis", "Json Array: "+jsArray.toString());
                params.put("kump_peers",json );
                params.put("met_id", met_id+"");
                params.put("username", username);
                params.put("tanggal", tgl);
                params.put("metode", metode);
                params.put("materi_bahasan", materi);
                params.put("catatan", cat);
                params.put("perke", String.valueOf(perke));
                params.put("jamawal", jamawal);
                params.put("jamakhir", jamakhir);
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
