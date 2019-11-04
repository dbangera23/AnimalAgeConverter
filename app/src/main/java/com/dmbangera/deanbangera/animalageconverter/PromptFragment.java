package com.dmbangera.deanbangera.animalageconverter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class PromptFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.prompt_fragment, null);
        final int animalViewId = (int) getArguments().getLong("Title");
        ImageView animalView = getActivity().findViewById(animalViewId);
        builder.setView(view)
                .setPositiveButton("Calculate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        int years = 0, months = 0, days = 0;
                        EditText yrsInput = view.findViewById(R.id.yrs_input);
                        EditText monthsInput = view.findViewById(R.id.month_input);
                        EditText daysInput = view.findViewById(R.id.days_input);
                        try {
                            years = Integer.parseInt(yrsInput.getText().toString());
                            months = Integer.parseInt(monthsInput.getText().toString());
                            days = Integer.parseInt(daysInput.getText().toString());
                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }
                        MainActivity activity = (MainActivity) getActivity();
                        if (activity != null) {
                            activity.showCalculatedResult(years, months, days, animalViewId);
                        } else {
                            Toast.makeText(getActivity(), R.string.age_calc_er, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cancel th calculation
                        PromptFragment.this.getDialog().cancel();
                    }
                })
                .setTitle(animalView.getTag().toString());
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
