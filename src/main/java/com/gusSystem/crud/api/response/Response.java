package com.gusSystem.crud.api.response;

import lombok.Data;

import java.util.List;

@Data
public class Response<T> {

    private T data;
    private List<String> erros;

    public Response(T data){
        this.data = data;
    }

    public Response(List<String> erros){
        this.erros = erros;
    }
}
