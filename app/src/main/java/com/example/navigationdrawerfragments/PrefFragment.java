package com.example.navigationdrawerfragments;

import android.preference.PreferenceFragment;
import android.os.Bundle;

public class PrefFragment extends PreferenceFragment {

    public static final String TAG = "PREF";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_frag);
    }

    public static PrefFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PrefFragment fragment = new PrefFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
