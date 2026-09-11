package com.example.mfappm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Pantalla2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2); // hacemos llamado al layout de pantalla2 y cargamos el diseño

        TextView tvMensajeRecibido = findViewById(R.id.tvMensajeRecibido);
        Button btnRecibir = findViewById(R.id.btnRecibir);
        Button btnCancelar = findViewById(R.id.btnCancelar);

        // Obtiene el mensaje enviado por MainActivity, el hasExtra verifica si la pantalla 1 envio datos tipo texto bajo la clave de mensaje enviado
        Intent intentEntrante = getIntent();
        if (intentEntrante != null && intentEntrante.hasExtra("MENSAJE_ENVIADO")) {
            String mensaje = intentEntrante.getStringExtra("MENSAJE_ENVIADO");
            tvMensajeRecibido.setText("Mensaje recibido: " + mensaje);
        }
//por ultimo el get ps obviamente extrae el texto y lo imprime en pantalla



        // Config btt de acciones
        btnRecibir.setOnClickListener(v -> devolverResultado("recibido"));
        btnCancelar.setOnClickListener(v -> devolverResultado("cancelado"));
    }

    private void devolverResultado(String respuesta) {
        Intent intentSalida = new Intent();
        intentSalida.putExtra("ESTADO_RESPUESTA", respuesta);
        setResult(RESULT_OK, intentSalida);
        finish(); // Cierra esta pantalla y vuelve a MainActivity
    }
}