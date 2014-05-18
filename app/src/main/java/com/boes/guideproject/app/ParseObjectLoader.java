package com.boes.guideproject.app;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ParseObjectLoader extends AsyncTaskLoader<ParseObject> {

    String type;
    String id;

    public ParseObjectLoader(Context context, String type, String id) {
        super(context);
        this.type = type;
        this.id = id;
    }

    @Override
    public ParseObject loadInBackground() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(type);

        ParseObject result;
        try {
            result = query.get(id);
        } catch (ParseException e) {
            result = new ParseObject("Exception");
            result.put("message", e.getMessage());
            Log.e("ParseObjectLoader", e.getMessage(), e);
        }

        return result;
    }

    @Override
    public void deliverResult(ParseObject data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

}
