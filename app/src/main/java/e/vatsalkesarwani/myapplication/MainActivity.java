package e.vatsalkesarwani.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult=findViewById(R.id.text_view_result);

        Retrofit retrofit =new Retrofit.Builder()                                   //retrofit build
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderApi jsonPlaceholderApi=retrofit.create(JsonPlaceholderApi.class);   //client object

        Call<List<post>> call;
        call = jsonPlaceholderApi.getPosts();                      //data extract

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
}
