package ifpb.edu.br.pj.ifpbichos.business.service;


import ifpb.edu.br.pj.ifpbichos.model.entity.ComissionMember;
import ifpb.edu.br.pj.ifpbichos.model.repository.ComissionMemberRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.MissingFieldException;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectAlreadyExistsException;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComissionMemberService {
    
	@Autowired
    private ComissionMemberRepository comissionMemberRepository;
	


    public List<ComissionMember> findAll() {
        return comissionMemberRepository.findAll();
    }



    public ComissionMember findById(Long id) throws Exception {
        if (id == null) {
            throw new MissingFieldException("id");
        }else if(!comissionMemberRepository.existsById(id)) {
        	throw new ObjectNotFoundException("Membro de comissão", "nome", id);
        }

        return comissionMemberRepository.getReferenceById(id);
    }

    public ComissionMember save(ComissionMember comissionMember) throws Exception {
    	if(comissionMemberRepository.existsByLogin(comissionMember.getLogin())) {
    		throw new ObjectAlreadyExistsException("Já existe um membro de comissão com esse login");
    	}
        return comissionMemberRepository.save(comissionMember);
    }

    public ComissionMember update(ComissionMember comissionMember) throws Exception {
    	if(comissionMember.getId() == null) {
    		throw new MissingFieldException("id", "update");
    	}else if(!comissionMemberRepository.existsById(comissionMember.getId())){
    		throw new ObjectNotFoundException("Membro de comissão", "id", comissionMember.getId());
    	}
        return comissionMemberRepository.save(comissionMember);
    }

    public void delete(ComissionMember comissionMember) throws Exception {
    	if (comissionMember.getId() == null) {
			throw new MissingFieldException("id", "delete");
		} else if (comissionMemberRepository.existsById(comissionMember.getId())) {
			throw new ObjectNotFoundException("Membro da comissão", "id", comissionMember.getId());
		}
		
    	comissionMemberRepository.delete(comissionMember);
    }

    public void deleteById(Long id) throws Exception {

        comissionMemberRepository.deleteById(id);
    }
}
