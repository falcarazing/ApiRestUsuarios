package com.nisum.ApiRestFulUsuarios.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.ApiRestFulUsuarios.Dto.MetaData;
import com.nisum.ApiRestFulUsuarios.Dto.Request.UserDto;
import lombok.Data;

@Data
public class ResponseUserPost {

    @JsonProperty("metadata")
    private MetaData metaData;

    @JsonProperty("data")
    private UserDto data;

}
