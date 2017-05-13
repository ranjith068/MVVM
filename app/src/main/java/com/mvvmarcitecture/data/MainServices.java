package com.mvvmarcitecture.data;

import com.mvvmarcitecture.model.CommitModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by rajesh on 29/4/17.
 */

public interface MainServices {

    @GET("repos/rails/rails/commits")
    Observable<List<CommitModel>> getCommits();

//    @GET Observable<List<CommitModel>> fetchPeople(@Url String url);
}
