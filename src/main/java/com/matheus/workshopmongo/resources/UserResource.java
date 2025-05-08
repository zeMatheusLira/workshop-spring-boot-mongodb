package com.matheus.workshopmongo.resources;

import com.matheus.workshopmongo.domain.User;
import com.matheus.workshopmongo.dto.UserDTO;
import com.matheus.workshopmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
       List<User> usersList = userService.findAll();
       List<UserDTO> listDTO = usersList.stream()
               .map(UserDTO::new)
               .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User userById = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(userById));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        User obj = userService.fromDTO(userDTO);
        obj = userService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
        User obj = userService.fromDTO(userDTO);
        obj.setId(id);
        obj = userService.save(obj);
        return ResponseEntity.noContent().build();
    }


}
