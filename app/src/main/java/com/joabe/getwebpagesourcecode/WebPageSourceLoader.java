package com.joabe.getwebpagesourcecode;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class WebPageSourceLoader extends AsyncTaskLoader<String> {

    private String mQueryUrl;

    public WebPageSourceLoader(@NonNull Context context, String queryUrl) {
        super(context);
        this.mQueryUrl = queryUrl;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return WebCrawlerUtil.getWebPageSourceCode(mQueryUrl);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
