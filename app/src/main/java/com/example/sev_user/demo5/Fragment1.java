package com.example.sev_user.demo5;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends android.support.v4.app.Fragment {


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

        refresh();

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), DetailActivity.class);
                Friend currentFriend = (Friend) mListview.getItemAtPosition(position);
                i.putExtra("current friend", currentFriend);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {
        list.clear();
        list.addAll(dbHelper.getFriend(0));
        adapter.notifyDataSetChanged();
    }

}
