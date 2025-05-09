package com.matheus.workshopmongo.dto;

import com.matheus.workshopmongo.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class AuthorDTO implements Serializable {
    private String id;
    private String Name;

    public AuthorDTO(User obj) {
        id = obj.getId();
        Name = obj.getName();
    }
}

