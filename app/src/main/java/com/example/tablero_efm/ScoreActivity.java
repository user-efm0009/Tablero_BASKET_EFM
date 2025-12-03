package com.example.tablero_efm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.tablero_efm.databinding.ActivityScoreBinding;
import com.example.tablero_efm.MainActivity.ScoreData;

public class ScoreActivity extends AppCompatActivity {

    private ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_score);

        // RESULTADO POR DEFECTO SI EXISTIERA UN ERROR

        Intent intent = getIntent();
        int localScore = intent.getIntExtra("local_score", 0);
        int visitorScore = intent.getIntExtra("visitor_score", 0);

        // ACTUALIZACIÓN DEL RESULTADO (LOS VALORES QUE LE HEMOS INDICADO A LA APLICACIÓN, POR EJEMPLO, 2-1)

        ScoreData scoreData = new ScoreData(localScore, visitorScore);
        binding.setScoreData(scoreData);

        // RESULTADO FINAL DEL PARTIDO - ESTO PERTENECE A LA SEGUNDA INTERFAZ

        String resultMessage = mensajesResultados(localScore, visitorScore);
        binding.setResultMessage(resultMessage);

        // PARA VOLVER A LA PANTALLA PRINCIPAL AL HACER CLICK

        binding.backButton.setOnClickListener(v -> finish());
    }

    private String mensajesResultados(int localScore, int visitorScore) {

        if (localScore > visitorScore) {

            return "Victoria del equipo local"; // RESULTADO POR PANTALLA SI GANA LOCAL

        } else if (visitorScore > localScore) {

            return "Victoria del equipo visitante"; // RESULTADO POR PANTALLA SI GANA VISITANTE

        } else {

            return "Empate"; // RESULTADO POR PANTALLA SI EMPATAN

        }

    }

}