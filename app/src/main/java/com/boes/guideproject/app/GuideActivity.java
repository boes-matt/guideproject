package com.boes.guideproject.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.ParseObject;

public class GuideActivity extends ActionBarActivity implements
        DataItemListener<ParseObject> {

    DataManager data;
    TextView guideTitle;
    ProgressBar networkProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide);
        guideTitle = (TextView) findViewById(R.id.guide_title);
        networkProgress = (ProgressBar) findViewById(R.id.network_progress);

        Bundle args = getIntent().getExtras();
        String id = args != null ? args.getString("id") : "";
        data = new ParseObjectManager(
                savedInstanceState,                 // Bundle savedInstanceState
                getSupportLoaderManager(),          // LoaderManager
                new ParseObjectLoader(this, id),    // Loader
                this                                // DataItemListener<ParseObject>
        );
        data.load();
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
    public void process(ParseObject item) {
        networkProgress.setVisibility(View.GONE);
        guideTitle.setText(item.getString("title"));
    }

}
