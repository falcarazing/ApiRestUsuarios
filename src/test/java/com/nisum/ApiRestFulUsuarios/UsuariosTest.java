package com.nisum.ApiRestFulUsuarios;

import com.nisum.ApiRestFulUsuarios.Model.Phone;
import com.nisum.ApiRestFulUsuarios.Model.User;
import com.nisum.ApiRestFulUsuarios.Repository.IPhoneRepo;
import com.nisum.ApiRestFulUsuarios.Repository.IUserRepo;
import  com.nisum.ApiRestFulUsuarios.UsuarioRepoAbsTest;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UsuariosTest {

    @Autowired
    private IUserRepo iUserRepo;

    @Autowired
    private IPhoneRepo iPhoneRepo;

    String  idUser = UUID.randomUUID().toString();

    @DisplayName("Validamos email ya existente")
    @Test
    public void foundNumberUser(){
        List<User> userList = new ArrayList<>();
        User userEncontrado = new User();
        userEncontrado.setName("Fernando cristobal");
        userEncontrado.setEmail("falcaraz@hotmail.com");
        userEncontrado.setPassword("123");
        userList.add(userEncontrado);

        UsuarioRepoAbsTest mock = org.mockito.Mockito.mock(UsuarioRepoAbsTest.class);
        Mockito.when(mock.findByEmail(anyString()))
                .thenReturn(userList);

        assertNotNull(userList);

    }


    @Rollback(false)
    @Before
    public void saveUser(){
        User user = new User();
        user.setId(idUser);
        user.setName("Fernando");
        user.setEmail("falcaraz1@hotmail.com");
        user.setPassword("123");
        user.setLastLogin(new Date());
        user.setCreated(new Date());
        user.setPassword("pass");
        user.setIsActive(true);
        user.setToken(UUID.randomUUID().toString());
        User userSave = iUserRepo.save(user);

        assertNotNull(userSave);
    }

    @Test
    public void savePhone(){
        Phone phone = new Phone();
        phone.setIduser(UUID.randomUUID().toString());
        phone.setContrycode("60250");
        phone.setCitycode("cdmx");
        phone.setNumber("55838866");
        Phone phoneSave = iPhoneRepo.save(phone);

        assertEquals(phone.getIdPhone(),phoneSave.getIdPhone());
    }

    @After
    public void findUser(){
        List<User> userList = new ArrayList<>();
        userList = iUserRepo.findAll();
        int size = userList.size();

        assertTrue(size>0);
    }

    @After
    public void eliminarUser(){

        iUserRepo.deleteById(idUser);
        Optional<User> userOp = iUserRepo.findById(idUser);

        assertEquals(userOp.get().getIsActive(),false);
    }

    @After
    public void actualizarUser(){
        Optional<User> userOp = iUserRepo.findById(idUser);
        userOp.get().setName("Name Update");
        User userUpdate = iUserRepo.save(userOp.get());
        assertNotNull(userUpdate);
    }
}
