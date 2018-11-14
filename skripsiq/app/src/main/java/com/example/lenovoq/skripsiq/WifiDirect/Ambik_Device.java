package com.example.lenovoq.skripsiq.WifiDirect;

import android.app.ProgressDialog;
import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.lenovoq.skripsiq.Presensi.NamaMhs_Obj;
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.AppController;
import com.example.lenovoq.skripsiq.Volley.Server;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ambik_Device extends AppCompatActivity{
    private String url = Server.URL + "ambil_device.php";
    String tag_json_obj = "json_obj_req";
    ProgressDialog pDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ambik_device);
        ArrayList<WifiP2pDevice> kump_peers = Wifi_Main.peers;
        simpanData(kump_peers);
    }
    
    public void simpanData(final ArrayList<WifiP2pDevice> kump_peers) {
      
//        url = url + "?met_id=" + met_id;
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response.toString());

                try {
                    JSONObject jObject = new JSONObject(response);
//                    String pesan = jObject.getString("pesan");
 //                    String hasil = jObject.getString("result");
//                    if (hasil.equalsIgnoreCase("true")) {
//                        Toast.makeText(Ambik_Device.this, pesan, Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(Ambik_Device.this, pesan, Toast.LENGTH_SHORT).show();
//                    }
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
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }


}
