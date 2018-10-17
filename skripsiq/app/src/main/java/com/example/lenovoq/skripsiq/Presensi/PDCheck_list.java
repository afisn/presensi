package com.example.lenovoq.skripsiq.Presensi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovoq.skripsiq.R;

import java.util.ArrayList;
import java.util.List;

public class PDCheck_list extends RecyclerView.Adapter<PDCheck_list.ViewHolder> {
    private LayoutInflater inflater;
    public static List<NamaMhs_Obj> list_data;
    private Context ctx;

    public PDCheck_list(List<NamaMhs_Obj> list_data) {
        inflater = LayoutInflater.from(ctx);
        this.list_data = list_data;
        this.ctx = ctx;

    }

    @Override
    public PDCheck_list.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pdcheck_list, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final PDCheck_list.ViewHolder holder, int position) {

        holder.checkBox.setText("Checkbox " + position);
        holder.checkBox.setChecked(list_data.get(position).getSelected());
        holder.namamhs.setText(list_data.get(position).getNama());
        holder.nrp.setText(list_data.get(position).getNrp());

        // holder.checkBox.setTag(R.integer.btnplusview, convertView);
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer intpos = (Integer) holder.checkBox.getTag();
                Toast.makeText(ctx, list_data.get(intpos).getNama() + " clicked!", Toast.LENGTH_SHORT).show();

                if (list_data.get(intpos).getSelected()) {
                    list_data.get(intpos).setSelected(false);
                } else {
                    list_data.get(intpos).setSelected(true);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        protected CheckBox checkBox;
        private TextView namamhs, nrp;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.list_view_item_checkbox);
            namamhs = (TextView) itemView.findViewById(R.id.namamhstxt_list);
            nrp = (TextView) itemView.findViewById(R.id.nrptxt_list);
        }

    }
}
