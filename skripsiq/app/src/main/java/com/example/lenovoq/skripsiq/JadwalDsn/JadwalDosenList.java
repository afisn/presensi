package com.example.lenovoq.skripsiq.JadwalDsn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovoq.skripsiq.R;

import java.util.List;

public class JadwalDosenList extends RecyclerView.Adapter<JadwalDosenList.ViewHolder> {

    private List<JDosen>list_data;

    public JadwalDosenList(List<JDosen> list_data) {
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dsn,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JDosen listData=list_data.get(position);
        //kalau integer pake string value of
        holder.txtid.setText(String.valueOf(listData.getId_jadwal()));
        holder.txttahun.setText(String.valueOf(listData.getTahun()));
        //kalau string langsung aja diset
        holder.txtperiode.setText(listData.getPeriode());
        holder.txtkd_mk.setText(listData.getKd_mk());
        holder.txtnama_mk.setText(listData.getNama_mk());
        holder.txtkelas.setText(listData.getKelas());
        holder.txthari.setText(listData.getHari());
        holder.txtjam_mulai.setText(listData.getJam_mulai());
        holder.txtjam_selesai.setText(listData.getJam_selesai());
        holder.txtpengajar.setText(listData.getPengajar());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtid,txttahun, txtperiode,txtkd_mk, txtnama_mk, txtkelas, txthari,txtjam_mulai, txtjam_selesai, txtpengajar ;
        public ViewHolder(View itemView) {
            super(itemView);
            txtid=(TextView)itemView.findViewById(R.id.txt_idjadwal);
            txttahun=(TextView)itemView.findViewById(R.id.txttahun);
            txtperiode=(TextView)itemView.findViewById(R.id.txtperiode);
            txtkd_mk=(TextView)itemView.findViewById(R.id.txtkd_mk);
            txtnama_mk=(TextView)itemView.findViewById(R.id.txtnama_mk);
            txtkelas=(TextView)itemView.findViewById(R.id.txtkelas);
            txthari=(TextView)itemView.findViewById(R.id.txthari);
            txtjam_mulai=(TextView)itemView.findViewById(R.id.txtjam_mulai);
            txtjam_selesai=(TextView)itemView.findViewById(R.id.txtjam_selesai);
            txtpengajar=(TextView)itemView.findViewById(R.id.txtpengajar);
        }
    }
}
