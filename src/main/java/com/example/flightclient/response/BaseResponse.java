package com.example.flightclient.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private boolean status;
    private String statusText;
}
