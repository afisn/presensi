package com.example.lenovoq.skripsiq;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovoq.skripsiq.History.History_Dsn;
import com.example.lenovoq.skripsiq.JadwalDsn.JadwalDosen;
import com.example.lenovoq.skripsiq.Presensi.PresensiDosen;
import com.example.lenovoq.skripsiq.Help.Help;
import com.example.lenovoq.skripsiq.WifiDirect.Wifi_Main;

public class MainActivityPengajar extends AppCompatActivity {
    Button btn_jadwal, btn_presensi, btn_presensiplus, btn_history;
    TextView txt_id, txt_username;
    String id, username;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain_pengajar);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_username = (TextView) findViewById(R.id.txt_username);
        btn_jadwal = (Button) findViewById(R.id.btn_jadwal);
        btn_presensi = (Button) findViewById(R.id.btn_presensi);
        btn_presensiplus = (Button) findViewById(R.id.btn_presensi_wifi);
        btn_history = (Button) findViewById(R.id.btn_history);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);

        txt_id.setText("ID : " + id);
        txt_username.setText("USERNAME : " + username);

        btn_jadwal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent o = new Intent(MainActivityPengajar.this, JadwalDosen.class);
                startActivity(o);
            }
        });

        btn_presensi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent o = new Intent(MainActivityPengajar.this, PresensiDosen.class);
                startActivity(o);
            }
        });

        btn_presensiplus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent o = new Intent(MainActivityPengajar.this, Wifi_Main.class);
                startActivity(o);
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent o = new Intent(MainActivityPengajar.this, History_Dsn.class);
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

                Intent Ilogout = new Intent(MainActivityPengajar.this, Login.class);
                finish();
                startActivity(Ilogout);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
