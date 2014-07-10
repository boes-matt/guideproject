package com.boes.guideproject.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GuideCardImage extends Fragment {

    public static GuideCardImage newInstance(int drawable) {
        Bundle args = new Bundle();
        args.putInt("drawable", drawable);
        GuideCardImage fragment = new GuideCardImage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.place_image, container, false);

        int drawable = getArguments().getInt("drawable");
        ImageView img = (ImageView) v.findViewById(R.id.image);
        img.setImageDrawable(getResources().getDrawable(drawable));

        return v;
    }

}
