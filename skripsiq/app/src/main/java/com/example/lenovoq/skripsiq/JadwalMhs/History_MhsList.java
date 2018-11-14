package com.example.lenovoq.skripsiq.JadwalMhs;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovoq.skripsiq.History.History_Dsn;
import com.example.lenovoq.skripsiq.R;

import java.util.List;

public class History_MhsList extends RecyclerView.Adapter<History_MhsList.ViewHolder> {

    private List<JMahasiswa> list_data;


    public History_MhsList(List<JMahasiswa> list_data) {
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.presensimhs_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final JMahasiswa listData = list_data.get(position);
        //kalau integer pake string value of
        holder.txtmet_id.setText(String.valueOf(listData.getMet_id()));
        //kalau string langsung aja diset
        holder.txtkd_mk.setText(listData.getKd_mk());
        holder.txtnama_mk.setText(listData.getNama_mk());
        holder.txtkelas.setText(listData.getKelas());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = listData.getMet_id();
                Log.e("Afis", "id" + listData.getMet_id());
                Intent intent = new Intent(view.getContext(), Rekap_Mhs.class);
                intent.putExtra("Met_id", id);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtmet_id, txtkd_mk, txtnama_mk, txtkelas;

        public ViewHolder(View itemView) {
            super(itemView);
            txtmet_id = (TextView) itemView.findViewById(R.id.txtmet_id);
            txtkd_mk = (TextView) itemView.findViewById(R.id.txtkd_mk);
            txtnama_mk = (TextView) itemView.findViewById(R.id.txtnama_mk);
            txtkelas = (TextView) itemView.findViewById(R.id.txtkelas);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), PDCheck.class);
//                    v.getContext().startActivity(intent);
//                }
//            });
        }
    }
}