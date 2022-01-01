// Hao Zhong
// GPL - 202110
// MainActivity.java
package com.fullsail.gpl.zhonghao_ce03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final ArrayList<String> mWords = new ArrayList<>();
    private final ArrayList<Integer> mNumLetters = new ArrayList<>();
    private EditText textField;
    private TextView average;
    private TextView median;
    private NumberPicker picker;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textField = findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(this);
        average = findViewById(R.id.textView);
        median = findViewById(R.id.textView2);
        findViewById(R.id.button2).setOnClickListener(this);
        picker = findViewById(R.id.numberPicker);

        setup();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            onAddTapped();
        } else if (view.getId() == R.id.button2) {
            if (mWords.size() == 0) {
                onViewEmpty();
            } else {
                onViewTapped();
            }
        }
    }

    private void setup() {
        average.setText(R.string.textView_average);
        median.setText(R.string.textView_median);
        picker.setMinValue(0);
        picker.setMaxValue(0);
    }

    private void onAddTapped() {
        String input = textField.getText().toString();
        if (input.length() == 0) {
            showToast(getString(R.string.error_empty));
        } else if (input.trim().length() == 0) {
            showToast(getString(R.string.error_whiteSpace));
        } else if (mWords.contains(input)) {
            showToast(getString(R.string.error_redundant));
        } else {
            mWords.add(input);
            textField.getText().clear();
            update();
        }
    }

    private void onViewTapped() {
        int index = picker.getValue();
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle(R.string.alert_title);
        alert.setMessage(mWords.get(index));
        alert.setNegativeButton("Cancel", null);
        alert.setPositiveButton("Remove", (dialogInterface, i) -> removeTapped(index));
        alert.show();
    }

    private void removeTapped(int i) {
        mWords.remove(i);
        if (mWords.size() == 0) {
            setup();
        } else {
            update();
        }
    }

    private void onViewEmpty() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle(R.string.alert_title);
        alert.setMessage("The word list is empty!");
        alert.setNegativeButton("Cancel", null);

        alert.show();
    }

    private void showToast(String message) {
        if(mToast != null) {
            mToast.cancel();
        }

        mToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        mToast.show();
    }

    private void update() {
        mNumLetters.clear();
        int sum = 0;
        for (int i = 0; i < mWords.size(); i++) {
            mNumLetters.add(mWords.get(i).length());
            sum += mWords.get(i).length();
        }

        double mAverage = (double) sum / (double) mNumLetters.size();
        average.setText(String.format(Locale.US, "Average word length: %.2f", mAverage));

        Collections.sort(mNumLetters);
        double mMedian;
        if (mNumLetters.size() % 2 == 0) {
            mMedian = ((double)mNumLetters.get(mNumLetters.size() / 2) + (double)mNumLetters.get(mNumLetters.size() / 2 - 1))/2;
        } else {
            mMedian = (double)mNumLetters.get(mNumLetters.size() / 2);
        }
        median.setText(String.format(Locale.US, "Median word length: %.2f", mMedian));

        picker.setMinValue(0);
        picker.setMaxValue(mWords.size()-1);
    }

}