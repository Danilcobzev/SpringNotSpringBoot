package org.example.service.serviceImpl;

import org.example.Exceptions.BadRequestException;
import org.example.Exceptions.UnauthorizedException;
import org.example.dto.UserDto;
import org.example.repos.UserRepo;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<UserDto> getAll() {
        ArrayList<UserDto> list = new ArrayList<UserDto>();
        for (UserDto item : userRepo.findAll()) {
            UserDto userDto = new UserDto(
                    item.getUsername(),
                    item.getPassword()
            );
            list.add(userDto);
        }
        return list;
    }

    @Override
    public UserDto getUserPOJOFromBody(String body) {
        String[] parts = body.split(" ");
        return new UserDto(parts[0], parts[1]);
    }

    @Override
    public void authorise(UserDto userDto) {
        if(!userRepo.findAll().contains(userDto))
            throw new UnauthorizedException("Unauthorized");
    }

    @Override
    public void changePassword(String body) {
        String[] parts = body.split(" ");
        for (UserDto item : userRepo.findAll()) {
            if (Objects.equals(item.getUsername(), parts[0]) &&
                    Objects.equals(item.getPassword(), parts[1]) &&
                    !Objects.equals(parts[1], parts[2])) {
                item.setPassword(parts[2]);
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
