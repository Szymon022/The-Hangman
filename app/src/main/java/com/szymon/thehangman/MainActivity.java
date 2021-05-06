package com.szymon.thehangman;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NewGameDialogFragment.NewGameDialogFragmentListener{

    private Hangman hangman;

//    private ImageButton btRestartGame;
    private Button btEnterGuess;
    private TextView tvNewGameButton;
    private EditText etPlayerGuess;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivity();
        // once app was started user is required to enter new word to start the game
        showNewGameDialog();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initActivity() {
        hangman = new Hangman(this);

//        btRestartGame = findViewById(R.id.bt_game_restart);
        tvNewGameButton = findViewById(R.id.tv_new_game);
        btEnterGuess = findViewById(R.id.bt_enter_guess);
        etPlayerGuess = findViewById(R.id.et_user_guess);

        setOnClickListeners();
    }

    private void setOnClickListeners() {

        // in case Player wants to restart hangman
        tvNewGameButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                showNewGameDialog();
            }
        });

        // in case player wants to check his guess
        btEnterGuess.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String playerGuess = etPlayerGuess.getText().toString().toUpperCase();
                if(!playerGuess.isEmpty()) {
                    hangman.enterGuess(playerGuess.charAt(0));
                    // clear guess entrance field each time
                    etPlayerGuess.setText("");
                }
            }
        });
    }

    private void showNewGameDialog() {
        DialogFragment newGameDialog = new NewGameDialogFragment();
        newGameDialog.show(getSupportFragmentManager(), "newGame");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDialogStartClick(DialogFragment dialog) {
        // if Start button in dialog was clicked then pass given word to the game
        if(hangman != null) {
            EditText etWord = dialog.getDialog().findViewById(R.id.et_word);
            String word = etWord.getText().toString();

            hangman.newGame(word);
            dialog.dismiss();
        }
    }
}