package com.example.testefaculdade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class criar_conta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_conta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.criar_conta_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Tela_login (View view) {
        EditText email = findViewById(R.id.textView6);
        EditText password = findViewById(R.id.textView13);
        EditText confirmPassword = findViewById(R.id.textView14);

        boolean isValid = true;

        if (email.getText().toString().trim().isEmpty()) {
            email.setError("Campo obrigatório");
            isValid = false;
        }
        if (password.getText().toString().trim().isEmpty()) {
            password.setError("Campo obrigatório");
            isValid = false;
        }
        if (confirmPassword.getText().toString().trim().isEmpty()) {
            confirmPassword.setError("Campo obrigatório");
            isValid = false;
        }

        if (isValid) {
            Intent in = new Intent(criar_conta.this, SecondActivity.class);
            startActivity(in);

        }
    }
}