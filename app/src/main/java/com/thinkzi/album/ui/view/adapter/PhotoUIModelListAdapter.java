package com.thinkzi.album.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thinkzi.album.ui.databinding.ItemPhotoBinding;
import com.thinkzi.album.ui.model.PhotoUIModel;

import java.util.List;

/**
 * provide adapter for list of PhotoUIModel for PhotoUIModelListActivity
 * */
public class PhotoUIModelListAdapter extends RecyclerView.Adapter<PhotoUIModelListAdapter.PhotoUIModelViewHolder> {

    /**
     * provide listener for onItemClick
     * */
    public interface OnItemClickListener {

        void onItemClick(PhotoUIModel _photoUIModel);

    }

    // list of PhotoUIModel
    private List<PhotoUIModel> _photoUIModelList;

    // listener for onItemClick
    private OnItemClickListener _onItemClickListener;

    // application context
    private Context _context;

    public PhotoUIModelListAdapter(Context _context, List<PhotoUIModel> _photoUIModelList, OnItemClickListener _onItemClickListener) {

        this._context = _context;

        this._photoUIModelList = _photoUIModelList;

        this._onItemClickListener = _onItemClickListener;
    }

    @NonNull
    @Override
    public PhotoUIModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return(new PhotoUIModelViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)));

    }

    @Override
    public void onBindViewHolder(@NonNull PhotoUIModelViewHolder holder, int position) {

        holder.bind(_photoUIModelList.get(position), _onItemClickListener);

    }

    @Override
    public int getItemCount() {
        return _photoUIModelList.size();
    }

    /**
     * set list of PhotoUIModel for adapter
     * */
    public void setPhotoUIModelList(List<PhotoUIModel> _photoUIModelList) {

        this._photoUIModelList = _photoUIModelList;

        notifyDataSetChanged();
    }

    /**
     * provide a viewholder to bind data
     * */
    class PhotoUIModelViewHolder extends RecyclerView.ViewHolder {

        private final ItemPhotoBinding _itemPhotoBinding;

        PhotoUIModelViewHolder(ItemPhotoBinding _itemPhotoBinding) {
            super(_itemPhotoBinding.getRoot());
            this._itemPhotoBinding = _itemPhotoBinding;
        }

        void bind(final PhotoUIModel _photoUIModel, final OnItemClickListener _onItemClickListener) {
            _itemPhotoBinding.lblAlbumId.setText(String.valueOf(_photoUIModel.getAlbumId()));
            _itemPhotoBinding.lblId.setText(String.valueOf(_photoUIModel.getId()));
            _itemPhotoBinding.lblTitle.setText(String.valueOf(_photoUIModel.getTitle()));

            Picasso.get().load(_photoUIModel.getUrl()).into(_itemPhotoBinding.imgURL);
            Picasso.get().load(_photoUIModel.getUrl()).into(_itemPhotoBinding.imgThumbnailUrl);

            _itemPhotoBinding.executePendingBindings();

            _itemPhotoBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _onItemClickListener.onItemClick(_photoUIModel);
                }
            });
        }

    }
}
