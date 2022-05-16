package pl.mosom.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pl.mosom.springboot.model.Samochod;

public interface SamochodService {
	List<Samochod> getAllSamochody();

	void saveSamochod(Samochod samochod);

	Samochod getSamochodById(long id);

	void deleteSamochodById(long id);
	
	void assignSamochod(long id);

	Page<Samochod> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}