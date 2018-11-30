package userInteface.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"HOME", "INVOICES"};

    ArrayList<Fragment> fragments=new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //ADD PAGE
    public void addFragment(Fragment f)
    {
        fragments.add(f);
    }

    //set title

    @Override
    public CharSequence getPageTitle(int position) {
     return tabTitles[position];
    }
}