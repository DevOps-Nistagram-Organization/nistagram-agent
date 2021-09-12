package com.nistagram.agent.controller;

import com.nistagram.agent.converter.CampaignConverter;
import com.nistagram.agent.model.dto.CampaignDTO;
import com.nistagram.agent.model.dto.CreateCampaignDTO;
import com.nistagram.agent.model.entity.Campaign;
import com.nistagram.agent.service.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "campaign")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CampaignDTO> createCampaign(@RequestBody CreateCampaignDTO createCampaignDTO) throws Exception {
        Campaign campaign = campaignService.createCampaign(createCampaignDTO);
        CampaignDTO dto = CampaignConverter.toDTO(campaign);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "getAgent")
    public ResponseEntity<List<CampaignDTO>> getAgentsCampaign() {
        List<Campaign> campaigns = campaignService.getAgentsCampaign();
        List<CampaignDTO> dto = campaigns.stream().map(CampaignConverter::toDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Boolean> deleteCampaign(@PathVariable("id") Long id) {
        Boolean status = campaignService.deleteCampaign(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
