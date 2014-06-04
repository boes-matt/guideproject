package com.boes.guideproject.app;

import android.graphics.Bitmap;

import com.boes.guideproject.core.GuideMap;
import com.boes.guideproject.core.GuideMarker;
import com.boes.guideproject.core.MapListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.util.HashMap;
import java.util.Map;

public class GoogleGuideMap implements
        GuideMap,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnCameraChangeListener {

    private final static float DEFAULT_ZOOM = 15;

    GoogleMap googleMap;
    private float zoomLevel;
    MapListener mapListener;
    Map<Marker, GuideMarker> markerMap;
    IconGenerator iconFactory;

    public GoogleGuideMap(GoogleMap googleMap, HashMap<Marker, GuideMarker> markerMap, IconGenerator iconFactory) {
        this.googleMap = googleMap;
        this.googleMap.setOnMapClickListener(this);
        this.googleMap.setOnMarkerClickListener(this);
        this.googleMap.setOnCameraChangeListener(this);
        this.zoomLevel = DEFAULT_ZOOM;
        this.mapListener = null;
        this.markerMap = markerMap;
        this.iconFactory = iconFactory;
    }

    @Override
    public void addMarker(int guidePosition, String title, double latitude, double longitude) {
        Bitmap bitmap = iconFactory.makeIcon(title);
        MarkerOptions opts = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .position(new LatLng(latitude, longitude));
        Marker marker = googleMap.addMarker(opts);
        markerMap.put(marker, new GuideMarker(guidePosition, latitude, longitude));
    }

    @Override
    public void centerAt(double latitude, double longitude) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoomLevel));
    }

    @Override
    public void animateTo(double latitude, double longitude) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoomLevel));
    }

    @Override
    public void animateToGuidePosition(int guidePosition) {
        for (GuideMarker marker : markerMap.values()) {
            if (marker.getPosition() == guidePosition)
                animateTo(marker.getLatitude(), marker.getLongitude());
        }
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
    public boolean onMarkerClick(Marker marker) {
        if (mapListener != null) {
            GuideMarker guideMarker = markerMap.get(marker);
            mapListener.onMarkerClick(
                    guideMarker.getPosition(),
                    guideMarker.getLatitude(),
                    guideMarker.getLongitude());

            return true;
        }

        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (mapListener != null) mapListener.onMapClick();
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        zoomLevel = cameraPosition.zoom;
    }

}
