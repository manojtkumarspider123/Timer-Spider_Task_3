package spider_task_three.timer;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * Created by manojkumar on 6/10/15.
 */
public class TimerCount extends Thread {

    long millisUntilFinished;
    long CountDownIntervals;
    DataDownloader datadownloader;
    Handler mainhandler;
    Handler mhandler;
    Runnable runnable_main;
    Runnable runnable_timeloop;
    String URL = "http://spider.nitt.edu/~vishnu/time.php";

    public TimerCount(long millisUntilFinished, long CountDownIntervals, Handler handler, Runnable runnable)
    {
        this.millisUntilFinished = millisUntilFinished*1000;
        this.CountDownIntervals = CountDownIntervals*1000;
        mainhandler = handler;
        runnable_main = runnable;
    }

    @Override
    public void run()
    {
       datadownloader = new DataDownloader(mainhandler);
       Looper.prepare();
       datadownloader.start();
       mhandler = new Handler();
       mainhandler.post(runnable_main);
       runnable_timeloop = new Runnable() {
           @Override
           public void run() {
              millisUntilFinished = millisUntilFinished - CountDownIntervals;
               Log.i("millisuntilfinished", "" + millisUntilFinished);
              if(millisUntilFinished<0) {
                  Log.i("IT IS OVER", "IT IS OVER" + CountDownIntervals);
                  datadownloader.queueData(URL);
              }
               else
              {
                  mainhandler.post(runnable_main);
                  mhandler.postDelayed(runnable_timeloop, CountDownIntervals);
              }

           }
       };
      mhandler.postDelayed(runnable_timeloop, CountDownIntervals);
      Looper.loop();
    }

    public long getTime()
    {
        Log.i("IN GET TIME", "" + millisUntilFinished);
        return millisUntilFinished;
    }

    public void setTimeStart(long numberlastdig)
    {
        millisUntilFinished = numberlastdig *1000;
        mainhandler.post(runnable_main);
        mhandler.postDelayed(runnable_timeloop, CountDownIntervals);
    }
}
