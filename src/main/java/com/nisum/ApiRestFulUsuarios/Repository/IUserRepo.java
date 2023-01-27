package com.nisum.ApiRestFulUsuarios.Repository;

import com.nisum.ApiRestFulUsuarios.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepo extends JpaRepository<User,String> {

        List<User> findByEmail(String email);

}
