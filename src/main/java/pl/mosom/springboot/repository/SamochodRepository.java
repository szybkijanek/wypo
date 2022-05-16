package pl.mosom.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.mosom.springboot.model.Samochod;

@Repository
public interface SamochodRepository extends JpaRepository<Samochod, Long> {

}
