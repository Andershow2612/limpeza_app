package com.example.testefaculdade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testefaculdade.model.User;
import com.example.testefaculdade.service.UserService;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etEndereco, etBairro, etCidade, etEstado;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_conta);

        etEmail    = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etEndereco = findViewById(R.id.address);
        etBairro   = findViewById(R.id.neighborhood);
        etCidade   = findViewById(R.id.city);
        etEstado   = findViewById(R.id.state);

        // usar apenas UserService
        userService = new UserService(this);
    }

    public void onCreateAccountClick(View view) {
        if (etEmail.getText().toString().trim().isEmpty() ||
                etPassword.getText().toString().trim().isEmpty() ||
                etEndereco.getText().toString().trim().isEmpty() ||
                etBairro.getText().toString().trim().isEmpty() ||
                etCidade.getText().toString().trim().isEmpty() ||
                etEstado.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        User u = new User();
        u.setEmail(etEmail.getText().toString().trim());
        u.setPassword(etPassword.getText().toString().trim());
        u.setAddress(etEndereco.getText().toString().trim());
        u.setNeighborhood(etBairro.getText().toString().trim());
        u.setCity(etCidade.getText().toString().trim());
        u.setState(etEstado.getText().toString().trim());

        long newId = userService.createAccount(u);
        if (newId > 0) {
            Toast.makeText(this, "Conta criada! ID = " + newId, Toast.LENGTH_SHORT).show();
            // opcional: navegar para tela de login futura
            // startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Erro ao criar conta", Toast.LENGTH_SHORT).show();
        }
    }
}
