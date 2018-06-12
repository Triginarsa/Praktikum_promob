package com.example.aditriginarsa.promob1234.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aditriginarsa.promob1234.R;
import com.example.aditriginarsa.promob1234.model.PegawaiList;

import java.util.List;

public class PegawaiAdapter extends RecyclerView.Adapter<PegawaiAdapter.MyViewHolder> {
    private List<PegawaiList> pegawaiList;
    private final ListItemClick mItemClick;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView TV_lnama, TV_lemail, TV_lserius;
        Button B_edit;
        public RelativeLayout RLBacground, RLForeground;

        public MyViewHolder(View view) {
            super(view);
            TV_lnama        = itemView.findViewById(R.id.TV_lnama);
            TV_lemail       = itemView.findViewById(R.id.TV_lemail);
            TV_lserius      = itemView.findViewById(R.id.TV_lserius);
            B_edit         = itemView.findViewById(R.id.B_edit);
            RLBacground     = itemView.findViewById(R.id.view_background);
            RLForeground    = itemView.findViewById(R.id.view_foreground);
            RLForeground.setOnClickListener(this);
            B_edit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (v.getId()){
                case R.id.B_edit:
                    mItemClick.onUpdate(position);
                    break;
                case R.id.view_foreground:
                    mItemClick.onItemClick(position);
                    break;
            }
        }
    }


    public PegawaiAdapter(List<PegawaiList> pegawaiList, ListItemClick mItemClick) {
        this.pegawaiList = pegawaiList;
        this.mItemClick = mItemClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_pegawai, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.TV_lnama.setText(pegawaiList.get(position).getNama());
        holder.TV_lemail.setText(pegawaiList.get(position).getEmail());
        holder.TV_lserius.setText(pegawaiList.get(position).getSerius());
    }

    @Override
    public int getItemCount() {
        return pegawaiList.size();
    }

    public void removeItem(int position) {
        pegawaiList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyDataSetChanged();
    }

    public void CancelRemove(int position) {
//        notifyItemRemoved(position + 1);
        notifyItemRangeChanged(position, getItemCount());
    }

    public interface ListItemClick{
        void onItemClick(int position);
        void onUpdate(int position);
    }
}