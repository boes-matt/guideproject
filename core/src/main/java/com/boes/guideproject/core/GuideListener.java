package com.boes.guideproject.core;

public interface GuideListener {
    void setGuide(String title, double latitude, double longitude);
    void setPlace(int position, String title, double latitude, double longitude);
}
