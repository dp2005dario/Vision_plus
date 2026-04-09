package com.example.visin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private ImageView ivPerfil;
    private NumberPicker npAnio, npMes, npDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro); // Asegúrate de que el XML se llame así

        // --- PASO 1: ENLAZAR ELEMENTOS ---
        ivPerfil = findViewById(R.id.ivPerfilRegistro);
        Button btnCargarFoto = findViewById(R.id.btnCargarFoto);
        npAnio = findViewById(R.id.npAnio);
        npMes = findViewById(R.id.npMes);
        npDia = findViewById(R.id.npDia);

        // --- PASO 2: CONFIGURAR SELECTORES DE FECHA ---

        // Año: De 1900 hasta hoy
        npAnio.setMinValue(1900);
        npAnio.setMaxValue(2026);
        npAnio.setValue(2000);

        // Mes: Usamos los nombres de los meses
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        npMes.setMinValue(0);
        npMes.setMaxValue(11);
        npMes.setDisplayedValues(meses);

        // Día: Del 1 al 31
        npDia.setMinValue(1);
        npDia.setMaxValue(31);

        // --- PASO 3: LÓGICA DE LA FOTO ---

        // Esto prepara el "explorador" para elegir la foto
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        ivPerfil.setImageURI(uri);
                    }
                }
        );

        // Al pulsar el botón, lanzamos el explorador
        btnCargarFoto.setOnClickListener(v -> {
            mGetContent.launch("image/*");
        });
    }
}
