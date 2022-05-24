package com.aux.provider.repositories;

import com.aux.provider.models.ProveedorModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProveedorRepository extends CrudRepository<ProveedorModel, Long> {
    @Override
    Optional<ProveedorModel> findById(Long aLong);
}
