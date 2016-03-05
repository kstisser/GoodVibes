package band.man.your.goodvibes;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
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
        camDetector = new CameraDetector(context, CameraDetector.CameraType.CAMERA_FRONT, cameraPreview);
        String licensePath="affdex.license";
        camDetector.setLicensePath(licensePath);
        camDetector.setImageListener(this);
        camDetector.setDetectValence(true);
        //switching from front to back
        //camDetector.setOnCameraEventListener(context);

    }

    @Override
    public void onImageResults(List<Face> list, Frame frame, float v) {
        if (list == null)
            return;;
        if (list.size() == 0) {
            MainActivity.ErrorTxt.setText("No image processed!");
        } else {
            Face face = list.get(0);
            float facialValence = face.emotions.getValence();

        }
    }

    void startDetector() {
        if (!camDetector.isRunning()) {
            camDetector.start();
        }
    }

    void stopDetector() {
        if (camDetector.isRunning()) {
            camDetector.stop();
        }
    }

}
