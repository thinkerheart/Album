package com.thinkzi.album.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.thinkzi.album.data.model.PhotoDataModel;

import java.util.List;

/**
 * provide Phot DAO interface for data CRUD operations
 * */
@Dao
public interface PhotoDAO {

    /**
     * save data of a photo to room database, if exist -> replace it
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPhotoDataModel(PhotoDataModel photoDataModel);

    /**
     * update data of a photo to room database
     * */
    @Update
    void updatePhotoDataModel(PhotoDataModel photoDataModel);

    /**
     * delete data of a photo to room database
     * */
    @Delete
    void deletePhotoDataModel(PhotoDataModel photoDataModel);

    /**
     * get all photos from room database
     * */
    @Query("select * from Photos")
    List<PhotoDataModel> getPhotoDataModelList();

    /**
     * get data of a photo with id parameter of photo
     * */
    @Query("select * from Photos where id ==:id")
    PhotoDataModel getPhotoDataModel(int id);

    /**
     * get data of a photo from room database
     * */
    @Query("select * from Photos limit 1")
    PhotoDataModel getPhotoDataModel();

    /**
     * get limit photos from position offset from room database order by orderByColumnName asc
     * */
    @Query("select * from Photos order by :orderByColumnName asc limit :limit offset :offset")
    List<PhotoDataModel> getPhotoDataModelList(String orderByColumnName, int limit, int offset);

    /**
     * get amount of photo from room database
     * */
    @Query("select count(*) from Photos")
    int getAmountOfPhoto();

}
