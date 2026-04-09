package com.example.visin;

import android.content.Intent; // Necesario para cambiar de pantalla
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Asegúrate de que este nombre coincida con tu XML del login crema
        setContentView(R.layout.activity_login);

        // --- CÓDIGO AÑADIDO PARA EL SALTO DE PANTALLA ---

        // 1. Buscamos el botón por su ID del XML
        Button btnCrearCuenta = findViewById(R.id.btnCrearCuentaLogin);

        // 2. Configuramos el click
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent: De esta pantalla (LoginActivity) a la de Registro (RegistroActivity)
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        // ------------------------------------------------
    }
}