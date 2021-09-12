package com.nistagram.agent.client;

import com.nistagram.agent.model.dto.CreatePostDTO;
import com.nistagram.agent.model.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${api.postService}", name = "PostService")
public interface PostClient {
    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PostDTO> createPost(@RequestBody CreatePostDTO dto, @RequestHeader("Authorization") String bearerToken);
}
