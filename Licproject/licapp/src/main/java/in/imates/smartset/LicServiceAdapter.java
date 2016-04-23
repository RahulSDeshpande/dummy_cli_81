package in.imates.smartset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by root on 24/12/15.
 */
public class LicServiceAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] service_item_name;
    private final int[] service_item_Image_id;

    public LicServiceAdapter(Context c,String[] item_name,int[] item_Image_id ) {
        mContext = c;
        this.service_item_name = item_name;
        this.service_item_Image_id = item_Image_id ;
    }
    @Override
    public int getCount() {
        return service_item_name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.lic_servicing_item,null);
            TextView itemname = (TextView) grid.findViewById(R.id.gridlic_text);
            ImageView itemimage = (ImageView) grid.findViewById(R.id.gridlic_image);
            itemname.setText(service_item_name[position]);
            itemimage.setImageResource(service_item_Image_id[position]);
        }
        else {
            grid = (View) view;
        }
        return grid;
    }
}
