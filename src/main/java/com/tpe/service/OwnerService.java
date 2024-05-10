package com.tpe.service;

import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDTO;
import com.tpe.exceptions.ConflictException;
import com.tpe.exceptions.OwnerNotFounException;
import com.tpe.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository repository;


    //1-b
    public void saveOwner(OwnerDTO ownerDTO) {

        boolean isExists=repository.existsByEmail(ownerDTO.getEmail());
        if (isExists){
            throw new ConflictException("Bu email zaten kullanılıyor!");
        }

        Owner newOwner=new Owner();//id,,registrationDate:auto generated
        newOwner.setName(ownerDTO.getName());
        newOwner.setLastName(ownerDTO.getLastName());
        newOwner.setPhoneNumber(ownerDTO.getPhoneNumber());
        newOwner.setEmail(ownerDTO.getEmail());

        //Owner yeniOwner=new Owner(ownerDTO);

        repository.save(newOwner);
    }

    //2-b
    public List<Owner> getAll() {

         List<Owner> owners=repository.findAll();

         if (owners.isEmpty()){
             throw new OwnerNotFounException("Hiç üye bulunamadı!");
         }

         return owners;
    }

    //3-b
    public OwnerDTO getOwnerDTOById(Long id) {
        Owner owner=getOwnerById(id);
        OwnerDTO ownerDTO=new OwnerDTO();
        ownerDTO.setName(owner.getName());
        ownerDTO.setLastName(owner.getLastName());
        ownerDTO.setPhoneNumber(owner.getPhoneNumber());
        ownerDTO.setEmail(owner.getEmail());
        return ownerDTO;
    }

    //3-c
    public Owner getOwnerById(Long id){

        Owner owner=repository.findById(id).
                orElseThrow(()->new OwnerNotFounException("Üye bulunamadı. ID : "+id));
        return owner;
    }


}
