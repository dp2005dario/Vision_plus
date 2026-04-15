package com.example.visin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    private TextView tvNombre, tvTituloRango, tvEmojiFuego;
    private ShapeableImageView ivPerfil;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializar Firebase
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Enlazar UI
        tvNombre = findViewById(R.id.tvNombreHome);
        tvTituloRango = findViewById(R.id.tvTituloRango);
        tvEmojiFuego = findViewById(R.id.tvEmojiFuego);
        ivPerfil = findViewById(R.id.ivPerfilHome);

        // Botones
        LinearLayout btnListado = findViewById(R.id.btnListado);
        LinearLayout btnEscanear = findViewById(R.id.btnEscanear);
        LinearLayout btnModoVision = findViewById(R.id.btnModoVision);
        Button btnSalir = findViewById(R.id.btnSalir);
        ImageView ivAjustes = findViewById(R.id.ivGestionUsuario);

        // 1. Cargar datos del usuario
        cargarDatosDesdeFirestore();

        // 2. Configurar Botón Salir
        btnSalir.setOnClickListener(v -> {
            mAuth.signOut();
            finish();
        });

        // 3. Configurar Ajustes (Gestión Usuario)
        ivAjustes.setOnClickListener(v -> {
            // Toast.makeText(this, "Ir a Gestión de Usuario", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(this, GestionUsuarioActivity.class);
            // startActivity(intent);
        });
    }

    private void cargarDatosDesdeFirestore() {
        FirebaseUser userAuth = mAuth.getCurrentUser();
        // Intentamos pillar el ID manual que pasamos desde el LoginActivity (el nombre de usuario)
        String idManual = getIntent().getStringExtra("ID_USUARIO_MANUAL");

        if (userAuth != null) {
            // CASO GOOGLE: Buscamos el documento por el Email (que es el ID que usa Google)
            String email = userAuth.getEmail();
            db.collection("usuarios").document(email).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            mostrarDatosEnPantalla(documentSnapshot, userAuth);
                        } else {
                            // Si Google no encuentra por ID de documento, busca por el campo email
                            db.collection("usuarios").whereEqualTo("email", email).get()
                                    .addOnSuccessListener(q -> {
                                        if(!q.isEmpty()) mostrarDatosEnPantalla(q.getDocuments().get(0), userAuth);
                                    });
                        }
                    });
        } else if (idManual != null) {
            // CASO MANUAL: El usuario no tiene email, buscamos por el "nombre" que es el ID del documento
            db.collection("usuarios").document(idManual).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            mostrarDatosEnPantalla(documentSnapshot, null);
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error al cargar perfil manual", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    // Método auxiliar para no repetir código al pintar la pantalla
    private void mostrarDatosEnPantalla(DocumentSnapshot doc, FirebaseUser userAuth) {
        String nombre = doc.getString("nombre");
        Long nivelLong = doc.getLong("nivel");
        int nivel = (nivelLong != null) ? nivelLong.intValue() : 0;
        String fotoUrl = doc.getString("fotoUrl");

        // Lógica de foto: 1º Firestore (Url guardada), 2º Google Auth, 3º nada
        String fotoFinal = "";
        if (fotoUrl != null && !fotoUrl.isEmpty() && !fotoUrl.equals("default")) {
            fotoFinal = fotoUrl;
        } else if (userAuth != null && userAuth.getPhotoUrl() != null) {
            fotoFinal = userAuth.getPhotoUrl().toString();
        }

        // Seteamos los textos
        tvNombre.setText(nombre != null ? nombre : "Usuario");
        tvTituloRango.setText(obtenerTituloPorNivel(nivel) + " Lvl " + nivel);

        // Cargar foto con Glide
        if (!fotoFinal.isEmpty()) {
            Glide.with(this)
                    .load(fotoFinal)
                    .circleCrop()
                    .placeholder(R.drawable.logodefecto)
                    .error(R.drawable.logodefecto)
                    .into(ivPerfil);
        } else {
            ivPerfil.setImageResource(R.drawable.logodefecto);
        }

        // Lógica de Racha
        checkRacha();
    }

    private void checkRacha() {
        boolean tieneRacha = true;
        if (tieneRacha) {
            tvEmojiFuego.setVisibility(View.VISIBLE);
        }
    }

    private String obtenerTituloPorNivel(int lvl) {
        if (lvl <= 5) return "-Novato Visionero";
        if (lvl <= 10) return "-Rastreador de etiquetas";
        if (lvl <= 15) return "-Olfateador de ofertas";
        if (lvl <= 20) return "-Consultor de pasillos inexistentes";
        if (lvl <= 25) return "-Ninja del código de barras";
        if (lvl <= 30) return "-Avistador de Objetos No Escaneados";
        if (lvl <= 35) return "-Sabueso de Ingredientes";
        if (lvl <= 40) return "-Enemigo Público de las Etiquetas Rotas";
        if (lvl <= 45) return "-Aficionado Visionero";
        if (lvl <= 50) return "-Especialista en productos manipulados";
        if (lvl <= 55) return "-Maestro del sello";
        if (lvl <= 60) return "Consultor de Perchas";
        if (lvl <= 65) return "-Ludópata de prismas";
        if (lvl <= 70) return "-Experto simulador de voces";
        if (lvl <= 75) return "-Iluminado de los lácteos";
        if (lvl <= 80) return "-Gran Arquitecto de Compras";
        if (lvl <= 85) return "-Guardián de los pasillos";
        if (lvl <= 90) return "-Interfaz Humana de Inventario";
        if (lvl <= 95) return "-Visionado Blostesiano";
        return "-MISTER BLOSTE";
    }
}