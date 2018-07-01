package com.example.sev_user.demo5;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Minh Anh on 1/10/2018.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private final int PAGE_COUNT = 3;

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public TabAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Favorite";
            case 1:
                return "Contact";
            case 2:
                return "Blacklist";
            default:
                return null;
        }
    }


}
