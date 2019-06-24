package com.example.demo.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Domain.Annoncement;

public interface AnnoncementDao extends CrudRepository<Annoncement, Integer> {

}
