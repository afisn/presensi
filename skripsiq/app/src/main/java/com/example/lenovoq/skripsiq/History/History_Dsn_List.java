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
import com.example.lenovoq.skripsiq.History.History_Dsn;
import com.example.lenovoq.skripsiq.R;

import java.util.List;

public class History_Dsn_List extends RecyclerView.Adapter<History_Dsn_List.ViewHolder> {

    private List<MatkulDosen_Obj> list_data;
    private Animation animationUp;
    private Animation animationDown;

    public History_Dsn_List(List<MatkulDosen_Obj> list_data) {
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_dosen, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(History_Dsn_List.ViewHolder holder, int position) {
        final MatkulDosen_Obj listData = list_data.get(position);
        //kalau integer pake string value of
        holder.txtmet_id.setText(String.valueOf(listData.getMet_id()));
        //kalau string langsung aja diset
        holder.txtkd_mk.setText(listData.getKd_mk());
        holder.txtnama_mk.setText(listData.getNama_mk());
        holder.txtkelas.setText(listData.getKelas());

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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(History_Dsn.txtContent.isShown()){
                    History_Dsn.txtContent.setVisibility(View.GONE);
                    History_Dsn.txtContent.startAnimation(animationUp);
                }
                else{
                    History_Dsn.txtContent.setVisibility(View.VISIBLE);
                    History_Dsn.txtContent.startAnimation(animationDown);
                }
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
            txtkd_mk = (TextView) itemView.findViewById(R.id.content_text_mk);
            txtnama_mk = (TextView) itemView.findViewById(R.id.txtnama_mk);
            txtkelas = (TextView) itemView.findViewById(R.id.txtkelas);

        }
    }
}