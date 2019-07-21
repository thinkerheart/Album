package com.thinkzi.album.ui.navigation;

import android.content.Context;
import android.content.Intent;

import com.thinkzi.album.ui.view.activity.PhotoUIModelListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * provide a navigator used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() { }

    /**
     * navigate to PhotoUIModelListActivity
     * */
    public void navigateToPhotoUIModelListActivity(Context _context) {

        if (_context != null) {
            Intent _intent = PhotoUIModelListActivity.getCallingIntent(_context);
            _context.startActivity(_intent);
        }

    }

}
