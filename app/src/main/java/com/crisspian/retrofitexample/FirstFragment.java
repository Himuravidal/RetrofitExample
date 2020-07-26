package com.crisspian.retrofitexample;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crisspian.retrofitexample.adapter.MarsObjectAdapter;
import com.crisspian.retrofitexample.databinding.FragmentFirstBinding;
import com.crisspian.retrofitexample.model.MarsObject;
import com.crisspian.retrofitexample.presenter.Presenter;
import com.crisspian.retrofitexample.presenter.ViewInterface;

import java.util.List;

public class FirstFragment extends Fragment  implements ViewInterface, MarsObjectAdapter.PassMarObject {

    private FragmentFirstBinding binding;
    private Presenter presenter;
    private MarsObjectAdapter adapter;
    private  RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new Presenter( this);
        adapter = new MarsObjectAdapter(this);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        recyclerView = binding.rvContainer;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
        //presenter.getFetchData();

        presenter.fetchLivedata().observe(getViewLifecycleOwner(), new Observer<List<MarsObject>>() {
            @Override
            public void onChanged(List<MarsObject> marsObjectList) {
                adapter.updateMarsObject(marsObjectList);
            }
        });


        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showFetchData(List<MarsObject> marsObjectList) {
        adapter.updateMarsObject(marsObjectList);
    }

    @Override
    public void showErrorOnData(Throwable t) {
        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passMarsObject(MarsObject marsObject) {
        Bundle args = new Bundle();
        args.putString("description", marsObject.getType());
        args.putString("url", marsObject.getImgSrc());
        args.putInt("price", marsObject.getPrice());
        NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment, args );
    }
}