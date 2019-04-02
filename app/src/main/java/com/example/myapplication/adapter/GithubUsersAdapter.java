package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.GithubUsers;
import com.example.myapplication.view.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GithubUsersAdapter extends RecyclerView.Adapter<GithubUsersAdapter.ViewHolder> {

    List<GithubUsers> usersArray;

    public GithubUsersAdapter(List<GithubUsers> usersArray) {
        this.usersArray = usersArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

        GithubUsers githubUser = usersArray.get(viewHolder.getAdapterPosition());

        TextView textView = viewHolder.view.findViewById(R.id.profile_name);
        textView.setText(githubUser.getUsername());
        ImageView imageView = viewHolder.view.findViewById(R.id.profile_image);
        Picasso.get()
                .load(usersArray.get(viewHolder.getAdapterPosition()).getAvatar()).into(imageView);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("USERNAME",
                        usersArray.get(viewHolder.getAdapterPosition()).getUsername());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.view = view;

        }

    }
}
