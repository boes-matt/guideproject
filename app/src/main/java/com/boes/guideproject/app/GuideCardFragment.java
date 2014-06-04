package com.boes.guideproject.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GuideCardFragment extends Fragment {

    public static GuideCardFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        GuideCardFragment fragment = new GuideCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_guide_card, container, false);

        String title = getArguments().getString("title");
        TextView tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvTitle.setText(title);

        return v;
    }

}
