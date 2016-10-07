package com.lelelee.codingcamels;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

public class WelcomePageFragment extends Fragment {
    public static final String TAG = WelcomePageFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome_page, null);
        if (view == null) {
            return null;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextFragment();
            }
        });
        return view;
    }

    private void nextFragment() {
        MainActivity.setFirstTimeUser(getActivity(), false);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
