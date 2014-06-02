package com.boes.guideproject.app;

import android.graphics.Bitmap;

import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.MapListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

public class GoogleGuideMap implements
        GuideMap,
        GoogleMap.OnMapClickListener {

    private final static int DEFAULT_ZOOM = 15;

    GoogleMap googleMap;
    MapListener mapListener;
    IconGenerator iconFactory;

    public GoogleGuideMap(GoogleMap googleMap, IconGenerator iconFactory) {
        this.googleMap = googleMap;
        this.googleMap.setOnMapClickListener(this);
        this.mapListener = null;
        this.iconFactory = iconFactory;
    }

    @Override
    public void addMarker(String title, double latitude, double longitude) {
        Bitmap bitmap = iconFactory.makeIcon(title);
        MarkerOptions opts = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .position(new LatLng(latitude, longitude));
        googleMap.addMarker(opts);
    }

    @Override
    public void centerAt(double latitude, double longitude) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), DEFAULT_ZOOM));
    }

    @Override
    public void setMarkerStyle(MarkerStyle style) {
        if (style == MarkerStyle.GUIDE) iconFactory.setStyle(IconGenerator.STYLE_BLUE);
        else iconFactory.setStyle(IconGenerator.STYLE_GREEN);
    }

    public void setMapListener(MapListener mapListener) {
        this.mapListener = mapListener;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (mapListener != null) mapListener.onMapClick();
    }

}
