package com.nisum.ApiRestFulUsuarios.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.ApiRestFulUsuarios.Dto.MetaData;
import lombok.Data;

@Data
public class ResponseUserDelete {
    @JsonProperty("metadata")
    private MetaData metaData;

}
