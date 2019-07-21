package com.thinkzi.album.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.thinkzi.album.ui.R;
import com.thinkzi.album.ui.databinding.ActivityListPhotoBinding;
import com.thinkzi.album.ui.model.PhotoUIModel;
import com.thinkzi.album.ui.view.adapter.PhotoUIModelListAdapter;
import com.thinkzi.album.ui.viewmodel.PhotoUIModelListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * provide activity for show list of PhotoUIModel
 * */
public class PhotoUIModelListActivity extends BaseActivity {

    @Inject
    PhotoUIModelListViewModel _photoUIModelListViewModel;

    // android data binding
    private ActivityListPhotoBinding _activityListPhotoBinding;

    // adapter for a list of PhotoUIModel
    private PhotoUIModelListAdapter _photoUIModelListAdapter;

    // intent contain source context and destination context (this Activity) for Navigator
    public static Intent getCallingIntent(Context _context) {
        return new Intent(_context, PhotoUIModelListActivity.class);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inject dependancy for this Activity
        this.getApplicationComponent().inject(this);

        // android data binding
        _activityListPhotoBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_photo);

        _activityListPhotoBinding.setLifecycleOwner(this);

        // set viewmodel for databiding
        _activityListPhotoBinding.setPhotoUIModelListViewModel(_photoUIModelListViewModel);

        // initialize adapter with onItemClick event for list of PhotoUIModel
        _photoUIModelListAdapter = new PhotoUIModelListAdapter(getApplicationContext(), new ArrayList<PhotoUIModel>(), new PhotoUIModelListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PhotoUIModel photoUIModel) {
                Toast.makeText(PhotoUIModelListActivity.this, photoUIModel.getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        // set adapter for RecyclerView
        _activityListPhotoBinding.rcvPhotoList.setLayoutManager(new LinearLayoutManager(PhotoUIModelListActivity.this));
        _activityListPhotoBinding.rcvPhotoList.setAdapter(_photoUIModelListAdapter);

        // live data to observe the change of list of PhotoUIModel
        _photoUIModelListViewModel.getPhotoUIModelListMutableLiveData().observe(PhotoUIModelListActivity.this, new Observer<List<PhotoUIModel>>() {
            @Override
            public void onChanged(List<PhotoUIModel> photoUIModelList) {
                _photoUIModelListAdapter.setPhotoUIModelList(photoUIModelList);

                // just for test amount of items
                if (photoUIModelList.size() == 5000)
                    Toast.makeText(PhotoUIModelListActivity.this, getResources().getString(R.string.just_for_test)
                            + photoUIModelList.size() + getResources().getString(R.string.items), Toast.LENGTH_LONG).show();
            }
        });

        // live data to observe operation mode
        _photoUIModelListViewModel.getOperationModeMutableLiveData().observe(PhotoUIModelListActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                Toast.makeText(PhotoUIModelListActivity.this, s, Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onBackPressed() {

        finish();
        System.exit(0);

    }
}
