package com.project.faculty.nestedrecyclerviewtest25012016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.faculty.nestedrecyclerviewtest25012016.Animation.AnimationUtils;

public class NestedRecyclerViewActivity extends AppCompatActivity {
    RecyclerView outerRecyclerView;
    OuterRecyclerViewCA outerRVAdapter;
    private int previousPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        outerRVAdapter = new OuterRecyclerViewCA();
        outerRecyclerView = (RecyclerView) findViewById(R.id.outerRecyclerView);
        outerRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
        outerRecyclerView.setAdapter(outerRVAdapter);
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
            if (position > previousPosition) {
                AnimationUtils.animateAlphaFadeAndScatter(holder, true);
            } else {
                AnimationUtils.animateAlphaFadeAndScatter(holder, false);
            }
            previousPosition = position;
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            RecyclerView innerRecyclerView;

            public ViewHolder(View itemView) {
                super(itemView);
                innerRecyclerView = (RecyclerView) itemView.findViewById(R.id.nestedRecyclerView);
                innerRecyclerView.setLayoutManager(
                        new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false)
                );
                innerRecyclerView.setAdapter(innerRCAdapter);
            }

            public RecyclerView getInnerRecyclerView() {
                return innerRecyclerView;
            }
        }

        public class InnerRecyclerViewCA extends RecyclerView.Adapter<InnerRecyclerViewCA.ViewHolder> {
            private int prevPos=0;
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view =  LayoutInflater.from(parent.getContext())
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
}
