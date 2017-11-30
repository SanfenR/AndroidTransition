package co.sanfen.android.androidtransition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.TransitionManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ShareElementActivity extends AppCompatActivity {

    ImageView mSampleIcon;
    LinearLayout mViewRoot;
    boolean sizeChanged;
    private int savedWidth;
    private boolean positionChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mSampleIcon = findViewById(R.id.sample_icon);
        mViewRoot = findViewById(R.id.ll_viewroot);

        mSampleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateRevealColorFromCoordinates(mViewRoot, R.color.sample_blue, (mViewRoot.getRight() - mViewRoot.getLeft()) / 2,
                        (mViewRoot.getBottom() - mViewRoot.getTop()) / 2);
            }
        });

        findViewById(R.id.btn_change_size).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSize();
            }
        });

        findViewById(R.id.btn_change_position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePosition();
            }
        });

    }

    private void changeSize() {
        TransitionManager.beginDelayedTransition(mViewRoot);
        ViewGroup.LayoutParams params = mSampleIcon.getLayoutParams();
        if (sizeChanged) {
            params.width = savedWidth;
        } else {
            savedWidth = params.width;
            params.width = 200;
        }
        sizeChanged = !sizeChanged;
        mSampleIcon.setLayoutParams(params);
    }


    private void changePosition() {
        TransitionManager.beginDelayedTransition(mViewRoot);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mSampleIcon.getLayoutParams();

        if (positionChange) {
            lp.gravity = Gravity.CENTER;
        } else {
            lp.gravity = Gravity.LEFT;
        }
        positionChange = !positionChange;
        mSampleIcon.setLayoutParams(lp);
    }

    private Animator animateRevealColorFromCoordinates(ViewGroup viewRoot, @ColorRes int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        viewRoot.setBackgroundColor(ContextCompat.getColor(this, color));
        anim.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        return anim;
    }
}
