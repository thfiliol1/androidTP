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
        View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        // Note that system bars will only be "visible" if none of the
                        // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            // TODO: The system bars are visible. Make any desired
                            // adjustments to your UI, such as showing the action bar or
                            // other navigational controls.
                            //NavigationUtils.hideNavigation(getWindow().getDecorView());
                            int uiOptions =  View.SYSTEM_UI_FLAG_IMMERSIVE
                                    // Set the content to appear under the system bars so that the
                                    // content doesn't resize when the system bars hide and show.
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                            getWindow().getDecorView().setSystemUiVisibility(uiOptions);
                        } else {
                            // TODO: The system bars are NOT visible. Make any desired
                            // adjustments to your UI, such as hiding the action bar or
                            // other navigational controls.
                        }
                    }
                });
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showTimeSelected = (ShowTime) getIntent().getSerializableExtra("showTimeModel");
        setTitle(showTimeSelected.onShow.movie.title);
        Fragment fragment = new CinemaDetailFragment();


        Bundle bundle = new Bundle();
        bundle.putSerializable("showTime", showTimeSelected);
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerDetail, fragment).commitAllowingStateLoss();
    }

}
