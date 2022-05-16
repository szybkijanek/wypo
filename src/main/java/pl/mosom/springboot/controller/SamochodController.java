package pl.mosom.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.mosom.springboot.model.Samochod;
import pl.mosom.springboot.service.SamochodService;

@Controller
public class SamochodController {

	@Autowired
	private SamochodService samochodService;

	// lista samochodow
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "marka", "asc", model);
	}

	@GetMapping("/showNewSamochodForm")
	public String showNewSamochodForm(Model model) {
		// utwórz atrybut modelu do powiązania danych formularza
		Samochod samochod = new Samochod();
		model.addAttribute("samochod", samochod);
		return "nowy_samochod";
	}

	@PostMapping("/saveSamochod")
	public String saveSamochod(@ModelAttribute("samochod") Samochod samochod) {
		// zapisanie samochodu do bazy
		samochodService.saveSamochod(samochod);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// pobranie samochodu z serwisu
		Samochod samochod = samochodService.getSamochodById(id);
		// ustaw samochod jako atrybut modelu do wstępnego wypełnienia formularza
		model.addAttribute("samochod", samochod);
		return "zmiana_samochod";
	}

	@GetMapping("/deleteSamochod/{id}")
	public String deleteSamochod(@PathVariable(value = "id") long id) {

		// wywolanie metody delete
		this.samochodService.deleteSamochodById(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Samochod> page = samochodService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Samochod> listEmployees = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listSamochody", listEmployees);
		return "index";
	}
	
	@GetMapping("/assignSamochod/{id}")
	public String assignSamochod(@PathVariable(value = "id") long id, Model model) {
		model.addAttribute("wypozyczony", true);
		return "redirect:/";
	}
	
	@GetMapping("/dismissSamochod/{id}")
	public String dismissSamochod(Model model) {
		model.addAttribute("wypozyczony", false);
		return "redirect:/";
	}
}
