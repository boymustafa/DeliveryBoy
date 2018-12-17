package com.boymustafa.experiment.app;

import android.app.Application;
import android.content.Context;

import com.boymustafa.experiment.network.ApiService;
import com.boymustafa.experiment.network.DeliverService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class AppController extends Application {

    private DeliverService deliverService;
    private Scheduler scheduler;

    private static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

    public DeliverService getDeliverService() {
        if (deliverService == null) {
            deliverService = ApiService.create();
        }

        return deliverService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setUserService(DeliverService deliverService) {
        this.deliverService = deliverService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

}
