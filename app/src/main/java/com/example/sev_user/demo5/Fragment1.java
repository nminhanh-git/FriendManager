package com.example.sev_user.demo5;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    public Fragment1() {
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
        adapter = new myAdapter(getActivity(), list);
        mListview.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        refresh();
        super.onResume();
    }

    public void refresh() {
        list.addAll(dbHelper.getFriend(0));
        adapter.notifyDataSetChanged();
    }

}
