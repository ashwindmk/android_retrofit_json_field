package com.ashwin.android.retrofitjsonfield.data;

import com.ashwin.android.retrofitjsonfield.model.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileApi {


    @GET("profile.json")
    Call<Profile> getProfile();
}
