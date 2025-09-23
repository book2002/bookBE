package com.team2002.capstone.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {;

    /*ID_DUPLICATION("ID_001", "이미 존재하는 아이디입니다.", 400),
    ID_NOT_FOUND("ID_002", "존재하지 않는 아이디입니다.", 404),
    EMAIL_DUPLICATION("EMAIL_001", "이미 존재하는 이메일입니다.", 400),
    EMAIL_NOT_FOUND("EMAIL_002", "존재하지 않는 이메일입니다.", 404),
    INPUT_VALUE_INVALID("INPUT_001", "입력값이 올바르지 않습니다.", 400);*/

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
