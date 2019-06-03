package com.android.library.upgrade.net;

import com.android.library.upgrade.VersionUploadEntity;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UpDateServer {
    @POST("")
    Observable<VersionUploadEntity> upload(@Body RequestBody requestBody);
}
