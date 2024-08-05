package com.rahul.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean status;
    public ApiResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
}
