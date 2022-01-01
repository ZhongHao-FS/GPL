// Hao Zhong
// GPL - 202110
// MainActivity.java
package com.fullsail.gpl.zhonghao_ce06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TimerAsyncTask.OnFinished {

    private TimerAsyncTask mTimerTask = null;
    private TextView mTimer;
    private EditText mMin;
    private EditText mSec;
    private View mStart;
    private View mStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimer = findViewById(R.id.textView_colon);
        mMin = findViewById(R.id.editText_minutes);
        mSec = findViewById(R.id.editText_seconds);
        mStart = findViewById(R.id.button_start);
        mStop = findViewById(R.id.button_stop);

        mStart.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {
                        // Toggle button functionality
                        if (validateInput()) {
                            mStart.setEnabled(false);
                            mStop.setEnabled(true);

                            // Always create a NEW task as it is ONE TIME USE only
                            mTimerTask = new TimerAsyncTask(MainActivity.this);
                            mTimerTask.execute(convertToSecs());

                            // Clear the edit field to indicate to the user their input was accepted
                            mMin.getText().clear();
                            mSec.getText().clear();
                        } else {
                            Toast.makeText(MainActivity.this, R.string.onWrongInput, Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

        mStop.setEnabled(false);
        mStop.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v) {
                        // Disable the cancel button since it has no purpose until
                        // the async task has been started again.
                        mStop.setEnabled(false);

                        // Make sure we have a task to cancel, then cancel
                        if(mTimerTask!=null) {

                            mTimerTask.cancel(true);
                        }
                    }
                }
        );
    }

    private boolean validateInput() {
        String minInput = mMin.getText().toString();
        String secInput = mSec.getText().toString();

        try {
            int min = Integer.parseInt(minInput);
            int sec = Integer.parseInt(secInput);

            if (min >= 0 && sec >= 0 && sec < 60) {
                if (min == 0 && sec == 0) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }

        } catch (NumberFormatException ex) {
            return false;
        }

    }

    private String convertToSecs() {
        String minInput = mMin.getText().toString();
        String secInput = mSec.getText().toString();
        int min = Integer.parseInt(minInput);
        int sec = Integer.parseInt(secInput);

        int total = min * 60 + sec;

        return Integer.toString(total);
    }

    @Override
    public void onPre() {
        Toast.makeText(this, R.string.onPre_toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProg(Integer... values) {
        if (values[0] < 60) {
            mTimer.setText(String.format(getString(R.string.onProg_timer), values[0]));
        } else {
            Integer min = values[0] / 60;
            Integer sec = values[0] % 60;
            mTimer.setText(String.format(getString(R.string.onProg_timer2), min, sec));
        }
    }

    @Override
    public void onPost(Float aFloat) {
        Integer min = (int)(aFloat / 60);
        Integer sec = (int)(aFloat % 60);
        String msg = String.format(getString(R.string.onPostExe), min, sec);
        showDialog(msg);
    }

    @Override
    public void onCan(Float aFloat) {
        Integer min = (int)(aFloat / 60);
        Integer sec = (int)(aFloat % 60);
        String msg = String.format(getString(R.string.onPostExe), min, sec);
        showDialog(msg);
    }

    @Override
    public void onFinished() {
        mStart.setEnabled(true);
        mStop.setEnabled(false);
        mTimer.setText(R.string.tv_colon);
    }

    private void showDialog(String _msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.onFinished_title);
        alert.setMessage(_msg);
        alert.setNeutralButton(R.string.onFinished_pop, null);

        alert.show();
    }
}