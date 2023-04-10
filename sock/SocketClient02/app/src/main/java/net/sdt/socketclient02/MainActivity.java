package net.sdt.socketclient02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://10.82.1.113:3000");
        } catch (URISyntaxException e) {}
    }

    private EditText mInputMessageView;
    Button btn_send ;
    TextView tv_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSocket.on("new message", onNewMessage); //nhận dữ liệu server gửi xuống

        mSocket.connect();


        btn_send = findViewById(R.id.btn_send);
        mInputMessageView = findViewById(R.id.mInputMessageView);
        tv_show = findViewById(R.id.tv_show);
//        mSocket.on("new message", onNewMessage);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mInputMessageView.getText().toString().trim();
                if (TextUtils.isEmpty(message)) {
                    return;
                }

                mInputMessageView.setText("");

                mSocket.emit("new message", message);
            }
        });



    }


    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("zzzzzz", "run: " + args[0]);
                    try {
                        JSONObject data = (JSONObject) args[0];
                        String username;
                        String message;
                        username = data.getString("username");
                        message = data.getString("message");

                        tv_show.setText(  tv_show.getText().toString() + "\n" +    username +":" + message);
                    } catch (JSONException e) {
                        return;
                    }


                }
            });
        }
    };

}