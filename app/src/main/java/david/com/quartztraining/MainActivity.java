package david.com.quartztraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.BasemapType;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.Map;



public class MainActivity extends AppCompatActivity {

    //Button to open ContentActivity (holder for list of content)
    Button mButton;
    private MapView mapView;

    //Variables to hold credentials obtained from LoginActivity
    private String mUsername;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflate mapview
        mapView = (MapView)findViewById(R.id.mapView);
        Map map = new Map(BasemapType.IMAGERY, 34.056295, -117.195800,16);
        mapView.setMap(map);

        //Inflate the Layer List Button
        mButton = (Button) findViewById(R.id.listActivityButton);

        //Add an on click listener to the Button that launches ContentActivity
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Launch the new Activity for listing user content
                Intent i = new Intent(MainActivity.this, ContentActivity.class);
                startActivity(i);
            }
        });


    }
}
