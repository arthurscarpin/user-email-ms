package com.github.arthurscarpin.user.service;

import com.github.arthurscarpin.user.exception.ResourceNotFoundException;
import com.github.arthurscarpin.user.model.User;
import com.github.arthurscarpin.user.producer.UserProducer;
import com.github.arthurscarpin.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserProducer producer;

    @Transactional
    public User saveAndPublish(User user) {
        user = repository.save(user);
        producer.publishEvent(user);
        return user;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found! Id: " + id));
    }

    public User updateById(UUID id, User dto) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found! Id: " + id));
        user.setId(id);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return repository.save(user);
    }

    public void deleteById(UUID id) {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found! Id: " + id));
        repository.deleteById(id);
    }
}
