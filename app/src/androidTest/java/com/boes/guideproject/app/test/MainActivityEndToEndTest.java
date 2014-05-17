package com.boes.guideproject.app.test;

import android.test.ActivityInstrumentationTestCase2;

import com.boes.guideproject.app.MainActivity;
import com.boes.guideproject.app.R;
import com.google.android.apps.common.testing.ui.espresso.Espresso;
import com.google.android.apps.common.testing.ui.espresso.action.ViewActions;
import com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;

public class MainActivityEndToEndTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityEndToEndTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testStartsGuideActivityOnButtonClick() {
        // Click button
        Espresso.onView(ViewMatchers.withId(R.id.guide_button))
                .perform(ViewActions.click());

        // Check in GuideActivity now
        Espresso.onView(ViewMatchers.withId(R.id.guide_title))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.guide_button))
                .check(ViewAssertions.doesNotExist());

        // Go back
        Espresso.pressBack();

        // Check in MainActivity now
        Espresso.onView(ViewMatchers.withId(R.id.guide_button))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
