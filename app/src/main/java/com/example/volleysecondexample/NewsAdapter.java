package com.example.volleysecondexample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Articles> data;

    public NewsAdapter(Context context, ArrayList<Articles> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_row_news_design, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Articles articles = data.get(position);

        holder.autherTV.setText(articles.getName());
        Glide.with(holder.newsImage.getContext()).load(articles.getNewsImage()).into(holder.newsImage);
        holder.newsTitleTV.setText(articles.getNewsTitle());
        holder.newsDescriptionTV.setText(articles.getNewsDescription());

        holder.sourceTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articles.getNewsSource()));
                view.getContext().startActivity(browserIntent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),InnerNewsActivity.class);
                intent.putExtra("name", articles.getName());
                intent.putExtra("title", articles.getNewsTitle());
                intent.putExtra("content", articles.getNewsContent());
                intent.putExtra("source", articles.getNewsSource());
                intent.putExtra("imgurl", articles.getNewsImage());
                view.getContext().startActivity(intent);
            }
        });

        if(holder.autherTV.getText().equals("") || holder.autherTV.getText().equals("null")){
            holder.autherTV.setText("No Author");
        }

        if(holder.newsDescriptionTV.getText().equals("") || holder.newsDescriptionTV.getText().equals("null")){
            holder.newsDescriptionTV.setText("");
        }

        if(articles.getNewsImage().equals("null")){
            holder.newsImage.setBackgroundResource(R.drawable.no_image);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView newsImage;
        TextView autherTV, newsTitleTV, newsDescriptionTV, sourceTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = (ImageView) itemView.findViewById(R.id.newsImage);
            autherTV = (TextView) itemView.findViewById(R.id.autherTV);
            newsTitleTV = (TextView) itemView.findViewById(R.id.newsTitleTV);
            newsDescriptionTV = (TextView) itemView.findViewById(R.id.newsDescriptionTV);
            sourceTV = (TextView) itemView.findViewById(R.id.sourceTV);
        }
    }
}
