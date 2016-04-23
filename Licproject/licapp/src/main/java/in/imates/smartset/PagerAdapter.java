package in.imates.smartset;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by root on 2/1/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int NoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.NoOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentIndividualPolicy frag = new FragmentIndividualPolicy();
                return frag;
            case 1:
                return new FragmentPensionPolicy();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NoOfTabs;
    }
}
