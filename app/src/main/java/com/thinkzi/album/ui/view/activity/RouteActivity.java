package com.thinkzi.album.ui.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * provide main first Activity
 * */
public class RouteActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
        _navigator.navigateToPhotoUIModelListActivity(this);
        finish();
    }
}
