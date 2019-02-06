package com.example.cineapp.src.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cineapp.R;
import com.example.cineapp.src.adapter.CinemaAdapter;
import com.example.cineapp.src.model.MainJSONResult;
import com.example.cineapp.src.rest.ApiHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CinemaListFragment extends Fragment {

    private RecyclerView recyclerViewCinema;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cinema, container, false);
        recyclerViewCinema = view.findViewById(R.id.recyclerViewCinema);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerViewCinema.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiHelper.getInstance().getCinemaApi().getMainJSONResult().enqueue(new Callback<MainJSONResult>() {
            @Override
            public void onResponse(Call<MainJSONResult> call, Response<MainJSONResult> response) {
                if (response.isSuccessful()) {
                    MainJSONResult result = response.body();
                    recyclerViewCinema.setAdapter(new CinemaAdapter(result.movieShowtimes));
                }
            }

            @Override
            public void onFailure(Call<MainJSONResult> call, Throwable t) {

            }
        });


    }
}
