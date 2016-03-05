package band.man.your.goodvibes;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    public static TextView ErrorTxt;
    public static SurfaceView surfaceView;
    public static TextView facialValence;
    Button startBtn;
    Button stopBtn;
    Cam camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ErrorTxt = (TextView)findViewById(R.id.errorTxt);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        startBtn = (Button)findViewById(R.id.startBtn);
        stopBtn = (Button)findViewById(R.id.stopBtn);
        facialValence = (TextView)findViewById(R.id.facialValenceTxt);
        camera = new Cam(this, surfaceView);

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
