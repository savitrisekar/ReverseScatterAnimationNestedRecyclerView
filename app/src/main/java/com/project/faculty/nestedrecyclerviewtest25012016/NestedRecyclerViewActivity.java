package com.project.faculty.nestedrecyclerviewtest25012016;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.project.faculty.nestedrecyclerviewtest25012016.Animation.AnimationUtils;

public class NestedRecyclerViewActivity extends AppCompatActivity {

    RecyclerView outerRecyclerView;
    OuterRecyclerViewCA outerRVAdapter;
    private int previousPosition = 0;
    private String mAnimationType = AnimationUtils.REVERSE_SCATTER;
    private final String ANIMATION_TYPE_STRING_KEY = "animationTypeStringKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_recycler_view);

        if (savedInstanceState != null) {
            mAnimationType = savedInstanceState.getString(ANIMATION_TYPE_STRING_KEY,AnimationUtils.REVERSE_SCATTER);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        outerRVAdapter = new OuterRecyclerViewCA();
        outerRecyclerView = (RecyclerView) findViewById(R.id.outerRecyclerView);
        outerRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
        outerRecyclerView.setAdapter(outerRVAdapter);
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        outState.putString(ANIMATION_TYPE_STRING_KEY, mAnimationType);
        outPersistentState.putString(ANIMATION_TYPE_STRING_KEY, mAnimationType);

        super.onSaveInstanceState(outState, outPersistentState);
    }

    public class OuterRecyclerViewCA extends RecyclerView.Adapter<OuterRecyclerViewCA.ViewHolder> {
        InnerRecyclerViewCA innerRCAdapter;

        OuterRecyclerViewCA() {

            innerRCAdapter = new InnerRecyclerViewCA();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.outer_recycler_view_row_element, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            switch (mAnimationType) {
                case AnimationUtils.REVERSE_SCATTER:

                    if (holder.getAdapterPosition() > previousPosition) {
                        AnimationUtils.animateAlphaFadeAndScatter(holder, true);
                    } else {
                        AnimationUtils.animateAlphaFadeAndScatter(holder, false);
                    }
                    previousPosition = holder.getAdapterPosition();

                    break;

                case AnimationUtils.FLIP:
                    AnimationUtils.viewFlip(holder);
                    break;
                case AnimationUtils.SLIDE_BOTTOM_TO_TOP:
                    if (holder.getAdapterPosition() > previousPosition) {
                        AnimationUtils.slideBottomToTop(holder, true);
                    } else {
                        AnimationUtils.slideBottomToTop(holder, true);
                    }
                    previousPosition = holder.getAdapterPosition();

                    break;
                case AnimationUtils.SLIDE_LEFT_TO_RIGHT:
                    AnimationUtils.slideLeftToRight(holder);
                    break;
                case AnimationUtils.AVATAR_FLIP_SLIDE_IN_FADE_IN:
                    AnimationUtils.animateAvatarFadeInFlip(holder);
                    break;
                case AnimationUtils.ALPHA_FADE_SLIDE:
                    AnimationUtils.alphaFadeSlide(holder);
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            RecyclerView innerRecyclerView;
            CardView posterCard;

            public ViewHolder(View itemView) {
                super(itemView);
                innerRecyclerView = (RecyclerView) itemView.findViewById(R.id.nestedRecyclerView);
                innerRecyclerView.setLayoutManager(
                        new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false)
                );
                innerRecyclerView.setAdapter(innerRCAdapter);
                posterCard = (CardView) itemView.findViewById(R.id.posterCard);
            }

            public RecyclerView getInnerRecyclerView() {
                return innerRecyclerView;
            }
        }

        public class InnerRecyclerViewCA extends RecyclerView.Adapter<InnerRecyclerViewCA.ViewHolder> {
            private int prevPos = 0;

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.inner_recycler_view_row_element, parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                if (position > prevPos) {
                    AnimationUtils.animateAlphaFadeAndScatter(holder, true);
                } else {
                    AnimationUtils.animateAlphaFadeAndScatter(holder, false);
                }
                prevPos = position;
            }

            @Override
            public int getItemCount() {
                return 100;
            }

            class ViewHolder extends RecyclerView.ViewHolder {

                public ViewHolder(View itemView) {
                    super(itemView);
                }
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nested_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.animate_avatar_fade_in_flip:
                mAnimationType = AnimationUtils.AVATAR_FLIP_SLIDE_IN_FADE_IN;
                if (outerRecyclerView != null) {
                    outerRecyclerView.invalidate();
                }
                break;
            case R.id.alpha_fade_slide_in:
                mAnimationType = AnimationUtils.ALPHA_FADE_SLIDE;
                if (outerRecyclerView != null) {
                    outerRecyclerView.invalidate();
                }
                break;
            case R.id.slide_left_to_right:
                mAnimationType = AnimationUtils.SLIDE_LEFT_TO_RIGHT;
                if (outerRecyclerView != null) {
                    outerRecyclerView.invalidate();
                }
                break;
            case R.id.slide_bottom_to_top:
                mAnimationType = AnimationUtils.SLIDE_BOTTOM_TO_TOP;
                if (outerRecyclerView != null) {
                    outerRecyclerView.invalidate();
                }
                break;
            case R.id.reverse_scatter:
                mAnimationType = AnimationUtils.REVERSE_SCATTER;
                if (outerRecyclerView != null) {
                    outerRecyclerView.invalidate();
                }
                break;
            case R.id.flip:
                mAnimationType = AnimationUtils.FLIP;
                if (outerRecyclerView != null) {
                    outerRecyclerView.invalidate();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
