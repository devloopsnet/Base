package com.devloops.activities.http;


import com.devloops.activities.models.BaseModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Odey M. Hhalaf <odey@devloops.net>
 * @path – variable substitution for the API endpoint. For example movie id will be swapped for{id} in the URL endpoint.
 * @query – specifies the query key name with the value of the annotated parameter.
 * @body – payload for the POST call
 * @header – specifies the header with the value of the annotated parameter
 **/
public interface ApiMethods {

    @GET(Methods.EXAMPLE_PATH)
    Call<BaseModel> getExampleRequest();


    interface Methods {
        String EXAMPLE_PATH = "example_endpoint";
    }
}