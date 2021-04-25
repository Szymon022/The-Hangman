package com.szymon.thehangman;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;


public class UI {

    // UI contents:
    private TextView tvLivesLeft;
    private TextView tvAnswer;
    private ImageView ivGallows;

    private Activity activity;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    UI(Activity activity, Hangman hangmanGame) {
        // activity instance is necessary to access mainActivity layout
        this.activity = activity;
        setContentView();
        update(hangmanGame);
    }


    /**
     * Initializes UI elements.
     */
    private void setContentView() {
        // setting all ids of views
        tvLivesLeft     = activity.findViewById(R.id.tv_lives_left);
        tvAnswer        = activity.findViewById(R.id.tv_answer);
        ivGallows       = activity.findViewById(R.id.iv_gallows_pole);
        System.out.println("UI elements successfully initialized!");
    }

    /**
     * Updates UI with data provided in gameInstance.
     * @param gameInstance
     */

    // it is necessary as getDrawable() isn't supported in earlier Android versions
    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    public void update(Hangman gameInstance) {
        // set current lives left
        String currentLives = Integer.toString(gameInstance.getLivesLeft());
        tvLivesLeft.setText(currentLives);

        // set current answer
        String currentAnswerState = genAnswerString(gameInstance.getAnswer());
        tvAnswer.setText(currentAnswerState);

        // TODO Create better Gallows pole images
        // updates gallows pole image based on current lives
        Drawable gallowsDrawable = null;
        switch(gameInstance.getLivesLeft()) {
            case 5:
                gallowsDrawable = activity.getDrawable(R.drawable.img_lives_5);
                break;
            case 4:
                gallowsDrawable = activity.getDrawable(R.drawable.img_lives_4);
                break;
            case 3:
                gallowsDrawable = activity.getDrawable(R.drawable.img_lives_3);
                break;
            case 2:
                gallowsDrawable = activity.getDrawable(R.drawable.img_lives_2);
                break;
            case 1:
                gallowsDrawable = activity.getDrawable(R.drawable.img_lives_1);
                break;
            case 0:
                gallowsDrawable = activity.getDrawable(R.drawable.img_lives_0);
                break;
            default:
                System.out.println("Error in getting gallows drawable");
            break;
        }
        ivGallows.setImageDrawable(gallowsDrawable);
    }

    /**
     * Generates answer suitable to be shown in TextView.
     * @param answer
     * @return String "_ _ _   _ _ _ _ "
     */
    private String genAnswerString(final char[] answer) {
        // creating StringBuilder object to create an answer that will be printed in TextView
        StringBuilder generatedAnswer = new StringBuilder();

        for(char c : answer) {
            if(c != ' ') {
                // add '_' + space
                generatedAnswer.append(c + " ");
                // when c == ' ' then add 2 spaces
            } else {
                generatedAnswer.append("  ");
            }
        }
        return generatedAnswer.toString();
    }
}
