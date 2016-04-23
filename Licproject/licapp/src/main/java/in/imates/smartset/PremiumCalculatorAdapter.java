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
public class PremiumCalculatorAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] premium_item_name;
    private final int[] premium_item_Image_id;

    public PremiumCalculatorAdapter(Context c,String[] item_name,int[] item_Image_id ) {
        mContext = c;
        this.premium_item_name = item_name;
        this.premium_item_Image_id = item_Image_id ;
    }
    @Override
    public int getCount() {
        return premium_item_name.length;
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
            grid = inflater.inflate(R.layout.premium_calculator_item,null);
            TextView itemname = (TextView) grid.findViewById(R.id.gridprem_text);
            ImageView itemimage = (ImageView) grid.findViewById(R.id.gridprem_image);
            itemname.setText(premium_item_name[position]);
            itemimage.setImageResource(premium_item_Image_id[position]);
        }
        else {
            grid = (View) view;
        }
        return grid;
    }

}
