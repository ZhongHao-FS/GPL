// Hao Zhong
// GPL - 202110
// MainActivity.java
package com.fullsail.gpl.zhonghao_ce07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements APITask.DownloadTask {

    private ProgressBar mSpinner;
    private ArrayList<Book> mBooks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = findViewById(R.id.progressBar);

        if(!isConnected()){
            Toast.makeText(this, R.string.update_notConnected, Toast.LENGTH_LONG).show();
        } else {
            APITask task = new APITask(MainActivity.this);
            task.execute();
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
        GridView mBookShelf = findViewById(R.id.gridView);
        GridAdapter adapter = new GridAdapter(this, mBooks);
        mBookShelf.setAdapter(adapter);
    }

    @Override
    public void onPre() {
        mSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPost(ArrayList<Book> list) {
        mBooks = list;
        mSpinner.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFinished() {
        if (mBooks != null) {
            showGridView();
        }
    }

}