package it.univpm.ProgettoOOP.controller;

import java.util.Vector; 

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.univpm.ProgettoOOP.filters.Filter;
import it.univpm.ProgettoOOP.model.Domain;
import it.univpm.ProgettoOOP.service.DomainService;
import it.univpm.ProgettoOOP.service.DomainServiceImpl;

/**
 * Rappresenta la classe che gestisce tutte le chiamate al Server
 * @author Beci Paolo
 * @author Izzi Giuseppe
 * @author Grieco Emilio Joseph
 */
@RestController
public class Controller {

	@Autowired
	DomainService d;
	
	/**
	 * Rotta per visualizzare i domini commerciali contenenti la parola chiave "facebook" (limite 50)
	 * @return il Vector contenente i domain
	 */
	@GetMapping("/domains")
		public ResponseEntity<Object> getDomains(){
			return new ResponseEntity<>(d.getDomains(), HttpStatus.OK);
		}

	/**
	 * Rotta per visualizzare le statistiche elaborate sui domini forniti dall'API
	 * @return Statistiche sui domini
	 */
	/*
	@GetMapping("/stats")
		public ResponseEntity<Object> getStats(){
			return new ResponseEntity<>(d.getStats(), HttpStatus.OK); // return statistiche  formato:(JSONObject)
		}
	*/

	/**
	 * Rotta per visualizzare i domini o le informazioni filtrate
	 * @return Filtri sui domini
	 */
	
	@PostMapping("/filter")
		public ResponseEntity<Object> getFilters(@RequestBody JSONObject bodyFilter) {
			return new ResponseEntity<>(d.getFilteredDomains(bodyFilter), HttpStatus.OK); // return filtri  formato:(JSONObject)
		}
	
}













