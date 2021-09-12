package com.nistagram.agent.converter;

import com.nistagram.agent.model.dto.CampaignDTO;
import com.nistagram.agent.model.entity.Campaign;

public class CampaignConverter {

    public static CampaignDTO toDTO(Campaign campaign) {
        CampaignDTO dto = new CampaignDTO();
        dto.setCriteria(campaign.getCriteria());
        dto.setAgentUsername(campaign.getAgentUsername());
        dto.setDatePosted(campaign.getDatePosted());
        dto.setStartDate(campaign.getStartDate());
        dto.setEndDate(campaign.getEndDate());
        dto.setId(campaign.getId());
        dto.setImageUrl(campaign.getImageUrl());
        dto.setIntervals(campaign.getIntervals());
        dto.setLink(campaign.getLink());
        dto.setPostId(campaign.getPostId());
        return dto;
    }
}
