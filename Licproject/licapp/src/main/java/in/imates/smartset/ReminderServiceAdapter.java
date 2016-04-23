package in.imates.smartset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by root on 5/1/16.
 */
public class ReminderServiceAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] reminder_item_name;
    private final int[] reminder_item_Image_id;

    public ReminderServiceAdapter(Context c,String[] item_name,int[] item_Image_id ) {
        mContext = c;
        this.reminder_item_name = item_name;
        this.reminder_item_Image_id = item_Image_id ;
    }
    @Override
    public int getCount() {
        return reminder_item_name.length;
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
            grid = inflater.inflate(R.layout.reminder_services_item,null);
            TextView itemname = (TextView) grid.findViewById(R.id.gridrem_text);
            ImageView itemimage = (ImageView) grid.findViewById(R.id.gridrem_image);
            itemname.setText(reminder_item_name[position]);
            itemimage.setImageResource(reminder_item_Image_id[position]);
        }
        else {
            grid = (View) view;
        }
        return grid;
    }
}
