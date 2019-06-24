package com.example.demo.Service;

import java.util.List;

import com.example.demo.Domain.Annoncement;

public interface AnnoncementService {
	Annoncement findone(Integer anId);
	Annoncement create(Annoncement annoncement);
	List<Annoncement>findAll();
}
