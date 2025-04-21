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

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cardView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void loadingPage (View view) {
        EditText email = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);

        boolean isValid = true;

        if (email.getText().toString().trim().isEmpty()){
            email.setError("Campo obrigatório");
            isValid = false;
        }

        if (password.getText().toString().trim().isEmpty()){
            password.setError("Campo obrigatório");
            isValid = false;
        }

        if (isValid){
            Intent in = new Intent(SecondActivity.this, loading_page.class);
            startActivity(in);
        }
    }

}