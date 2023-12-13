package ifpb.edu.br.pj.ifpbichos.business.service;


import ifpb.edu.br.pj.ifpbichos.model.entity.ComissionMember;
import ifpb.edu.br.pj.ifpbichos.model.repository.ComissionMemberRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.MissingFieldException;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ComissionMemberService {
    @Autowired
    private ComissionMemberRepository comissionMemberRepository;



    public List<ComissionMember> findAll() {
        return comissionMemberRepository.findAll();
    }



    public ComissionMember findById(Integer id) throws Exception {
        if (id == null) {
            throw new MissingFieldException("id");
        }

        return comissionMemberRepository.getReferenceById(id);
    }

    public ComissionMember save(ComissionMember comissionMember) throws Exception {

        return comissionMemberRepository.save(comissionMember);
    }

    public ComissionMember update(ComissionMember comissionMember) throws Exception {

        return comissionMemberRepository.save(comissionMember);
    }

    public void delete(ComissionMember comissionMember) throws Exception {

        comissionMemberRepository.delete(comissionMember);
    }

    public void deleteById(String cpf) throws Exception {

        comissionMemberRepository.deleteById(Integer.valueOf(cpf));
    }
}
