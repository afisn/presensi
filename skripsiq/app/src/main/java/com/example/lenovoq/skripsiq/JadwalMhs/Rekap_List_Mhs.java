package com.example.lenovoq.skripsiq.JadwalMhs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovoq.skripsiq.R;

import java.util.List;

public class Rekap_List_Mhs extends RecyclerView.Adapter<Rekap_List_Mhs.ViewHolder> {

    private List<RekapMhs_Obj> list_data;

    public Rekap_List_Mhs(List<RekapMhs_Obj> list_data) {
        this.list_data = list_data;
    }

    @Override
    public Rekap_List_Mhs.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rekap_mhs, parent, false);
        return new Rekap_List_Mhs.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Rekap_List_Mhs.ViewHolder holder, int position) {
        final RekapMhs_Obj listData = list_data.get(position);
        //kalau integer pake string value of
        holder.txtper.setText(String.valueOf(listData.getPertemuan()));
        //kalau string langsung aja diset
        holder.txttgl.setText(listData.getTanggal());
        holder.txtmateri.setText(listData.getMateri());
        holder.itemstatus.setText(listData.getStatus());
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtper,txttgl, txtmateri, itemstatus ;

        public ViewHolder(View itemView) {
            super(itemView);
            txtper = (TextView) itemView.findViewById(R.id.itemPertemuan);
            txttgl = (TextView) itemView.findViewById(R.id.judultgl);
            txtmateri = (TextView) itemView.findViewById(R.id.txtmaterinya);
            itemstatus = (TextView) itemView.findViewById(R.id.item_counter_ket);

        }
    }
}