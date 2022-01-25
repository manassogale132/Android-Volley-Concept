package com.example.volleysecondexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InnerNewsActivity extends AppCompatActivity {

    ImageView innerNewsImage;
    TextView innerAutherTV, innerNewsTitleTV, innerNewsContentTV, sourceTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_news);

        Intent in = getIntent();

        innerNewsImage = (ImageView) findViewById(R.id.innerNewsImage);
        innerAutherTV = (TextView) findViewById(R.id.innerAutherTV);
        innerNewsTitleTV = (TextView) findViewById(R.id.innerNewsTitleTV);
        innerNewsContentTV = (TextView) findViewById(R.id.innerNewsContentTV);
        sourceTV = (TextView) findViewById(R.id.sourceTV);

        innerAutherTV.setText(in.getStringExtra("name"));
        innerNewsTitleTV.setText(in.getStringExtra("title"));
        innerNewsContentTV.setText(in.getStringExtra("content"));
        //sourceTV.setText(in.getStringExtra("source"));
        Glide.with(this).load(in.getStringExtra("imgurl")).into(innerNewsImage);

        sourceTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(in.getStringExtra("source")));
                startActivity(browserIntent);
            }
        });

        if (innerAutherTV.getText().equals("") || innerAutherTV.getText().equals("null")) {
            innerAutherTV.setText("No Author");
        }

        if (innerNewsContentTV.getText().equals("") || innerNewsContentTV.getText().equals("null")) {
            innerNewsContentTV.setText("");
        }

        if (in.getStringExtra("imgurl").equals("null")) {
            innerNewsImage.setBackgroundResource(R.drawable.no_image);
        }

    }
}