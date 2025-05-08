package com.matheus.workshopmongo.service;

import com.matheus.workshopmongo.domain.User;
import com.matheus.workshopmongo.dto.UserDTO;
import com.matheus.workshopmongo.repository.UserRepository;
import com.matheus.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Object Not Found"));

    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public User update(User user) {
        User newUser = userRepository.findById(user.getId()).orElseThrow(() -> new ObjectNotFoundException("Object Not Found"));
        updateData(newUser,user);
        return userRepository.save(newUser);
    }

    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }

    public User fromDTO(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
     }
}
