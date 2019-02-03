package com.example.cineapp.src.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.cineapp.R;
import com.example.cineapp.src.model.MainJSONResult;
import com.example.cineapp.src.rest.ApiHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiHelper.getInstance().getCinemaApi().getMainJSONResult().enqueue(new Callback<MainJSONResult>() {
            @Override
            public void onResponse(Call<MainJSONResult> call, Response<MainJSONResult> response) {
                if (response.isSuccessful()) {
                    MainJSONResult result = response.body();
                    TextView name = findViewById(R.id.cinemaName);
                    name.setText(result.place.theater.name);
                    Log.d("MainActivity", "Mon cinema: " + result);
                }
            }

            @Override
            public void onFailure(Call<MainJSONResult> call, Throwable t) {

            }
        });
        Log.d("MonTag", "coucou stephane!");
    }
}
