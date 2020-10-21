package com.ashwin.android.retrofitjsonfield.presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ashwin.android.retrofitjsonfield.R;
import com.ashwin.android.retrofitjsonfield.data.AddressDeserializer;
import com.ashwin.android.retrofitjsonfield.data.ProfileApi;
import com.ashwin.android.retrofitjsonfield.model.Profile;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String APP_TAG = "retrofit-json-variable";
    private static final String BASE_URL = "https://gist.githubusercontent.com/ashwindmk/1e2097ac3de60a40c469ec1a60f35b41/raw/";

    private TextView cityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityTextView = findViewById(R.id.city_textview);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadProfile();
    }

    private void loadProfile() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(JSONObject.class, new AddressDeserializer());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();

        ProfileApi profileApi = retrofit.create(ProfileApi.class);
        Call<Profile> call = profileApi.getProfile();
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                try {
                    int statusCode = response.code();
                    if (statusCode == 200) {
                        Profile profile = response.body();
                        Log.w(APP_TAG, "onResponse| response: " + profile);
                        final String city = profile.getAddress().getString("city");
                        runOnUiThread(() -> {
                            cityTextView.setText("City: " + city);
                        });
                    } else {
                        Log.e(APP_TAG, "onResponse | status: " + statusCode);
                    }
                } catch (Exception e) {
                    Log.e(APP_TAG, "onResponse | exception", e);
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.e(APP_TAG, "onFailure", t);
            }
        });
    }
}
