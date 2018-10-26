package com.example.lenovoq.skripsiq.Presensi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    public static ArrayList<NamaMhs_Obj> list_data = new ArrayList<>();
    private Context ctx;
//    NamaMhs_Obj[] mhs;

//    ItemClickListener itemClickListener;

    public PDCheck_list(ArrayList<NamaMhs_Obj> list_data) {

        this.list_data = list_data;


    }

    @Override
    public PDCheck_list.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdcheck_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PDCheck_list.ViewHolder holder, final int position) {
//        final NamaMhs_Obj mhs = mhs[position];
        holder.checkBox.setChecked(list_data.get(position).isSelected());
        holder.namamhs.setText(list_data.get(position).getNama());
        holder.nrp.setText(list_data.get(position).getNrp());
        holder.status.setText(list_data.get(position).getStatus());

//        holder.checkBox.setTag(R.integer.btnplusview, convertView);
        Log.d("Afis", "onBindViewHolder: " + list_data.get(position).isSelected());
        if (list_data.get(position).isSelected()) {

            holder.checkBox.setSelected(true);
        } else {
            holder.checkBox.setSelected(false);
        }
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Integer intpos = (Integer) holder.checkBox.getTag();
                //Toast.makeText(ctx, list_data.get(position).getNama() + " clicked!", Toast.LENGTH_SHORT).show();

                if (list_data.get(position).isSelected()) {
                    list_data.get(position).setSelected(false);
                } else {
                    list_data.get(position).setSelected(true);
                }


            }
        });
//        holder.setItemClickListener(new PDCheck_list.ItemClickListener() {
//            @Override
//            public void onItemClick(View v, int pos) {
//                CheckBox myCheckBox= (CheckBox) v;
//                SpiritualTeacher currentTeacher=teachers[pos];
//
//                if(myCheckBox.isChecked()) {
//                    currentTeacher.setSelected(true);
//                    checkedTeachers.add(currentTeacher);
//                }
//                else if(!myCheckBox.isChecked()) {
//                    currentTeacher.setSelected(false);
//                    checkedTeachers.remove(currentTeacher);
//                }
//            }
//        });


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

//    public void setSelected(int position) {
//        NamaMhs_Obj items = list_data.get(position);
//        items.setSelected(!items.isSelected());
//        notifyDataSetChanged();
//    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected CheckBox checkBox;
        private TextView namamhs, nrp, status;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.list_view_item_checkbox);
            namamhs = (TextView) itemView.findViewById(R.id.namamhstxt_list);
            nrp = (TextView) itemView.findViewById(R.id.nrptxt_list);
            status = (TextView) itemView.findViewById(R.id.txtstatus);
            checkBox.setOnClickListener(this);
        }

//        void bind(int position) {
//            // use the sparse boolean array to check
//            if (!checkBox.get(position, false)) {
//                C.setChecked(false);
//            } else {
//                mCheckedTextView.setChecked(true);
//            }
//        }

        //        public void setItemClickListener(ItemClickListener ic) {
//            this.itemClickListener = ic;
//        }
//
        @Override
        public void onClick(View view) {
//            this.itemClickListener.onItemClick(view, getLayoutPosition());
        }
    }

//    interface ItemClickListener {
//
//        void onItemClick(View v, int pos);
//    }
}
