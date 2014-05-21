package com.boes.guideproject.app.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

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
        startActivity();
        checkForCorrectViews();
    }

    public void testDisplaysErrorOnStartIfNoNetworkConnection() throws InterruptedException {
        turnNetworkOff();
        startActivity();
        checkForErrorViews();
    }

    public void testMaintainsStateOnRestartOrDeviceRotationEvenWithLostConnection() throws InterruptedException {
        // Load guide from network
        testReceivesGuideFromNetworkOnStart();

        // Lost connection!
        turnNetworkOff();

        // User switches to another app, and then returns; restarts activity
        restartActivity();

        // Check views remain valid
        checkForCorrectViews();

        // Rotate device with still no network connection; recreates activity
        rotateDevice();

        // Check views remain valid
        checkForCorrectViews();
    }

    private void checkForCorrectViews() {
        // Assert error message is NOT displayed
        Espresso.onView(ViewMatchers.withId(R.id.network_error))
                .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())));

        // Assert progress bar is NOT displayed
        Espresso.onView(ViewMatchers.withId(R.id.network_progress))
                .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())));

        // Assert correct guide title
        Espresso.onView(ViewMatchers.withId(R.id.guide_title))
                .check(ViewAssertions.matches(ViewMatchers.withText(title)));
    }

    private void checkForErrorViews() {
        // Assert error message is displayed
        Espresso.onView(ViewMatchers.withId(R.id.network_error))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Assert progress bar is NOT displayed
        Espresso.onView(ViewMatchers.withId(R.id.network_progress))
                .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())));

        // Check that guide title is NOT set
        Espresso.onView(ViewMatchers.withId(R.id.guide_title))
                .check(ViewAssertions.matches(ViewMatchers.withText("")));
    }

    private void startActivity() {
        // Build intent
        Intent i = new Intent();
        i.putExtra("id", id);

        // Start activity, passing intent with guide id
        setActivityIntent(i);
        getActivity();
    }

    private void restartActivity() {
        final Activity activity = getActivity();
        final Instrumentation instrumentation = getInstrumentation();

        // Restart activity: onPause -> onStop -> onRestart -> onStart -> onResume
        instrumentation.callActivityOnPause(activity);
        instrumentation.callActivityOnStop(activity);
        instrumentation.callActivityOnRestart(activity);
        instrumentation.runOnMainSync(new Runnable() {

            @Override
            public void run() {
                instrumentation.callActivityOnStart(activity);
                instrumentation.callActivityOnResume(activity);
            }

        });
    }

    private void rotateDevice() {
        int currentOrientation = getActivity().getRequestedOrientation();

        if (currentOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            // Assert NOT in landscape layout
            Espresso.onView(ViewMatchers.withId(R.id.landscape_mode))
                    .check(ViewAssertions.doesNotExist());

            // Rotate to landscape
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            // Check for landscape layout
            Espresso.onView(ViewMatchers.withId(R.id.landscape_mode))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        } else {
            // Check for landscape layout
            Espresso.onView(ViewMatchers.withId(R.id.landscape_mode))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

            // Rotate to portrait
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            // Assert NOT in landscape layout
            Espresso.onView(ViewMatchers.withId(R.id.landscape_mode))
                    .check(ViewAssertions.doesNotExist());
        }
    }

    private void turnNetworkOff() throws InterruptedException {
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

        Log.d("Network", "Network turned off");
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

    @Override
    protected void tearDown() throws Exception {
        turnWiFiOnIfNetworkOff();
        super.tearDown();
    }

}
