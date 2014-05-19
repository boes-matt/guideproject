package com.boes.guideproject.app.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.test.ActivityInstrumentationTestCase2;

import com.boes.guideproject.app.GuideActivity;
import com.boes.guideproject.app.R;
import com.google.android.apps.common.testing.ui.espresso.Espresso;
import com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.hamcrest.Matchers;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GuideActivityEndToEndTest extends ActivityInstrumentationTestCase2<GuideActivity> {

    String id;
    String title;

    public GuideActivityEndToEndTest() {
        super(GuideActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Turn on WIFI if network off
        turnWiFiOnIfNetworkOff();

        // Login dev user
        ParseUser.logIn("dev", "password");

        // Get dev user's previous test data and delete
        List<ParseObject> objects = ParseQuery.getQuery("Place")
                                              .whereEqualTo("author", ParseUser.getCurrentUser())
                                              .find();
        ParseObject.deleteAll(objects);

        // Reset test state
        id = null;
        title = "Guide " + new Random().nextInt(10);

        // Create new test data
        ParseObject g1 = new ParseObject("Place");
        g1.put("author", ParseUser.getCurrentUser());
        g1.put("isGuide", true);
        g1.put("isPublished", true);
        g1.put("title", title);

        // Set permissions for published guide: public read; private write
        ParseACL acl = g1.getACL();
        acl.setPublicReadAccess(true);

        // Insert test data
        g1.save();
        id = g1.getObjectId();

        // Logout dev user
        ParseUser.logOut();
    }

    public void testReceivesGuideFromNetworkOnStart() {
        // Build intent
        Intent i = new Intent();
        i.putExtra("id", id);

        // Start activity, passing intent with guide id
        setActivityIntent(i);
        getActivity();

        // Received guide
        Espresso.onView(ViewMatchers.withId(R.id.network_progress))
                .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())));

        Espresso.onView(ViewMatchers.withId(R.id.guide_title))
                .check(ViewAssertions.matches(ViewMatchers.withText(title)));

        // Assert error message is NOT displayed
        Espresso.onView(ViewMatchers.withId(R.id.network_error))
                .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())));
    }

    public void testMaintainsStateOnDeviceRotation() {
        // Load guide from network
        testReceivesGuideFromNetworkOnStart();

        // Assert NOT in landscape layout
        Espresso.onView(ViewMatchers.withId(R.id.landscape_mode))
                .check(ViewAssertions.doesNotExist());

        // Rotate device
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Check for landscape layout
        Espresso.onView(ViewMatchers.withId(R.id.landscape_mode))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check for correct guide title
        Espresso.onView(ViewMatchers.withId(R.id.guide_title))
                .check(ViewAssertions.matches(ViewMatchers.withText(title)));
    }

    public void testDisplaysErrorOnNoNetworkConnection() throws InterruptedException {
        // Turn off WIFI.  REQUIRES permissions in manifest for testing purposes.
        Context context = getInstrumentation().getTargetContext();
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);

        Thread.sleep(1000);    // Wait for WIFI to disconnect

        // ASSUMES tester manually turns off mobile data on device.  Fail otherwise.
        ConnectivityManager connection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = connection.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        assertTrue("Please turn mobile data OFF manually.",
                mobile == null || mobile.getState() == NetworkInfo.State.DISCONNECTED);

        // Build intent
        Intent i = new Intent();
        i.putExtra("id", id);

        // Start activity, passing intent with guide id
        setActivityIntent(i);
        getActivity();

        // Check for error message
        Espresso.onView(ViewMatchers.withId(R.id.network_error))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check that guide title is NOT set
        Espresso.onView(ViewMatchers.withId(R.id.guide_title))
                .check(ViewAssertions.matches(ViewMatchers.withText("")));
    }

    private void turnWiFiOnIfNetworkOff() throws InterruptedException {
        Context context = getInstrumentation().getTargetContext();
        ConnectivityManager connection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connection.getActiveNetworkInfo();
        CountDownLatch latch = null;

        if (network == null || !network.isConnected()) {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            wifi.setWifiEnabled(true);
            latch = new CountDownLatch(1);
        }

        // Wait for WIFI to reconnect
        if (latch != null) {
            do {
                network = connection.getActiveNetworkInfo();
            } while (network == null || !network.isConnected());
            latch.await(1000, TimeUnit.MILLISECONDS);
        }
    }

}
