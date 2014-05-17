package com.boes.guideproject.app;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.parse.ParseObject;

public class ParseObjectManager implements
        DataManager,
        LoaderManager.LoaderCallbacks<ParseObject> {

    AsyncTaskLoader<ParseObject> loader;
    Bundle savedInstanceState;
    LoaderManager manager;
    DataItemListener<ParseObject> listener;

    public ParseObjectManager(
            Bundle savedInstanceState,
            LoaderManager manager,
            AsyncTaskLoader<ParseObject> loader,
            DataItemListener<ParseObject> listener) {
        this.savedInstanceState = savedInstanceState;
        this.manager = manager;
        this.loader = loader;
        this.listener = listener;
    }

    @Override
    public void load() {
        manager.initLoader(0, null, this);    // Check if savedInstanceState != null
    }

    @Override
    public Loader<ParseObject> onCreateLoader(int id, Bundle args) {
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<ParseObject> loader, ParseObject data) {
        listener.process(data);
    }

    @Override
    public void onLoaderReset(Loader<ParseObject> loader) {

    }

}
