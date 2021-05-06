package com.szymon.thehangman;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class StatusNotificationDialogFragment extends DialogFragment {

    // game instance needed to get game status
    private Hangman gameInstance;


    public StatusNotificationDialogFragment(Hangman gameInstance) {
        this.gameInstance = gameInstance;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // TODO implement interface to communicate with main activity
        // click -> interface -> dismiss notification dialog in MainActivity -> show new Game dialog
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.game_status_notification_layout, null))

                .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO implement transition from notification dialog to new game dialog
                    }
                })

                .setNegativeButton(R.string.play_again, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StatusNotificationDialogFragment.this.dismiss();
                    }
                });

        return builder.create();
    }

    // TODO implement a function that makes appropriate notification header that matches game status
}
