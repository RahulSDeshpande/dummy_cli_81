package in.imates.smartset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainScreenAdapter extends BaseAdapter{

    private Context mContext;
    private final String[] item_name;
    private final int[] item_Image_id;

    public MainScreenAdapter(Context c,String[] item_name,int[] item_Image_id ) {
        mContext = c;
        this.item_Image_id = item_Image_id;
        this.item_name = item_name ;
    }
    @Override
    public int getCount() {
        return item_name.length;
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
            grid = inflater.inflate(R.layout.main_screen_item,null);
            TextView itemname = (TextView) grid.findViewById(R.id.grid_text);
            ImageView itemimage = (ImageView) grid.findViewById(R.id.grid_image);
            itemname.setText(item_name[position]);
            itemimage.setImageResource(item_Image_id[position]);
        }
        else {
            grid = (View) view;
        }
        return grid;
    }
}
