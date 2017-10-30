package cn.eshop.cloud.user.service;

import cn.eshop.cloud.user.entity.User;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    public User findById(Long id) {
        throw new HystrixBadRequestException("business exception happens.", new Exception(""));
    }
}
