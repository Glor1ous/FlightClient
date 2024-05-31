package com.example.flightclient.response;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.List;

@Getter
@Setter


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse<T> {
    private boolean status;
    private String statusText;
    private List<T> data;
}
