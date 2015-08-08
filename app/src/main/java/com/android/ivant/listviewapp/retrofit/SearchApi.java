package com.android.ivant.listviewapp.retrofit;

import com.android.ivant.listviewapp.model.SongsResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by User on 04/08/2015.
 */

public interface SearchApi {
    @GET("/search/")
    void getItunesSearchResults(@Query("term") String term, Callback<SongsResponse> searchCallback);

}
