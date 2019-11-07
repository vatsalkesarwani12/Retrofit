package e.vatsalkesarwani.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceholderApi {

    @GET("/posts")
    Call<List<post>> getPosts();     // functions gets u list of posts //return a call object
    
}
