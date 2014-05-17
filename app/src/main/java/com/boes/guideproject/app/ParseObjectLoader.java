package com.boes.guideproject.app;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ParseObjectLoader extends AsyncTaskLoader<ParseObject> {

    String id;

    public ParseObjectLoader(Context context, String id) {
        super(context);
        this.id = id;
    }

    @Override
    public ParseObject loadInBackground() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        ParseObject result = failed();

        try {
            result = query.get(id);
        } catch (ParseException e) {
            Log.e("ParseObjectLoader", e.getMessage(), e);
        }

        return result;
    }

    @Override
    public void deliverResult(ParseObject data) {
        super.deliverResult(data);
    }

    private ParseObject failed() {
        ParseObject failure = new ParseObject("Place");
        failure.put("title", "Error");
        return failure;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

}
