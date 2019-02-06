package com.example.cineapp.src.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.cineapp.R;
import com.example.cineapp.src.fragment.CinemaListFragment;
import com.example.cineapp.src.model.MainJSONResult;
import com.example.cineapp.src.rest.ApiHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new CinemaListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commitAllowingStateLoss();
    }
}
