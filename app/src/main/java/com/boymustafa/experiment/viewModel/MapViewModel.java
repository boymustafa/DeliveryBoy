package com.boymustafa.experiment.viewModel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.boymustafa.experiment.model.Delivers;
import com.boymustafa.experiment.model.Location;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Observable;

public class MapViewModel extends Observable {

    private Delivers deliver;
    private Location location;
    private Double lat,lng;
    private static String address;
    public ObservableField<LatLng>  mMapLatLng = new ObservableField<>();
    private static final String TAG = MapViewModel.class.getSimpleName();

    public MapViewModel(Delivers delivers){
        this.deliver = delivers;
        Location location = deliver.location;
        lat = location.getLat();
        lng = location.getLng();
        address = location.getAddress();
        mMapLatLng.set(new LatLng(location.getLat(),location.getLng()));
    }


    public String getDescription(){
        return deliver.description;
    }

    public LatLng getLatLng(){
        Location location = deliver.location;
        return new LatLng(location.getLat(),location.getLng());
    }

    public String getThumbnail(){
        return deliver.imageUrl;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl){
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    @BindingAdapter("initMap")
    public static void initMap(MapView mapView, final LatLng latLng) {
        if (mapView != null) {
            mapView.onCreate(new Bundle());
            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    Log.d(TAG,"on map ready!!");
                    // Add a marker
                    googleMap.addMarker(new MarkerOptions().position(latLng).title(address));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                }
            });
        }
    }




}
