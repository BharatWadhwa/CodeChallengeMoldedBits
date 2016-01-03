package com.challenge.moldedbits.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.challenge.moldedbits.models.Data;
import com.challenge.moldedbits.utils.CircleTransform;
import com.challenge.moldedbits.utils.DateUtils;

import challenge.moldedbits.R;

/**
 * Created by bharat on 03/01/16.
 */
public class RecentPostListAdapter extends RecyclerView.Adapter<RecentPostListAdapter.ViewHolder> {

    private Context context;
    private List<Data> posts;

    public RecentPostListAdapter(Context context, List<Data> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public RecentPostListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(RecentPostListAdapter.ViewHolder holder, int position) {
        Data data = posts.get(position);
        holder.tvPost.setText(data.getText());
        holder.tvPosterName.setText(data.getUser().getUsername());
        holder.tvCreationDate.setText(DateUtils.convertStringToDateFormat(data.getCreationDate(), "yyyy-MM-dd'T'HH:mm:ss'Z'", "dd/MM/yy 'at' hh:mm a"));
        Picasso.with(context).load(data.getUser().getAvatarImage().getUrl()).transform(new CircleTransform()).into(holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return posts != null ? posts.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPosterName;
        public TextView tvPost;
        public TextView tvCreationDate;
        public ImageView ivAvatar;

        public ViewHolder(View view) {
            super(view);
            tvPost = (TextView) view.findViewById(R.id.tv_post);
            tvPosterName = (TextView) view.findViewById(R.id.tv_user_name);
            ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
            tvCreationDate = (TextView) view.findViewById(R.id.tv_creation_date);
        }
    }
}
