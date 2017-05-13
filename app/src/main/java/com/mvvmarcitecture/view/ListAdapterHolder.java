package com.mvvmarcitecture.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvvmarcitecture.R;
import com.mvvmarcitecture.databinding.ListItemBinding;

import com.mvvmarcitecture.model.CommitModel;
import com.mvvmarcitecture.viewmodel.ItemPeopleViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rajesh on 29/4/17.
 */

public class ListAdapterHolder extends RecyclerView.Adapter<ListAdapterHolder.CustomViewHolder> {


    private List<CommitModel> mCommitList = new ArrayList<CommitModel>();


    public ListAdapterHolder() {
        this.mCommitList = Collections.emptyList();
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ListItemBinding itemPeopleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item,
                        parent, false);
        return new CustomViewHolder(itemPeopleBinding);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.bindPeople(mCommitList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCommitList.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        ListItemBinding mItemPeopleBinding;

        public CustomViewHolder(ListItemBinding itemPeopleBinding) {
            super(itemPeopleBinding.cardLayout);
            this.mItemPeopleBinding = itemPeopleBinding;
        }

        void bindPeople(CommitModel people) {
            if (mItemPeopleBinding.getPeopleViewModel() == null) {
                mItemPeopleBinding.setPeopleViewModel(
                        new ItemPeopleViewModel(people, itemView.getContext()));
            } else {
                mItemPeopleBinding.getPeopleViewModel().setPeople(people);
            }
        }
    }

    public void setPeopleList(List<CommitModel> peopleList) {
        mCommitList = peopleList;
        notifyDataSetChanged();
    }


}
