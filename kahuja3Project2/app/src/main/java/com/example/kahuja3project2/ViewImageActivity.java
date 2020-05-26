package com.example.kahuja3project2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;


public class ViewImageActivity extends Activity {

    ImageView theImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewimage);
        theImageView = (ImageView) findViewById(R.id.imageView);
        final Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            theImageView.setImageResource(bundle.getInt("movieImage"));
        }
        theImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(bundle.getString("movieIMDB")));
                startActivity(intent);

            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();

        Runtime.getRuntime().gc();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Runtime.getRuntime().gc();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
