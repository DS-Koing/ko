package com.example.koing;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private static UserRetrofitInterface userRetrofitInterface;
    private static CartRetrofitInterface  cartRetrofitInterface;
    private static MenuRetrofitInterface menuRetrofitInterface;
    private static S_MenuRetrofitInterface smenuRetrofitInterface;
    private static OrderRetrofitInterface orderRetrofitInterface;

    private static String baseUrl = "http://10.0.2.2:8080/"; //서버가 구동되고 있는 ip주소

    private RetrofitClient() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(baseUrl) //뒷부분을 쉽게 처리할 수 있도록 인터페이스 제공
                .addConverterFactory(GsonConverterFactory.create()) //서버로부터 데이터를 받아와서 원하는 타입으로 데이터를 바꾸기 위해 사용
                .build();
        userRetrofitInterface = retrofit.create(UserRetrofitInterface.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static UserRetrofitInterface getUserRetrofitInterface() {
        return userRetrofitInterface;
    }

    public static CartRetrofitInterface getCartRetrofitInterface() {
        return cartRetrofitInterface;
    }

    public static MenuRetrofitInterface getMenuRetrofitInterface() {
        return menuRetrofitInterface;
    }

    public static S_MenuRetrofitInterface getS_MenuRetrofitInterface() {
        return smenuRetrofitInterface;
    }

    public static OrderRetrofitInterface getOrderRetrofitInterface() {
        return orderRetrofitInterface;
    }
}
