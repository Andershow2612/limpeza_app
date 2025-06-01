package com.example.testefaculdade;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testefaculdade.R;
import com.example.testefaculdade.model.Pedido;
import com.example.testefaculdade.service.AuthService;
import com.example.testefaculdade.service.PedidoService;

import java.util.List;

public class MinhasSolicitacoesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PedidoAdapter adapter;
    private PedidoService pedidoService;
    private long userIdLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_history);

        recyclerView = findViewById(R.id.rvSolicitacoes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Pega o ID do usuário autenticado
        userIdLogado = AuthService.getCurrentUserId(this); // você precisa ter esse método

        pedidoService = new PedidoService(this);
        List<Pedido> pedidos = pedidoService.listarPedidosDoUsuario(userIdLogado);

        adapter = new PedidoAdapter(pedidos);
        recyclerView.setAdapter(adapter);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());

        EditText etBuscar = findViewById(R.id.etBuscar);
        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filtrar(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }


}
