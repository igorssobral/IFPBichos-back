package ifpb.edu.br.pj.ifpbichos.presentation.controller;


import ifpb.edu.br.pj.ifpbichos.business.service.CampaignService;
import ifpb.edu.br.pj.ifpbichos.business.service.converter.CampaignConverterService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.enums.Animal;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.CampaignDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.InvalidDateRangeException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CampaignControllerTest {

    @InjectMocks
    private CampaignController campaignController;

    @Mock
    private CampaignService campaignService;

    @Mock
    private CampaignConverterService converterService;
    @Mock
    private CampaignRepository campaignRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        Campaign campaign = new Campaign();
        CampaignDTO campaignDTO = new CampaignDTO();

        when(campaignService.findAll()).thenReturn(Arrays.asList(campaign));
        when(converterService.CampaignsToDtos(anyList())).thenReturn(Arrays.asList(campaignDTO));

        ResponseEntity responseEntity = campaignController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Arrays.asList(campaignDTO), responseEntity.getBody());

        verify(campaignService, times(1)).findAll();
        verify(converterService, times(1)).CampaignsToDtos(anyList());
    }

    @Test
    public void testFindById() throws Exception {
        Long id = 1L;
        Campaign campaign = new Campaign();
        CampaignDTO campaignDTO = new CampaignDTO();

        when(campaignService.findById(id)).thenReturn(campaign);
        when(converterService.campaignToDto(campaign)).thenReturn(campaignDTO);

        ResponseEntity responseEntity = campaignController.findById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(campaignDTO, responseEntity.getBody());

        verify(campaignService, times(1)).findById(id);
        verify(converterService, times(1)).campaignToDto(campaign);
    }

    @Test
    public void testSave() throws Exception {
        Campaign campaign = new Campaign();
        CampaignDTO campaignDTO = new CampaignDTO();

        when(converterService.dtoToCampaign(campaignDTO)).thenReturn(campaign);
        when(campaignService.save(campaign)).thenReturn(campaign);
        when(converterService.campaignToDto(campaign)).thenReturn(campaignDTO);

        ResponseEntity responseEntity = campaignController.save(campaignDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(campaignDTO, responseEntity.getBody());

        verify(converterService, times(1)).dtoToCampaign(campaignDTO);
        verify(campaignService, times(1)).save(campaign);
        verify(converterService, times(1)).campaignToDto(campaign);
    }



    @Test
    public void testUpdate() throws Exception {
        Long id = 1L;
        Campaign campaign = new Campaign();
        campaign.setId(id);
        campaign.setTitle("campanha");
        campaign.setEnd(LocalDateTime.of(2023, 12, 31, 23, 59));
        campaign.setDescription("Descrição");
        campaign.setAnimal(Animal.CACHORRO);
        campaign.setStart(LocalDateTime.now().minusDays(1));
        campaign.setBalance(BigDecimal.valueOf(1000));
        campaign.setUndirectedBalance(BigDecimal.valueOf(500));
        campaign.setCampaingStatus(true);

        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setTitle("campanha");
        campaignDTO.setEnd(LocalDateTime.of(2023, 12, 31, 23, 59));
        campaignDTO.setDescription("Descrição");
        campaignDTO.setAnimal(Animal.CACHORRO);

        when(campaignRepository.findById(id)).thenReturn(Optional.of(campaign));
        when(campaignService.update(campaign)).thenReturn(campaign);
        when(converterService.campaignToDto(campaign)).thenReturn(campaignDTO);
        when(converterService.dtoToCampaign(campaignDTO)).thenReturn(campaign);

        ResponseEntity responseEntity = campaignController.update(id, campaignDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(campaignDTO, responseEntity.getBody());

        verify(campaignRepository, times(1)).findById(id);
        verify(campaignService, times(1)).update(campaign);
        verify(converterService, times(1)).campaignToDto(campaign);
    }

    @Test
    public void testUpdate_NotFound() throws Exception {
        Long id = 1L;
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setEnd(LocalDateTime.of(2023, 12, 31, 23, 59));

        when(campaignRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity responseEntity = campaignController.update(id, campaignDTO);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Campaign not found", responseEntity.getBody());

        verify(campaignRepository, times(1)).findById(id);
        verify(campaignService, times(0)).update(any(Campaign.class));
        verify(converterService, times(0)).campaignToDto(any(Campaign.class));
    }

    @Test
    public void testUpdate_InvalidDateRange() throws Exception {
        Long id = 1L;

        Campaign campaign = new Campaign();
        campaign.setId(id);
        campaign.setTitle("campanha");
        campaign.setStart(LocalDateTime.of(2024, 04, 11, 23, 59));
        campaign.setEnd(LocalDateTime.of(2024, 04, 8, 23, 59));

        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setTitle("campanha");
        campaignDTO.setEnd(LocalDateTime.of(2023, 12, 30, 23, 59));
        campaignDTO.setDescription("Descrição");
        campaignDTO.setAnimal(Animal.CACHORRO);

        when(campaignRepository.findById(id)).thenReturn(Optional.of(campaign));
        when(converterService.dtoToCampaign(any(CampaignDTO.class))).thenReturn(campaign);

        doThrow(new InvalidDateRangeException("Não pode editar essa campanha Data de início deve ser anterior à data de término"))
                .when(campaignService).update(campaign);

        ResponseEntity responseEntity = campaignController.update(id, campaignDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Não pode editar essa campanha Data de início deve ser anterior à data de término", responseEntity.getBody());

        verify(campaignRepository, times(1)).findById(id);
        verify(campaignService, times(1)).update(campaign);

    }



}
