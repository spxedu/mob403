package net.sdt.demovolleyretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText ed_content;
    Button btn_get_volley, btn_post_volley;
    String url_api = "http://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_content = findViewById(R.id.ed_content);
        btn_get_volley = findViewById(R.id.btn_get_volley);
        btn_post_volley = findViewById(R.id.btn_post_volley);

        btn_get_volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// DemoGetVolley();
                DemoGETRetrofit();
            }
        });

        btn_post_volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DemoPostVolley();
                DemoPOSTRetrofit();
            }
        });
    }
    void DemoGetVolley(){

        RequestQueue queue = Volley.newRequestQueue(this);
        // StringRequest( method, url, response_callback, error_callback) ;
        StringRequest request = new StringRequest(Request.Method.GET, url_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // xử lý khi dữ liệu trả về thành công
                        // chuyển kiểu dữ liệu về object nếu cần....
                        //ví dụ này gán trực tiếp vào edittext
                        ed_content.setText( response );

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // xử lý khi xảy ra lỗi
                Log.e("zzzzzz", "onErrorResponse: ", error );
                ed_content.setText("lỗi load dữ liệu " + error.getMessage() );
            }
        }
        );

        queue.add(request);

    }
    void DemoPostVolley(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        try {
            // tạo dữ liệu để gửi lên API
            JSONObject obj = new JSONObject();
            obj.put("username", "nguyenvana");
            obj.put("email", "nva@gmail.com");
            obj.put("passwd","123");
            // tạo request body để gửi lên server
            final String reqBody = obj.toString();

            StringRequest req = new StringRequest(Request.Method.POST,
                    url_api,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Nơi dữ liệu nhận về
                            ed_content.setText( response );
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ed_content.setText( error.getMessage()  );
                }
            }){
                // phần gửi đi
                @Override  // khai báo kiểu dữ liệu gửi lên API
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override // chuyển nội dung gửi thành mã byte
                public byte[] getBody() throws AuthFailureError {

                    if(reqBody == null)
                        return null ;
                    else
                       return reqBody.getBytes(StandardCharsets.UTF_8);
                }

                @Override // bắt lỗi kết nối internet
                protected VolleyError parseNetworkError(VolleyError volleyError) {
//                    String response = "";
//                    if(volleyError != null)
//                        response =String.valueOf( volleyError.getMessage());

                    return super.parseNetworkError(volleyError);
                }
            };

            // add req vào quee
            requestQueue.add(req );

        }catch (JSONException e){
            e.printStackTrace(); // ghi ra log cấu trúc lỗi
        }


    }

    //========= retrofit

    void DemoGETRetrofit (){
        // tạo đối tượng chuyển đổi GSON
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        // sử dụng Interface
        UserInterface userInterface = retrofit.create(UserInterface.class);
        // tạo call
        Call< List<UserDTO> > objCall = userInterface.lay_danh_sach();
        // gọi get:
        objCall.enqueue(new Callback<List<UserDTO>>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, retrofit2.Response<List<UserDTO>> response) {

                if(response.isSuccessful()){
                    List<UserDTO> dsTK = response.body();
                    // bạn làm gì với cái list này thì tự bạn làm
                    Log.d("zzzzzz", "Ket qua: " + dsTK.size());

                }
                else{
                    Log.d("zzzz", "onResponse: lỗi " + response.errorBody() );
                }

            }
            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                Log.e("zzzzzzz", "onFailure: " + t.getMessage());
            }
        });

    }

    void DemoPOSTRetrofit (){

    }

}