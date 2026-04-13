package com.github.arthurscarpin.user.controller;

import com.github.arthurscarpin.user.docs.UserControllerDoc;
import com.github.arthurscarpin.user.dto.UserDTO;
import com.github.arthurscarpin.user.model.User;
import com.github.arthurscarpin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController implements UserControllerDoc {

    private final UserService service;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDTO dto) {
        var user = new User();
        BeanUtils.copyProperties(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAndPublish(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateById(@PathVariable UUID id, @RequestBody UserDTO dto) {
        var user = new User();
        BeanUtils.copyProperties(dto, user);
        return ResponseEntity.ok(service.updateById(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
