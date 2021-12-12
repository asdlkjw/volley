package com.example.volley_java;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
/*
<< Volley 라이브러리를 이용해 HTTP로 요청 하고 응답 받기 >>

requestQueue가(Volley라이브러리에서 제공) 스레드를 알아서 처리해주기 때문에
사용자가 스레드나 핸들러를 직접적으로 처리할 필요가 없어 코드양이 줄어들고 편리

사용을 위해서는
1. gradle에 Volley라이브러리 추가
2. manifest에서는 인터넷권한 추가
* */

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
                System.out.println("##########onClick");
            }
        });

        if(AppHelper.requestQueue == null) {
            //리퀘스트큐 생성. MainActivit가 메모리에서 만들어질 때 같이 생성 됨.
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void sendRequest() {
        String url = "http://3.38.115.198:8081/image";
//        String url = "http://3.38.115.198:8081";
//        String url = "https://www.google.com";

        //StringRequest 선언.
        //요청객체는 보내는방식(GET,POST), URL, 응답성공리스너, 응답실패리스너 이렇게 4개의 파라미터를 전달할 수 있다.
        //화면에 결과를 표시할때 핸들러를 사용하지 않아도 됨.
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() { //응답을 문자열로 받아옴(응답을 성공적으로 받았을 때 자동으로 호출되는 메소드.)
                    @Override
                    public void onResponse(String response) {
                        println("응답 => " + response); //코틀린 아님!! 메소드에 문자열을 파라메터로 전달해서 텍뷰에 띄움.
                    }
                },
                new Response.ErrorListener(){ //에러발생시 호출될 리스너 객체
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 => "+ error.getMessage()); //코틀린 아님!! 메소드에 문자열을 파라메터로 전달해서 텍뷰에 띄움.
                    }
                }
        ){
            //만약 POST 방식에서 전달할 요청 파라미터가 있다면, getParams 메소드가 리턴하는 HashMap 객체에 넣어줄 것.
            //이렇게 만든 요청 객체(StringRequest request)를 요청 큐(requestQueue)에 넣어주기만 하면 됨.
            //POST방식을 이용할 때만 필요한 듯.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("num", "heo hyun jun babo post");
                return params;
            }
        };

        //아래 add코드처럼 넣어줄때 Volley가 내부에서 캐싱을 해주기 때문에 한 번 보내고 받은 응답결과가 있으면
        //그 다음에 보냈을 떄 이전 것을 보여줄 수도 있다.
        //따라서 매번 받은 결과를 그대로 보여주기 위해 다음과 같이 setShouldCache를 false로 설정.
        //결과적으로 이전 결과가 있어도 새로 요청한 응답을 보여줌
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        println("요청 보냄!!");
    }

    public void println(String data){
        System.out.println(data);
        textView.append(data + "\n");
    }
}