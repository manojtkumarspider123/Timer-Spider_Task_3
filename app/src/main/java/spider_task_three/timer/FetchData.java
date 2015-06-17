package spider_task_three.timer;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by manojkumar on 6/9/15.
 */
public class FetchData {



    public String getInfo(String urlspec)throws IOException
    {
        URL url = new URL(urlspec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setConnectTimeout(60000);

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Log.i("CONNECTION", "IS ESTABLISHED");
            String s = bufferedReader.readLine();
            Log.i("NUMBER", "THE NUMBER IS" + s);
            bufferedReader.close();
            return s;
        }
        finally {

            connection.disconnect();
        }



    }
}
