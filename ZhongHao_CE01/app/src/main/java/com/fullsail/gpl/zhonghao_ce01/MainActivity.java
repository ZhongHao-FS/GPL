// Hao Zhong
// GPL - 202110
// MainActivity.java
package com.fullsail.gpl.zhonghao_ce01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Integer mCount = 0;
    private Integer mButtonsLeft = 16;
    private TextView mCountView = null;
    private Button button1 = null;
    private Button button2 = null;
    private Button button3 = null;
    private Button button4 = null;
    private Button button5 = null;
    private Button button6 = null;
    private Button button7 = null;
    private Button button8 = null;
    private Button button9 = null;
    private Button button10 = null;
    private Button button11 = null;
    private Button button12 = null;
    private Button button13 = null;
    private Button button14 = null;
    private Button button15 = null;
    private Button button16 = null;
    private Button buttonRestart = null;
    private ArrayList<Button> mActiveButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);
        button15.setOnClickListener(this);
        button16.setOnClickListener(this);

        mCountView = findViewById(R.id.textView2);
        buttonRestart = findViewById(R.id.button17);
        buttonRestart.setOnClickListener(this);

        resetGame();
    }

    private void resetGame() {
        mCount = 0;
        updateCount();
        mButtonsLeft = 16;
        buttonRestart.setVisibility(View.INVISIBLE);
        buttonRestart.setClickable(false);
        ArrayList<String> letters = new ArrayList<>();

        letters.add("A");
        letters.add("A");
        letters.add("B");
        letters.add("B");
        letters.add("C");
        letters.add("C");
        letters.add("D");
        letters.add("D");
        letters.add("E");
        letters.add("E");
        letters.add("F");
        letters.add("F");
        letters.add("G");
        letters.add("G");
        letters.add("H");
        letters.add("H");
        Collections.shuffle(letters);

        button1.setText(letters.get(0));
        button2.setText(letters.get(1));
        button3.setText(letters.get(2));
        button4.setText(letters.get(3));
        button5.setText(letters.get(4));
        button6.setText(letters.get(5));
        button7.setText(letters.get(6));
        button8.setText(letters.get(7));
        button9.setText(letters.get(8));
        button10.setText(letters.get(9));
        button11.setText(letters.get(10));
        button12.setText(letters.get(11));
        button13.setText(letters.get(12));
        button14.setText(letters.get(13));
        button15.setText(letters.get(14));
        button16.setText(letters.get(15));

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);
        button7.setVisibility(View.VISIBLE);
        button8.setVisibility(View.VISIBLE);
        button9.setVisibility(View.VISIBLE);
        button10.setVisibility(View.VISIBLE);
        button11.setVisibility(View.VISIBLE);
        button12.setVisibility(View.VISIBLE);
        button13.setVisibility(View.VISIBLE);
        button14.setVisibility(View.VISIBLE);
        button15.setVisibility(View.VISIBLE);
        button16.setVisibility(View.VISIBLE);

        button1.setTextSize(0);
        button2.setTextSize(0);
        button3.setTextSize(0);
        button4.setTextSize(0);
        button5.setTextSize(0);
        button6.setTextSize(0);
        button7.setTextSize(0);
        button8.setTextSize(0);
        button9.setTextSize(0);
        button10.setTextSize(0);
        button11.setTextSize(0);
        button12.setTextSize(0);
        button13.setTextSize(0);
        button14.setTextSize(0);
        button15.setTextSize(0);
        button16.setTextSize(0);

        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
        button4.setClickable(true);
        button5.setClickable(true);
        button6.setClickable(true);
        button7.setClickable(true);
        button8.setClickable(true);
        button9.setClickable(true);
        button10.setClickable(true);
        button11.setClickable(true);
        button12.setClickable(true);
        button13.setClickable(true);
        button14.setClickable(true);
        button15.setClickable(true);
        button16.setClickable(true);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button17){
            resetGame();
        } else if (mActiveButtons.size() == 2) {
            mActiveButtons.get(0).setTextSize(0);
            mActiveButtons.get(1).setTextSize(0);
            mActiveButtons.get(0).setClickable(true);
            mActiveButtons.get(1).setClickable(true);
            mActiveButtons.clear();
            mCount += 1;
            updateCount();
            showButtonText(view);
        } else {
            showButtonText(view);
            if (mActiveButtons.size() == 2 && mActiveButtons.get(0).getText().equals(mActiveButtons.get(1).getText())) {
                mActiveButtons.get(0).setVisibility(View.INVISIBLE);
                mActiveButtons.get(1).setVisibility(View.INVISIBLE);
                mActiveButtons.clear();
                mButtonsLeft -= 2;
                checkGameOver();
            }
        }
    }

    private void updateCount() {
        String formatted = getString(R.string.countingNum, mCount);
        mCountView.setText(formatted);
    }

    private void showButtonText(View view) {
        Button buttonClicked = findViewById(view.getId());
        buttonClicked.setTextSize(14);
        mActiveButtons.add(buttonClicked);
        buttonClicked.setClickable(false);
    }

    private void checkGameOver() {
        if (mButtonsLeft == 0) {
            buttonRestart.setVisibility(View.VISIBLE);
            buttonRestart.setClickable(true);
        }
    }
}