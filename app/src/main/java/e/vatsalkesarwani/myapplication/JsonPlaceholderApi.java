package e.vatsalkesarwani.myapplication;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceholderApi {

    // @GET("/posts")                                              /*when the url contains ? it means that the query starts from there*/
    // Call<List<post>> getPosts(@Query("userId") int usrId);     // functions gets u list of posts //return a call object

    @GET("posts")
    Call<List<post>> getPosts(                //multiple query
      @Query("userId") Integer[] userId,
      @Query("_sort") String sort,
      @Query("_order") String oreder
    );


    @GET("posts")
    Call<List<post>> getPosts(@QueryMap Map<String,String> parameter);

    @GET("posts/{id}/comments")                            //dynamic calling //dynamic content inside the parenthesis
    Call<List<Comment>> getComments(@Path("id") int postid);  //@Path annotation specify where the data has to go

    @GET
    Call<List<Comment>> getComments(@Url String url);  //can pass relative url directly here
}
