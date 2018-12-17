package com.boymustafa.experiment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.boymustafa.experiment.model.Delivers;
import com.boymustafa.experiment.viewModel.MapViewModel;

import com.boymustafa.experiment.databinding.ActivityMapsBinding;
public class MapsActivity extends FragmentActivity {
    private static final String EXTRA_DELIVER = "EXTRA_DELIVER";

    private ActivityMapsBinding activityMapsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMapsBinding = DataBindingUtil.setContentView(this,R.layout.activity_maps);
        getExtrasFromIntent();
    }

    public static Intent fillDetail(Context context, Delivers delivers){
        Intent intent = new Intent(context,MapsActivity.class);
        intent.putExtra(EXTRA_DELIVER,delivers);
        return intent;
    }

    private void getExtrasFromIntent(){
        Delivers delivers = (Delivers) getIntent().getSerializableExtra(EXTRA_DELIVER);
        MapViewModel mapViewModel = new MapViewModel(delivers);
        activityMapsBinding.setMapViewModel(mapViewModel);
    }
}
