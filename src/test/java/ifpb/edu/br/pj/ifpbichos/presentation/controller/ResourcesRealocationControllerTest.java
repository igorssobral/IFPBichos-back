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

import java.util.Arrays;

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

        when(resourcesRealocationService.findAll()).thenReturn(Arrays.asList(resourcesRealocation));
        when(converter.ResourcesRealocationToDtos(anyList())).thenReturn(Arrays.asList(resourcesRealocationDTO));

        ResponseEntity responseEntity = resourcesRealocationController.getALL();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Arrays.asList(resourcesRealocationDTO), responseEntity.getBody());

        verify(resourcesRealocationService, times(1)).findAll();
        verify(converter, times(1)).ResourcesRealocationToDtos(anyList());
    }

    @Test
    public void testcreateResourcesRealocation() throws Exception {
        ResourcesRealocation resourcesRealocation = new ResourcesRealocation();
        ResourcesRealocationDTO resourcesRealocationDTO = new ResourcesRealocationDTO();

        when(converter.toEntity(resourcesRealocationDTO)).thenReturn(resourcesRealocation);
        when(resourcesRealocationService.save(resourcesRealocation)).thenReturn(resourcesRealocation);
        when(converter.toDto(resourcesRealocation)).thenReturn(resourcesRealocationDTO);

        ResponseEntity responseEntity = resourcesRealocationController.createResourcesRealocation(resourcesRealocationDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(resourcesRealocationDTO, responseEntity.getBody());

        verify(converter, times(1)).toEntity(resourcesRealocationDTO);
        verify(resourcesRealocationService, times(1)).save(resourcesRealocation);
        verify(converter, times(1)).toDto(resourcesRealocation);
    }

    @Test
    public void testsave() throws Exception {
        ResourcesRealocation resourcesRealocation = new ResourcesRealocation();
        ResourcesRealocationDTO resourcesRealocationDTO = new ResourcesRealocationDTO();

        when(converter.toEntity(resourcesRealocationDTO)).thenReturn(resourcesRealocation);
        when(resourcesRealocationService.save(resourcesRealocation)).thenReturn(resourcesRealocation);
        when(converter.toDto(resourcesRealocation)).thenReturn(resourcesRealocationDTO);

        ResponseEntity responseEntity = resourcesRealocationController.save(resourcesRealocationDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(resourcesRealocationDTO, responseEntity.getBody());

        verify(converter, times(1)).toEntity(resourcesRealocationDTO);
        verify(resourcesRealocationService, times(1)).save(resourcesRealocation);
        verify(converter, times(1)).toDto(resourcesRealocation);
    }






}
