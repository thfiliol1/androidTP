package com.example.cineapp.src.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cineapp.R;
import com.example.cineapp.src.model.Casting;
import com.example.cineapp.src.model.Genre;
import com.example.cineapp.src.model.ShowTime;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class CinemaDetailFragment extends Fragment {

    private ShowTime showTime;
    private boolean isImageFitToScreen=false;
    private View view;

    public CinemaDetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activity_detail, container, false);

        showTime = (ShowTime) getArguments().getSerializable("showTime");

        int heures = showTime.onShow.movie.runtime / 3600;
        int minutes =(showTime.onShow.movie.runtime % 3600) / 60;
        String runtime = heures + "h" + minutes + "min";

        StringBuilder stringBuilder = new StringBuilder(runtime);
        stringBuilder.append(" / ");

        for(Genre g : showTime.onShow.movie.genre) {
            stringBuilder.append(g.name);
            stringBuilder.append(" ");
        }
        stringBuilder.append("/ ").append(showTime.version.name);

        ((TextView) view.findViewById(R.id.textViewDurCatCountry)).setText(stringBuilder);

        final ImageView imageViewTrailer = view.findViewById(R.id.imageViewTrailer);

        if (showTime.onShow.movie.trailer != null) {
            imageViewTrailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(showTime.onShow.movie.trailer.href)));
                }
            });
            StringBuilder youtubeImage = new StringBuilder("https://i.ytimg.com/vi/");
            youtubeImage.append(showTime.onShow.movie.trailer.href.split("=")[1]);
            youtubeImage.append("/hqdefault.jpg");
            Picasso.get().load(youtubeImage.toString()).into(imageViewTrailer);
        }
        else{
            imageViewTrailer.setForeground(null);
            imageViewTrailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isImageFitToScreen) {
                        isImageFitToScreen=false;
                        //showSystemUI();
                        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                        getActivity().setTheme(R.style.AppTheme);
                        float density = getContext().getResources()
                                .getDisplayMetrics()
                                .density;
                        imageViewTrailer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Math.round((float) 185 * density)));
                        imageViewTrailer.setAdjustViewBounds(true);
                    }else{
                        isImageFitToScreen=true;
                        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
                       // hideSystemUI();
                        imageViewTrailer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                        imageViewTrailer.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                }
            });
            Picasso.get().load(showTime.onShow.movie.poster.href).into(imageViewTrailer);

        }

        ((TextView) view.findViewById(R.id.textViewDirectors)).setText("Directors : " + showTime.onShow.movie.castingShort.directors);
        ((TextView) view.findViewById(R.id.textViewActors)).setText("Actors : " + showTime.onShow.movie.castingShort.actors);
        ((TextView) view.findViewById(R.id.textViewUserRating)).setText("User :");
        ((RatingBar) view.findViewById(R.id.ratingBarUser)).setRating(showTime.onShow.movie.statistics.userRating);
        ((TextView) view.findViewById(R.id.textViewPressRating)).setText("Press :");
        ((RatingBar) view.findViewById(R.id.ratingBarPress)).setRating(showTime.onShow.movie.statistics.pressRating);

        return view;
    }


}
