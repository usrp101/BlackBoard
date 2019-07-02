package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Annoncement;

public interface AnnoncementDao extends JpaRepository<Annoncement, Integer> {

}
