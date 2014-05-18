package com.boes.guideproject.app;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.parse.ParseObject;

public class ParseObjectToPlaceTranslator implements LoaderManager.LoaderCallbacks<ParseObject> {

    AsyncTaskLoader<ParseObject> loader;
    PlaceListener listener;

    public ParseObjectToPlaceTranslator(AsyncTaskLoader<ParseObject> loader, PlaceListener listener) {
        this.loader = loader;
        this.listener = listener;
    }

    @Override
    public Loader<ParseObject> onCreateLoader(int id, Bundle args) {
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<ParseObject> loader, ParseObject data) {
        if (isValidResponse(data)) listener.process(data.getString("title"));
        else listener.process(new Exception(data.getString("message")));
    }

    @Override
    public void onLoaderReset(Loader<ParseObject> loader) {

    }

    private boolean isValidResponse(ParseObject data) {
        if ("Exception".equals(data.getClassName())) return false;
        else return true;
    }

}
