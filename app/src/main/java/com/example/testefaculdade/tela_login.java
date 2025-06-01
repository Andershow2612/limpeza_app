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

import com.example.testefaculdade.model.User;
import com.example.testefaculdade.service.UserService;

public class tela_login extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.cardView),
                (v, insets) -> {
                    Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                    return insets;
                }
        );

        etEmail    = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        userService = new UserService(this);
    }

    /** Chamado pelo android:onClick="loadingPage" */
    public void loadingPage(View view) {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Campo obrigatório");
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Campo obrigatório");
            return;
        }

        User logged = userService.login(email, password);
        if (logged != null) {
            // Salva o ID do usuário em SharedPreferences
            getSharedPreferences("app_prefs", MODE_PRIVATE)
                    .edit()
                    .putLong("USER_ID", logged.getId())
                    .apply();

            Toast.makeText(this, "Bem-vindo, " + logged.getEmail(), Toast.LENGTH_SHORT).show();
            // Vai para a tela de seleção (selecao_prop)
            Intent in = new Intent(this, selecao_prop.class);
            startActivity(in);
            finish();
        } else {
            Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}
