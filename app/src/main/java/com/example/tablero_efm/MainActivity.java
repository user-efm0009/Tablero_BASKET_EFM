package com.example.tablero_efm;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import android.view.WindowManager;

import com.example.tablero_efm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // ATRIBUTOS PRIVADOS - PARA MARCADOR LOCAL Y VISITANTE
    private static final String EXTRA_LOCAL_SCORE = "local_score";
    private static final String EXTRA_VISITOR_SCORE = "visitor_score";

    private ActivityMainBinding binding;
    private ScoreData scoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // CONFIGURACIÓN DATABINDING

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        scoreData = new ScoreData();
        binding.setScoreData(scoreData);
        setupClickListeners(); // PARA DETECTAR CUANDO UN USUARIO HACE CLICK EN UN BOTON O ELEMENTO DE LA INTERFAZ

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupClickListeners() {

        // BOTONES EQUIPO LOCAL

        binding.localMinusBtn.setOnClickListener(v -> actualizarMarcadorLocal(-1));
        binding.localPlus1Btn.setOnClickListener(v -> actualizarMarcadorLocal(1));
        binding.localPlus2Btn.setOnClickListener(v -> actualizarMarcadorLocal(2));

        // BOTONES EQUIPO VISITANTE

        binding.visitorMinusBtn.setOnClickListener(v -> actualizarMarcadorVisitante(-1));
        binding.visitorPlus1Btn.setOnClickListener(v -> actualizarMarcadorVisitante(1));
        binding.visitorPlus2Btn.setOnClickListener(v -> actualizarMarcadorVisitante(2));

        // BOTONES PARA REINICIAR Y VER RESULTADOS

        binding.resetBtn.setOnClickListener(v -> reiniciarMarcadores());
        binding.resultsBtn.setOnClickListener(v -> verResultados());
    }

    // ACTUALIZACIÓN DEL MARCADOR LOCAL

    private void actualizarMarcadorLocal(int points) {

        int newScore = scoreData.getLocalScore() + points;

        // CONDICIÓN PARA ACTUALIZAR EL MARCADOR LOCAL CUANDO SE PULSA CLICK
        // (EL MARCADOR NO PUEDE SER NEGATIVO)

        if (newScore >= 0) {

            scoreData.setLocalScore(newScore);
            binding.setScoreData(scoreData);

        }

    }

    // ACTUALIZACIÓN DEL MARCADOR VISITANTE
    private void actualizarMarcadorVisitante(int points) {

        int newScore = scoreData.getVisitorScore() + points;

        // CONDICIÓN PARA ACTUALIZAR EL MARCADOR VISITANTE CUANDO SE PULSA CLICK
        // (EL MARCADOR NO PUEDE SER NEGATIVO)

        if (newScore >= 0) {

            scoreData.setVisitorScore(newScore);
            binding.setScoreData(scoreData);

        }

    }

    // METODO PARA REINICIAR LOS MARCADORES

    private void reiniciarMarcadores() {

        scoreData.setLocalScore(0);
        scoreData.setVisitorScore(0);
        binding.setScoreData(scoreData);

    }

    // METODO PARA VER LOS RESULTADOS FINALES

    private void verResultados() {

        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra(EXTRA_LOCAL_SCORE, scoreData.getLocalScore());
        intent.putExtra(EXTRA_VISITOR_SCORE, scoreData.getVisitorScore());
        startActivity(intent);

    }

    // CLASE INDEPENDIENTE PARA LOS MARCADORES

    public static class ScoreData {

        // ATRIBUTOS PRIVADOS
        private int localScore;
        private int visitorScore;

        // CONSTRUCTOR VACÍO

        public ScoreData() {

            this.localScore = 0;
            this.visitorScore = 0;

        }

        // CONSTRUCTOR COMPLETO

        public ScoreData(int localScore, int visitorScore) {

            this.localScore = localScore;
            this.visitorScore = visitorScore;

        }

        // LOCALSCORE - GETTERS Y SETTERS

        public int getLocalScore() {

            return localScore;

        }
        public void setLocalScore(int localScore) {

            this.localScore = localScore;

        }

        // VISITORSCORE - GETTERS Y SETTERS

        public int getVisitorScore() {

            return visitorScore;

        }
        public void setVisitorScore(int visitorScore) {

            this.visitorScore = visitorScore;

        }

    }

}