package com.mvvmarcitecture.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mvvmarcitecture.R;
import com.mvvmarcitecture.databinding.ActivityMainBinding;

import com.mvvmarcitecture.model.CommitModel;

import com.mvvmarcitecture.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding peopleActivityBinding;
    private MainViewModel peopleViewModel;
    private ListAdapterHolder adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupListPeopleView(peopleActivityBinding.listPeople);
        setupObserver(peopleViewModel);

    }

    private void initDataBinding() {
        peopleActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        peopleViewModel = new MainViewModel(this);
        peopleActivityBinding.setMainViewModel(peopleViewModel);
    }

    private void setupListPeopleView(RecyclerView listPeople) {

        adapter = new ListAdapterHolder();


        listPeople.setHasFixedSize(true);
        listPeople.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listPeople.setItemAnimator(new DefaultItemAnimator());
        listPeople.setAdapter(adapter);

    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        peopleViewModel.reset();
    }


    @Override public void update(Observable observable, Object data) {
        if (observable instanceof MainViewModel) {
            ListAdapterHolder peopleAdapter = (ListAdapterHolder) peopleActivityBinding.listPeople.getAdapter();
            MainViewModel peopleViewModel = (MainViewModel) observable;
            peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
//            peopleAdapter.notifyDataSetChanged();
        }
    }


}