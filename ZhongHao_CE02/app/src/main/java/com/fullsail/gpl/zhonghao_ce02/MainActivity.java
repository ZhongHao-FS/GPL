// Hao Zhong
// GPL - 202110
// MainActivity.java
package com.fullsail.gpl.zhonghao_ce02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int[] mNumbers = new int[4];
    private final Random random = new Random();
    private final EditText[] mTextFields = new EditText[4];
    private int[] mGuesses;
    private Toast mToast = null;
    private int mAttemptsLeft = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextFields[0] = findViewById(R.id.editTextNumber);
        for (int i = 1; i < mTextFields.length; i++) {
            String viewID = "editTextNumber" + (i + 1);
            mTextFields[i] = findViewById(getResources().getIdentifier(viewID, "id", getPackageName()));
        }

        findViewById(R.id.button).setOnClickListener(this);
        resetGame();
    }

    @Override
    public void onClick(View view) {
        if (validInput()) {
            int correctGuesses = 0;
            for (int i = 0; i < mGuesses.length; i++) {
                switch (Integer.signum(mGuesses[i] - mNumbers[i])) {
                    case 0:
                        mTextFields[i].setTextColor(Color.GREEN);
                        correctGuesses++;
                        break;
                    case -1:
                        mTextFields[i].setTextColor(Color.BLUE);
                        break;
                    case 1:
                        mTextFields[i].setTextColor(Color.RED);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + (mGuesses[i] - mNumbers[i]));
                }
            }

            if (correctGuesses == 4) {
                gameOver("You Win!");
            } else {
                mAttemptsLeft--;
                if (mAttemptsLeft == 0) {
                    gameOver("You Lose!");
                } else {
                    showToast(String.format(Locale.US, "Very close. You have %1$d guesses left.", mAttemptsLeft));
                }
            }
        } else {
            showToast("Valid guesses are between 0 and 9");
        }
    }

    private Boolean validInput() {
        String[] inputs = new String[4];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = mTextFields[i].getText().toString();
        }
        boolean[] validations = new boolean[4];

        try {
            for (int i = 0; i < validations.length; i++) {
                int guess = Integer.parseInt(inputs[i]);
                validations[i] = guess >= 0 && guess <= 9;
                mGuesses[i] = guess;
            }
        } catch (Exception e) {
            showToast(e.getLocalizedMessage());
            return false;
        }

        return validations[0] && validations[1] && validations[2] && validations[3];
    }

    private void showToast(String message) {
        if(mToast != null) {
            mToast.cancel();
        }

        mToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        mToast.show();
    }

    private void gameOver(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle(message);
        alert.setMessage("Game over! Click Restart to play again.");
        alert.setPositiveButton("Restart", (dialogInterface, i) -> resetGame());
        alert.show();
    }

    private void resetGame() {
        for (int i = 0; i < mNumbers.length; i++) {
            mNumbers[i] = random.nextInt(10);
            mTextFields[i].getText().clear();
            mTextFields[i].setTextColor(Color.BLACK);
        }
        mGuesses = new int[4];
        mAttemptsLeft = 4;
    }
}