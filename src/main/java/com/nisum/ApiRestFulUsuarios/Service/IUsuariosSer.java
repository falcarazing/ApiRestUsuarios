package com.nisum.ApiRestFulUsuarios.Service;

import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserDelete;
import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserGet;
import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserPath;
import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserPost;
import com.nisum.ApiRestFulUsuarios.Model.User;

public interface IUsuariosSer {
    ResponseUserPost saveUsuarios(User user);
    ResponseUserGet getUsuarios();
    ResponseUserPath updateUsuario(String idUser, User user);
    ResponseUserDelete eliminarUsuario(String idUser);

}
