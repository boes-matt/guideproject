package com.boes.guideproject.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ParseObjectLoader extends AsyncTaskLoader<ParseObject> {

    final String type;
    final String id;

    ParseObject data;

    public ParseObjectLoader(Context context, String type, String id) {
        super(context);
        this.type = type;
        this.id = id;

        data = null;
    }

    @Override
    public ParseObject loadInBackground() {
        Log.d("Loader", "loadInBackground");

        if (!isConnected()) return createParseObjectException("No network connection");

        ParseQuery<ParseObject> query = ParseQuery.getQuery(type);
        try {
            return query.get(id);
        } catch (ParseException e) {
            Log.e("ParseObjectLoader", e.getMessage(), e);
            return createParseObjectException(e.getMessage());
        }

        // n.b. register any observers or receivers before returning
    }

    @Override
    public void deliverResult(ParseObject data) {
        Log.d("Loader", "deliverResult");

        if (isReset()) {
            return;
        }

        this.data = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        Log.d("Loader", "onStartLoading");

        if (data != null) {
            deliverResult(data);
        }

        if (takeContentChanged() || data == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        data = null;

        // n.b. unregister any observers or receivers here

        onStopLoading();
    }

    private boolean isConnected() {
        ConnectivityManager connection =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connection.getActiveNetworkInfo();
        if (network == null || !network.isConnected()) return false;
        else return true;
    }

    private ParseObject createParseObjectException(String message) {
        ParseObject e = new ParseObject("Exception");
        e.put("message", message);
        return e;
    }

}
