package com.xirpla.remedial.bookinghotel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListProdukAdapter extends RecyclerView.Adapter<ListProdukAdapter.CategoryViewHolder> {
    public ListProdukAdapter(Context context) {
        this.context = context;
    }

    private Context context;

    public ArrayList<Produk> getListPresident() {
        return listPresident;
    }

    public void setListPresident(ArrayList<Produk> listPresident) {
        this.listPresident = listPresident;
    }

    private ArrayList<Produk> listPresident;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_president, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int position) {
        categoryViewHolder.tvName.setText(getListPresident().get(position).getName());
        categoryViewHolder.tvRemarks.setText(getListPresident().get(position).getRemarks());
        categoryViewHolder.tvHarga.setText(getListPresident().get(position).getHarga());
        Glide.with(context)
                .load(getListPresident().get(position).getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(categoryViewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getListPresident().size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvRemarks;
        TextView tvHarga;
        ImageView imgPhoto;
        Button btnPesan;
        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRemarks = itemView.findViewById(R.id.tv_item_remarks);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            imgPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    switch (getAdapterPosition()){
                        case 0 :
                            intent = new Intent(context, DetailActivity.class);
                            break;
                        case 1 :
                            intent = new Intent(context, DetailActivity1.class);
                            break;
                        case 2 :
                            intent = new Intent(context, DetailActivity2.class);
                            break;
                    }
                    context.startActivity(intent);
                }
            });
            btnPesan = itemView.findViewById(R.id.btnPesan);
            btnPesan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    switch (getAdapterPosition()){
                        case 0 :
                            intent = new Intent(context, PesanActivity.class);
                            break;
                        case 1 :
                            intent = new Intent(context, PesanActivity1.class);
                            break;
                        case 2 :
                            intent = new Intent(context, PesanActivity2.class);
                            break;
                    }
                    context.startActivity(intent);
                }
            });

        }
    }

}
