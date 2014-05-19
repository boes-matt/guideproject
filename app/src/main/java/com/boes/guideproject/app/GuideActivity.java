package com.boes.guideproject.app;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.ParseObject;

public class GuideActivity extends ActionBarActivity implements PlaceListener {

    private static final int PLACE_LOADER = 1;
    private static final int PLACES_LOADER = 2;

    TextView guideTitle;
    ProgressBar networkProgress;
    TextView networkErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide);
        guideTitle = (TextView) findViewById(R.id.guide_title);
        networkProgress = (ProgressBar) findViewById(R.id.network_progress);
        networkErrorMessage = (TextView) findViewById(R.id.network_error);

        Bundle args = getIntent().getExtras();
        String id = args.getString("id");

        AsyncTaskLoader<ParseObject> loader = new ParseObjectLoader(this, "Place", id);
        LoaderCallbacks<ParseObject> callbacks = new ParseObjectToPlaceTranslator(loader, this);
        getSupportLoaderManager().initLoader(PLACE_LOADER, null, callbacks);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void process(String title) {
        networkProgress.setVisibility(View.GONE);
        guideTitle.setText(title);
    }

    @Override
    public void process(Exception e) {
        networkProgress.setVisibility(View.GONE);
        networkErrorMessage.setText(e.getMessage());
        networkErrorMessage.setVisibility(View.VISIBLE);
    }

}
