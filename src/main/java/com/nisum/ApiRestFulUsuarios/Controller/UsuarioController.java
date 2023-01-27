package com.nisum.ApiRestFulUsuarios.Controller;

import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserDelete;
import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserGet;
import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserPath;
import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserPost;
import com.nisum.ApiRestFulUsuarios.Model.User;
import com.nisum.ApiRestFulUsuarios.Service.IUsuariosSer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/user")
public class UsuarioController {

    private static final Logger logger = Logger.getLogger(UsuarioController.class);

    @Autowired
    private IUsuariosSer iUsuariosSer;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation("getUsuarios")
    public ResponseEntity<ResponseUserPost> createUsuario(@RequestBody @Valid User body
                                                          ) {
        logger.info("Inicamos la petición de guardado de ususario");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Baeldung-Example-Header","Value-ResponseEntityBuilderWithHttpHeaders");

        ResponseUserPost responseUserPost = iUsuariosSer.saveUsuarios(body);
        logger.info("Fin de la petición de guardado de ususario");
        return ResponseEntity.status(responseUserPost.getMetaData().getHttp_code())
                .headers(responseHeaders)
                .body(responseUserPost);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation("getUsuarios")
    public ResponseEntity<ResponseUserGet> getUsuarios() {
        logger.info("Inicamos la petición de guardado de ususario");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Baeldung-Example-Header","Value-ResponseEntityBuilderWithHttpHeaders");

        ResponseUserGet responseUserGet = iUsuariosSer.getUsuarios();
        logger.info("Fin de la petición de guardado de ususario");
        return ResponseEntity.status(responseUserGet.getMetaData().getHttp_code())
                .headers(responseHeaders)
                .body(responseUserGet);
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation("deleteUsuario")
    public ResponseEntity<ResponseUserDelete> deleteUsuario(@RequestParam String IDUser) {
        logger.info("Inicamos la petición deleteUsuario");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Baeldung-Example-Header","Value-ResponseEntityBuilderWithHttpHeaders");

        ResponseUserDelete responseUserDelete = iUsuariosSer.eliminarUsuario(IDUser);
        logger.info("Fin de la petición de guardado de ususario");
        return ResponseEntity.status(responseUserDelete.getMetaData().getHttp_code())
                .headers(responseHeaders)
                .body(responseUserDelete);
    }

    @PatchMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation("updateUsuario")
    public ResponseEntity<ResponseUserPath> updateUsuario(
            @RequestBody @Valid User body,
            @RequestParam String IDUser) {
        logger.info("Inicamos la petición de update de ususario");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Baeldung-Example-Header","Value-ResponseEntityBuilderWithHttpHeaders");

        ResponseUserPath responseUserPath = iUsuariosSer.updateUsuario(IDUser,body);
        logger.info("Fin de la petición de update de ususario");
        return ResponseEntity.status(responseUserPath.getMetaData().getHttp_code())
                .headers(responseHeaders)
                .body(responseUserPath);
    }

}
