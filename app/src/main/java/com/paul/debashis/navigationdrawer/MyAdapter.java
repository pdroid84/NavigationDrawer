package com.paul.debashis.navigationdrawer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Admin on 25/02/2015.
 */
public class MyAdapter extends ArrayAdapter{
    int[] imageArray = {R.drawable.ic_action_attach,R.drawable.ic_action_call,R.drawable.ic_action_copy,
        R.drawable.ic_action_cut,R.drawable.ic_action_delete,R.drawable.ic_action_done,R.drawable.ic_action_edit,
        R.drawable.ic_action_locate,R.drawable.ic_action_mail,R.drawable.ic_action_mail_add};
        String[] titleArray;
    Context context;
    public MyAdapter(Context context, String[] titleArray) {
        super(context, R.layout.single_drawer_item, R.id.drawer_text,titleArray);
        Log.d("DEB","MyAdapter constructor is called");
        this.context = context;
        this.titleArray = titleArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("DEB","getView is called");
        LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mLayoutInflater.inflate(R.layout.single_drawer_item,parent,false);
        ImageView mImageView = (ImageView) row.findViewById(R.id.drawer_image);
        TextView mTextView = (TextView) row.findViewById(R.id.drawer_text);
        mTextView.setText(titleArray[position]);
        mImageView.setImageResource(imageArray[position]);
        return row;
    }
}
