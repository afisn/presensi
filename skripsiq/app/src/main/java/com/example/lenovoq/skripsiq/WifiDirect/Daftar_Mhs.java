package com.example.lenovoq.skripsiq.WifiDirect;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pDevice;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovoq.skripsiq.Login;
import com.example.lenovoq.skripsiq.Presensi.BeritaAcara;
import com.example.lenovoq.skripsiq.Presensi.NamaMhs_Obj;
import com.example.lenovoq.skripsiq.Presensi.PDCheck;
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.AppController;
import com.example.lenovoq.skripsiq.Volley.Server;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Daftar_Mhs extends AppCompatActivity {
    private String url = Server.URL + "ambil_device.php";
//    ArrayList<HashMap<String, String>> daftarmhs = new ArrayList<HashMap<String, String>>();

    private RecyclerView rv;
    private DaftarMhs_list adapter;
    public static List<WifiP2pDevice> kump_peers;
    String tag_json_obj = "json_obj_req";
//    ProgressDialog pDialog;
    private int met_id;
    private Button btnsimpandf;

    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    String username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_mhs);
        // Call Async task to get the match fixture
        kump_peers = Wifi_Main.peers;
        met_id = getIntent().getIntExtra("Met_id", 0);
        Log.e("afis", "met_id" + met_id);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        username = sharedpreferences.getString(Login.TAG_USERNAME, null);
        simpanpeers(kump_peers);

        btnsimpandf = (Button) findViewById(R.id.btnsimpandf);

        rv = (RecyclerView) findViewById(R.id.recyclerview_nama);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line));
        rv.addItemDecoration(itemDecoration);

        BeritaAcara.kump_mhs = new ArrayList<>();
//        ambildaftar();

        btnsimpandf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent o = new Intent(Daftar_Mhs.this, BeritaAcara.class);
                o.putExtra("Met_id",met_id);
                startActivity(o);
            }
        });

    }

    public void simpanpeers(final List<WifiP2pDevice> kump_peers) {
//        pDialog = new ProgressDialog(this);
//        pDialog.setCancelable(false);
//        pDialog.setMessage("Processing ...");
//        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response_ku", response.toString());

                try {
                    JSONObject jObject = new JSONObject(response);
                    JSONArray array = jObject.getJSONArray("kump_mhs");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject ob = array.getJSONObject(i);
                        NamaMhs_Obj list = new NamaMhs_Obj(ob.getString("NRP"), ob.getString("Nama_Mhs"));
//                        list.setStatus("hadir");
//                        list.setId_status(1);
                        BeritaAcara.kump_mhs.add(list);
                        Log.e("Afis", String.valueOf(list));

                    }
                    adapter = new DaftarMhs_list(BeritaAcara.kump_mhs);
                    rv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Daftar_Mhs.this, "Error JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ERROR", error.getMessage());
                Toast.makeText(Daftar_Mhs.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                Log.d("Afis", "getParams: " + kump_peers);
                //JSONObject jsonObject = new JSONObject();
                /*

                 */
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<WifiP2pDevice>>() {
                }.getType();
                String json = gson.toJson(kump_peers, type);
                System.out.println("Afis Json" + json);
                //Merubah JSON String ke Objectya (ArrayList)
//                ArrayList<NamaMhs_Obj> fromJson = gson.fromJson(json, type);
                //Log.d("Afis", "Json Array: "+jsArray.toString());
                params.put("kump_peers", json);
//                params.put("met_id", met_id + "");
//                params.put("username", username);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}
