package com.example.testefaculdade;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class selecao_prop extends AppCompatActivity {

    private long loggedUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecao_prop);
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.selecao_prop),
                (v, insets) -> {
                    Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                    return insets;
                }
        );

        // Recupera o ID do usuário logado
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        loggedUserId = prefs.getLong("USER_ID", -1);
        if (loggedUserId < 0) {
            Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void solicitacao(View view) {
        Intent in = new Intent(selecao_prop.this, Solicitacao_limpeza.class);
        in.putExtra("USER_ID", loggedUserId);
        startActivity(in);
    }

    public void fila(View view) {
        Intent in = new Intent(selecao_prop.this, senha_virtual.class);
        startActivity(in);
    }

    public void suporte(View view) {
        Intent in = new Intent(selecao_prop.this, suporte_user.class);
        startActivity(in);
    }

    public void myAccount(View view) {
        Intent in = new Intent(selecao_prop.this, my_account.class);
        startActivity(in);
    }
}
