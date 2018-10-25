package com.example.lenovoq.skripsiq.Coba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lenovoq.skripsiq.R;
import com.example.lenovoq.skripsiq.Presensi.PDCheck_list;

public class NextActivity  extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        tv = (TextView) findViewById(R.id.tv);

        for (int i = 0; i < PDCheck_list.list_data.size(); i++){
            if(PDCheck_list.list_data.get(i).isSelected()) {
                tv.setText(tv.getText() + " " + PDCheck_list.list_data.get(i).getNama());
            }
        }
    }
}