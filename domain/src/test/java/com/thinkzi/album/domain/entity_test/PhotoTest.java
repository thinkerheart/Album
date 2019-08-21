package com.thinkzi.album.domain.entity_test;

import com.thinkzi.album.domain.entity.Photo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * provide PhotoTest for testing Photo class
 * */
public class PhotoTest {

    //photo object instance for test
    private Photo _photo;

    /**
     * prepare things for testing
     * */
    @Before
    public void setUp() {
        _photo = new Photo();
    }

    /**
     * test case : constructor of Photo object without arguments
     * */
    @Test
    public void constructorTestCase() {
        assertEquals(0, _photo.getAlbumId());
        assertEquals(0, _photo.getId());
        assertEquals("", _photo.getTitle());
        assertEquals("", _photo.getUrl());
        assertEquals("", _photo.getThumbnailUrl());
    }

    /**
     * test case : get album Id
     * */
    @Test
    public void getAlbumIdTestCase() {
        assertEquals(0, _photo.getAlbumId());
    }

    /**
     * test case : set album Id with album Id = 1
     * */
    @Test
    public void setAlbumIdTestCase() {
        _photo.setAlbumId(1);
        assertEquals(1, _photo.getAlbumId());
    }

    /**
     * test case : get photo Id
     * */
    @Test
    public void getIdTestCase() {
        assertEquals(0, _photo.getId());
    }

    /**
     * test case : set photo Id with album Id = 1
     * */
    @Test
    public void setIdTestCase() {
        _photo.setId(1);
        assertEquals(1, _photo.getId());
    }

    /**
     * test case : get photo title
     * */
    @Test
    public void getTitleTestCase() {
        assertEquals("", _photo.getTitle());
    }

    /**
     * test case : set photo title with photo title = Trang
     * */
    @Test
    public void setTitleTestCase() {
        _photo.setTitle("Trang");
        assertEquals("Trang", _photo.getTitle());
    }

    /**
     * test case : get photo url
     * */
    @Test
    public void getUrlTestCase() {
        assertEquals("", _photo.getUrl());
    }

    /**
     * test case : set photo url
     * */
    @Test
    public void setUrlTestCase() {
        _photo.setUrl("https://scontent-cdt1-1.xx.fbcdn.net/v/t1.0-9/61079065_1072580549614576_6739388756458995712_n.jpg?_nc_cat=107&_nc_oc=AQk_-ivaWgpyPl9TMNvzpU_mQmUmxpJUZNc_-V1Y_nSfCiBEcaNxqg56wxbATrL-8tk&_nc_ht=scontent-cdt1-1.xx&oh=05c016f18e6e74e57969aaa2b6ba32ae&oe=5E0D89D1");
        assertEquals("https://scontent-cdt1-1.xx.fbcdn.net/v/t1.0-9/61079065_1072580549614576_6739388756458995712_n.jpg?_nc_cat=107&_nc_oc=AQk_-ivaWgpyPl9TMNvzpU_mQmUmxpJUZNc_-V1Y_nSfCiBEcaNxqg56wxbATrL-8tk&_nc_ht=scontent-cdt1-1.xx&oh=05c016f18e6e74e57969aaa2b6ba32ae&oe=5E0D89D1", _photo.getUrl());
    }

    /**
     * test case : get thumnail url
     * */
    @Test
    public void getThumbnailUrlTestCase() {
        assertEquals("", _photo.getThumbnailUrl());
    }

    /**
     * test case : set photo thumnail url
     * */
    @Test
    public void setThumbnailUrlTestCase() {
        _photo.setThumbnailUrl("https://scontent-cdt1-1.xx.fbcdn.net/v/t1.0-9/61079065_1072580549614576_6739388756458995712_n.jpg?_nc_cat=107&_nc_oc=AQk_-ivaWgpyPl9TMNvzpU_mQmUmxpJUZNc_-V1Y_nSfCiBEcaNxqg56wxbATrL-8tk&_nc_ht=scontent-cdt1-1.xx&oh=05c016f18e6e74e57969aaa2b6ba32ae&oe=5E0D89D1");
        assertEquals("https://scontent-cdt1-1.xx.fbcdn.net/v/t1.0-9/61079065_1072580549614576_6739388756458995712_n.jpg?_nc_cat=107&_nc_oc=AQk_-ivaWgpyPl9TMNvzpU_mQmUmxpJUZNc_-V1Y_nSfCiBEcaNxqg56wxbATrL-8tk&_nc_ht=scontent-cdt1-1.xx&oh=05c016f18e6e74e57969aaa2b6ba32ae&oe=5E0D89D1", _photo.getThumbnailUrl());
    }

}
