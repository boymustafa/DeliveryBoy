package com.boymustafa.experiment.viewModel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.boymustafa.experiment.app.AppController;
import com.boymustafa.experiment.model.Delivers;
import com.boymustafa.experiment.network.DeliverService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class DeliverViewModel extends Observable {

    public ObservableInt mDeliverRecyler;
    public ObservableInt progressBar;
    public ObservableInt fab;
    public List<Delivers> mDeliverlist;

    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final String TAG = DeliverViewModel.class.getSimpleName();

    private final int DELIVER_OFFSET = 20;
    private final int DELIVER_LIMIT = 20;

    public DeliverViewModel(@NonNull Context context){
        this.context = context;
        this.mDeliverlist = new ArrayList<>();
        fab = new ObservableInt(View.GONE);
        progressBar = new ObservableInt(View.VISIBLE);
        this.mDeliverRecyler = new ObservableInt(View.GONE);
        fetchDeliverList();
    }

    public void onClickFAB(View view){
        fab.set(View.GONE);
        fetchDeliverList();
    }


    private void fetchDeliverList(){
        progressBar.set(View.VISIBLE);
        AppController mAppController = AppController.create(context);
        DeliverService mDeliverService = mAppController.getDeliverService();

        Disposable disposable = mDeliverService.getDeliverList(DELIVER_OFFSET,DELIVER_LIMIT)
                .subscribeOn(mAppController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Delivers>>() {
                    @Override
                    public void accept(List<Delivers> delivers) throws Exception {
                        Log.d(TAG,"delivers: "+delivers);
                        updateDeliverlist(delivers);
                        fab.set(View.GONE);
                        progressBar.set(View.GONE);
                        mDeliverRecyler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        progressBar.set(View.GONE);
                        fab.set(View.VISIBLE);
                        Log.d(TAG,"ERROR on LOADING DELIVER VIEW MODEL: "+throwable.getLocalizedMessage());
                        Log.d(TAG,"ERROR on LOADING DELIVER VIEW MODEL message: "+throwable.getMessage());
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void updateDeliverlist(List<Delivers> delivers) {
        mDeliverlist.addAll(delivers);
        setChanged();
        notifyObservers();
    }

    public List<Delivers> getDeliverList(){
        return mDeliverlist;
    }

    private void unsubscribeFromObservable(){
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset(){
        unsubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }

}
