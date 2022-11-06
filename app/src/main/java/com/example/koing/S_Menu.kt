package com.example.koing

//전송 받은 데이터를 저장할 객체 클래스 (S_Menu)
class S_Menu
    (
    var SMenu_CD: Int, //음식 코드(PK)
    var MMenu_CD: Int, //메뉴 코드(FK)
    var SMenu_PR: Int, //음식 가격
    var SMenu_NM: String, //음식 이름
    var SMenu_CH: Int //음식 개수
) {
    //setter

    //getter
}