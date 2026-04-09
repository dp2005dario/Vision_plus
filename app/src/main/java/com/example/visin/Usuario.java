package com.example.visin;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    // 1. Atributos Directos del Formulario (image_5.png)
    public String nombre;
    public String password; // ¡OJO! Más adelante habrá que encriptarlo por seguridad
    public String dni;
    public String fechaNacimiento; // Guardaremos "AAAA-MM-DD"
    public String domicilio;
    public String poblacion;
    public String codigoPostal;
    public String provincia;
    public String fotoUrl; // La dirección de la foto en Firebase Storage

    // 2. Atributos de Progresión y Gamificación (image_6.png y image_7.png)
    public int nivel;         // Empezará en 1
    public int prismas;       // Moneda, empezará en 0
    public int productosEscaneadosTotal; // Para calcular el nivel
    public boolean rachaActiva; // El emoji de fuego (falso al principio)
    public int diasRacha;      // Para saber cuántos días seguidos lleva

    // 3. Atributos de Configuración y Futuro
    public boolean modoVisionPlus; // El botón amarillo, empezará en false
    public boolean asistenteVozActivo; // Para el asistente de voz futuro (true por defecto)
    public String asistenteVozSeleccionado; // Ej: "Elena", "Pablo"...
    public List<String> asistentesComprados; // Lista de IDs de asistentes que posee

    // Constructor vacío requerido por Firebase
    public Usuario() {}

    // Constructor para el registro inicial
    public Usuario(String nombre, String password, String dni, String fechaNacimiento,
                   String domicilio, String poblacion, String codigoPostal, String provincia) {
        this.nombre = nombre;
        this.password = password;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.poblacion = poblacion;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;

        // Valores por defecto para un usuario nuevo
        this.fotoUrl = "default"; // O la URL de la imagen circular por defecto
        this.nivel = 1;
        this.prismas = 0;
        this.productosEscaneadosTotal = 0;
        this.rachaActiva = false;
        this.diasRacha = 0;
        this.modoVisionPlus = false;
        this.asistenteVozActivo = true;
        this.asistenteVozSeleccionado = "Elena"; // Asistente por defecto
        this.asistentesComprados = new ArrayList<>();
        this.asistentesComprados.add("Elena"); // Ya tiene el gratuito
    }

    // --- LÓGICA DE NIVELES (image_6.png) ---
    // Inventando nombres cada 5 niveles
    public String getRango() {
        if (nivel < 5) return "Iniciado Visionero";
        if (nivel < 10) return "Explorador de Símbolos";
        if (nivel < 15) return "Rastreador de Precios";
        if (nivel < 20) return "Cazador de Ofertas";
        if (nivel < 25) return "Maestro del Escaneo";
        if (nivel < 30) return "Visionero Experto";
        if (nivel < 35) return "Aficionado Visionero (Lvl 35)"; // Como en tu foto
        if (nivel < 40) return "Leyenda de los Prismas";
        return "Visión+ Suprema";
    }
}