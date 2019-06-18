package com.xirpla.remedial.bookinghotel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class AdapterList1 extends RecyclerView.Adapter<AdapterList1.ViewHolder> {

        Context context;
        ArrayList<HashMap<String, String>> list_data;

public AdapterList1(BelumBayarActivity listVoucherActivity, ArrayList<HashMap<String, String>> list_data) {
        this.context = listVoucherActivity;
        this.list_data = list_data;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_voucher1, null);
        return new ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtIdTransaksi.setText(list_data.get(i).get("id_transaksi"));
        viewHolder.txtNamaPemesan.setText(list_data.get(i).get("nama_lengkap"));
        viewHolder.txtNamaHotel.setText(list_data.get(i).get("nama_hotel"));

    }

@Override
public int getItemCount() {
        return list_data.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtIdTransaksi,txtNamaPemesan,txtNamaHotel,txtCI,txtCO,harga;

    Button btnDetil1;


    public ViewHolder(View itemView) {
        super(itemView);

        txtIdTransaksi = (TextView) itemView.findViewById(R.id.txtIdTransaksi);
        txtNamaPemesan = (TextView) itemView.findViewById(R.id.txtNamaPemesan);
        txtNamaHotel = (TextView) itemView.findViewById(R.id.txtNamaHotel);
        btnDetil1 = (Button) itemView.findViewById(R.id.btnDetil1);
        btnDetil1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,DetailTransaksiActivity.class);
                context.startActivity(i);
            }
        });

    };


}
    }
