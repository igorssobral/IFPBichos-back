package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import ifpb.edu.br.pj.ifpbichos.business.service.converter.ResourcesRealocationConverterService;
import ifpb.edu.br.pj.ifpbichos.business.service.ResourcesRealocationService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.entity.ResourcesRealocation;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.ResourcesRealocationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/resources-realocations")
public class ResourcesRealocationController {

    private final ResourcesRealocationService resourcesRealocationService;
    private final ResourcesRealocationConverterService converter;

    @Autowired
    public ResourcesRealocationController(ResourcesRealocationService resourcesRealocationService, ResourcesRealocationConverterService converter) {
        this.resourcesRealocationService = resourcesRealocationService;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<List<ResourcesRealocationDTO>> getAllResourcesRealocations() {
        List<ResourcesRealocation> realocations = resourcesRealocationService.findAll();
        List<ResourcesRealocationDTO> dtoList = realocations.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResourcesRealocationById(@PathVariable Long id) {
        try {
            ResourcesRealocation realocation = resourcesRealocationService.findById(id);
            ResourcesRealocationDTO dto = converter.toDto(realocation);
            return ResponseEntity.ok(dto);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createResourcesRealocation(@RequestBody ResourcesRealocationDTO dto) {
        try {
            ResourcesRealocation entity = converter.toEntity(dto);
            ResourcesRealocation createdRealocation = resourcesRealocationService.save(entity);
            ResourcesRealocationDTO createdDto = converter.toDto(createdRealocation);
            return ResponseEntity.ok(createdDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateResourcesRealocation(@PathVariable Long id, @RequestBody ResourcesRealocationDTO dto) {
        try {
            ResourcesRealocation entity = converter.toEntity(dto);
            ResourcesRealocation updatedRealocation = resourcesRealocationService.update(id, entity);
            ResourcesRealocationDTO updatedDto = converter.toDto(updatedRealocation);
            return ResponseEntity.ok(updatedDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResourcesRealocation(@PathVariable Long id) {
        try {
            resourcesRealocationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/check-campaign")
    public ResponseEntity<?> createIfNeeded(@RequestBody Campaign campaign) {
        try {
            resourcesRealocationService.createResourcesRealocationIfNeeded(campaign);
            return ResponseEntity.ok("Resources realocation created if needed.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}