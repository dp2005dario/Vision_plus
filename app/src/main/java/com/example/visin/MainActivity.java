package com.example.visin;

import android.content.Intent;
import android.net.Uri; // Necesario para los enlaces
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView; // Necesario para los iconos de redes sociales
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- BOTÓN ENTRAR (Tu código original) ---
        Button btnEntrar = findViewById(R.id.btn_entrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // --- BOTÓN INSTAGRAM (Visión+ Oficial) ---
        ImageView btnInstagram = findViewById(R.id.btn_instagram); // Revisa que este ID coincida con tu XML
        btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/visionplusesp?igsh=MWpiYWo1OXk1MWNvNg==";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        // --- BOTÓN TIKTOK (Visión+ Oficial) ---
        ImageView btnTiktok = findViewById(R.id.btn_tiktok); // Revisa que este ID coincida con tu XML
        btnTiktok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.tiktok.com/@visionplusesp?_r=1&_t=ZN-95O3eFzCM6w";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}