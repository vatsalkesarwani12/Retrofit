package e.vatsalkesarwani.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult ;

    private JsonPlaceholderApi jsonPlaceholderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult=findViewById(R.id.text_view_result);

        Gson gson=new GsonBuilder().serializeNulls().create();     //done to customize Gson converter

        Retrofit retrofit =new Retrofit.Builder()                                   //retrofit build
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceholderApi=retrofit.create(JsonPlaceholderApi.class);   //client object

        //getPosts();

        //getComments();

        //createPost();

        //updatePost();

        deletePost();
    }

    public void getPosts()
    {
        Map<String,String> parameters=new HashMap<>();
        parameters.put("userId","2");
        parameters.put("_sort","id");
        parameters.put("_order","desc");
        Call<List<post>> call;
        //call = jsonPlaceholderApi.getPosts(new Integer[]{2,4,6},"id","desc");   //if u don't want the parameters to work pass null  //data extract //multiple query parameter
        call =jsonPlaceholderApi.getPosts(parameters);

        call.enqueue(new Callback<List<post>>() {
            @Override
            public void onResponse(Call<List<post>> call, Response<List<post>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<post> posts = response.body();

                for (post postt : posts) {
                    String content = "";
                    content += "ID: " + postt.getId() + "\n";
                    content += "User ID: " + postt.getUserId() + "\n";
                    content += "Title: " + postt.getTitle() + "\n";
                    content += "Text: " + postt.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }

        });
    }

    public void getComments()
    {
        //Call<List<Comment>> call=jsonPlaceholderApi.getComments(3);    //for example we pass 3 as id

        Call<List<Comment>> call=jsonPlaceholderApi.getComments("posts/2/comments");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful())
                {
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                List<Comment> comments=response.body();

                for(Comment comment :comments)
                {
                    String content="";
                    content +="PostId: "+comment.getPostId()+"\n";
                    content +="Id: "+comment.getId()+"\n";
                    content +="Name: "+comment.getName()+"\n";
                    content +="Email: "+comment.getEmail()+"\n";
                    content +="Body: "+comment.getText()+"\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void createPost()
    {
        post posts=new post(23,"new title","new text");    //1 st post we pass post in Call<post> call=jsonPlaceholderApi.createPost(posts);

        Map<String,String> fields=new HashMap<>();
        fields.put("userId","23");
        fields.put("title","new title");
        fields.put("body","new text");

        //Call<post> call=jsonPlaceholderApi.createPost(23,"new title","new text");   //2nd method of POST

        Call<post> call=jsonPlaceholderApi.createPost(fields);

        call.enqueue(new Callback<post>() {
            @Override
            public void onResponse(Call<post> call, Response<post> response) {
                if(!response.isSuccessful())
                {
                    textViewResult.setText("Code: "+response.code());
                    return;
                }
                post createResponse=response.body();

                    String content = "";
                    content +="Code: " + response.code()+"\n";
                    content += "ID: " + createResponse.getId() + "\n";
                    content += "User ID: " + createResponse.getUserId() + "\n";
                    content += "Title: " + createResponse.getTitle() + "\n";
                    content += "Text: " + createResponse.getText() + "\n\n";

                    textViewResult.append(content);


            }

            @Override
            public void onFailure(Call<post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void updatePost()
    {
        post posts =new post(12,null,"new text");

        Call<post> call =jsonPlaceholderApi.patchPost(5,posts);         //no changes only change put with patch to use patch post

        call.enqueue(new Callback<post>() {
            @Override
            public void onResponse(Call<post> call, Response<post> response) {
                if(!response.isSuccessful())
                {
                    textViewResult.setText("Code: "+response.code());
                    return;
                }
                post postResponse=response.body();

                String content = "";
                content +="Code: " + response.code()+"\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";

                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void deletePost()
    {
        Call<Void> call=jsonPlaceholderApi.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewResult.setText("Code: "+response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
