package com.example.lenovoq.skripsiq.History;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.lenovoq.skripsiq.Presensi.MatkulDosen_Obj;
import com.example.lenovoq.skripsiq.Presensi.PDCheck;
import com.example.lenovoq.skripsiq.R;

import java.util.List;

public class Rekap_List extends RecyclerView.Adapter<Rekap_List.ViewHolder> {

    private List<Rekapan_Obj> list_data;

    public Rekap_List(List<Rekapan_Obj> list_data) {
        this.list_data = list_data;
    }

    @Override
    public Rekap_List.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rekapan_dsn, parent, false);
        return new Rekap_List.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Rekap_List.ViewHolder holder, int position) {
        final Rekapan_Obj listData = list_data.get(position);
        //kalau integer pake string value of
        holder.itemper.setText(String.valueOf(listData.getPertemuan()));
        holder.itemhadir.setText(String.valueOf(listData.getHadir()));
        holder.itemsakit.setText(String.valueOf(listData.getSakit()));
        holder.itemijin.setText(String.valueOf(listData.getIjin()));
        holder.itemtugas.setText(String.valueOf(listData.getTugas()));
        holder.itemalpa.setText(String.valueOf(listData.getAlpa()));
        //kalau string langsung aja diset
        holder.itemmateri.setText(listData.getMateri());
        holder.itemmetode.setText(listData.getMetode());
        holder.itemcat.setText(listData.getCatatan());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int id = listData.getMet_id();
//                Log.e("Afis", "id" + listData.getMet_id());
//                Intent intent = new Intent(view.getContext(), PDCheck.class);
//                intent.putExtra("Met_id",id);
//                view.getContext().startActivity(intent);
//            }
//        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(History_Dsn.txtContent.isShown()){
//                    History_Dsn.txtContent.setVisibility(View.GONE);
//                    History_Dsn.txtContent.startAnimation(animationUp);
//                }
//                else{
//                    History_Dsn.txtContent.setVisibility(View.VISIBLE);
//                    History_Dsn.txtContent.startAnimation(animationDown);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemper, itemhadir, itemsakit, itemijin, itemtugas, itemalpa,itemmetode, itemmateri, itemcat ;

        public ViewHolder(View itemView) {
            super(itemView);
            itemper = (TextView) itemView.findViewById(R.id.itemPertemuan);
            itemhadir = (TextView) itemView.findViewById(R.id.item_counter);
            itemsakit = (TextView) itemView.findViewById(R.id.item_counter_sakit);
            itemijin = (TextView) itemView.findViewById(R.id.item_counter_ijin);
            itemtugas = (TextView) itemView.findViewById(R.id.item_counter_tugas);
            itemalpa = (TextView) itemView.findViewById(R.id.item_counter_alpa);
            itemmetode = (TextView) itemView.findViewById(R.id.txtmetodenya);
            itemmateri = (TextView) itemView.findViewById(R.id.txtmaterinya);
            itemcat = (TextView) itemView.findViewById(R.id.txtketnya);

        }
    }
}