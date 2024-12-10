package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.business.service.CampaignService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.enums.Animal;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CampaignServiceTest {

    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private CampaignService campaignService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave_ValidCampaign() throws Exception {
        Campaign campaign = createValidCampaign();

        when(campaignRepository.save(any(Campaign.class))).thenReturn(campaign);

        Campaign savedCampaign = campaignService.save(campaign);

        assertNotNull(savedCampaign);
        assertEquals("Campanha de Teste", savedCampaign.getTitle());
        assertEquals(Animal.CACHORRO, savedCampaign.getAnimal());

        verify(campaignRepository, times(1)).save(any(Campaign.class));
    }

    @Test(expected = ObjectAlreadyExistsException.class)
    public void testSave_DuplicateTitle() throws Exception {
        Campaign campaign = createValidCampaign();

        when(campaignRepository.existsByTitle("Campanha de Teste")).thenReturn(true);

        campaignService.save(campaign);
    }

    @Test(expected = InvalidDateRangeException.class)
    public void testSave_InvalidDateRange() throws Exception {
        Campaign campaign = createValidCampaign();
        campaign.setStart(LocalDateTime.now().plusDays(1));
        campaign.setEnd(LocalDateTime.now());

        campaignService.save(campaign);
    }

    @Test(expected = InvalidCollectionGoalException.class)
    public void testSave_InvalidCollectionGoal() throws Exception {
        Campaign campaign = createValidCampaign();
        campaign.setCollectionGoal(BigDecimal.valueOf(-100));

        campaignService.save(campaign);
    }

    @Test(expected = InvalidCollectionPercentageException.class)
    public void testSave_InvalidCollectionPercentage() throws Exception {
        Campaign campaign = createValidCampaign();
        campaign.setBalance(BigDecimal.valueOf(-5));

        campaignService.save(campaign);
    }

    @Test
    public void testUpdate_ValidCampaign() throws Exception {
        Campaign campaign = createValidCampaign();
        campaign.setId(1L);

        when(campaignRepository.existsById(1L)).thenReturn(true);


        when(campaignRepository.save(any(Campaign.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Campaign updatedCampaign = campaignService.update(campaign);


        assertNotNull(updatedCampaign);
        assertEquals("Campanha de Teste", updatedCampaign.getTitle());

        verify(campaignRepository, times(1)).save(any(Campaign.class));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testUpdate_CampaignNotFound() throws Exception {
        Campaign campaign = createValidCampaign();
        campaign.setId(1L);

        when(campaignRepository.existsById(1L)).thenReturn(false);

        campaignService.update(campaign);
    }



    @Test(expected = CampaignNotEditableException.class)
    public void testUpdate_CampaignNotEditable() throws Exception {
        Campaign campaign = createValidCampaign();
        campaign.setId(1L);
        campaign.setCampaingStatus(false);

        when(campaignRepository.existsById(1L)).thenReturn(true);

        campaignService.update(campaign);
    }

    @Test
    public void testDelete_ValidCampaign() throws Exception {
        Campaign campaign = createValidCampaign();
        campaign.setId(1L);

        when(campaignRepository.existsById(1L)).thenReturn(true);

        campaignService.delete(campaign);

        verify(campaignRepository, times(1)).delete(any(Campaign.class));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testDelete_CampaignNotFound() throws Exception {
        Campaign campaign = createValidCampaign();
        campaign.setId(1L);

        when(campaignRepository.existsById(1L)).thenReturn(false);

        campaignService.delete(campaign);
    }

    @Test
    public void testDeleteById_ValidId() throws Exception {
        when(campaignRepository.existsById(1L)).thenReturn(true);

        campaignService.deleteById(1L);

        verify(campaignRepository, times(1)).deleteById(1L);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testDeleteById_CampaignNotFound() throws Exception {
        when(campaignRepository.existsById(1L)).thenReturn(false);

        campaignService.deleteById(1L);
    }

    @Test
    public void testFindByName_ValidName() throws Exception {
        Campaign campaign = createValidCampaign();

        when(campaignRepository.existsByTitle("Campanha de Teste")).thenReturn(true);
        when(campaignRepository.findByTitle("Campanha de Teste")).thenReturn(Optional.of(campaign));

        Optional<Campaign> foundCampaign = campaignService.findByName("Campanha de Teste");

        assertTrue(foundCampaign.isPresent());
        assertEquals("Campanha de Teste", foundCampaign.get().getTitle());
    }

    @Test(expected = MissingFieldException.class)
    public void testFindByName_NullName() throws Exception {
        campaignService.findByName(null);
    }

    @Test(expected = MissingFieldException.class)
    public void testFindByName_BlankName() throws Exception {
        campaignService.findByName("");
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testFindByName_NonExistingName() throws Exception {
        when(campaignRepository.existsByTitle("Campanha Inexistente")).thenReturn(false);

        campaignService.findByName("Campanha Inexistente");
    }

    @Test
    public void testFindById_ValidId() throws Exception {
        Campaign campaign = createValidCampaign();
        campaign.setId(1L);

        when(campaignRepository.existsById(1L)).thenReturn(true);
        when(campaignRepository.getReferenceById(1L)).thenReturn(campaign);

        Campaign foundCampaign = campaignService.findById(1L);

        assertNotNull(foundCampaign);
        assertEquals("Campanha de Teste", foundCampaign.getTitle());
    }

    @Test(expected = MissingFieldException.class)
    public void testFindById_NullId() throws Exception {
        campaignService.findById(null);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testFindById_NonExistingId() throws Exception {
        when(campaignRepository.existsById(1L)).thenReturn(false);

        campaignService.findById(1L);
    }

    private Campaign createValidCampaign() {
        Campaign campaign = new Campaign();
        campaign.setTitle("Campanha de Teste");
        campaign.setAnimal(Animal.CACHORRO);
        campaign.setStart(LocalDateTime.now());
        campaign.setEnd(LocalDateTime.now().plusDays(7));
        campaign.setCollectionGoal(BigDecimal.valueOf(250));
        campaign.setBalance(BigDecimal.valueOf(10));
        campaign.setUndirectedBalance(BigDecimal.valueOf(20));
        campaign.setCampaingStatus(true);
        return campaign;
    }
}
