package com.example.sev_user.demo5;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sev_user on 6/29/2018.
 */
public class myAdapter extends ArrayAdapter<Friend> {
    public myAdapter(Context context, ArrayList<Friend> list) {
        super(context, 0, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.e("MTINH", "getView" + " " + position);
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.list_item_layout, parent, false);

            /*Anh xa*/
            holder.lsItemAvt = convertView.findViewById(R.id.profile_image);
            holder.lsItemName = convertView.findViewById(R.id.lsItemName);
            holder.lsItemPhone = convertView.findViewById(R.id.lsItemPhone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /*Hien thi Item*/
        Friend fr = getItem(position);

        if (fr.getAvata() != null) {
            Uri uri = Uri.parse(fr.getAvata().toString());
            GlideApp.with(getContext()).load(uri).into(holder.lsItemAvt);
        }

        holder.lsItemName.setText(fr.getName().toString());
        holder.lsItemPhone.setText(fr.getPhone().toString());

        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    class ViewHolder {
        CircleImageView lsItemAvt;
        TextView lsItemName;
        TextView lsItemPhone;
    }
}