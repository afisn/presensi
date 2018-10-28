package com.example.lenovoq.skripsiq.Presensi;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Volley.Server;

import org.json.JSONObject;

public class BeritaAcara extends AppCompatActivity {

    private TextView tgltxt, metodetxt, materitxt, kettxt;
    private Button btn_simpan;
    private EditText txttgl, txtmetode, txtmateri, txtket;

    private String url = Server.URL + "berita_acara.php";
    ConnectivityManager conMgr;

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
                String tgl = txttgl.getText().toString();
                String metode = txtmetode.getText().toString();
                String materi = txtmateri.getText().toString();
                String ket = txtket.getText().toString();

                // mengecek kolom yang kosong
                if (metode.trim().length() > 0 && materi.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        simpanData(tgl, metode, materi);
                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Data belum terisi", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void simpanData(String tgl, String metode, String materi) {

    }
}