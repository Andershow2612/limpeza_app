package com.example.testefaculdade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class selecao_prop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecao_prop);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.selecao_prop), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void solicitacao (View view) {
        Intent in = new Intent(selecao_prop.this, Solicitacao_limpeza.class);
        startActivity(in);
    }

    public void fila (View view) {
        Intent in = new Intent(selecao_prop.this, senha_virtual.class);
        startActivity(in);
    }
    public void suporte (View view) {
        Intent in = new Intent(selecao_prop.this, suporte_user.class);
        startActivity(in);
    }
}