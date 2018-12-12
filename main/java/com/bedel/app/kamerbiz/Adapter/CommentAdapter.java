package com.bedel.app.kamerbiz.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bedel.app.kamerbiz.Entity.Comment;
import com.bedel.app.kamerbiz.R;
import com.bedel.app.kamerbiz.Tools.FormatterUtil;
import com.bedel.app.kamerbiz.views.ExpandableTextView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    Context mContext;
    List<Comment> items;

    public class CommentViewHolder extends RecyclerView.ViewHolder  {

        RelativeLayout ll;
        ExpandableTextView commenttext;
        TextView commentdate;
        ImageView commentImage;


        CommentViewHolder(View itemView) {
            super(itemView);
            //ll = (RelativeLayout)itemView.findViewById(R.id.course_image);
            commentImage = (ImageView)itemView.findViewById(R.id.avatarImageView);
            commenttext = (ExpandableTextView)itemView.findViewById(R.id.commentText);
            commentdate = (TextView)itemView.findViewById(R.id.dateTextView);

        }

    }
    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item, parent, false);
        return  new CommentViewHolder(v);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = items.get(position);
        //holder.placeImage.setText(Course.setPhoto());
        holder.commenttext.setText(comment.getText());
        CharSequence date = FormatterUtil.getRelativeTimeSpanString(mContext, comment.getDate());
        holder.commentdate.setText(date);
    }



    public CommentAdapter(Context mContext, List<Comment> items){
        this.mContext = mContext;
        this.items = items;
    }



    @Override
    public int getItemCount() {
        return items.size();
    }
}


