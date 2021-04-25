package com.szymon.thehangman;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Hangman hangman;

    private ImageButton btRestartGame;
    private Button btEnterGuess;
    private EditText etPlayerGuess;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO new game after entering application
        initActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initActivity() {
        hangman = new Hangman(this);

        btRestartGame = findViewById(R.id.bt_game_restart);
        btEnterGuess = findViewById(R.id.bt_enter_guess);
        etPlayerGuess = findViewById(R.id.et_user_guess);

        setOnClickListeners();
    }

    private void setOnClickListeners() {

        // in case Player wants to restart hangman
        btRestartGame.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                // TODO hangman restart action - dialog with new word to enter by user
                hangman.newGame("android");
            }
        });

        // in case player wants to check his guess
        btEnterGuess.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                char playerGuess = etPlayerGuess.getText().toString().toUpperCase().charAt(0);
                hangman.enterGuess(playerGuess);
                // clear input field each time
                etPlayerGuess.setText("");
            }
        });
    }
}