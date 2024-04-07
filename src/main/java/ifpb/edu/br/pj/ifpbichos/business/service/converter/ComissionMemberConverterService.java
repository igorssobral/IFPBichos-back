package ifpb.edu.br.pj.ifpbichos.business.service.converter;

import ifpb.edu.br.pj.ifpbichos.model.entity.ComissionMember;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.ComissionMemberDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComissionMemberConverterService {
    public ComissionMember dtoToComissionMember(ComissionMemberDTO dto){
        if (dto != null) {
            ComissionMember entity = new ComissionMember(dto.getName(), dto.getCPF(), dto.getPhoneNumber(), dto.getEmail(), dto.getLogin(),dto.getPassword(),  dto.getUserRole(), dto.getRole());
            return entity;
        }
        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }

    public ComissionMemberDTO comissionMemberToDto(ComissionMember entity) {
        if (entity != null) {
            ComissionMemberDTO dto = new ComissionMemberDTO(entity.getName(),entity.getCPF(), entity.getPhoneNumber(), entity.getEmail(), entity.getLogin(),entity.getPassword() , entity.getUserRole(), entity.getRole());
            return dto;
        }

        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }

    public List<ComissionMember> dtosToComissionMembers(List<ComissionMemberDTO> dtoList) {

        if (dtoList != null) {
            List<ComissionMember> entityList = new ArrayList<>();

            ComissionMember entity = null;

            if (dtoList != null && !dtoList.isEmpty()) {
                for (ComissionMemberDTO dto : dtoList) {
                    entity = dtoToComissionMember(dto);
                    entityList.add(entity);
                }
            }

            return entityList;
        }

        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }

    public List<ComissionMemberDTO> comissionMembersToDtos(List<ComissionMember> entityList) {
        if (entityList != null) {
            List<ComissionMemberDTO> dtoList = new ArrayList<>();

            ComissionMemberDTO dto = null;

            if (!entityList.isEmpty()) {
                for (ComissionMember comissionMember: entityList) {
                    dto = comissionMemberToDto(comissionMember);
                    dtoList.add(dto);
                }
            }

            return dtoList;
        }

        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }
}
