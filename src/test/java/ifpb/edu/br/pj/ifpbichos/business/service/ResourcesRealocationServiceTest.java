package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.model.entity.ResourcesRealocation;
import ifpb.edu.br.pj.ifpbichos.model.repository.ResourcesRealocationRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResourcesRealocationServiceTest {

    @Mock
    private ResourcesRealocationRepository resourcesRealocationRepository;

    @InjectMocks
    private ResourcesRealocationService resourcesRealocationService;

    @Test
    public void testFindAll_EmptyList() {
        when(resourcesRealocationRepository.findAll()).thenReturn(List.of());
        List<ResourcesRealocation> result = resourcesRealocationService.findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAll_NonEmptyList() {
        List<ResourcesRealocation> expectedList = List.of(new ResourcesRealocation(), new ResourcesRealocation());
        when(resourcesRealocationRepository.findAll()).thenReturn(expectedList);
        List<ResourcesRealocation> result = resourcesRealocationService.findAll();
        assertEquals(expectedList, result);
    }

    @Test
    public void testFindById_Success() throws ObjectNotFoundException {
        Long id = 1L;
        ResourcesRealocation expectedRealocation = new ResourcesRealocation();
        expectedRealocation.setId(id);

        when(resourcesRealocationRepository.findById(id)).thenReturn(Optional.of(expectedRealocation));

        ResourcesRealocation result = resourcesRealocationService.findById(id);
        assertEquals(expectedRealocation, result);
    }






}
