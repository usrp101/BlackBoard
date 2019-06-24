package com.example.demo.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.AnnoncementDao;
import com.example.demo.Domain.Annoncement;
import com.example.demo.Service.AnnoncementService;

@Service
public class AnnoncementServiceImp implements AnnoncementService {
	
	@Autowired
	public AnnoncementDao annoncementDao;

	@Override
	public Annoncement findone(Integer anId) {
		Annoncement annoncement = null;
		Optional<Annoncement> opannoncement =annoncementDao.findById(anId); 
		if(opannoncement.isPresent()) {
			annoncement = opannoncement.get();
		}
		return (annoncement != null) ? annoncement : null;
	}

	@Override
	public Annoncement create(Annoncement annoncement) {
		annoncementDao.save(annoncement);
		return (annoncement != null) ? annoncement : null;
	}

	@Override
	public List<Annoncement> findAll() {
		List<Annoncement> list = new ArrayList<Annoncement>();
		list =   (List<Annoncement>) annoncementDao.findAll();
		return list;
	}

}
