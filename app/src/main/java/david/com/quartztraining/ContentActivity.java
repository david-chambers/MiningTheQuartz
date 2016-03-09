package david.com.quartztraining;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class ContentActivity extends Activity {

    private RecyclerView mPortalRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allportalcontent);
        mPortalRecyclerView = (RecyclerView) findViewById(R.id.portal_recycler_view);

        //mPortalRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mPortalRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<FeatureServices> allFS = (ArrayList<FeatureServices>) getIntent().getExtras().getSerializable("FS");
        Log.d("NOHE", allFS.toString());
        mAdapter = new MyAdapter(allFS);
        mPortalRecyclerView.setAdapter(mAdapter);
    }
}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<FeatureServices> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mThumbnailView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.portal_content_title);
            mThumbnailView = (ImageView) v.findViewById(R.id.portalcontentthumbnailholder);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<FeatureServices> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.portalcontent, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getTitle());
        if (mDataset.get(position).getThumbnail() != null) {
            Bitmap bit = BitmapFactory.decodeByteArray(mDataset.get(position).getThumbnail(), 0, mDataset.get(position).getThumbnail().length);
            BitmapDrawable drawableBit = new BitmapDrawable(holder.mThumbnailView.getContext().getResources(), bit);
            Bitmap.createScaledBitmap(bit, 256, 256, false);
            holder.mThumbnailView.setImageBitmap(drawableBit.getBitmap());
        } else {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            //Bitmap bit = BitmapFactory.decodeResource(holder.mThumbnailView.getResources(), R.drawable.monteemoji);
            //BitmapDrawable drawableBit = new BitmapDrawable(holder.mThumbnailView.getContext().getResources(), R.drawable.monteemoji);
            Bitmap bit = BitmapFactory.decodeResource(holder.mThumbnailView.getContext().getResources(), R.drawable.esri);
            Bitmap.createScaledBitmap(bit, 256, 256, false);
            holder.mThumbnailView.setImageBitmap(bit);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}