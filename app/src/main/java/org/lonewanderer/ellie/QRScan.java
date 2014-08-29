package org.lonewanderer.ellie;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QRScan.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QRScan#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class QRScan extends Fragment implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View imgLocalLogo;
    private View imgQrSample;
    private int mShortAnimationDuration;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QRScan.
     */
    // TODO: Rename and change types and number of parameters
    public static QRScan newInstance(String param1, String param2) {
        QRScan fragment = new QRScan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static QRScan newInstance() {
        QRScan fragment = new QRScan();
        return fragment;
    }

    public QRScan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCANa");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);

        } catch (ActivityNotFoundException anfe) {
            Log.e("onCreate", "Scanner Not Found", anfe);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qrscan, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");

                String[] info = LocalDataParser.parseQrCodeData(contents);

                if(info == null) {
                    contents = "ERROR";
                    ((ImageView)getView().findViewById(R.id.imgVwQr)).setVisibility(View.GONE);
                    ((TextView)getView().findViewById(R.id.txtVwvLocalName)).setText("Locale: " + contents);
                    ((TextView)getView().findViewById(R.id.txtVwvLocalName)).setVisibility(View.VISIBLE);
                }
                else if(info.length == 3)
                {
                    ((ImageView)getView().findViewById(R.id.imgVwQr)).setVisibility(View.GONE);
                    ((ImageView)getView().findViewById(R.id.imgLocalLogo)).setVisibility(View.VISIBLE);
                    ((TextView)getView().findViewById(R.id.txtVwvLocalName)).setText("Locale: " + info[0]);
                    ((TextView)getView().findViewById(R.id.txtVwvLocalCity)).setText("Città: " + info[1]);
                    ((TextView)getView().findViewById(R.id.txtVwvLocalCountryCode)).setText("Provincia: " + info[2]);
                    ((TextView)getView().findViewById(R.id.txtVwvLocalName)).setVisibility(View.VISIBLE);
                    ((TextView)getView().findViewById(R.id.txtVwvLocalCity)).setVisibility(View.VISIBLE);
                    ((TextView)getView().findViewById(R.id.txtVwvLocalCountryCode)).setVisibility(View.VISIBLE);
                }
            } else if (resultCode ==  Activity.RESULT_CANCELED) {
                // Handle cancel
                Toast toast = Toast.makeText(getActivity(), "Scan was Cancelled!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();

            }
        }
    }
}