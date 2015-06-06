package com.paul.debashis.navigationdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Admin on 23/02/2015.
 */
public class FragmentList extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_list,container,false);
        int i = getArguments().getInt("position");
        String mItem = getResources().getStringArray(R.array.items_array)[i];
      //  getActivity().setTitle(mItem);
        ((TextView)mView.findViewById(R.id.fragment_textView)).setText(mItem);
        return mView;
    }
}
