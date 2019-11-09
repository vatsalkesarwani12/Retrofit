package e.vatsalkesarwani.myapplication;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @POST("posts")
    Call<post> createPost(@Body post posts);

    //or using formUrlEncoder

    @FormUrlEncoded
    @POST("posts")
    Call<post> createPost(
            @Field("id") int id,
            @Field("title") String title,
            @Field("body") String text
            );

    @FormUrlEncoded
    @POST("posts")
    Call<post> createPost(@FieldMap Map<String ,String > fields);

    @PUT("posts/{id}")
    Call<post> putPost(@Path("id") int id, @Body post posts);

    @PATCH("posts/{id}")
    Call<post> patchPost(@Path("id") int id, @Body post posts);

}
