package modelo;

import org.json.JSONException;
import org.json.JSONObject;

public class Pescador {
	
	
	private int id;
	private String cidade;
	private int quantidade;
	private boolean status;
	private int excedente;
	private double multa;
	


	public Pescador() {
		
	}
	
	public Pescador(int id, String cidade, int quantidade, boolean status, int execedente, double multa) {
			
		this.id = id;
		this.cidade = cidade;
		this.quantidade = quantidade;
		this.status = status;
		this.excedente = execedente;
		this.multa = multa;
	}

	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getExcedente() {
		return excedente;
	}
	public void setExcedente(int excedente) {
		this.excedente = excedente;
	}
	public double getMulta() {
		return multa;
	}
	public void setMulta(double multa) {
		this.multa = multa;
	}
	
	
	public JSONObject toJSON() { 
		
		JSONObject jo = new JSONObject();
		
		try {
			jo.put("id", id);
			jo.put("cidade", cidade);
			jo.put("quantidade", quantidade);
			jo.put("status", status);
			jo.put("excedente", excedente);
			jo.put("multa", multa);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return jo;
	}
	
	

}
