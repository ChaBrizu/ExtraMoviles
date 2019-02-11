package com.chabrizu.rest.extrainventario.baseDatos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chabrizu.rest.extrainventario.EditProducto;
import com.chabrizu.rest.extrainventario.R;

import java.util.ArrayList;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ViewHolderProducto> {
    ArrayList<Producto> listaProducto;
    private ClicListener mClicListener;

    public AdaptadorProducto(ArrayList<Producto> lista, ClicListener clicListener){
        this.listaProducto=lista;
        this.mClicListener=clicListener;
    }

    @Override
    public ViewHolderProducto onCreateViewHolder(ViewGroup parent, int i) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_producto,null,false);
        return new ViewHolderProducto(vista,mClicListener);

    }

    @Override
    public void onBindViewHolder(ViewHolderProducto holderProducto, int i) {
        holderProducto.idProd.setText(listaProducto.get(i).getId_producto());
        holderProducto.nomProd.setText(listaProducto.get(i).getNombre());
        holderProducto.preProd.setText(Float.toString(listaProducto.get(i).getPrecio()));
        holderProducto.modProd.setText(listaProducto.get(i).getModelo());
        holderProducto.descProd.setText(listaProducto.get(i).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listaProducto.size();
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView idProd, nomProd, preProd, modProd, descProd;
        public View vista;

        ClicListener clicListener;

        public ViewHolderProducto(View itemView, ClicListener clicListener) {
            super(itemView);
            vista=itemView;

            idProd=(TextView) itemView.findViewById(R.id.idProducto);
            nomProd=(TextView) itemView.findViewById(R.id.nomProducto);
            preProd=(TextView) itemView.findViewById(R.id.precioProducto);
            modProd=(TextView) itemView.findViewById(R.id.modProducto);
            descProd=(TextView) itemView.findViewById(R.id.descProducto);

            this.clicListener=clicListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clicListener.onCardClick(getAdapterPosition());
        }
    }

    public interface ClicListener{
        void onCardClick(int pos);
    }
}
