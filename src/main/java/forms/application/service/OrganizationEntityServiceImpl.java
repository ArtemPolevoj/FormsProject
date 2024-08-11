package forms.application.service;


import forms.application.model.OrganizationEntity;
import forms.application.repository.OrganizationEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrganizationEntityServiceImpl implements ServiceEntity<OrganizationEntity> {

    private final OrganizationEntityRepository organizationEntityRepository;

    @Override
    public OrganizationEntity create(OrganizationEntity organizationEntity) {
        return organizationEntityRepository.save(organizationEntity);
    }

    @Override
    public List<OrganizationEntity> getAll() {
        return organizationEntityRepository.findAll();
    }

    @Override
    public OrganizationEntity getById(Long id) {
        return organizationEntityRepository.findById(id).orElse(null);
    }

    @Override
    public OrganizationEntity update(OrganizationEntity organizationEntity) {

        OrganizationEntity updatedOrganizationEntity =
                organizationEntityRepository.findById(organizationEntity.getId()).orElse(null);

        updatedOrganizationEntity.setName(organizationEntity.getName());
        updatedOrganizationEntity.setEmail(organizationEntity.getEmail());
        updatedOrganizationEntity.setPhone(organizationEntity.getPhone());

        return organizationEntityRepository.save(updatedOrganizationEntity);
    }

    @Override
    public void delete(Long id) {
        OrganizationEntity organizationEntity = getById(id);
        organizationEntityRepository.delete(organizationEntity);
    }
    public List<String> getAllNames(){
        return organizationEntityRepository.findAll().stream().map(OrganizationEntity::getName).
                collect(Collectors.toList());
    }
    public void createByName(String name){
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setName(name);
        organizationEntityRepository.save(organizationEntity);
    }
}
