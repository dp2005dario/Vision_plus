package com.example.visin;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity; // Importante

// TIENES QUE AÑADIR LO QUE ESTÁ EN NEGRITA:
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Asegúrate de que este nombre coincida con tu XML del login crema
        setContentView(R.layout.activity_login);
    }
}