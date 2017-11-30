package co.sanfen.android.androidtransition;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView mSampleIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSampleIcon = findViewById(R.id.sample_icon);

        mSampleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Pair> participants = new ArrayList<>();
                Pair<ImageView, String> imageViewStringPair = new Pair<>(mSampleIcon, getString(R.string.square_blue_name));
                participants.add(imageViewStringPair);
                startActivity(ShareElementActivity.class, participants.toArray(new Pair[participants.size()]));
            }
        });

    }

    private void startActivity(Class target, Pair<View, String>[] pairs) {
        Intent i = new Intent(this, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }

}
