package org.example.repos;

import org.example.dto.UserPOJO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepo {
    List<UserPOJO> db = new ArrayList<>();
    public void saveUser(UserPOJO userPOJO){
        db.add(userPOJO);
    }

    public List<UserPOJO> findAll(){
        return db;
    }
}
