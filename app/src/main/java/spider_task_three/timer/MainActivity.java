package spider_task_three.timer;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;



public class MainActivity extends Activity {

    TextView mtext_timer;
    TextView mtext_data;
    Button mexit_button;
    CountDownTimer timekeeper;
    DataDownloader datadownload;
    Runnable timer_run;
    TimerCount timekeep;
    SimpleDataDownloader simpledatadownload;
    String URL = "http://spider.nitt.edu/~vishnu/time.php";
    long data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtext_timer = (TextView) findViewById(R.id.text_timer);
        mtext_data = (TextView) findViewById(R.id.text_data);
        mexit_button = (Button) findViewById(R.id.exit_button);
        mexit_button.setText("EXIT");

            /* datadownload = new DataDownloader(new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                data = Long.parseLong((String)msg.obj);
                Log.i("THE MAIN HANDLER HAS DATA", "DATA IS" + data);
                Log.i("The number i will give is", "" + (data%10 + 1)*1000);
                mtext_data.setText((String)msg.obj);
                new CountDownTimer((data%10 + 1)*1000, 500)
                {
                    int i=0;
                   @Override
                    public void onTick(long millisUntilFinished)
                   {
                        Log.i("TIME IS", "" + millisUntilFinished);
                       if(millisUntilFinished < 1000)
                       mtext_timer.setText("0");
                       if(i%2 == 1)
                       mtext_timer.setText("" + millisUntilFinished/1000);
                       i++;


                   }

                    @Override
                    public void onFinish()
                    {

                        datadownload.queueData(URL);
                        cancel();
                    }
                }.start();
                return true;
            }
        }));
        datadownload.start(); */

        timekeep = new TimerCount(10,1, new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what==0) {
                    mtext_data.setText((String) msg.obj);
                    timekeep.setTimeStart((Long.parseLong((String) msg.obj)) % 10);
                    return true;
                }
                else if(msg.what==1)
                {
                    Toast.makeText(MainActivity.this, "NET NOT WORKING", Toast.LENGTH_LONG).show();
                    //Log.i("NOT WORKaldkfalskdfalskdf", "alkdfalskdf");
                    return true;
                }
                else
                    return false;
            }
        }), timer_run = new Runnable() {
            @Override
            public void run() {
                mtext_timer.setText("" + timekeep.getTime()/1000);
            }
        });
        timekeep.start();


        /*timekeeper = new CountDownTimer(11000, 500) {

            int i=0;
            @Override
            public void onTick(long millisUntilFinished) {

                if(millisUntilFinished < 1000)
                    mtext_timer.setText("0");
                if(i%2 == 1)
                    mtext_timer.setText("" + millisUntilFinished/1000);
                i++;


            }

            @Override
            public void onFinish() {
                mtext_timer.setText("0");
                datadownload.queueData(URL);
                cancel();
            }
        };
        timekeeper.start(); */

        mexit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

