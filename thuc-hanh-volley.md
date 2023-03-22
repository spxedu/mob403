Thực hành dụng volley

https://google.github.io/volley/

Link api demo: https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/users 


1. Tạo file layout

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/ed_content"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        app:layout_constraintTop_toTopOf="parent"
        />

    <Button
        android:id="@+id/btn_get_volley"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="GET Volley"
        app:layout_constraintHorizontal_bias="0.122"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/ed_content" />

    <Button
        android:id="@+id/btn_post_volley"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="POST Volley"
        app:layout_constraintHorizontal_bias="0.821"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/ed_content" />


</androidx.constraintlayout.widget.ConstraintLayout>
```

2. Ánh xạ view và tạo các hàm Get POST trong activity


```
package net.sdt.demovolleyretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    EditText ed_content;
    Button btn_get_volley, btn_post_volley;
    String url_api = "https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/users";
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
                DemoGetVolley();
            }
        });

        btn_post_volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DemoPostVolley();
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

    }
}


```
3. Vào file manifest khai bao quyền

```
    <uses-permission android:name="android.permission.INTERNET" />

```
trong thẻ <application khai báo thêm thuộc tính
```
android:usesCleartextTraffic="true"
```





