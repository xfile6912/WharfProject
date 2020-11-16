package com.example.ulsan.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {
    private LocalDateTime transactionTime;//통신시간
    private String resultCode;//응답코드
    private String description;//부가설명
    private T data;
    private Pagination pagination;

    public static<T> Header<T> OK()
    {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }
    public static<T> Header<T> OK(T data)//data가 있을 때
    {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }
    public static<T> Header<T> OK(T data, Pagination pagination)//data가 있을 때
    {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .pagination(pagination)
                .build();
    }
    public static<T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}
