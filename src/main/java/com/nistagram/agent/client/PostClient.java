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

// app.post.url from application.properties
@FeignClient(name = "post", url = "${app.post.url}")
public interface PostClient {
    @PostMapping(value = "post/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PostDTO> createPost(@RequestBody CreatePostDTO dto, @RequestHeader("Authorization") String bearerToken);
}
