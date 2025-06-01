package com.example.testefaculdade.service;

import android.content.Context;

import com.example.testefaculdade.data.PedidoRepository;
import com.example.testefaculdade.model.Pedido;

import java.util.List;

public class PedidoService {
    private final PedidoRepository repo;

    public PedidoService(Context ctx) {
        repo = new PedidoRepository(ctx);
    }

    /**
     * Cria a solicitação de limpeza. Retorna o ID (>0) ou <=0 se falhar.
     */
    public long createRequest(Pedido p) {
        // aqui você poderia validar campos ou regras de negócio antes
        return repo.create(p);
    }

    public List<Pedido> listarPedidosDoUsuario(long userId) {
        return repo.buscarPedidosPorUserId(userId);
    }
}
