package org.lonewanderer.ellie;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dghisalberti on 29/08/2014.
 */
public class HomeFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((StartActivtiy) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void crossfade() {
        View container = getView();
        View imgEllieLogo;
        int mShortAnimationDuration;

        if(container != null) {
            imgEllieLogo = container.findViewById(R.id.imgEllieLogo);
            imgEllieLogo.setVisibility(View.GONE);
            mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);
        }
        else
            return;

        imgEllieLogo.setAlpha(0f);
        imgEllieLogo.setVisibility(View.VISIBLE);

        imgEllieLogo.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);
    }
}