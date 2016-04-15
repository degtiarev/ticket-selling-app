package com.delexa.chudobilet.ForChildren;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForChildrenFragment extends Fragment {


    public ForChildrenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_for_children, container, false);
    }

}
