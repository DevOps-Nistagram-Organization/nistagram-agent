package com.nistagram.agent.client;

import com.nistagram.agent.model.dto.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${api.userService}", name = "UserService")
public interface UserClient {
    @GetMapping(value = "getUser/{username}")
    UserInfoDTO getUser(@PathVariable("username") String username);
}
