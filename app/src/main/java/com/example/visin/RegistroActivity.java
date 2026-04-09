package com.example.visin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView; // IMPORTANTE: Nueva librería
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistroActivity extends AppCompatActivity {

    // CAMBIO: Ahora es ShapeableImageView para soportar el círculo del XML
    private ShapeableImageView ivPerfil;
    private NumberPicker npAnio, npMes, npDia;

    private EditText etNombre, etPass, etDni, etDomicilio, etPoblacion, etCP;
    private Spinner spProvincia;
    private Button btnCrearCuenta;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // PASO 1: ENLAZAR ELEMENTOS
        // Aquí enlazamos con el nuevo tipo de vista circular
        ivPerfil = findViewById(R.id.ivPerfilRegistro);

        Button btnCargarFoto = findViewById(R.id.btnCargarFoto);
        npAnio = findViewById(R.id.npAnio);
        npMes = findViewById(R.id.npMes);
        npDia = findViewById(R.id.npDia);

        etNombre = findViewById(R.id.etNombre);
        etPass = findViewById(R.id.etPass);
        etDni = findViewById(R.id.etDni);
        etDomicilio = findViewById(R.id.etDomicilio);
        etPoblacion = findViewById(R.id.etPoblacion);
        etCP = findViewById(R.id.etCodigoPostal);
        spProvincia = findViewById(R.id.spProvincia);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);

        // PASO 2: CONFIGURAR SELECTORES DE FECHA
        npAnio.setMinValue(1900);
        npAnio.setMaxValue(2026);
        npAnio.setValue(2000);

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        npMes.setMinValue(0);
        npMes.setMaxValue(11);
        npMes.setDisplayedValues(meses);

        npDia.setMinValue(1);
        npDia.setMaxValue(31);

        // PASO 3: LÓGICA DE LA FOTO (Cargará la foto y el ShapeableImageView la hará circular)
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        ivPerfil.setImageURI(uri);
                    }
                }
        );

        btnCargarFoto.setOnClickListener(v -> {
            mGetContent.launch("image/*");
        });

        // PASO 4: LÓGICA DE GUARDAR EN FIREBASE
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarRegistro();
            }
        });
    }

    private void ejecutarRegistro() {
        String nombre = etNombre.getText().toString().trim();
        String password = etPass.getText().toString().trim();
        String dni = etDni.getText().toString().trim();
        String domicilio = etDomicilio.getText().toString().trim();
        String poblacion = etPoblacion.getText().toString().trim();
        String cp = etCP.getText().toString().trim();
        String provincia = (spProvincia.getSelectedItem() != null) ? spProvincia.getSelectedItem().toString() : "";

        String fechaNac = npDia.getValue() + "/" + (npMes.getValue() + 1) + "/" + npAnio.getValue();

        if (nombre.isEmpty() || dni.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Nombre, Password y DNI son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario nuevoUser = new Usuario(nombre, password, dni, fechaNac, domicilio, poblacion, cp, provincia);

        db.collection("usuarios").document(dni)
                .set(nuevoUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegistroActivity.this, "¡Cuenta Visión+ creada con éxito!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistroActivity.this, "Error al guardar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("FirebaseError", e.getMessage());
                    }
                });
    }
}