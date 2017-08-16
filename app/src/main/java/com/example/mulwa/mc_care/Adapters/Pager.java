package com.example.mulwa.mc_care.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mulwa.mc_care.HealthTips.AfterPregnancy;
import com.example.mulwa.mc_care.HealthTips.DuringPregnancy;

/**
 * Created by mulwa on 7/24/17.
 */

public class Pager extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    int tabCount;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                AfterPregnancy tab1 = new AfterPregnancy();
                return tab1;
            case 1:
                DuringPregnancy tab2 = new DuringPregnancy();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
