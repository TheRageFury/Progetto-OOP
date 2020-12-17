package it.univpm.ProgettoOOP.filters;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.model.Domain;

public class Filter {
	
	protected Vector<Filter> filters= new Vector<Filter>();
	protected String value;
	protected boolean or;
		
	public Filter() {}
	
	public Filter(String value) {
		this.value= value;
		this.or= false;
	}
	
	public Filter(String value, boolean or) { //COSTRUTTORE PER FILTRI MULTIPLI
		this.value= value;
		this.or= or;
	}
	
	public void filtra(Vector<Domain> domainsToFilter) {}
	
	public void filtra(Vector<Domain> domainsToFilter, Vector<Domain> filteredDomains) {}
	
	/**
	 * Non è possibile inserire piu di un filtro dello stesso tipo
	 * @param bodyFilter
	 */
	public void parsingFilters(JSONObject bodyFilter) {
		
		if(bodyFilter.containsKey("||name")) { //Filtraggio OR
			Filter f= new Filter();
			
			for(String s: f.parseString((String)bodyFilter.get("||name"))){
				Filter f1= new FilterName(s, true);
				filters.add(f1);
			}
		}	

		if(bodyFilter.containsKey("&name")) { //Filtraggio AND
			Filter f= new Filter();
			
			for(String s: f.parseString((String)bodyFilter.get("&name"))){
				Filter f1= new FilterName(s, false);
				filters.add(f1);
			}
		}	
		
		if(bodyFilter.containsKey("||country")) { //Filtraggio OR
			Filter f= new Filter();
			
			for(String s: f.parseString((String)bodyFilter.get("||country"))){
				Filter f1= new FilterCountry(s, true);
				filters.add(f1);
			}
		}	
			
		if(bodyFilter.containsKey("&country")) { //Filtraggio AND
			Filter f= new Filter();
			for(String s: f.parseString((String)bodyFilter.get("&country"))){
				Filter f1= new FilterCountry(s, false);
				filters.add(f1);
			}
		}	
			
		if(bodyFilter.containsKey("createDate")) {
			Filter f= new FilterCreateDate((String)bodyFilter.get("createDate"));
			filters.add(f);
		}
			
		if(bodyFilter.containsKey("updateDate")) {
			Filter f= new FilterUpdateDate((String)bodyFilter.get("updateDate"));
			filters.add(f);
		}
			
		if(bodyFilter.containsKey("isDead")) {
			String d = (String)bodyFilter.get("isDead");
			// Controllo correttezza inserimento
			if(d.contains("TRUE")||d.contains("true")){
				d = "True";
			}
			if(d.contains("FALSE")||d.contains("false")){
				d = "False";
			}
			Filter f= new FilterIsDead(d);
			filters.add(f);
		}
	}
	
	public String[] parseString(String riga) {
		
		String[] rigaSplitted= riga.split(";");	
		return rigaSplitted;
	}
	
	public Vector<Filter> getFilters() {
		return this.filters;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public boolean getOr() {
		return this.or;
	}
}
