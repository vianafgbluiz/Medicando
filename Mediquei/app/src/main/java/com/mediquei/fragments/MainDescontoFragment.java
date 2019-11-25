package com.mediquei.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediquei.R;
import com.mediquei.util.UtilBuscas;

import java.util.Objects;


public class MainDescontoFragment extends Fragment {


    public MainDescontoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_desconto, container, false);
        return view;
    }

}
