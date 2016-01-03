package com.challenge.moldedbits.activites;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.challenge.moldedbits.adapters.RecentPostListAdapter;
import com.challenge.moldedbits.models.Data;
import com.challenge.moldedbits.models.RecentPosts;
import com.challenge.moldedbits.tasks.RecentsPostTask;
import com.challenge.moldedbits.utils.DateUtils;
import com.challenge.moldedbits.utils.NetworkUtils;

import challenge.moldedbits.R;

public class RecentPostsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = RecentPostsActivity.class.getSimpleName();
    private RecyclerView rvRecentPosts;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_posts);
        initViews();
        if (NetworkUtils.isConnected(this)) {
            fetchRecentPosts();
        } else {
            Toast.makeText(RecentPostsActivity.this, getResources().getString(R.string.internet_connectivity_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_view);
        swipeRefreshLayout.setOnRefreshListener(this);
        rvRecentPosts = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRecentPosts.setLayoutManager(linearLayoutManager);
        dialog = ProgressDialog.show(RecentPostsActivity.this, getString(R.string.fetching_results), getString(R.string.wait_message));
    }

    private void fetchRecentPosts() {
        RecentsPostTask recentsPostTask = new RecentsPostTask() {
            @Override
            protected void onPostExecute(RecentPosts recentPosts) {
                super.onPostExecute(recentPosts);
                if (isFinishing()) {
                    return;
                }

                swipeRefreshLayout.setRefreshing(false);
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }

                if (recentPosts == null) {
                    Toast.makeText(RecentPostsActivity.this, getResources().getString(R.string.generic_error_response), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (recentPosts.getMeta().getErrorMessage() != null) {
                    Toast.makeText(RecentPostsActivity.this, recentPosts.getMeta().getErrorMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                showResults(recentPosts.getData());
            }
        };
        recentsPostTask.execute();
    }

    private void showResults(List<Data> recentPosts) {
        if (recentPosts != null && !recentPosts.isEmpty()) {
            sortPosts(recentPosts);
            rvRecentPosts.setAdapter(new RecentPostListAdapter(RecentPostsActivity.this, recentPosts));
        }
    }

    @Override
    public void onRefresh() {
        if (NetworkUtils.isConnected(this)) {
            fetchRecentPosts();
        } else {
            Toast.makeText(RecentPostsActivity.this, getResources().getString(R.string.internet_connectivity_error), Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void sortPosts(List<Data> posts) {
        final String dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        Collections.sort(posts, new Comparator<Data>() {
            @Override
            public int compare(Data lhs, Data rhs) {
                Date lhsDate = DateUtils.convertStringToDate(lhs.getCreationDate(), dateFormat);
                Date rhsdate = DateUtils.convertStringToDate(rhs.getCreationDate(), dateFormat);
                if (lhsDate.after(rhsdate))
                    return -1;
                else if (rhsdate.after(lhsDate))
                    return 1;
                else
                    return 0;
            }
        });
    }
}