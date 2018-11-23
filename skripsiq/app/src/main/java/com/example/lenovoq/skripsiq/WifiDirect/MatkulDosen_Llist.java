package com.example.lenovoq.skripsiq.WifiDirect;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovoq.skripsiq.Presensi.MatkulDosen_Obj;
import com.example.lenovoq.skripsiq.R;

import java.util.List;

public class MatkulDosen_Llist extends RecyclerView.Adapter<MatkulDosen_Llist.ViewHolder> {

    private List<MatkulDosen_Obj> list_data;


    public MatkulDosen_Llist(List<MatkulDosen_Obj> list_data) {
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.presensidosen_list, parent, false);
        return new MatkulDosen_Llist.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatkulDosen_Llist.ViewHolder holder, int position) {
        final MatkulDosen_Obj listData = list_data.get(position);
        //kalau integer pake string value of
        holder.txtmet_id.setText(String.valueOf(listData.getMet_id()));
        //kalau string langsung aja diset
        holder.txtkd_mk.setText(listData.getKd_mk());
        holder.txtnama_mk.setText(listData.getNama_mk());
        holder.txtkelas.setText(listData.getKelas());
        holder.txthari.setText(listData.getHari());
        holder.txtjam_mulai.setText(listData.getJam_mulai());
        holder.txtjam_selesai.setText(listData.getJam_selesai());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = listData.getMet_id();
                Log.e("Afis", "id" + listData.getMet_id());
                Intent intent = new Intent(view.getContext(), Wifi_Main.class);
                intent.putExtra("Met_id",id);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtmet_id, txtkd_mk, txtnama_mk, txtkelas, txthari, txtjam_mulai, txtjam_selesai;

        public ViewHolder(View itemView) {
            super(itemView);
            txtmet_id = (TextView) itemView.findViewById(R.id.txtmet_id);
            txtkd_mk = (TextView) itemView.findViewById(R.id.txtkd_mk);
            txtnama_mk = (TextView) itemView.findViewById(R.id.txtnama_mk);
            txtkelas = (TextView) itemView.findViewById(R.id.txtkelas);
            txthari = (TextView) itemView.findViewById(R.id.txthari);
            txtjam_mulai = (TextView) itemView.findViewById(R.id.txtjam_mulai);
            txtjam_selesai = (TextView) itemView.findViewById(R.id.txtjam_selesai);

        }
    }
}