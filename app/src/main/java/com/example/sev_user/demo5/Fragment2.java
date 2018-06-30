package com.example.sev_user.demo5;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sev_user on 6/28/2018.
 */
public class Fragment2 extends Fragment {
    public Fragment2() {
        // Required empty public constructor
    }

    private ListView mListview;
    private myAdapter adapter;
    private ArrayList<Friend> list;
    private MyDB dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        mListview = view.findViewById(R.id.friend_list_view);

        list = new ArrayList<>();
        dbHelper = new MyDB(getActivity());
        list = dbHelper.getFriend(1);
        adapter = new myAdapter(getActivity(), list);

        mListview.setAdapter(adapter);
        return view;
    }

}