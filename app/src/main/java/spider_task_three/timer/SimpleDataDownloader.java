package spider_task_three.timer;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/**
 * Created by manojkumar on 6/9/15.
 */
public class SimpleDataDownloader extends AsyncTask<String,Void,Void>{

    String number;

    @Override
    protected Void doInBackground(String... params) {
        try {
            //urlspec = (String) msg.obj;
            number = new FetchData().getInfo("http://spider.nitt.edu/~vishnu/time.php");
            Log.i("NUMBER IS", number);

        } catch (IOException ioe) {
            Log.i("ERROR DIDNT FETCH DATA", "" + ioe);

        }
        return null;
    }

}
