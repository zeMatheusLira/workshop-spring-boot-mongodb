package com.matheus.workshopmongo.service;

import com.matheus.workshopmongo.domain.Post;
import com.matheus.workshopmongo.domain.User;
import com.matheus.workshopmongo.repository.PostRepository;
import com.matheus.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> Post = postRepository.findById(id);
        return Post.orElseThrow(() -> new ObjectNotFoundException("Object Not Found"));

    }
}
