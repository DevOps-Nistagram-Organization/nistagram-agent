package com.nistagram.agent.service;

import com.nistagram.agent.client.PostClient;
import com.nistagram.agent.model.dto.CreateCampaignDTO;
import com.nistagram.agent.model.dto.CreatePostDTO;
import com.nistagram.agent.model.dto.PostDTO;
import com.nistagram.agent.model.dto.UserInfoDTO;
import com.nistagram.agent.model.entity.Campaign;
import com.nistagram.agent.model.entity.Criteria;
import com.nistagram.agent.repository.CampaignRepository;
import com.nistagram.agent.repository.CriteriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final UserService userService;
    private final PostClient postClient;
    private final CriteriaRepository criteriaRepository;

    public CampaignService(CampaignRepository campaignRepository, UserService userService, PostClient postClient, CriteriaRepository criteriaRepository) {
        this.campaignRepository = campaignRepository;
        this.userService = userService;
        this.postClient = postClient;
        this.criteriaRepository = criteriaRepository;
    }

    public Campaign createCampaign(@RequestBody CreateCampaignDTO createCampaignDTO) throws Exception {

        CreatePostDTO createPostDTO = new CreatePostDTO(createCampaignDTO.getImageUrl(), new ArrayList<>());
        ResponseEntity<PostDTO> postDTO = postClient.createPost(createPostDTO, userService.getTokenString());
        if(postDTO.getStatusCode().equals(HttpStatus.OK)) {
            PostDTO post = postDTO.getBody();
            String username = userService.getUsername();
            Criteria criteria = criteriaRepository.save(createCampaignDTO.getCriteria());
            Campaign campaign = new Campaign(null,
                    post.getId(),
                    username,
                    createCampaignDTO.getLink(),
                    createCampaignDTO.getImageUrl(),
                    new Date(),
                    createCampaignDTO.getStartDate(),
                    createCampaignDTO.getEndDate(), criteria, new HashSet<>(createCampaignDTO.getIntervals()));
            return campaignRepository.save(campaign);
        }
        throw new Exception("Error saving campaign");
    }

}
