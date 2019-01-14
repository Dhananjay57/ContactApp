package com.example.drizzle.smsapp;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Hashtable;

class SMSPagerAdapter  extends FragmentStatePagerAdapter {

    @NonNull
    private String[] mPageTitle;
    private Hashtable<Integer, Fragment> fragmentHolder = new Hashtable<>();


    public SMSPagerAdapter(FragmentManager fm, @NonNull String[] pageTitle) {
        super(fm);
        this.mPageTitle = pageTitle;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitle[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = ContactListFragment.newInstance();
                break;
            case 1:
                fragment = DetailsFragment.newInstance();
                break;

        }
        fragmentHolder.put(position, fragment);
        return fragment;
    }


    @Override
    public int getCount() {
        return mPageTitle.length;
    }

    public Fragment getFragment(int position) {
        return fragmentHolder.get(position);

    }
}
