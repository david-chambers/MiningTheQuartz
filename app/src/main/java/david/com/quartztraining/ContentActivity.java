package david.com.quartztraining;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        final ListView listView = (ListView)findViewById(R.id.listView);
        String[] strings = {"Changes", "Mamamia", "Changes", "tests", "Ghana", "Oxford"};

        final ArrayList<String> arrayList = new ArrayList<String>();
        for(int i=0; i < strings.length; i++){
            arrayList.add(strings[i]);
        }

        //declare an adapter to hold the data and set it to the list

        final KwasiStableArrayAdapter stableArrayAdapter = new KwasiStableArrayAdapter(this, R.id.listView, arrayList);

        listView.setAdapter(stableArrayAdapter);

            //something animation on click

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
               final String item = (String)parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        arrayList.remove(item);
                        stableArrayAdapter.notifyDataSetChanged();
                        view.setAlpha(0);
                    }
                });
            }
        });
    }

    private class KwasiStableArrayAdapter extends ArrayAdapter<String>{
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        public KwasiStableArrayAdapter(Context context, int resource, List<String> list) {
            super(context, resource);
            for(int i=0; i < list.size(); i++){
                    hashMap.put(list.get(i), i);
            }

        }

        @Override
        public long getItemId(int position){
           String item = getItem(position);
            return hashMap.get(item);
        }

        @Override
        public boolean hasStableIds(){
            return true;
        }
    }
}
