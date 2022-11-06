package com.example.koing

import java.time.LocalDateTime

//전송 받은 데이터를 저장할 객체 클래스 (User)
class User
    (
    var User_CD: Int, //유저 코드(PK)
    var User_NM: String, //유저 이름
    var User_NKNM: String, //유저 닉네임
    var User_PW: String, //유저의 비밀번호
    var User_EM: String, //유저의 덕성 이메일
    var CREATE_DATE: LocalDateTime, //생성 날짜
    var User_ID: String //유저의 속성 여부
) {
    //setter

    //getter
}