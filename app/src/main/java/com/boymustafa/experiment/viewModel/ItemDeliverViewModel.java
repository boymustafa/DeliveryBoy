package com.boymustafa.experiment.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.boymustafa.experiment.MapsActivity;
import com.boymustafa.experiment.model.Delivers;
import com.boymustafa.experiment.model.Location;
import com.bumptech.glide.Glide;

public class ItemDeliverViewModel extends BaseObservable {

    private Delivers mDeliver;
    private Context context;
    private Location location;

    public ItemDeliverViewModel(Delivers delivers, Context context) {
        this.mDeliver = delivers;
        this.context = context;
        this.location = mDeliver.location;
    }

    public String getDescription(){
        return mDeliver.description;
    }

    public String getDeliverImg(){
        return mDeliver.imageUrl;
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setDeliver(Delivers deliver){
        this.mDeliver = deliver;
        notifyChange();
    }

    public void onItemClick(View v){
        context.startActivity(MapsActivity.fillDetail(v.getContext(), mDeliver));
    }
}
