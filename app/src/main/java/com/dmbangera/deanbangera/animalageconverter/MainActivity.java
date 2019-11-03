package com.dmbangera.deanbangera.animalageconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startAgePrompt(View view) {
        Bundle bundle = new Bundle();
        int animalId = view.getId();
        playAnimalSound(animalId);
        bundle.putLong("Title", animalId);
        DialogFragment promptFragment = new PromptFragment();
        promptFragment.setArguments(bundle);
        promptFragment.show(getSupportFragmentManager(), "Age Prompt");
    }

    private void playAnimalSound(int animalId) {
        MediaPlayer mp = null;
        switch (animalId) {
            case R.id.cat:
                mp = MediaPlayer.create(this, R.raw.cat);
                break;
            case R.id.chicken:
                mp = MediaPlayer.create(this, R.raw.chicken);
                break;
            case R.id.cow:
                mp = MediaPlayer.create(this, R.raw.cow);
                break;
            case R.id.elephant:
                mp = MediaPlayer.create(this, R.raw.elephant);
                break;
            default:
                break;
        }
        if (mp != null) {
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            mp.start();
        }
    }

    private double getRatio(int animalId) {
        double ratio = 1;
        switch (animalId) {
            case R.id.dog:
                ratio = 7;
                break;
            case R.id.cat:
                ratio = 4;
                break;
            case R.id.panda:
                ratio = 3;
                break;
            case R.id.chicken:
                ratio = 8;
                break;
            case R.id.cow:
                ratio = 2;
                break;
            case R.id.elephant:
                ratio = 1.5;
                break;
            case R.id.fish:
                ratio = 10;
                break;
            case R.id.human:
                ratio = 1;
                break;
            case R.id.rabbit:
                ratio = 4.5;
                break;
            default:
                Toast.makeText(this, R.string.age_calc_er, Toast.LENGTH_SHORT).show();
                break;
        }
        return ratio;
    }

    public void showCalculatedResult(int years, int months, int days, int animalId) {
        double ratio = getRatio(animalId);
        long calculatedYrs = Math.round(years * ratio);
        long calculatedMonths = Math.round(months * ratio);
        long calculatedDays = Math.round(days * ratio);
        if (calculatedDays > 30) {
            calculatedMonths += Math.floor(calculatedDays / 30);
            calculatedDays = (calculatedDays % 30);
        }
        if (calculatedMonths > 12) {
            calculatedYrs += Math.floor(calculatedMonths / 12);
            calculatedMonths = (calculatedMonths % 12);
        }
        Bundle bundle = new Bundle();
        bundle.putLong("Yrs", calculatedYrs);
        bundle.putLong("Months", calculatedMonths);
        bundle.putLong("Days", calculatedDays);
        ImageView animalView = findViewById(animalId);
        bundle.putString("Animal", animalView.getTag().toString());
        DialogFragment calculatedFragment = new CalculatedFragment();
        calculatedFragment.setArguments(bundle);
        calculatedFragment.show(getSupportFragmentManager(), "Calculated Fragment");
    }
}
