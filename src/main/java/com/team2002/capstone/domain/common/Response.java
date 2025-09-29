package com.team2002.capstone.domain.common;

import com.team2002.capstone.domain.enums.ResponseType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Builder // ⭐ 이 빌더를 사용합니다.
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;

    // 성공 응답 (데이터가 있는 경우)
    public static <T> Response<T> success(T data) {
        return Response.<T>builder()
                .code(ResponseType.SUCCESS.getCode())
                .message(ResponseType.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    // 성공 응답 (데이터가 없는 경우)
    public static Response<Void> success() {
        return Response.<Void>builder()
                .code(ResponseType.SUCCESS.getCode())
                .message(ResponseType.SUCCESS.getMessage())
                .build();
    }

    // 실패 응답 (메시지를 직접 입력)
    public static Response<Void> failure(int value, String message) {
        return Response.<Void>builder()
                .code(ResponseType.BAD_REQUEST.getCode()) // 400이 아닌 경우 변경 필요
                .message(message)
                .build();
    }
}