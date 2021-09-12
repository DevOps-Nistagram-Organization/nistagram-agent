package com.nistagram.agent.model.dto;

import com.nistagram.agent.model.entity.Criteria;
import com.nistagram.agent.model.entity.DateInterval;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class CreateCampaignDTO {
    private String link;
    private String imageUrl;

    private Date startDate;
    private Date endDate;
    private Criteria criteria;
    private List<String> intervals;

    public CreateCampaignDTO() {
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

    public List<String> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<String> intervals) {
        this.intervals = intervals;
    }
}
