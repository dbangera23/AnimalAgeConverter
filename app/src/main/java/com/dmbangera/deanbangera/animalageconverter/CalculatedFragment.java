package com.dmbangera.deanbangera.animalageconverter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CalculatedFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Long yrs = getArguments().getLong("Yrs");
        Long months = getArguments().getLong("Months");
        Long days = getArguments().getLong("Days");
        String animal = getArguments().getString("Animal");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String message = "Your " + animal + " is " +
                yrs.toString() + " yrs " +
                months.toString() + " months " +
                days.toString() + " days old!";
        builder.setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // All Done!
                        CalculatedFragment.this.getDialog().dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
