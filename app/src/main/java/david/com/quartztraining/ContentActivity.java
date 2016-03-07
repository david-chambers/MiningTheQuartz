package david.com.quartztraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ArrayList<FeatureServices> allFS = (ArrayList<FeatureServices>) getIntent().getExtras().getSerializable("FS");
        for (int i = 0; i < allFS.size(); i++)
        {
            Log.d("TAG2", allFS.get(i).getTitle());
        }



    }
}
