package com.example.lenovoq.skripsiq.Coba;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovoq.skripsiq.History.History_Dsn_List;
import com.example.lenovoq.skripsiq.JadwalDsn.JadwalDosen;
import com.example.lenovoq.skripsiq.Presensi.MatkulDosen_Obj;
import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Expandablelist extends ExpandableListActivity {
    List<String> groupListjdwl;
    List<String> childListjdwl;
//    Map<String, List<String>> pertemuan;
    ExpandableListView expListView;
    private String url = Server.URL + "jadwal_dosen_history.php";
    private String url1 = Server.URL + "ambil_pertemuan.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_screen);
        createGroupList();
        createPertemuan();
        expListView = (ExpandableListView) findViewById(android.R.id.list);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupListjdwl, childListjdwl);
        expListView.setAdapter(expListAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();

                return true;
            }
        });
    }

    private void createGroupList() {
       groupListjdwl = new ArrayList<String>();
       JadwalDosenList();
    }

    public void JadwalDosenList(){
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                            try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("jadwaldosen_history");
//                    Log.e("Successfully Login!", jsonObject.toString());
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        MatkulDosen_Obj listData=new MatkulDosen_Obj(ob.getString("Nama_MK"));
                        groupListjdwl.add(String.valueOf(listData));
                    }
                    //rv.setAdapter(adapter);
                    Log.d("list_data",String.valueOf(groupListjdwl.size()));
//                    adapter=new History_Dsn_List(groupListjdwl);
//                    rv.setAdapter(adapter);
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

    private void createPertemuan() {
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("pertemuan");
//                    Log.e("Successfully Login!", jsonObject.toString());
                    for (int i=0; i<array.length(); i++ ){
                        JSONObject ob=array.getJSONObject(i);
                        PertemuanObj listData=new PertemuanObj(ob.getInt("pertemuan_ke"));
                        childListjdwl.add(String.valueOf(listData));
                    }
                    //rv.setAdapter(adapter);
                    Log.d("list_data",String.valueOf(childListjdwl.size()));
//                    adapter=new History_Dsn_List(groupListjdwl);
//                    rv.setAdapter(adapter);
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

//        // preparing laptops collection(child)
//        String[] hpModels = {"HP Pavilion G6-2014TX", "ProBook HP 4540",
//                "HP Envy 4-1025TX"};
//        String[] hclModels = {"HCL S2101", "HCL L2102", "HCL V2002"};
//        String[] lenovoModels = {"IdeaPad Z Series", "Essential G Series",
//                "ThinkPad X Series", "Ideapad Z Series"};
//        String[] sonyModels = {"VAIO E Series", "VAIO Z Series",
//                "VAIO S Series", "VAIO YB Series"};
//        String[] dellModels = {"Inspiron", "Vostro", "XPS"};
//        String[] samsungModels = {"NP Series", "Series 5", "SF Series"};
//
//        pertemuan = new LinkedHashMap<String, List<String>>();
//
//        for (String laptop : groupListjdwl) {
//            if (laptop.equals("HP")) {
//                loadChild(hpModels);
//            } else if (laptop.equals("Dell"))
//                loadChild(dellModels);
//            else if (laptop.equals("Sony"))
//                loadChild(sonyModels);
//            else if (laptop.equals("HCL"))
//                loadChild(hclModels);
//            else if (laptop.equals("Samsung"))
//                loadChild(samsungModels);
//            else
//                loadChild(lenovoModels);
//
//            laptopCollection.put(laptop, childListjdwl);
//        }
    }

    public class PertemuanObj  {
        private int pertemuan_ke;

        public PertemuanObj(int pertemuan_ke){
            this.pertemuan_ke = pertemuan_ke;
        }

        public int getPertemuan_ke() {
            return pertemuan_ke;
        }

        public void setPertemuan_ke(int pertemuan_ke) {
            this.pertemuan_ke = pertemuan_ke;
        }
    }

    private void loadChild(String[] pertemuan) {
        childListjdwl = new ArrayList<String>();
        for (String model : pertemuan)
            childListjdwl.add(model);
    }

}