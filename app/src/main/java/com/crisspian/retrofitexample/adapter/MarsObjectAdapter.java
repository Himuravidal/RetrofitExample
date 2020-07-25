package com.crisspian.retrofitexample.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crisspian.retrofitexample.databinding.MarsItemListBinding;
import com.crisspian.retrofitexample.model.MarsObject;

import java.util.List;

public class MarsObjectAdapter extends RecyclerView.Adapter<MarsObjectAdapter.MarsObjectViewHolder> {
    private List<MarsObject> marsObjectList;
    private PassMarObject callback;

    public MarsObjectAdapter(PassMarObject listener) {
        callback = listener;
    }

    @NonNull
    @Override
    public MarsObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MarsItemListBinding binding = MarsItemListBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new MarsObjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MarsObjectViewHolder holder, int position) {
        String urlImage = marsObjectList.get(position).getImgSrc();
        Glide.with(holder.imageView.getContext()).load(urlImage).centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (marsObjectList != null) {
            return marsObjectList.size();
        } else {
            return 0;
        }
    }

    public class  MarsObjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        public MarsObjectViewHolder(@NonNull MarsItemListBinding binding) {
            super(binding.getRoot());
            imageView = binding.marsImage;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MarsObject marsObject = marsObjectList.get(getAdapterPosition());
            callback.passMarsObject(marsObject);
        }
    }

    public void updateMarsObject(List<MarsObject> list) {
        marsObjectList = list;
        notifyDataSetChanged();
    }

    public interface PassMarObject {
        void passMarsObject(MarsObject marsObject);
    }

}
