package in.imates.smartset;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class GreetingCategoryAdapter extends BaseAdapter {

    private final String[] item_name;
    private final String item_image_url;
    private Context mContext;

    public GreetingCategoryAdapter(Context c, String[] item_name, String item_image_url) {

        mContext = c;
        this.item_image_url = item_image_url;
        this.item_name = item_name;
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

        if (view == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.item_greeting_category, null);

            //  TextView itemname = (TextView) grid.findViewById(R.id.grid_text);
            ImageView ivGreeting = (ImageView) grid.findViewById(R.id.grid_image);

            //  itemname.setText(item_name[position]);
            //  itemimage.setImageResource(item_Image_id[position]);

            Log.e("GreetingCategoryAdapter.getView()", "position: " + position);
            Log.e("GreetingCategoryAdapter.getView()", "image_url: " + item_image_url + item_name[position]);

            //CLOUDINARY
            Picasso.with(mContext)
                    .load(item_image_url + item_name[position])
                    .placeholder(R.drawable.imates)
                    .into(ivGreeting);

        } else {
            grid = (View) view;
        }
        return grid;
    }
}