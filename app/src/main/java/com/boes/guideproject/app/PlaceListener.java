package com.boes.guideproject.app;

public interface PlaceListener {
    void process(String title, double latitude, double longitude);
    void process(Exception e);
}
