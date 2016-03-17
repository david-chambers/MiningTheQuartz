package david.com.quartztraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.BasemapType;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.Map;
import com.esri.arcgisruntime.portal.Portal;
import com.esri.arcgisruntime.portal.PortalFolder;
import com.esri.arcgisruntime.portal.PortalItem;
import com.esri.arcgisruntime.portal.PortalItemType;
import com.esri.arcgisruntime.portal.PortalUser;
import com.esri.arcgisruntime.portal.PortalUserContent;
import com.esri.arcgisruntime.security.Credential;
import com.esri.arcgisruntime.security.UserCredential;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //Button to open ContentActivity (holder for list of content)
    Button mButton;
    ImageButton listButton;
    private MapView mapView;

    //Variables to hold credentials obtained from LoginActivity
    private String mUsername;
    private String mPassword;

    //Portal variable
    private Portal essMaps;

    //Array of portal Items
    private ArrayList<PortalItem> myPortalItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = getIntent().getExtras().getString("usernameKey");
        mPassword = getIntent().getExtras().getString("passwordKey");

        essMaps = new Portal("http://ess.maps.arcgis.com", new UserCredential(mUsername, mPassword));
        essMaps.loadAsync();
        essMaps.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                if(essMaps.getLoadStatus() == LoadStatus.LOADED){
                    Toast.makeText(getApplicationContext(),"Portal loaded!",Toast.LENGTH_LONG).show();
                    fetchUserContent();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Log.d("MyTag");
                        }
                    });
                }

            }
        });

        //inflate mapview
        mapView = (MapView)findViewById(R.id.mapView);
        Map map = new Map(BasemapType.IMAGERY, 34.056295, -117.195800,16);
        mapView.setMap(map);

        //Inflate the Layer List Button
        //mButton = (Button) findViewById(R.id.listActivityButton);

        //Add an on click listener to the Button that launches ContentActivity
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Launch the new Activity for listing user content
                Intent i = new Intent(MainActivity.this, ContentActivity.class);
                startActivity(i);
            }
        });

        listButton = (ImageButton)findViewById(R.id.listActivityButton);


    }



    public void fetchUserContent(){
        final PortalUser user = essMaps.getPortalUser();

        //fetch the content in the users root folder
        final ListenableFuture<PortalUserContent> contentFuture = user.fetchContentAsync();
        contentFuture.addDoneListener(new Runnable() {

            @Override
            public void run() {
                try {
                    StringBuilder userInfo = new StringBuilder();
                    //iterate items in root folder...
                    final PortalUserContent portalUserContent = contentFuture.get();

                    //Instantiate ArrayList
                    myPortalItems = new ArrayList<PortalItem>();

                    for (PortalItem item : portalUserContent.getItems())
                    {
                        if(item.getType() == PortalItemType.FEATURE_SERVICE) {
                            myPortalItems.add(item);
                            userInfo.append(String.format("Item: %s\n", item.getTitle()));
                        }


                    }

                    //iterate user's folders - maybe implement this in the future
                    if(myPortalItems != null){
                        for(PortalItem item : myPortalItems){
                            Log.d("MyTag", item.getTitle());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
