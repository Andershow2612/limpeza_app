package com.example.testefaculdade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testefaculdade.model.Pedido;
import com.example.testefaculdade.service.PedidoService;

public class Solicitacao_limpeza extends AppCompatActivity {

    private EditText etEndereco, etCidade, etBairro, etReferencia, etDescricao;
    private PedidoService pedidoService;
    private long loggedUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solicitacao_limpeza);

        // Ajuste de padding para barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.solicitacao_limpeza),
                (v, insets) -> {
                    Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                    return insets;
                }
        );

        // 1) Vincula os campos de texto
        etEndereco   = findViewById(R.id.textView21);
        etCidade     = findViewById(R.id.textView22);
        etBairro     = findViewById(R.id.textView23);
        etReferencia = findViewById(R.id.textView27);
        etDescricao  = findViewById(R.id.textView28);

        // 2) Instancia o service de pedidos
        pedidoService = new PedidoService(this);

        // 3) Recupera o ID do usuário logado passado pela Intent
        loggedUserId = getIntent().getLongExtra("USER_ID", -1);
        if (loggedUserId < 0) {
            Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    public void votlaMenu(View view) {
        startActivity(new Intent(this, selecao_prop.class));
    }

    public void sucesso(View view) {
        boolean isValid = true;

        // Validações
        if (etEndereco.getText().toString().trim().isEmpty()) {
            etEndereco.setError("Campo obrigatório"); isValid = false;
        }
        if (etCidade.getText().toString().trim().isEmpty()) {
            etCidade.setError("Campo obrigatório"); isValid = false;
        }
        if (etBairro.getText().toString().trim().isEmpty()) {
            etBairro.setError("Campo obrigatório"); isValid = false;
        }
        if (etReferencia.getText().toString().trim().isEmpty()) {
            etReferencia.setError("Campo obrigatório"); isValid = false;
        }
        if (etDescricao.getText().toString().trim().isEmpty()) {
            etDescricao.setError("Campo obrigatório"); isValid = false;
        }
        if (!isValid) return;

        // Monta o objeto Pedido
        Pedido p = new Pedido();
        p.setAddress(etEndereco.getText().toString().trim());
        p.setCity(etCidade.getText().toString().trim());
        p.setNeighborhood(etBairro.getText().toString().trim());
        p.setReference(etReferencia.getText().toString().trim());
        p.setDescription(etDescricao.getText().toString().trim());
        p.setUserId(loggedUserId);

        // Persiste no SQLite
        long id = pedidoService.createRequest(p);
        if (id > 0) {
            Toast.makeText(this, "Solicitação criada! ID=" + id, Toast.LENGTH_SHORT).show();
            // Se quiser retornar ao menu:
            startActivity(new Intent(this, tela_sucesso.class));
            finish();
        } else {
            Toast.makeText(this, "Erro ao criar solicitação", Toast.LENGTH_SHORT).show();
        }
    }
}
