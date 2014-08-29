package org.lonewanderer.ellie;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutFrag extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_APP_VERS = "version";

    private String mVersion;

    private View imgEllieLogo;
    private View txtVersion;
    private View txtCopyright;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param version Version.
     * @return A new instance of fragment QRScan.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFrag newInstance(String version) {
        AboutFrag fragment = new AboutFrag();
        Bundle args = new Bundle();
        args.putString(ARG_APP_VERS, version);
        fragment.setArguments(args);
        return fragment;
    }
    public AboutFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVersion = getArguments().getString(ARG_APP_VERS);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        crossfade();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View V = inflater.inflate(R.layout.fragment_about, container, false);

        ((TextView)(V.findViewById(R.id.txtVers))).setText(getString(R.string.app_name) + " v. " + mVersion);
        ((TextView)(V.findViewById(R.id.txtCopy))).setText(getString(R.string.ABOUT_Copyright));
        imgEllieLogo = V.findViewById(R.id.imgEllieLogoAbout);
        txtVersion = V.findViewById(R.id.txtVers);
        txtCopyright = V.findViewById(R.id.txtCopy);

        imgEllieLogo.setVisibility(View.GONE);
        txtVersion.setVisibility(View.GONE);
        txtCopyright.setVisibility(View.GONE);

        imgEllieLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Inflate the layout for this fragment
        return V;
    }

    private void crossfade() {
        int mShortAnimationDuration;
            mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);


        imgEllieLogo.setAlpha(0f);
        txtVersion.setAlpha(0f);
        txtCopyright.setAlpha(0f);

        imgEllieLogo.setVisibility(View.VISIBLE);
        txtVersion.setVisibility(View.VISIBLE);
        txtCopyright.setVisibility(View.VISIBLE);

        imgEllieLogo.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);
        txtVersion.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);
        txtCopyright.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);
    }
}
