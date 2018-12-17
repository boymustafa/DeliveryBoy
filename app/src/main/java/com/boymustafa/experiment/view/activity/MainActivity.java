package com.boymustafa.experiment.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

import com.boymustafa.experiment.R;
import com.boymustafa.experiment.databinding.ActivityMainBinding;
import com.boymustafa.experiment.view.adapter.DeliverAdapter;
import com.boymustafa.experiment.viewModel.DeliverViewModel;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding activityMainBinding;
    private DeliverViewModel deliverViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(activityMainBinding.toolbar);
        setUpListOfUsersView(activityMainBinding.listDeliver);
        setUpObserver(deliverViewModel);
    }

    private void initDataBinding(){
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        deliverViewModel = new DeliverViewModel(this);
        activityMainBinding.setDeliverViewModel(deliverViewModel);
    }

    private void setUpListOfUsersView(RecyclerView listDeliver) {
        DeliverAdapter deliverAdapter = new DeliverAdapter();
        listDeliver.setAdapter(deliverAdapter);
        listDeliver.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpObserver(Observable observable){
        observable.addObserver(this);
    }


     @Override
    public void update(Observable o, Object arg) {
        if (o instanceof DeliverViewModel) {
            DeliverAdapter deliverAdapter = (DeliverAdapter) activityMainBinding.listDeliver.getAdapter();
            DeliverViewModel deliverViewModel = (DeliverViewModel) o;
            deliverAdapter.setDeliverList(deliverViewModel.getDeliverList());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deliverViewModel.reset();
    }


}
