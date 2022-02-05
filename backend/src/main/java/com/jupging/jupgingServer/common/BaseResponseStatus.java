package com.jupging.jupgingServer.common;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),
    POST_USERS_GOOGLE_ERROR(false, 2011, "구글 로그인에 실패했습니다."),


    /**
     * 3000 : Response 오류
     */
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),


    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),
    DUPLICATE_KEY_ERROR(false, 4002, "이미 존재하는 row입니다."),
    EMPTY_USER(false, 4003, "user를 찾지 못했습니다."),


    /**
     * 5000 : File 오류
     */
    FILE_CONVERT_ERROR(false, 5000, "file 변환에 실패하였습니다."),
    FILE_EMPTY(false, 5001, "file이 없습니다."),
    GCS_ERROR(false, 5002, "GCS통신 오류"),
    FILE_EXTENSION_ERROR(false, 5003, "file 확장자를 확인해주세요.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}

