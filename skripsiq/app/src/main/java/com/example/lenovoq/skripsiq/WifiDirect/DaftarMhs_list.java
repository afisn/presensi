package com.example.lenovoq.skripsiq.WifiDirect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovoq.skripsiq.Presensi.NamaMhs_Obj;
import com.example.lenovoq.skripsiq.Presensi.PDCheck_list;
import com.example.lenovoq.skripsiq.R;

import java.util.ArrayList;

public class DaftarMhs_list extends RecyclerView.Adapter<DaftarMhs_list.ViewHolder> {

    public static ArrayList<NamaMhs_Obj> list_data = new ArrayList<>();
    private Context ctx;
//    NamaMhs_Obj[] mhs;

//    ItemClickListener itemClickListener;

    public DaftarMhs_list(ArrayList<NamaMhs_Obj> list_data) {

        this.list_data = list_data;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_mhslist, parent, false);
        return new DaftarMhs_list.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DaftarMhs_list.ViewHolder holder, final int position) {
//        final NamaMhs_Obj mhs = mhs[position];
        holder.namamhs.setText(list_data.get(position).getNrp());
        holder.nrp.setText(list_data.get(position).getNama());
//        holder.status.setText(list_data.get(position).getStatus());

//        holder.checkBox.setTag(R.integer.btnplusview, convertView);

    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (list_data != null) {
            ret = list_data.size();
        }
        return ret;
    }


    @Override
    public long getItemId(int itemIndex) {
        return itemIndex;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView namamhs, nrp;

        public ViewHolder(View itemView) {
            super(itemView);
            namamhs = (TextView) itemView.findViewById(R.id.namaisi);
            nrp = (TextView) itemView.findViewById(R.id.nrpisi);
        }

        @Override
        public void onClick(View view) {
//            this.itemClickListener.onItemClick(view, getLayoutPosition());
        }
    }

}