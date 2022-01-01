// Hao Zhong
// GPL - 202110
// APITask.java
package com.fullsail.gpl.zhonghao_ce07;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.picasso.Picasso;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class APITask extends AsyncTask<Void, Void, ArrayList<Book>> {
    final private DownloadTask mDownLoadTask;

    public APITask(DownloadTask mDownLoadTask) {
        this.mDownLoadTask = mDownLoadTask;
    }

    interface DownloadTask {
        void onPre();
        void onPost(ArrayList<Book> list);
        void onFinished();
    }

    @Override
    protected ArrayList<Book> doInBackground(Void... voids) {
        String jsonData = "";
        ArrayList<Book> booksList = new ArrayList<>();

        final String webAddress = "https://www.googleapis.com/books/v1/volumes?q=android";

        // HTTP URL connection reference.
        HttpURLConnection connection;

        try {
            // Create new URL
            URL url = new URL(webAddress);
            // Open connection
            connection = (HttpURLConnection)url.openConnection();
            // Perform connection operation
            connection.connect();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }

        // Input stream reference
        InputStream is = null;
        try {
            // Error check connection
            // Get the stream
            is = connection.getInputStream();
            // Convert the stream to a string (think about out utils lib)
            jsonData = IOUtils.toString(is, StandardCharsets.UTF_8);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            // If we have a stream, try to close it.
            try{
                assert is != null;
                is.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            // If we have a connection disconnect it
            connection.disconnect();
        }

        if (jsonData.equals("")) {
            return null;
        }
        try {
            JSONObject obj = new JSONObject(jsonData);
            JSONArray booksArray = obj.getJSONArray("items");

            for (int i = 0; i < booksArray.length(); i++) {
                JSONObject book = booksArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String link = imageLinks.getString("thumbnail");

                booksList.add(new Book(volumeInfo.getString("title"), link));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return booksList;
    }

    @Override
    protected void onPreExecute() {
        mDownLoadTask.onPre();
    }

    @Override
    protected void onPostExecute(ArrayList<Book> list) {
        mDownLoadTask.onPost(list);
        mDownLoadTask.onFinished();
    }
}
