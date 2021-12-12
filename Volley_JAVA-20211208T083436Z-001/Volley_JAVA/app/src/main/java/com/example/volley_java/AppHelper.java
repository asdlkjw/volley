package com.example.volley_java;
import com.android.volley.RequestQueue;

//application 클래스를 정의하고 앱에 등록하여 사용하는 경우에는 이 Application 클래스 안에 넣어둘 수도 있고
// AppHelper와 같은 별도의 클래스를 만들어 그 안에 넣어둘 수도 있다. 후자 사용.
public class AppHelper {
    public static RequestQueue requestQueue;

    //요청(Request) 객체를 만들고, 요청 객체를 요청 큐(RequestQueue)에 넣으면 됨.
    //그러면 RequestQueue가 알아서 웹서버에 요청하고 응답까지 받아 사용자가 사용할 수 있도록 지정된 메소드를 호출해 줌.
}