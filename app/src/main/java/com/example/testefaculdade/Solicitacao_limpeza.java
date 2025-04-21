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

public class Solicitacao_limpeza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solicitacao_limpeza);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.solicitacao_limpeza), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void votlaMenu(View view) {
        Intent in = new Intent(this, selecao_prop.class);
        startActivity(in);
    }

    public void sucesso(View view) {
        EditText endereco = findViewById(R.id.textView21);
        EditText cidade = findViewById(R.id.textView22);
        EditText bairro = findViewById(R.id.textView23);
        EditText referencia = findViewById(R.id.textView27);
        EditText descricao = findViewById(R.id.textView28);

        boolean isValid = true;

        if (endereco.getText().toString().trim().isEmpty()) {
            endereco.setError("Campo obrigatório");
            isValid = false;
        }

        if (cidade.getText().toString().trim().isEmpty()) {
            cidade.setError("Campo obrigatório");
            isValid = false;
        }

        if (bairro.getText().toString().trim().isEmpty()) {
            bairro.setError("Campo obrigatório");
            isValid = false;
        }

        if (referencia.getText().toString().trim().isEmpty()) {
            referencia.setError("Campo obrigatório");
            isValid = false;
        }

        if (descricao.getText().toString().trim().isEmpty()) {
            descricao.setError("Campo obrigatório");
            isValid = false;
        }

        if (isValid) {
            Intent in = new Intent(this, tela_sucesso.class);
            startActivity(in);
        }
    }
}
