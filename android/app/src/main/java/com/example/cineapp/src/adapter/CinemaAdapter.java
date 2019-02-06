package com.example.cineapp.src.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cineapp.R;
import com.example.cineapp.src.activity.DetailActivity;
import com.example.cineapp.src.model.Genre;
import com.example.cineapp.src.model.Horaire;
import com.example.cineapp.src.model.Seance;
import com.example.cineapp.src.model.ShowTime;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.CinemaViewHolder> {

    private static List<ShowTime> showTimes;

    public CinemaAdapter(List<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }

    @NonNull
    @Override
    public CinemaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cinema, parent, false);
        return new CinemaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaViewHolder holder, int position) {
        ShowTime showTime = showTimes.get(position);
        holder.textViewMovieTitle.setText(showTime.onShow.movie.title);
        StringBuilder str = new StringBuilder(String.valueOf(showTime.onShow.movie.runtime)).append("s /");
        for(Genre g : showTime.onShow.movie.genre) {
            str.append(g.name);
            str.append(" ");
        }
        holder.textViewDurationAndCategory.setText(str);
        Picasso.get().load(showTime.onShow.movie.poster.href).into(holder.imageViewItemMoviePicture);

        holder.textViewUserRating.setText("User :");
        holder.ratingBarUserRating.setRating(showTime.onShow.movie.statistics.userRating);
        holder.textViewPressRating.setText("Press :");
        holder.ratingBarPressRating.setRating(showTime.onShow.movie.statistics.pressRating);
        StringBuilder seanceStringBuilder = new StringBuilder();
        for(Seance s : showTime.scr) {
            seanceStringBuilder.append(s.d).append(" : ");
            for(Horaire hours : s.t) {
                seanceStringBuilder.append(hours.name).append("/");
            }
            seanceStringBuilder.append("\n");
        }
        holder.textViewSeance.setText(seanceStringBuilder);

    }



    @Override
    public int getItemCount() {
        return showTimes.size();
    }

    static class CinemaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewMovieTitle;
        TextView textViewDurationAndCategory;
        ImageView imageViewItemMoviePicture;
        TextView textViewUserRating;
        RatingBar ratingBarUserRating;
        TextView textViewPressRating;
        RatingBar ratingBarPressRating;
        TextView textViewSeance;

        CinemaViewHolder(View view) {
            super(view);
            view.setClickable(true);
            view.setOnClickListener(this);
            textViewMovieTitle = view.findViewById(R.id.textViewItemMovieTitle);
            textViewDurationAndCategory = view.findViewById(R.id.textViewItemDurationCategory);
            imageViewItemMoviePicture = view.findViewById(R.id.imageViewItemMoviePicture);
            textViewUserRating = view.findViewById(R.id.textViewUserRatingOnList);
            ratingBarUserRating = view.findViewById(R.id.ratingBarUserOnList);
            textViewPressRating = view.findViewById(R.id.textViewPressRatingOnList);
            ratingBarPressRating = view.findViewById(R.id.ratingBarPressOnList);
            textViewSeance = view.findViewById(R.id.textViewSeance);
        }

        @Override
        public void onClick(View v) {
            Intent detailIntent = new Intent(v.getContext(), DetailActivity.class);

            ShowTime showTimeClicked = showTimes.get(getAdapterPosition());
            detailIntent.putExtra("showTimeModel", showTimeClicked);

            v.getContext().startActivity(detailIntent);
        }
    }
}
