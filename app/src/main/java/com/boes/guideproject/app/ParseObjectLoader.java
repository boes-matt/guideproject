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

    final Context context;
    final String type;
    final String id;

    public ParseObjectLoader(Context context, String type, String id) {
        super(context);
        this.context = context;
        this.type = type;
        this.id = id;
    }

    @Override
    public ParseObject loadInBackground() {
        if (!connected()) return createParseObjectException("No network connection");

        ParseQuery<ParseObject> query = ParseQuery.getQuery(type);
        try {
            return query.get(id);
        } catch (ParseException e) {
            Log.e("ParseObjectLoader", e.getMessage(), e);
            return createParseObjectException(e.getMessage());
        }
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

    private boolean connected() {
        ConnectivityManager connection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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
