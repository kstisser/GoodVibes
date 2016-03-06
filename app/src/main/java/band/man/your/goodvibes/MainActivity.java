package band.man.your.goodvibes;

import android.content.Context;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    public static TextView ErrorTxt;
    public static SurfaceView surfaceView;
    public static TextView facialValence;
    public static ImageView colorMood;
    public static int happy;
    public static int total;
    public static TextView percentageTxt;
    SurfaceView graphView;
    Button startBtn;
    Button stopBtn;
    Cam camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ErrorTxt = (TextView)findViewById(R.id.errorTxt);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        surfaceView.getHolder().setFixedSize(1,1);
        startBtn = (Button)findViewById(R.id.startBtn);
        stopBtn = (Button)findViewById(R.id.stopBtn);
        facialValence = (TextView)findViewById(R.id.facialValenceTxt);
        camera = new Cam(this, surfaceView);
        colorMood = (ImageView)findViewById(R.id.colorView);
        percentageTxt = (TextView)findViewById(R.id.timeTxt);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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

    public Cam startVideo(View view){

        camera.startDetector();
        return camera;
    }

    public void stopVideo(View view){
        camera.stopDetector();
    }
}
