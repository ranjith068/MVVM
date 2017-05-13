package com.mvvmarcitecture.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.mvvmarcitecture.MainApplication;

import com.mvvmarcitecture.data.MainServices;
import com.mvvmarcitecture.model.CommitModel;

import com.mvvmarcitecture.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rajesh on 12/5/17.
 */

public class MainViewModel extends Observable {

    public ObservableInt peopleRecycler;

    private List<CommitModel> peopleList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainViewModel(@NonNull Context context) {

        this.context = context;
        this.peopleList = new ArrayList<>();
        peopleRecycler = new ObservableInt(View.GONE);

        initializeViews();
        fetchPeopleList();
    }

    public void onClickFabLoad(View view) {
//        initializeViews();
//        fetchPeopleList();
    }

    //It is "public" to show an example of test
    public void initializeViews() {
        peopleRecycler.set(View.GONE);
    }

    private void fetchPeopleList() {



        MainApplication peopleApplication = MainApplication.create(context);
        MainServices peopleService = peopleApplication.getPeopleService();

        Disposable disposable = peopleService.getCommits()
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CommitModel>>() {
                    @Override
                    public void accept( List<CommitModel> commitModels) throws Exception {

                        changePeopleDataSet(commitModels);
                        peopleRecycler.set(View.VISIBLE);

                    }

                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {

                        peopleRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);

    }

    private void changePeopleDataSet(List<CommitModel> peoples) {
        peopleList.addAll(peoples);
        setChanged();
        notifyObservers();
    }

    public List<CommitModel> getPeopleList() {
        return peopleList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}
