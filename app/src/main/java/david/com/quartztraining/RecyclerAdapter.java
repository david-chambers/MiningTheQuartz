package david.com.quartztraining;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex7370 on 3/18/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.InnerViewHolder> {

    private ArrayList<String> mPortalDataset;

    @Override
    public InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.layer_view_holder, parent, false);
        return new InnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InnerViewHolder holder, int position) {
        final String layerName = mPortalDataset.get(position);
        holder.mTitleTextView.setText(layerName);
    }

    @Override
    public int getItemCount() {
        return mPortalDataset.size();
    }

    public RecyclerAdapter (ArrayList<String> PortalDataset) {
        mPortalDataset = PortalDataset;
    }
    
    public class InnerViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;
        public ImageView mThumbnailView;

        public InnerViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.layer_title);
            mThumbnailView = (ImageView) itemView.findViewById(R.id.layer_thumbnail);
        }
    }
}
