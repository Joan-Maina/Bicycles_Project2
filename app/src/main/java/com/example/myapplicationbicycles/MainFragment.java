package com.example.myapplicationbicycles;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    //initialize variable
    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //initialize view
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //assign variables:
        textView = view.findViewById(R.id.text_view);

        //get titke
        String sTitle = getArguments().getString("title");

        //set title
        textView.setText(sTitle);
        // return view
        return view;
    }
}