package com.mtm.test.mustafa.repository;

import com.mtm.test.mustafa.entity.DefCity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface DefCityRepository extends CrudRepository<DefCity,Integer> {

    public DefCity findByNameAndId(String name,Integer id);
}
