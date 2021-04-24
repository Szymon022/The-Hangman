package com.szymon.thehangman;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton btRestartGame;
    private Button btEnterGuess;

    private Hangman hangman;
    private UI gameUI;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initActivity() {
        hangman = new Hangman();
        gameUI = new UI(this, hangman);

        btRestartGame = findViewById(R.id.bt_game_restart);
        btEnterGuess = findViewById(R.id.bt_enter_guess);

        setOnClickListeners();
    }

    private void setOnClickListeners() {

        // in case Player wants to restart hangman
        btRestartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO hangman restart action
            }
        });

        // in case player wants to check his guess
        btEnterGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hangman.enterGuess(gameUI.getPlayerGuess());


            }
        });
    }
}