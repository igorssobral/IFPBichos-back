package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import ifpb.edu.br.pj.ifpbichos.business.service.ResourcesRealocationService;
import ifpb.edu.br.pj.ifpbichos.business.service.converter.ResourcesRealocationConverterService;
import ifpb.edu.br.pj.ifpbichos.model.entity.ResourcesRealocation;
import ifpb.edu.br.pj.ifpbichos.model.repository.ResourcesRealocationRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.ResourcesRealocationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;


public class ResourcesRealocationControllerTest {

    @InjectMocks
    private ResourcesRealocationController resourcesRealocationController;

    @Mock
    private ResourcesRealocationService resourcesRealocationService;

    @Mock
    private ResourcesRealocationRepository resourcesRealocationRepository;

    @Mock
    private ResourcesRealocationConverterService converter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetResourcesRealocationById_NotFound() throws ObjectNotFoundException {

        Long id = 1L;

        Mockito.when(resourcesRealocationService.findById(id)).thenThrow(new ObjectNotFoundException("Reallocation not found"));

        ResponseEntity<?> response = resourcesRealocationController.getResourcesRealocationById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAll() {
        ResourcesRealocation resourcesRealocation = new ResourcesRealocation();
        ResourcesRealocationDTO resourcesRealocationDTO = new ResourcesRealocationDTO();
        resourcesRealocationDTO.setDate(LocalDateTime.now());
        resourcesRealocationDTO.setValue(BigDecimal.valueOf(100));
        resourcesRealocationDTO.setCampaignId(1L);
        resourcesRealocationDTO.setTypeRealocation("someType");


        when(resourcesRealocationService.findAll()).thenReturn(List.of(resourcesRealocation));
        when(converter.toDto(any(ResourcesRealocation.class))).thenReturn(resourcesRealocationDTO);

        ResponseEntity<List<ResourcesRealocationDTO>> responseEntity = resourcesRealocationController.getAllResourcesRealocations();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(List.of(resourcesRealocationDTO), responseEntity.getBody());

        verify(resourcesRealocationService, times(1)).findAll();
        verify(converter, times(1)).toDto(any(ResourcesRealocation.class));
    }

    @Test
    public void testcreateResourcesRealocation() throws Exception {
        ResourcesRealocation resourcesRealocation = new ResourcesRealocation();
        ResourcesRealocationDTO resourcesRealocationDTO = new ResourcesRealocationDTO();


        when(resourcesRealocationService.save(resourcesRealocationDTO)).thenReturn(resourcesRealocation);

        ResponseEntity responseEntity = resourcesRealocationController.createResourcesRealocation(resourcesRealocationDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Saldo Avulso transferido para a campanha.", responseEntity.getBody());

        verify(resourcesRealocationService, times(1)).save(resourcesRealocationDTO);
    }

    @Test
    public void testsave() throws Exception {
        ResourcesRealocation resourcesRealocation = new ResourcesRealocation();
        ResourcesRealocationDTO resourcesRealocationDTO = new ResourcesRealocationDTO();

        when(resourcesRealocationService.save(resourcesRealocationDTO)).thenReturn(resourcesRealocation);

        ResponseEntity responseEntity = resourcesRealocationController.createResourcesRealocation(resourcesRealocationDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Saldo Avulso transferido para a campanha.", responseEntity.getBody());

        verify(resourcesRealocationService, times(1)).save(resourcesRealocationDTO);
    }






}
