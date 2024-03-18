package com.example.bottlespinnerdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutUSFragment extends Fragment {

//    JustifiedTextView justifiedTextView;

    String dummyText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about_u_s, container, false);

//        justifiedTextView = v.findViewById(R.id.howtoPlayJustifyId);

        return v;
    }
}