package com.thinkzi.album.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.thinkzi.album.data.model.PhotoDataModel;

/**
 * provide a database instance
 * */
@Database(entities = {PhotoDataModel.class}, version = 1, exportSchema = false)
public abstract class AlbumApplicationDatabase extends RoomDatabase {

    public static volatile AlbumApplicationDatabase _albumApplicationDatabase;

    public abstract PhotoDAO getPhotoDAO();

    public static AlbumApplicationDatabase getInstance(Context _context) {

        if (_albumApplicationDatabase == null) {

            synchronized (AlbumApplicationDatabase.class) {

                _albumApplicationDatabase = Room.databaseBuilder(_context.getApplicationContext(), AlbumApplicationDatabase.class, "PhotoDB.db").build();

            }

        }

        return _albumApplicationDatabase;

    }

}
