package com.lelelee.codingcamels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

public class LeleleeFragment extends Fragment {
    public static final String TAG = LeleleeFragment.class.getSimpleName();

    private static final int[] ROLL_IMAGES = {R.drawable.left, R.drawable.middle, R.drawable.right, R.drawable.middle};
    private static final int SMILE_IMAGE = R.drawable.smile;
    private static final int FPS = 100;

    private AnimationSet animationSet;
    private ImageView imlele;
    private Bitmap currentBitmap;
    private BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
    private int currentImageIndex;
    private MediaPlayer mediaPlayerLeLe;
    private MediaPlayer mediaPlayerLee;

    @Override
    public void onActivityCreated(Bundle saveOnState) {
        super.onActivityCreated(saveOnState);

        bitmapOptions.inSampleSize = 1;
        setMutability(bitmapOptions);
        setupAnimation();

        mediaPlayerLeLe = MediaPlayer.create(getActivity(), R.raw.le);
        mediaPlayerLee = MediaPlayer.create(getActivity(), R.raw.lee);
        imlele.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getAction();
                switch (action & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        mediaPlayerLeLe.setLooping(true);
                        mediaPlayerLeLe.start();
                        enableAnimation(true);
                        animate();
                        break;
                    case MotionEvent.ACTION_UP:
                        mediaPlayerLeLe.pause();
                        mediaPlayerLee.start();
                        enableAnimation(false);
                        showSmile();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lelelee, container, false);
        if (view != null) {
            imlele = (ImageView) view.findViewById(R.id.imLeLe);
        }
        return view;
    }

    private void setupAnimation() {
        currentImageIndex = 0;
        animationSet = new AnimationSet(true);
        Animation fadeOutAnimation = new AlphaAnimation(1.0f, 0.0f);
        Animation fadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
        animationSet.addAnimation(fadeInAnimation);
        animationSet.addAnimation(fadeOutAnimation);
        fadeInAnimation.setDuration(1);
        fadeInAnimation.setStartOffset(0);
        fadeOutAnimation.setDuration(1);
        fadeOutAnimation.setStartOffset(FPS);
        animationSet.setAnimationListener(animationListener);
    }

    private void enableAnimation(boolean enabled) {
        if (enabled) {
            animationSet.setAnimationListener(animationListener);
        } else {
            currentImageIndex = 0;
            imlele.clearAnimation();
            animationSet.setAnimationListener(null);
        }
    }

    private void animate() {
        loadBitmap(ROLL_IMAGES[currentImageIndex % ROLL_IMAGES.length], imlele);
        currentImageIndex++;
        imlele.startAnimation(animationSet);
    }

    private void showSmile() {
        loadBitmap(SMILE_IMAGE, imlele);
    }

    final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(final Animation animation) {
            // Do nothing
        }

        @Override
        public void onAnimationEnd(final Animation animation) {
            // launch showing of next image on the end of the animation
            animate();
        }

        @Override
        public void onAnimationRepeat(final Animation animation) {
            // Do nothing
        }
    };

    private void loadBitmap(int resId, ImageView imageView) {
        reuseOrRecycleBitmap(bitmapOptions);
        currentBitmap = BitmapFactory.decodeResource(getActivity().getResources(), resId, bitmapOptions);
        imageView.setImageBitmap(currentBitmap);
    }

    private void reuseOrRecycleBitmap(BitmapFactory.Options bitmapOptions) {
        bitmapOptions.inBitmap = currentBitmap;
    }

    private void setMutability(BitmapFactory.Options bitmapOptions) {
        bitmapOptions.inMutable = true;
    }
}
