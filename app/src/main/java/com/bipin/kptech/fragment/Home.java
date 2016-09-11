package com.bipin.kptech.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bipin.kptech.R;

/**
 * Created by Bipin on 8/30/2016.
 */
public class Home extends Fragment {

    private String sToolBarTitle;

    public Home() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sToolBarTitle = ((AppCompatActivity) getActivity()).getSupportActionBar().getTitle().toString();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getApplicationContext().getResources()
                .getString(R.string.home));
        final View rootView = inflater.inflate(R.layout.home_fragment, container, getActivity().
                getApplicationContext().getResources().getBoolean(R.bool.attach_to_root));

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(sToolBarTitle);
    }
}
