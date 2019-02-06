package com.example.cineapp.src.activity;

import android.os.Bundle;

import com.example.cineapp.src.fragment.CinemaDetailFragment;
import com.example.cineapp.src.fragment.CinemaListFragment;
import com.example.cineapp.src.model.ShowTime;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.View;

import com.example.cineapp.R;

public class DetailActivity extends AppCompatActivity {

    private ShowTime showTimeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        showTimeSelected = (ShowTime) getIntent().getSerializableExtra("showTimeModel");
        Fragment fragment = new CinemaDetailFragment();


        Bundle bundle = new Bundle();
        bundle.putSerializable("showTime", showTimeSelected);
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerDetail, fragment).commitAllowingStateLoss();
    }

}
