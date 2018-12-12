package com.bedel.app.kamerbiz.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.bedel.app.kamerbiz.Activity.Boot.BootActivity;
import com.bedel.app.kamerbiz.Activity.Login.LoginActivity;
import com.bedel.app.kamerbiz.Activity.Post.CommentActivity;
import com.bedel.app.kamerbiz.Activity.Post.DetailsEntrepriseActivity;
import com.bedel.app.kamerbiz.Entity.Enterprise;
import com.bedel.app.kamerbiz.Entity.Category;
import com.bedel.app.kamerbiz.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;


public class EnterpriseAdapter extends RecyclerView.Adapter<EnterpriseAdapter.ViewHolder> implements Filterable {

    private List<Enterprise> mEnterprises;
    private List<Enterprise> mEnterpriseListFiltered;
    ItemClickListener<Enterprise> itemClickListener;
    private Context mContext;


    public EnterpriseAdapter(Context context, List<Enterprise> enterprises, ItemClickListener<Enterprise> itemClickListener) {
        this.mContext = context;
        this.mEnterprises = enterprises;
        this.mEnterpriseListFiltered = enterprises;
        this.itemClickListener = itemClickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false));
    }

    public List<Enterprise> getEnterprises() {
        return mEnterpriseListFiltered;
    }

    public void setEntreprise(List<Enterprise> enterprises) {
        this.mEnterpriseListFiltered = enterprises;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Enterprise enterprise = mEnterpriseListFiltered.get(position);
        holder.avatar.setImageURI(enterprise.getLogo());
        holder.Name.setText(enterprise.getName());
        holder.textJobTitle.setText(enterprise.getDjname());
        holder.textDate.setText(enterprise.getDate());
        holder.Description.setText(enterprise.getDescription());
        Category firstCategory = enterprise.getCategories().get(0);
        holder.firstFilter.setText(firstCategory.getText());
        Category secondCategory = enterprise.getCategories().get(1);
        holder.secondFilter.setText(secondCategory.getText());

        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(1000);
        drawable.setColor(firstCategory.getColor());
        holder.firstFilter.setBackgroundDrawable(drawable);
        GradientDrawable drawable1 = new GradientDrawable();
        drawable1.setCornerRadius(1000);
        drawable1.setColor(secondCategory.getColor());
        holder.secondFilter.setBackgroundDrawable(drawable1);


        holder.view_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!enterprise.testlike){
                   enterprise.testlike = true;
                   holder.txt_like.setText((int) (enterprise.getLike()+1)+"");
               }else {
                   enterprise.testlike = false;
                   holder.txt_like.setText((int) (enterprise.getLike()-1)+"");
               }

            }
        });
        holder.view_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!enterprise.testlike){
                    enterprise.testlike = true;
                    holder.txt_dislike.setText((int) (enterprise.getDislike()+1)+"");
                }else {
                    enterprise.testlike = false;
                    holder.txt_dislike.setText((int) (enterprise.getDislike()-1)+"");
                }

            }
        });
        holder.view_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Enterprise item = enterprise;
                Intent intent = new Intent(mContext, CommentActivity.class);
                Bundle data = new Bundle();
                data.putSerializable("Enterprise", item);
                intent.putExtras(data);
                mContext.startActivity(intent);

            }
        });

    }

    private int getColor(int color) {
        return ContextCompat.getColor(mContext, color);
    }

    @Override
    public int getItemCount() {
        return mEnterpriseListFiltered.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView Name;
        TextView textJobTitle;
        TextView textDate;
        TextView Description;
        TextView firstFilter;
        TextView secondFilter;
        android.support.v7.widget.AppCompatImageView view_chat,view_like,view_dislike;
        TextView txt_chat,txt_like,txt_dislike;
        SimpleDraweeView avatar;




        public ViewHolder(View itemView) {
            super(itemView);

            Name = (TextView) itemView.findViewById(R.id.text_name);
            textJobTitle = (TextView) itemView.findViewById(R.id.text_job_title);
            textDate = (TextView) itemView.findViewById(R.id.text_date);
            Description = (TextView) itemView.findViewById(R.id.text_question);
            firstFilter = (TextView) itemView.findViewById(R.id.filter_first);
            secondFilter = (TextView) itemView.findViewById(R.id.filter_second);
            avatar = (SimpleDraweeView) itemView.findViewById(R.id.avatar);
            view_chat =  itemView.findViewById(R.id.view_chat);
            view_like =  itemView.findViewById(R.id.view_likes);
            view_dislike =  itemView.findViewById(R.id.view_dislike);
            txt_chat =  itemView.findViewById(R.id.text_chat_count);
            txt_like =  itemView.findViewById(R.id.text_likes_count);
            txt_dislike =  itemView.findViewById(R.id.text_dislike_count);
            Description.setOnClickListener((View.OnClickListener) this);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(mEnterpriseListFiltered.get(getPosition()), avatar);

        }
    }


    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mEnterpriseListFiltered = mEnterprises;
                } else {
                    List<Enterprise> filteredList = new ArrayList<>();
                    for (Enterprise row : mEnterprises) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getDescription().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    mEnterpriseListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mEnterpriseListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mEnterpriseListFiltered = (ArrayList<Enterprise>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
