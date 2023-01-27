package com.nisum.ApiRestFulUsuarios.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.ApiRestFulUsuarios.Dto.MetaData;
import com.nisum.ApiRestFulUsuarios.Model.User;
import lombok.Data;

import java.util.List;

@Data
public class ResponseUserGet {
    @JsonProperty("metadata")
    private MetaData metaData;

    @JsonProperty("data")
    private List<User> data;
}
