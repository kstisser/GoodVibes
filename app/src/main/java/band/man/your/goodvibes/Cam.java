package band.man.your.goodvibes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceView;

import com.affectiva.android.affdex.sdk.Frame;
import com.affectiva.android.affdex.sdk.detector.CameraDetector;
import com.affectiva.android.affdex.sdk.detector.Detector;
import com.affectiva.android.affdex.sdk.detector.Face;

import java.util.List;

/**
 * Created by User on 3/5/2016.
 */
public class Cam implements Detector.ImageListener{
    CameraDetector camDetector;
    /*
    public Cam() {
        int CAMIDFRONT = Camera.CameraInfo.CAMERA_FACING_FRONT;
        int CAMIDBACK = Camera.CameraInfo.CAMERA_FACING_BACK;
        int camID = CAMIDBACK;
        Camera object = null;
        object = Camera.open(camID);
    }
    */

    public Cam(Context context, SurfaceView cameraPreview){
        camDetector = new CameraDetector(context, CameraDetector.CameraType.CAMERA_BACK, cameraPreview);
        String licensePath="sdk_karissa.stisser@gmail.com.license";
        camDetector.setLicensePath(licensePath);
        camDetector.setImageListener(this);
        camDetector.setDetectValence(true);
        //switching from front to back
        //camDetector.setOnCameraEventListener(context);
        //int happy = 0;
        //int total = 0;
    }

    @Override
    public void onImageResults(List<Face> list, Frame frame, float v) {
        if (list == null)
            return;;
        if (list.size() == 0) {
            MainActivity.ErrorTxt.setText("No image processed!");
        } else {
            Face face = list.get(0);
            float facialValenceValue = face.emotions.getValence();
            MainActivity.facialValence.setText(Float.toString(facialValenceValue));
            if(facialValenceValue > 40){
                new AsyncPavlokInterface("vibrate", "100").execute();
                MainActivity.happy = MainActivity.happy + 1;
            }
            MainActivity.total = MainActivity.total + 1;
            if(facialValenceValue >=0) {
                MainActivity.colorMood.setBackgroundColor(Color.rgb(0,(Math.round(facialValenceValue/100*255)),0));
            }
            else{
                MainActivity.colorMood.setBackgroundColor(Color.rgb((Math.round(facialValenceValue/100*255)),0,0));
            }

        }
    }

    void startDetector() {
        //plot.clearData();
        MainActivity.happy = 0;
        MainActivity.total = 0;
        if (!camDetector.isRunning()) {
            Log.w("GoodVibes","Starting camera detector");
            camDetector.start();
        }
    }


    void stopDetector() {
        if (camDetector.isRunning()) {
            camDetector.stop();
            int percentage = 100*(MainActivity.happy)/(MainActivity.total);
            MainActivity.percentageTxt.setText(Integer.toString(percentage) + "%");
        }
    }

}
