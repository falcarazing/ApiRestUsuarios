package com.nisum.ApiRestFulUsuarios.Service;

import com.nisum.ApiRestFulUsuarios.Dto.MetaData;
import com.nisum.ApiRestFulUsuarios.Model.Phone;
import com.nisum.ApiRestFulUsuarios.Security.JwtTokenProvider;
import com.nisum.ApiRestFulUsuarios.Dto.Request.UserDto;
import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserDelete;
import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserGet;
import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserPath;
import com.nisum.ApiRestFulUsuarios.Dto.Response.ResponseUserPost;
import com.nisum.ApiRestFulUsuarios.Model.User;
import com.nisum.ApiRestFulUsuarios.Repository.IUserRepo;
import com.nisum.ApiRestFulUsuarios.Repository.IPhoneRepo;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuariosSerImp implements IUsuariosSer{
    private static final Logger logger = Logger.getLogger(UsuariosSerImp.class);

    @Autowired
    private IUserRepo iUserRepo;

    @Autowired
    private IPhoneRepo iPhoneRepo;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseUserPost saveUsuarios(User user) {
        logger.info("Iniciando metodo saveUsuarios");
        ResponseUserPost responseUserPost = new ResponseUserPost();
        MetaData metaData = new MetaData();
        UserDto userDto = new UserDto();
        try {
            logger.info("Validamos email");
            List<User> userList = iUserRepo.findByEmail(user.getEmail());
            if(userList == null || userList.size() <= 0) { //No encontramos coincidencias de Email
                user.setId(this.generarUUID());
                user.setCreated(new Date());
                user.setLastLogin(new Date());
                user.setToken(jwtTokenProvider.generateToken(user.getEmail()));
                user.setIsActive(true);

                iUserRepo.save(user);
                //Guardamos phone
                for(Phone data: user.getPhones()){
                    data.setIduser(user.getId());
                }
                iPhoneRepo.saveAll(user.getPhones());
                logger.info("Consultamos el usuario creado");
                Optional<User> usuarioEncontrado = iUserRepo.findById(user.getId());
                if (usuarioEncontrado.get() != null) {
                    userDto.setId(usuarioEncontrado.get().getId());
                    userDto.setCreated(usuarioEncontrado.get().getCreated());
                    userDto.setModified(usuarioEncontrado.get().getModified());
                    userDto.setLastLogin(usuarioEncontrado.get().getLastLogin());
                    userDto.setToken(usuarioEncontrado.get().getToken());
                    userDto.setIsActive(usuarioEncontrado.get().getIsActive());
                } else {
                    logger.error("Ocurrio un error al buscar el usuario guardado: ");
                    metaData.setMessage("Ocurrio un error al buscar el usuario guardado");
                    metaData.setStatus("Faile");
                    metaData.setHttp_code(404);
                }
            }else {
                logger.error("El email proporcionado ya existe: ");
                metaData.setMessage("El email proporcionado ya existe");
                metaData.setStatus("Faile");
                metaData.setHttp_code(500);
                userDto=null;
            }
        }catch (Exception ex){
            logger.error("Ocurrio un error al guardar usuario: " + ex.getMessage());
            metaData.setMessage("Ocurrio un error al guardar usuario");
            metaData.setStatus("Faile");
            metaData.setHttp_code(500);
            userDto=null;
        }
        responseUserPost.setMetaData(metaData);
        responseUserPost.setData(userDto);
        logger.info("Fin de metodo saveUsuarios");
        return responseUserPost;
    }

    @Override
    public ResponseUserGet getUsuarios() {
        logger.info("Iniciando metodo getUsuarios");
        MetaData metaData = new MetaData();
        ResponseUserGet responseUserGet = new ResponseUserGet();
        List<User> userList = new ArrayList<>();
        try{
            userList = iUserRepo.findAll();
            if(userList == null || userList.size() <=0){
                logger.error("No se encontro resultados: ");
                metaData.setMessage("No se encontro resultados");
                metaData.setStatus("Faile");
                metaData.setHttp_code(404);
            }else{
                for(User data:userList){
                    List<Phone> phoneList = iPhoneRepo.findByIduser(data.getId());
                    if(phoneList != null && phoneList.size() > 0){
                        data.setPhones(phoneList);
                    }
                }
            }
        }catch (Exception ex){
            logger.error("Ocurrio un error al obtener usuarios: " + ex.getMessage());
            metaData.setMessage("Ocurrio un error al obtener usuarios");
            metaData.setStatus("Faile");
            metaData.setHttp_code(500);
        }
        responseUserGet.setMetaData(metaData);
        responseUserGet.setData(userList);
        logger.info("Fin metodo getUsuarios");
        return responseUserGet;
    }

    @Override
    public ResponseUserPath updateUsuario(String idUser,User user) {
        logger.info("Se inicia metodo updateUsuario");
        ResponseUserPath responseUserPath = new ResponseUserPath();
        MetaData metaData = new MetaData();
        UserDto userDto = new UserDto();
        try {
            logger.info("Validamos email");
            List<User> userList = iUserRepo.findByEmail(user.getEmail());
            if(userList == null || userList.size() <= 0) { //No encontramos coincidencias de Email
                logger.info("Consultamos el usuario");
                Optional<User> usuarioEncontrado = iUserRepo.findById(idUser);
                if (usuarioEncontrado.get() == null) {
                    logger.error("No se encontro resultados: ");
                    metaData.setMessage("No se encontro resultados");
                    metaData.setStatus("Faile");
                    metaData.setHttp_code(404);
                } else {
                    logger.info("Actualizamos el ususario");
                    usuarioEncontrado.get().setName(user.getName());
                    usuarioEncontrado.get().setPassword(user.getPassword());
                    usuarioEncontrado.get().setEmail(user.getEmail());
                    usuarioEncontrado.get().setLastLogin(new Date());
                    usuarioEncontrado.get().setModified(new Date());
                    iUserRepo.save(usuarioEncontrado.get());
                }
            }else {
                logger.error("El email a actualizar ya existe: ");
                metaData.setMessage("El email a actualizar ya existe");
                metaData.setStatus("Faile");
                metaData.setHttp_code(500);
            }
        }catch (Exception ex){
            logger.error("Ocurrio un error al update usuarios: " + ex.getMessage());
            metaData.setMessage("Ocurrio un error al update usuarios");
            metaData.setStatus("Faile");
            metaData.setHttp_code(500);
        }
        responseUserPath.setMetaData(metaData);
        logger.info("Se termina metodo updateUsuario");
        return responseUserPath;
    }

    @Override
    public ResponseUserDelete eliminarUsuario(String idUser) {
        logger.info("Se inicia metodo eliminarUsuario");
        ResponseUserDelete responseUserDelete = new ResponseUserDelete();
        MetaData metaData = new MetaData();
        UserDto userDto = new UserDto();
        try {
            logger.info("Consultamos el usuario");
            Optional<User> usuarioEncontrado = iUserRepo.findById(idUser);
            if (usuarioEncontrado.get() == null) {
                logger.error("No se encontro resultados: ");
                metaData.setMessage("No se encontro resultados");
                metaData.setStatus("Faile");
                metaData.setHttp_code(404);
            }else {
                logger.info("Eliminamos de manera logica el usuario/Se actualiza la bandera isActive=false");
                usuarioEncontrado.get().setIsActive(false);
                iUserRepo.save(usuarioEncontrado.get());
            }

        }catch (Exception ex){
            logger.error("Ocurrio un error al obtener usuarios: " + ex.getMessage());
            metaData.setMessage("Ocurrio un error al obtener usuarios");
            metaData.setStatus("Faile");
            metaData.setHttp_code(500);
        }
        responseUserDelete.setMetaData(metaData);
        logger.info("Se termina metodo eliminarUsuario");
        return responseUserDelete;
    }

    private String generarUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
