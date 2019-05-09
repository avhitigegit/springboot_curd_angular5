package com.example.demo.serviceImpl;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserAlreadyExist;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    //Find All Users
    @Override
    public List<UserDto> findAll() {
        UserDto userDto = null;
        List<UserDto> userDtoList = null;

        try {
            List<User> userList = userRepository.findAll();
            if (userList != null) {
                for (User user : userList) {
                    BeanUtils.copyProperties(user, userDto);
                    userDtoList.add(userDto);
                }
            } else {
                System.out.println("Unable to reviewed any user from database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDtoList;
    }

    //Find A User By ID
    @Override
    public UserDto findOne(Long id) {
        UserDto userDto = null;
        User user = null;

        try {
            user = userRepository.findByUserId(id);
            BeanUtils.copyProperties(user, userDto);
        } catch (UsernameNotFoundException e) {
            e.getMessage();
        }
        return userDto;
    }

    //Create A User
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = new User();

        try {
            BeanUtils.copyProperties(userDto, user);
            user = userRepository.saveAndFlush(user);
            BeanUtils.copyProperties(user, userDto);
        } catch (UserAlreadyExist e) {
            e.printStackTrace();
        }
        return userDto;
    }

    //Update The User
    @Override
    public UserDto updateUser(UserDto userDto) {
        User user;
        try {
            user = userRepository.findByUserId(userDto.getId());

            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmailAddress(userDto.getEmailAddress());

            user = userRepository.saveAndFlush(user);
            BeanUtils.copyProperties(user, userDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDto;
    }

    //Delete The User
    @Override
    public void delete(UserDto userDto) {
        userRepository.deleteById(userDto.getId());
    }

}
