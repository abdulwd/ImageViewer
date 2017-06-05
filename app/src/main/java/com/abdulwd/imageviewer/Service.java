package com.abdulwd.imageviewer;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface Service {
    @GET("tutorial/jsonparsetutorial.txt")
    Observable<WorldPopulation<List<Image>>> getImages();
}
