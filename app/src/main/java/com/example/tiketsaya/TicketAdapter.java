package com.example.tiketsaya;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyTicket> myTicket;

    public TicketAdapter(Context c, ArrayList<MyTicket> p){
        context = c;
        myTicket = p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        return new MyViewHolder(LayoutInflater
                .from(context).inflate(R.layout.item_myticket, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.xnama_wisata.setText(myTicket.get(position).getNama_wisata());
        holder.xlokasi.setText(myTicket.get(position).getLokasi());
        holder.xjumlah_tiket.setText(myTicket.get(position).getJumlah_tiket() + " Tickets");
        String nama_wisata = myTicket.get(position).getNama_wisata();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoMyTicketDetails = new Intent(context, MyTicketDetailAct.class);
                gotoMyTicketDetails.putExtra("nama_wisata",nama_wisata);
                context.startActivity(gotoMyTicketDetails);
            }
        });
    }

    @Override
    public int getItemCount() {

        return myTicket.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView xnama_wisata,xlokasi,xjumlah_tiket;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            xnama_wisata = itemView.findViewById(R.id.xnama_wisata);
            xlokasi = itemView.findViewById(R.id.xlokasi);
            xjumlah_tiket = itemView.findViewById(R.id.xjumlah_tiket);
        }
    }
}
