package pl.mosom.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pl.mosom.springboot.model.Samochod;
import pl.mosom.springboot.repository.SamochodRepository;

@Service
public class SamochodServiceImpl implements SamochodService {

	@Autowired
	private SamochodRepository samochodRepository;

	@Override
	public List<Samochod> getAllSamochody() {

		return samochodRepository.findAll();
	}

	@Override
	public void saveSamochod(Samochod samochod) {
		this.samochodRepository.save(samochod);

	}

	@Override
	public Samochod getSamochodById(long id) {
		Optional<Samochod> optional = samochodRepository.findById(id);
		Samochod samochod = null;
		if (optional.isPresent()) {
			samochod = optional.get();
		} else {
			throw new RuntimeException(" Nie znaleziono samochodu po numerze :: " + id);
		}
		return samochod;
	}

	@Override
	public void deleteSamochodById(long id) {

		this.samochodRepository.deleteById(id);

	}

	@Override
	public Page<Samochod> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.samochodRepository.findAll(pageable);
	}

	@Override
	public void assignSamochod(long id) {
		
		//this.samochodRepository.getById(id);
		
	}
	

}
