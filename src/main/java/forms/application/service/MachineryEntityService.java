package forms.application.service;


import forms.application.model.MachineryEntity;

import java.util.List;

public interface MachineryEntityService {
    MachineryEntity create(MachineryEntity machineryEntity);

    List<MachineryEntity> getAll();


}
