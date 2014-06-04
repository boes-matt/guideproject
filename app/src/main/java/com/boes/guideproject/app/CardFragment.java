package com.boes.guideproject.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CardFragment extends Fragment {

    public static CardFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        CardFragment fragment = new CardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        TextView tvTitle = new TextView(getActivity());
        tvTitle.setText(title);
        return tvTitle;
    }

}
