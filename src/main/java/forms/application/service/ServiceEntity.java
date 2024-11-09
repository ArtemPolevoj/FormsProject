package forms.application.service;

import java.util.List;

public interface ServiceEntity <T>{
    T create(T param);

    List<T> getAll();

    T getById(Long id);

    T update(T param);

    void delete(Long id);
}
