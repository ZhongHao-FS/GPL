// Hao Zhong
// GPL - 202110
// TimerAsyncTask.java
package com.fullsail.gpl.zhonghao_ce06;

import android.os.AsyncTask;

public class TimerAsyncTask extends AsyncTask<String, Integer, Float> {
    static final private long SLEEP = 500;

    final private OnFinished mFinishedInterface;

    public TimerAsyncTask(OnFinished _finished) {
        mFinishedInterface = _finished;
    }

    interface OnFinished {
        void onPre();
        void onProg(Integer... values);
        void onPost(Float aFloat);
        void onCan(Float aFloat);
        void onFinished();
    }

    protected void onPreExecute() {
        mFinishedInterface.onPre();
    }

    @Override
    protected Float doInBackground(String... strings) {
        if(strings == null || strings.length <= 0 || strings[0].trim().isEmpty()){
            return 0.0f;
        }

        long startTime = System.currentTimeMillis();
        long count = Integer.parseInt(strings[0]) * 1000L;
        long stamp = System.currentTimeMillis();

        // block until count is satisfied
        while(count > 999 && !isCancelled()){
            publishProgress((int) (count / 1000));
            // suspend the thread for a period of time
            try{
                Thread.sleep(SLEEP);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            long elapsed = System.currentTimeMillis() - stamp;
            count -= elapsed;
            stamp = System.currentTimeMillis();
        }

        // Return real elapsed time
        return (System.currentTimeMillis() - startTime) / 1000.0f;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // Show toast
        mFinishedInterface.onProg(values);
    }

    @Override
    protected void onPostExecute(Float aFloat) {
        // Show toast
        // Invoke interface
        mFinishedInterface.onPost(aFloat);
        mFinishedInterface.onFinished();
    }

    @Override
    protected void onCancelled(Float aFloat) {
        // Show toast
        // Invoke interface
        mFinishedInterface.onCan(aFloat);
        mFinishedInterface.onFinished();
    }
}
