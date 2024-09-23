package com.wesleytb.project_login.service;

import com.wesleytb.project_login.dto.UserPostDto;
import com.wesleytb.project_login.dto.UserPutDto;
import com.wesleytb.project_login.model.User;
import org.hibernate.service.spi.ServiceException;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getAllUsers() throws ServiceException;
    Optional<User> getUserById(Long id) throws ServiceException;
    UserPostDto createUser(UserPostDto newUser) throws ServiceException;
    Optional<UserPutDto> updateUserById(Long id, UserPutDto updateUser) throws ServiceException;
    boolean deleteUserById(Long id) throws ServiceException;
}
