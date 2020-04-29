package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Tune;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TuneRepository extends CrudRepository<Tune, Integer> {
    @Query("select t from Tune t where t.name like %:name%")
    Iterable<Tune> findByName(@Param("name") String name);
}
