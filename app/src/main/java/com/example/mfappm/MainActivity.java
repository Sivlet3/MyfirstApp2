package com.example.mfappm;
//importacion de paquetes y librerias

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etMensaje;
    private TextView tvEstadoRespuesta;
    private ActivityResultLauncher<Intent> launcherActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMensaje = findViewById(R.id.etMensaje);
        tvEstadoRespuesta = findViewById(R.id.tvEstadoRespuesta);
        Button btnEnviar = findViewById(R.id.btnEnviar);

        // Registro del callback para recibir la respuesta de Activity2
        //Registro "registerForActivityResult" lo que hace es que notifica al ciclo de vida de android que pantalla 1(está) esta esperando una respuesta de 2
        launcherActivity2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String estado = result.getData().getStringExtra("ESTADO_RESPUESTA");
                        tvEstadoRespuesta.setText("Estado devuelto: " + estado);
                    }
                }
        );
        //StartActivityForResult es algo como un contrato, este hace lo de un contrato especifica que tipo de accion se espera:
// el bloque de result  es el que se queda en pausa espetando a que la pantalla 2 ejecute una accion para finalisar el estado y ps genera le respuesta dicha.


        //aqui ps el sistema escucha por asi decirlo los click de los botones y dependiendo de cual clikeemos envia una respuesta a pantalla 1
        btnEnviar.setOnClickListener(v -> {
            String textoMensaje = etMensaje.getText().toString();
            Intent intent = new Intent(MainActivity.this, Pantalla2.class);
            intent.putExtra("MENSAJE_ENVIADO", textoMensaje);
            launcherActivity2.launch(intent);
        });
    }
}

// los intent nos ayudan a crear un paquete de datos vacios para enviar la respuesta de regreso
// el putextra empaca la respuesta en ese paquete vacio bajo la clave denomidada(estado respuesta)
//por ultimo el setresultado marca como exitoso en la pantalla 1 y el finish destruye la actividad actual para regresar a pantalla 1 y capturar la respuesta