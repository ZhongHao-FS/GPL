// Hao Zhong
// GPL - 202110
// MainActivity.java
package com.fullsail.gpl.zhonghao_ce09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CoroutineInterface {

    private ProgressBar mSpinner;
    private final ArrayList<Book> mBooks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = findViewById(R.id.progressBar);

        if(!isConnected()){
            Toast.makeText(this, R.string.update_notConnected, Toast.LENGTH_LONG).show();
        } else {
            CoroutineTask task = new CoroutineTask(MainActivity.this);
            task.coBegin(getString(R.string.webAddress_GoogleAPI));
        }
    }

    protected boolean isConnected(){
        ConnectivityManager mgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if(mgr != null){
            NetworkInfo info = mgr.getActiveNetworkInfo();
            if(info != null){
                return info.isConnected();
            }
        }
        return false;
    }

    private void showGridView() {
        GridView bookShelf = findViewById(R.id.gridView);
        GridAdapter adapter = new GridAdapter(this, mBooks);
        bookShelf.setAdapter(adapter);
    }

    @Override
    public void onPre() {
        mSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinish(String data) {
        parseJSONData(data);
        mSpinner.setVisibility(View.INVISIBLE);
        if (mBooks != null) {
            showGridView();
        }
    }

    private void parseJSONData(String data) {
        if (data.equals("")) {
            return;
        }
        try {
            JSONObject obj = new JSONObject(data);
            JSONArray booksArray = obj.getJSONArray("items");

            for (int i = 0; i < booksArray.length(); i++) {
                JSONObject book = booksArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String link = imageLinks.getString("thumbnail");

                mBooks.add(new Book(volumeInfo.getString("title"), link));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}