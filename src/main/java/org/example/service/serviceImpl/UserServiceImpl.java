package org.example.service.serviceImpl;

import org.example.Exceptions.BadRequestException;
import org.example.Exceptions.UnauthorizedException;
import org.example.dto.UserDto;
import org.example.dto.UserDtoNewPassword;
import org.example.repos.UserRepo;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepo.findAll();
    }

    @Override
    public void authorise(UserDto userDto) {
        if (!userRepo.findAll().contains(userDto))
            throw new UnauthorizedException("Unauthorized");
    }

    @Override
    public void changePassword(UserDtoNewPassword userDtoNewPassword) {
        for (UserDto item : userRepo.findAll()) {
            if (Objects.equals(item.getUsername(), userDtoNewPassword.getUsername()) &&
                    Objects.equals(item.getPassword(), userDtoNewPassword.getPassword()) &&
                    !Objects.equals(userDtoNewPassword.getPassword(), userDtoNewPassword.getNewPassword())) {
                userRepo.changePassword(userDtoNewPassword);
                return;
            }
        }
        throw new BadRequestException("bad request");
    }

    @Override
    public void save(UserDto userDto) {
        for (UserDto item : userRepo.findAll()) {
            if (Objects.equals(item.getUsername(), userDto.getUsername()))
                throw new BadRequestException("bad request");
        }
        userRepo.saveUser(userDto);
    }
}
