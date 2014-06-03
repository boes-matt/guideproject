package com.boes.guideproject.core;

public interface GuideListener {
    void setGuide(String title, double latitude, double longitude);
    void setPlace(int guidePosition, String title, double latitude, double longitude);
}
