package com.example.testefaculdade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testefaculdade.R;
import com.example.testefaculdade.data.DbHelper;
import com.example.testefaculdade.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private List<Pedido> pedidos;
    private List<Pedido> pedidosFiltrados;

    public PedidoAdapter(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        this.pedidosFiltrados = new ArrayList<>(pedidos);
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = pedidosFiltrados.get(position);
        holder.tvEndereco.setText(pedido.getAddress());
        holder.tvDescricao.setText(pedido.getDescription());
        holder.tvData.setText(pedido.getCreatedAt());

        holder.btnApagar.setOnClickListener(v -> {
            // Pega o contexto para acessar o DbHelper
            Context context = v.getContext();
            DbHelper dbHelper = new DbHelper(context);

            boolean apagou = dbHelper.apagarPedido(pedido.getId());
            if (apagou) {
                pedidos.remove(pedido);
                pedidosFiltrados.remove(pedido);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, pedidosFiltrados.size());
            } else {
                Toast.makeText(context, "Erro ao apagar o pedido", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pedidosFiltrados.size();
    }

    public void filtrar(String texto) {
        pedidosFiltrados.clear();
        if (texto.isEmpty()) {
            pedidosFiltrados.addAll(pedidos);
        } else {
            texto = texto.toLowerCase();
            for (Pedido p : pedidos) {
                if (p.getDescription().toLowerCase().contains(texto) ||
                        p.getAddress().toLowerCase().contains(texto)) {
                    pedidosFiltrados.add(p);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView tvEndereco, tvDescricao, tvData;
        Button btnApagar;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEndereco = itemView.findViewById(R.id.tvEndereco);
            tvDescricao = itemView.findViewById(R.id.tvDescricao);
            tvData = itemView.findViewById(R.id.tvData);
            btnApagar = itemView.findViewById(R.id.btnApagar);
        }
    }
}
