package org.example.service.serviceImpl;

import org.example.dto.UserPOJO;
import org.example.repos.UserRepo;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserPOJO> getAll() {
        ArrayList<UserPOJO> list = new ArrayList<UserPOJO>();
        for (UserPOJO item : userRepo.findAll()) {
            UserPOJO userPOJO = new UserPOJO(
                    item.getUsername(),
                    item.getPassword()
            );
            list.add(userPOJO);
        }
        return list;
    }

    @Override
    public UserPOJO getUserPOJOFromBody(String body) {
        String[] parts = body.split(" ");
        return new UserPOJO(parts[0], parts[1]);
    }

    @Override
    public boolean authorise(UserPOJO userPOJO) {
        return userRepo.findAll().contains(userPOJO);
    }

    @Override
    public boolean changePassword(String body) {
        String[] parts = body.split(" ");
        for (UserPOJO item : userRepo.findAll()) {
            if (Objects.equals(item.getUsername(), parts[0]) &&
                    Objects.equals(item.getPassword(), parts[1]) &&
                    !Objects.equals(parts[1], parts[2])) {
                item.setPassword(parts[2]);
                return true;
            }
        }
        return false;
    }

    @Override
    public void save(UserPOJO userPOJO) {
        userRepo.saveUser(userPOJO);
    }
}
