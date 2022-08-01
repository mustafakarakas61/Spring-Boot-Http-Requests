package com.mtm.test.mustafa.repository;

import com.mtm.test.mustafa.entity.DefUsers;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface DefUsersRepository extends CrudRepository<DefUsers, Integer> {
    public DefUsers findByNameAndId(String name,String id);
}
