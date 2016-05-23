package com.project.faculty.nestedrecyclerviewtest25012016.Animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.project.faculty.nestedrecyclerviewtest25012016.R;

/**
 * Created by Sreesha on 13-07-2015.
 */
public class AnimationUtils {

    private static int counter = 0;

    public static final String AVATAR_FLIP_SLIDE_IN_FADE_IN = "animateAvatarFadeInFlip";
    public static final String SLIDE_LEFT_TO_RIGHT = "slideLeftToRight";
    public static final String SLIDE_BOTTOM_TO_TOP = "slideBottomToTop";
    public static final String ALPHA_FADE_SLIDE = "alphaFadeSlideIn";
    public static final String FLIP = "flip";
    public static final String REVERSE_SCATTER = "reverseScatter";

    public static void animateAvatarFadeInFlip(RecyclerView.ViewHolder holder) {

        View itemView = holder.itemView;
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator elementAnimatorTranslateX = ObjectAnimator.ofFloat(itemView, View.TRANSLATION_X, -900, 0);
        AnimatorSet avatarAnimatorSet = new AnimatorSet();
        ObjectAnimator avatarAnimatorFade = ObjectAnimator.ofFloat(
                holder.itemView.findViewById(R.id.posterCard)
                , View.ALPHA, 0, 1
        );
        ObjectAnimator avatarAnimatorFlipX = ObjectAnimator.ofFloat(holder.itemView.findViewById(R.id.posterCard)
                , View.ROTATION_X, -70, 0, 70, 0);
        avatarAnimatorSet.setDuration(500);
        avatarAnimatorSet.playTogether(avatarAnimatorFade, avatarAnimatorFlipX);

        animatorSet.setDuration(900);
        animatorSet.setInterpolator(new DecelerateInterpolator(1.1f));
        animatorSet.playSequentially(elementAnimatorTranslateX, avatarAnimatorSet);
        animatorSet.start();
    }

    public static void slideLeftToRight(RecyclerView.ViewHolder holder) {

        View view = holder.itemView;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, -900, 0);
        animatorSet.setDuration(900);
        animatorSet.setInterpolator(new DecelerateInterpolator(1.1f));
        animatorSet.playTogether(animatorTranslateX);
        animatorSet.start();
    }

    public static void slideBottomToTop(RecyclerView.ViewHolder holder, boolean goesDown) {
        View view = holder.itemView;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, goesDown? 700 : -700, 0);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 1f);

        animatorSet.setDuration(900);
        animatorSet.setInterpolator(new DecelerateInterpolator(1.1f));
        animatorSet.playTogether(animatorTranslateY, animatorAlpha);
        animatorSet.start();
    }

    public static void alphaFadeSlide(RecyclerView.ViewHolder holder) {
        counter++;
        View view = holder.itemView;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, counter % 2 == 0 ? 400 : -400, 0);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1f);
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new DecelerateInterpolator(1.1f));
        animatorSet.playTogether(animatorTranslateX, animatorAlpha);
        animatorSet.start();
    }

    public static void viewFlip(RecyclerView.ViewHolder holder) {
        counter++;
        View view = holder.itemView;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(view, View.ROTATION_X, counter % 2 == 0 ? 100 : -100, 0);
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(view, View.ROTATION_Y, -10, 0);

        animatorSet.setInterpolator(new DecelerateInterpolator(1.1f));
        animatorSet.setDuration(2000);
        animatorSet.playTogether(animatorTranslateX, animatorTranslateY);
        animatorSet.start();
    }

    public static void animateAlphaFadeAndScatter(RecyclerView.ViewHolder holder, boolean goesDown) {
        counter = ++counter % 4;
        int holderHeight = holder.itemView.getHeight();
        int holderWidth = holder.itemView.getWidth();
        View holderItemView = holder.itemView;
        holderItemView.setPivotY(goesDown ? 0 : holderHeight);
        holderItemView.setPivotX(holderWidth / 2);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY
                = ObjectAnimator.ofFloat(holderItemView, "translationY", goesDown == true ? 300 : -300, 0);
        ObjectAnimator animatorTranslateX
                = ObjectAnimator.ofFloat(holderItemView, "translationX", counter == 1 || counter == 3 ? holderWidth : -holderWidth, 0);
        ObjectAnimator animatorScaleX
                = ObjectAnimator.ofFloat(holderItemView, "scaleX", counter == 1 || counter == 2 ? 0 : 2, 1f);
        ObjectAnimator animatorScaleY
                = ObjectAnimator.ofFloat(holderItemView, "scaleY", counter == 1 || counter == 2 ? 0 : 2, 1f);
        ObjectAnimator animatorAlpha
                = ObjectAnimator.ofFloat(holderItemView, "alpha", 0f, 1f);
        animatorAlpha.setInterpolator(new AccelerateInterpolator(5.5f));
        animatorSet.playTogether(animatorAlpha, animatorScaleX, animatorScaleY, animatorTranslateX, animatorTranslateY);
        animatorSet.setDuration(2000).setInterpolator(new DecelerateInterpolator(1.1f));
        animatorSet.start();
    }
}