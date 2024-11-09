package forms.application.service;

import forms.application.dao.OrganizationDao;
import forms.application.model.OrganizationEntity;
import forms.application.service.dto.OrganizationDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDao organizationDao;

    @Override
    public List<OrganizationEntity> findAll() {
        return organizationDao.findAll();
    }

    @Override
    public OrganizationEntity findById(@NotNull Long id) {
        return organizationDao.findById(id)
                .orElseThrow(()
                        -> new EntityNotFoundException("Organization with id = " + id + " not found"));
    }

    @Override
    public void deleteById(@NotNull Long id) {
        organizationDao.deleteById(id);
    }

    @Override
    public OrganizationEntity create(OrganizationDto org) {
        return organizationDao.save(OrganizationDto.convert(org));
    }

    @Override
    public OrganizationEntity update(OrganizationDto org, @NotNull Long id) {
        OrganizationEntity byId = this.findById(id);

        byId.setName(org.getName());
        byId.setPhone(org.getPhone());
        byId.setEmail(org.getEmail());

        return organizationDao.save(byId);
    }

    public void createByName(String customValue) {
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setName(customValue);
        organizationDao.save(organizationEntity);
    }

    public List<String> getAllNames() {
        List<OrganizationEntity> organizationEntityList = findAll();
        List<String> temp = new ArrayList<>();
        for (OrganizationEntity organization:organizationEntityList){
            temp.add(organization.getName());
        }
        return temp;
    }
}
