package com.boes.guideproject.app;

import android.graphics.Bitmap;

import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.MapListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.util.HashMap;
import java.util.Map.Entry;

public class GoogleGuideMap implements
        GuideMap,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMapClickListener {

    private final static float DEFAULT_ZOOM = 15;

    GoogleMap googleMap;
    MapListener mapListener;
    HashMap<Integer, Marker> markerMap;
    IconGenerator iconFactory;

    public GoogleGuideMap(GoogleMap googleMap, HashMap<Integer, Marker> markerMap, IconGenerator iconFactory) {
        this.googleMap = googleMap;
        this.googleMap.setOnMapClickListener(this);
        this.googleMap.setOnMarkerClickListener(this);
        this.mapListener = null;
        this.markerMap = markerMap;
        this.iconFactory = iconFactory;
    }

    @Override
    public void setMarker(int guidePosition, String title, double latitude, double longitude) {
        Marker existingMarker = markerMap.get(guidePosition);
        if (existingMarker != null) existingMarker.remove();

        Bitmap bitmap = iconFactory.makeIcon(title);

        MarkerOptions opts = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .position(new LatLng(latitude, longitude));

        Marker marker = googleMap.addMarker(opts);
        markerMap.put(guidePosition, marker);
    }

    @Override
    public void centerAt(int guidePosition) {
        Marker marker = markerMap.get(guidePosition);
        LatLng latLng = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
    }

    @Override
    public void animateTo(int guidePosition) {
        Marker marker = markerMap.get(guidePosition);
        LatLng latLng = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void setMarkerStyle(MarkerStyle style) {
        if (style == GuideMap.MarkerStyle.GUIDE) iconFactory.setStyle(IconGenerator.STYLE_BLUE);
        else iconFactory.setStyle(IconGenerator.STYLE_GREEN);
    }

    public void setMapListener(MapListener mapListener) {
        this.mapListener = mapListener;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (mapListener != null) {
            mapListener.onMarkerClick(getGuidePosition(marker));
            return true;
        }
        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (mapListener != null) mapListener.onMapClick();
    }

    private int getGuidePosition(Marker marker) {
        for (Entry<Integer, Marker> entry : markerMap.entrySet()) {
            if (marker.equals(entry.getValue()))
                return entry.getKey();
        }
        return -1;
    }

}
