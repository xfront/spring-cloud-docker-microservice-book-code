package cn.eshop.cloud.controller;

import cn.eshop.cloud.repository.UserRepository;
import cn.eshop.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        User findOne = this.userRepository.findOne(id);
        return findOne;
    }

    @GetMapping("/get")
    public User get(User user) {
        return user;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        return user;
    }
}
