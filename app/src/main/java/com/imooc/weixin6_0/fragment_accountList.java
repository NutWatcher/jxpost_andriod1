package com.imooc.weixin6_0;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_accountList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_accountList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_accountList extends Fragment {

    private List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();
    private SimpleAdapter adapter ;
    private Button button ;
    private TextView textView ;
    private ListView listView ;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1 ;
    private String mParam2 ;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_accountList.
     */
    public static fragment_accountList newInstance(String param1, String param2) {
        fragment_accountList fragment = new fragment_accountList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public fragment_accountList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_account_list, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        Log.i("dd","start1");
        textView = (TextView)getActivity().findViewById(R.id.text);
        button = (Button)getActivity().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("lala");
            }
        });

        for (int i = 0 ; i < 10 ; i ++ ){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name","战三");
            map.put("money","800004.56");
            map.put("account","54684984896849698498");
            dataList.add(map);
        }
        adapter = new SimpleAdapter(getActivity().getApplicationContext(), dataList, R.layout.fragment_account_list_view,
                new String[]{"name","money","account"}, new int[]{R.id.listname, R.id.listmoney, R.id.account} );
        listView = (ListView) getActivity().findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedPosition = i;
                Log.i("fragment_log", String.valueOf(selectedPosition));
                mListener.onFragmentInteraction(String.valueOf(selectedPosition));
            }
        });
        super.onActivityCreated(savedInstanceState);

    }



    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String uri);
    }

}
