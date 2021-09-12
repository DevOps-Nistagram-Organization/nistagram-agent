package com.nistagram.agent.model.dto;

import com.nistagram.agent.model.entity.Criteria;
import com.nistagram.agent.model.entity.DateInterval;

import java.util.Date;
import java.util.Set;

public class CampaignDTO {
    private Long id;
    private Long postId;
    private String agentUsername;
    private String link;
    private String imageUrl;
    private Date datePosted;
    private Date startDate;
    private Date endDate;
    private Criteria criteria;
    private Set<String> intervals;

    public CampaignDTO() {
    }

    public CampaignDTO(Long id, Long postId, String agentUsername, String link, String imageUrl, Date datePosted, Date startDate, Date endDate, Criteria criteria, Set<String> intervals) {
        this.id = id;
        this.postId = postId;
        this.agentUsername = agentUsername;
        this.link = link;
        this.imageUrl = imageUrl;
        this.datePosted = datePosted;
        this.startDate = startDate;
        this.endDate = endDate;
        this.criteria = criteria;
        this.intervals = intervals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getAgentUsername() {
        return agentUsername;
    }

    public void setAgentUsername(String agentUsername) {
        this.agentUsername = agentUsername;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Set<String> getIntervals() {
        return intervals;
    }

    public void setIntervals(Set<String> intervals) {
        this.intervals = intervals;
    }


}
