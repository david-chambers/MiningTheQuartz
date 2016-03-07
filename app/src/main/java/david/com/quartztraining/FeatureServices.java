package david.com.quartztraining;

import java.io.Serializable;

/**
 * Created by alex7370 on 3/7/2016.
 */
public class FeatureServices implements Serializable{
    private byte[] mThumbnail;
    private String mFeatureServiceName;

    public FeatureServices(byte[] ThumbnailUrl, String FeatureServiceName)
    {
        mThumbnail = ThumbnailUrl;
        mFeatureServiceName = FeatureServiceName;
    }

    public byte[] getThumbnail() {
        return mThumbnail;
    }

    public String getTitle() {
        return mFeatureServiceName;
    }

    public void setThumbnail(byte[] thumbnail) {
        mThumbnail = thumbnail;
    }
}
