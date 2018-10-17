package com.example.lenovoq.skripsiq.Presensi;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovoq.skripsiq.R;

import java.util.List;

public class PresensiDosenList extends RecyclerView.Adapter<PresensiDosenList.ViewHolder> {

    private List<MatkulDosen_Obj> list_data;

    public PresensiDosenList(List<MatkulDosen_Obj> list_data) {
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.presensidosen_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MatkulDosen_Obj listData = list_data.get(position);
        //kalau integer pake string value of
        holder.txttahun.setText(String.valueOf(listData.getTahun()));
        //kalau string langsung aja diset
        holder.txtperiode.setText(listData.getPeriode());
        holder.txtkd_mk.setText(listData.getKd_mk());
        holder.txtnama_mk.setText(listData.getNama_mk());
        holder.txtkelas.setText(listData.getKelas());
        holder.txthari.setText(listData.getHari());
        holder.txtjam_mulai.setText(listData.getJam_mulai());
        holder.txtjam_selesai.setText(listData.getJam_selesai());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtid, txttahun, txtperiode, txtkd_mk, txtnama_mk, txtkelas, txthari, txtjam_mulai, txtjam_selesai, txtpengajar;

        public ViewHolder(View itemView) {
            super(itemView);
            txttahun = (TextView) itemView.findViewById(R.id.txttahun);
            txtperiode = (TextView) itemView.findViewById(R.id.txtperiode);
            txtkd_mk = (TextView) itemView.findViewById(R.id.txtkd_mk);
            txtnama_mk = (TextView) itemView.findViewById(R.id.txtnama_mk);
            txtkelas = (TextView) itemView.findViewById(R.id.txtkelas);
            txthari = (TextView) itemView.findViewById(R.id.txthari);
            txtjam_mulai = (TextView) itemView.findViewById(R.id.txtjam_mulai);
            txtjam_selesai = (TextView) itemView.findViewById(R.id.txtjam_selesai);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PDCheck.class);
                    v.getContext().startActivity(intent);
                   // Toast.makeText(v.getContext(), "os version is: " + feed.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}