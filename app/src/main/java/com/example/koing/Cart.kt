package com.example.koing

//전송 받은 데이터를 저장할 객체 클래스 (Cart)
class Cart
    (
    var Cart_CD: Int, //메뉴 코드(PK)
    var CUser_CD: Int, //유저 코드(FK)
    var CSMenu_CD: Int, //음식 코드(FK)
    var Cart_CT: Int //메뉴 개수
) {
    //setter

    //getter
}