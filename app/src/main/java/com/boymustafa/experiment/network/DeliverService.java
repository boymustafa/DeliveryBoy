package com.boymustafa.experiment.network;
import com.boymustafa.experiment.model.Delivers;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeliverService {

    @GET("deliveries")
    Observable<List<Delivers>> getDeliverList(@Query("offset") int offset,@Query("limit") int limit);

}
