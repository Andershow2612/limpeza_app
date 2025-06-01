package com.example.testefaculdade;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import com.example.testefaculdade.model.User;
import com.example.testefaculdade.service.UserService;
import com.google.android.material.textfield.TextInputEditText;
import android.view.View;


public class my_account extends AppCompatActivity {

    private long loggedUserId;
    private UserService userService;

    private TextInputEditText etName, etEmail, etAddress, etNeighborhood, etCity, etState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account2);

        // padding sistema
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.minha_conta_root),
                (v, insets) -> {
                    Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                    return insets;
                });

        // 1) recupera ID salvo
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        loggedUserId = prefs.getLong("USER_ID", -1);
        if (loggedUserId < 0) {
            Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // 2) encontra campos no layout
        etName         = findViewById(R.id.etName);
        etEmail        = findViewById(R.id.etEmail);
        etAddress      = findViewById(R.id.etAddress);
        etNeighborhood = findViewById(R.id.etNeighborhood);
        etCity         = findViewById(R.id.etCity);
        etState        = findViewById(R.id.etState);

        // 3) busca user e preenche UI
        userService = new UserService(this);
        User u = userService.getById(loggedUserId);
        if (u != null) {
            etName.setText(u.getEmail());
            etEmail.setText(u.getEmail());
            etAddress.setText(u.getAddress());
            etNeighborhood.setText(u.getNeighborhood());
            etCity.setText(u.getCity());
            etState.setText(u.getState());
        } else {
            Toast.makeText(this, "Erro ao carregar dados", Toast.LENGTH_SHORT).show();
        }
    }

    public void voltaMenu(View view) {
        finish(); // retorna à tela anterior
    }

    public void historico(View view) {
        Intent in = new Intent(my_account.this, MinhasSolicitacoesActivity.class);
        in.putExtra("USER_ID", loggedUserId);
        startActivity(in);
    }
}
