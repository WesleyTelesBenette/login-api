package com.wesleytb.project_login.service;

import com.wesleytb.project_login.dto.UserPostDto;
import com.wesleytb.project_login.dto.UserPutDto;
import com.wesleytb.project_login.model.User;
import com.wesleytb.project_login.repository.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcrypt;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcrypt) {
        this.userRepository = userRepository;
        this.bcrypt = bcrypt;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return this.userRepository.findAll();
        }
        catch (DataAccessException e) {
            throw new ServiceException("Erro ao buscar usuários", e);
        }
        catch (Exception e) {
            throw new ServiceException("Erro INESPERADO ao buscar usuários.", e);
        }
    }

    @Override
    public Optional<User> getUserById(Long id) throws ServiceException {
        try {
            return this.userRepository.findById(id);
        }
        catch (DataAccessException e) {
            throw new ServiceException(String.format("Erro ao buscar o usuário %d.", id), e);
        }
        catch (IllegalArgumentException e) {
            throw new ServiceException(String.format("O ID de usuário: %d, é inválido.", id), e);
        }
        catch (Exception e) {
            throw new ServiceException(String.format("Erro INESPERADO ao buscar o usuário %d.", id), e);
        }
    }

    @Override
    public UserPostDto createUser(UserPostDto user) throws ServiceException {
        try {
            User newUserToSave = new User(user);
            newUserToSave.setPassword(bcrypt.encode(newUserToSave.getPassword()));

            User savedUser = this.userRepository.save(newUserToSave);
            return new UserPostDto(savedUser);
        }
        catch (DataAccessException e) {
            throw new ServiceException("Erro ao criar usuário.", e);
        }
        catch (Exception e) {
            throw new ServiceException("Erro INESPERADO ao criar usuário,", e);
        }
    }

    @Override
    public Optional<UserPutDto> updateUserById(Long id, UserPutDto user) throws ServiceException {
        try {
            return this.userRepository.findById(id)
                .map(userFound -> {
                    userFound.updateData(user);
                    User updatedUser = this.userRepository.save(userFound);
                    return new UserPutDto(updatedUser);
                });
        }
        catch (DataAccessException e) {
            throw new ServiceException(String.format("Erro ao atualizar o usuário %d.", id), e);
        }
        catch (IllegalArgumentException e) {
            throw new ServiceException(String.format("O ID de usuário: %d, é inválido.", id), e);
        }
        catch (Exception e) {
            throw new ServiceException(String.format("Erro INESPERADO ao atualizar o usuário %d.", id), e);
        }
    }

    @Override
    public boolean deleteUserById(Long id) throws ServiceException {
        try {
            Optional<User> userFound = this.userRepository.findById(id);

            userFound.ifPresent(this.userRepository::delete);
            return userFound.isPresent();
        }
        catch (DataAccessException e) {
            throw new ServiceException(String.format("Erro ao deletar o usuário %d.", id), e);
        }
        catch (IllegalArgumentException e) {
            throw new ServiceException(String.format("O ID de usuário: %d, é inválido.", id), e);
        }
        catch (Exception e) {
            throw new ServiceException(String.format("Erro INESPERADO ao deletar o usuário %d.", id), e);
        }
    }
}