// Hao Zhong
// GPL - 202110
// MainActivity.java
package com.fullsail.gpl.zhonghao_ce08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String EDITTEXT_KEY = "EditText.Key";
    private static final String LIST_KEY = "ListView.Key";
    private EditText mInputField;
    private ArrayList<String> mArray = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputField = findViewById(R.id.editText);
        ListView listView = findViewById(R.id.listView);

        if (savedInstanceState != null) {
            mInputField.setText(savedInstanceState.getString(EDITTEXT_KEY));
            mArray = savedInstanceState.getStringArrayList(LIST_KEY);
        }
        
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mArray);
        findViewById(R.id.button).setOnClickListener(this);
        listView.setAdapter(mAdapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(EDITTEXT_KEY, mInputField.getText().toString());
        outState.putStringArrayList(LIST_KEY, mArray);
    }

    @Override
    public void onClick(View view) {
        String text = mInputField.getText().toString();
        if (validate(text)) {
            mArray.add(text);
            mInputField.getText().clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    private boolean validate(String _input) {
        if (_input.trim().length() > 0) {
            return true;
        } else {
            Toast.makeText(MainActivity.this, R.string.toast_msg, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}