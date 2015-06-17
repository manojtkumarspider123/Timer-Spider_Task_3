package spider_task_three.timer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.IOException;


/**
 * Created by manojkumar on 6/9/15.
 */
public class DataDownloader extends Thread {

    Handler mHandler;
    Handler mainHandler;
    String urlspec;
    String number;
    int MESSAGE_DOWNLOAD = 0;
    int MESSAGE_FAILED = 1;

    public DataDownloader( Handler handler)
    {

        mainHandler = handler;
    }

    @Override
    public void run()
    {
        Looper.prepare();
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what==0) {
                    try {

                        number = new FetchData().getInfo((String) msg.obj);
                        Log.i("NUMBER IS", number);
                        mainHandler.obtainMessage(MESSAGE_DOWNLOAD,number).sendToTarget();
                        return true;
                    } catch (IOException ioe) {
                        Log.i("ERROR DIDNT FETCH DATA", "" + ioe);
                        mainHandler.obtainMessage(MESSAGE_FAILED).sendToTarget();

                    }
                    return false;
                }

                else return false;

            }
        });

         Looper.loop();
    }

    public void queueData(String url)
    {
        urlspec = url;
        mHandler.obtainMessage(MESSAGE_DOWNLOAD, urlspec).sendToTarget();
    }

}
