package com.boes.guideproject.app;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

public class GuideActivity extends ActionBarActivity implements
        LoaderManager.LoaderCallbacks<ParseObject> {

    TextView guideTitle;
    ProgressBar networkProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide);
        guideTitle = (TextView) findViewById(R.id.guide_title);
        networkProgress = (ProgressBar) findViewById(R.id.network_progress);

        Bundle args = getIntent().getExtras();
        getSupportLoaderManager().initLoader(0, args, this);
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
    public Loader<ParseObject> onCreateLoader(int id, Bundle args) {
        return new ParseObjectLoader(this, "Place", args.getString("id"));
    }

    @Override
    public void onLoadFinished(Loader<ParseObject> loader, ParseObject data) {
        networkProgress.setVisibility(View.GONE);
        if (isValidResponse(data)) guideTitle.setText(data.getString("title"));
        else Toast.makeText(this, data.getString("message"), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoaderReset(Loader<ParseObject> loader) {
        networkProgress.setVisibility(View.VISIBLE);
        guideTitle.setText(null);
    }

    private boolean isValidResponse(ParseObject data) {
        if ("Exception".equals(data.getClassName())) return false;
        else return true;
    }

}
