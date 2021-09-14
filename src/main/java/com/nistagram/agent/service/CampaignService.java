package com.nistagram.agent.service;

import com.nistagram.agent.client.PostClient;
import com.nistagram.agent.exception.CampaignException;
import com.nistagram.agent.exception.NotApprovedException;
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
import java.util.stream.Collectors;

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

    public Campaign createCampaign(@RequestBody CreateCampaignDTO createCampaignDTO) throws CampaignException, NotApprovedException {
        isApproved();
        CreatePostDTO createPostDTO = new CreatePostDTO(createCampaignDTO.getImageUrl(), new ArrayList<>());
        ResponseEntity<PostDTO> postDTO = postClient.createPost(createPostDTO, userService.getTokenString());
        if (postDTO.getStatusCode().equals(HttpStatus.OK)) {
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
        throw new CampaignException("Error saving campaign");
    }

    public List<Campaign> getCampaignsForUser() {
        String username = userService.getUsername();
        UserInfoDTO userInfoDTO = userService.getUserInfo(username);
        int years = getDiffYears(userInfoDTO.getDateOfBirth(), new Date());
        String interval = getCurrentInterval();
        List<Campaign> campaigns = campaignRepository.findAllCriteria(userInfoDTO.getGender(), "Both", years);
        campaigns = campaigns.stream().filter(campaign -> campaign.getIntervals().contains(interval)).collect(Collectors.toList());
        campaigns = campaigns.stream().limit(3).collect(Collectors.toList());
        return campaigns;
    }

    public List<Campaign> getAgentsCampaign() {
        String username = userService.getUsername();
        List<Campaign> campaigns = campaignRepository.findByAgentUsername(username);
        return campaigns;
    }

    public Boolean deleteCampaign(Long id) throws Exception {
        isApproved();
        campaignRepository.deleteById(id);
        return true;
    }

    private int getDiffYears(Date first, Date last) {
        Calendar a = Calendar.getInstance();
        a.setTime(first);
        Calendar b = Calendar.getInstance();
        b.setTime(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    private String getCurrentInterval() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 10) {
            return "INTERVAL0" + hour;
        }
        return "INTERVAL" + hour;
    }

    private boolean isApproved() throws NotApprovedException {
        UserInfoDTO userInfoDTO = userService.getUserInfo(userService.getUsername());
        if(userInfoDTO.getApprovedAgent()) {
            return true;
        }
        throw new NotApprovedException("You are not approved");
    }
}
