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
public class FinancialCalculatorAdapter extends BaseAdapter {

    private Context mContext;
    private final String [] fincal_item_name;
    private final int[] fincal_item_Image_id;

    public FinancialCalculatorAdapter(Context c, String[] item_name,int[] item_Image_id ) {
        mContext = c;
        this.fincal_item_name = item_name;
        this.fincal_item_Image_id = item_Image_id ;
    }
    @Override
    public int getCount() {
        return fincal_item_name.length;
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
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.financial_calculator_item,null);


        }
        else {
            grid = (View) view;
        }

        TextView itemname = (TextView) grid.findViewById(R.id.gridfincal_text);
        itemname.setText(fincal_item_name[position]);
        ImageView itemimage = (ImageView) grid.findViewById(R.id.gridfincal_image);
        itemimage.setImageResource(fincal_item_Image_id[position]);
        return grid;
    }
}
