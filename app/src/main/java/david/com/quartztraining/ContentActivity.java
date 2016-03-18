package david.com.quartztraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.esri.arcgisruntime.portal.PortalItem;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<PortalItem> portalContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        recyclerView = (RecyclerView) findViewById(R.id.layersRecycler);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        portalContent = (ArrayList<PortalItem>) getIntent().getExtras().get("PortalItems");
        ArrayList<String> portalItem = new ArrayList<String>();
        portalItem.add(portalContent.get(0).getTitle().toString());
        recyclerView.setAdapter(new RecyclerAdapter(portalItem));
    }
}
