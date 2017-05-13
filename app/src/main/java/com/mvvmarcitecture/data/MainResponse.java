package com.mvvmarcitecture.data;

import com.google.gson.annotations.SerializedName;
import com.mvvmarcitecture.model.CommitModel;

import java.util.List;

/**
 * Created by rajesh on 12/5/17.
 */

public class MainResponse {
    @SerializedName("results") private List<CommitModel> peopleList;

    public List<CommitModel> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<CommitModel> mPeopleList) {
        this.peopleList = mPeopleList;
    }
}
