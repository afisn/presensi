package com.example.lenovoq.skripsiq;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovoq.skripsiq.JadwalMhs.JadwalMhs;
import com.example.lenovoq.skripsiq.Help.Help;
import com.example.lenovoq.skripsiq.JadwalMhs.History_Mhs;
import com.example.lenovoq.skripsiq.WifiDirect.Wifi_Main_mhs;


public class MainActivityMhs extends AppCompatActivity {
    TextView txt_id, txt_username;
    String id, username;
    Button btn_jadwal, btn_kehadiran, btn_presensi;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain_mhs);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_username = (TextView) findViewById(R.id.txt_username);
        btn_jadwal = (Button) findViewById(R.id.btn_jadwal);
        btn_presensi = (Button) findViewById(R.id.btn_presensi_wifi);
        btn_kehadiran = (Button) findViewById(R.id.btn_kehadiran);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        //ambil value data dari activity sebelum
        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);


        txt_id.setText("ID : " + id);
        txt_username.setText("USERNAME : " + username);
        btn_jadwal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent o = new Intent(MainActivityMhs.this, JadwalMhs.class);
                //ngirim data yang ada di activity ini ke activity yang dituju
                o.putExtra("username",username);
                startActivity(o);
            }
        });

        btn_presensi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent o = new Intent(MainActivityMhs.this, Wifi_Main_mhs.class);
                startActivity(o);
            }
        });

        btn_kehadiran.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent o = new Intent(MainActivityMhs.this, History_Mhs.class);
                o.putExtra("username",username);
                startActivity(o);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.action_about:
                Intent Iabout = new Intent(this, About.class);
                this.startActivity(Iabout);
                return true;

          case R.id.action_help:
                Intent ihelp = new Intent(this, Help.class);
                this.startActivity(ihelp);
                return true;

            case R.id.action_logout:
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_USERNAME, null);
                editor.commit();

                Intent Ilogout = new Intent(MainActivityMhs.this, Login.class);
                finish();
                startActivity(Ilogout);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
