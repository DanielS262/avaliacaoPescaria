package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;

import modelo.Pescador;

public class PescariaDAO {

	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	
	
	public JSONArray consultaTabela() {
		
		JSONArray ja = new JSONArray();
		conn = ConectaBD.getConnection();
		String query = "SELECT * FROM pescador";
				
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Pescador pesc = new Pescador();
				
				pesc.setId(rs.getInt("id"));
				pesc.setCidade(rs.getString("cidade"));
				pesc.setQuantidade(rs.getInt("quantidade"));
				pesc.setStatus(rs.getBoolean("status"));
				pesc.setExcedente(rs.getInt("excedente"));
				pesc.setMulta(rs.getDouble("multa"));
				
				ja.put(pesc.toJSON());	
			}
			
			ps.close();
			
		} catch (SQLException e) {	e.printStackTrace();	}
		
		return ja;	
	}
	
	public boolean inserirPescador(Pescador pesc) {
		
		
	
		pesc = gerarPesc(pesc);
		
		conn = ConectaBD.getConnection();
		String query = "INSERT INTO pescador (cidade, quantidade, status, excedente, multa) values (?,?,?,?,?)";
		
		try {
			
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, pesc.getCidade());
			ps.setInt(2, pesc.getQuantidade());
			ps.setBoolean(3, pesc.getStatus());
			ps.setInt(4, pesc.getExcedente());
			ps.setDouble(5, pesc.getMulta());
			
			
			if(ps.executeUpdate() > 0) {
				
				rs = ps.getGeneratedKeys();
				rs.next();
				
				int id = rs.getInt(1);
				
				pesc.setId(id);
				
				ps.close();
				
				return true;
				
			}
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	public boolean alterarPescador(Pescador pesc) {
		
		conn = ConectaBD.getConnection();
		
		String query = "UPDATE pescador set cidade = ?, quantidade = ?, status = ?, excedente = ?, multa = ? WHERE id = ?";
		
		
		try {
			
			pesc = gerarPesc(pesc);
			
			ps = conn.prepareStatement(query);
			ps.setString(1, pesc.getCidade());
			ps.setInt(2, pesc.getQuantidade());
			ps.setInt(3, pesc.getId());
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	public Pescador gerarPesc(Pescador pesc) {
		
		final double MULTABASE = 1000;
		int excedente = 0;
		double multa = (double) 0;
		int limitePedreira = 10;
		int limiteSantos = 100;
		int limiteCampinas = 30;
		int limiteUbatuba = 150;
		
		
		if(pesc.getCidade().equals("Pedreira")) {
			excedente = pesc.getQuantidade() - limitePedreira;
			pesc.setExcedente(excedente);
			multa = MULTABASE * excedente;
			pesc.setMulta(multa);
			
			if(excedente > 0) {
				pesc.setStatus(true);
			}
			
			else {
				pesc.setStatus(false);
			}
		}
		
		else if(pesc.getCidade().equals("Santos")) {
			excedente = pesc.getQuantidade() - limiteSantos;
			pesc.setExcedente(excedente);
			multa = MULTABASE * excedente;
			pesc.setMulta(multa);
			
			if(excedente > 0) {
				pesc.setStatus(true);
			}
			
			else {
				pesc.setStatus(false);
			}
		}
		
		else if(pesc.getCidade().equals("Campinas")) {
			excedente = pesc.getQuantidade() - limiteCampinas;
			pesc.setExcedente(excedente);
			multa = MULTABASE * excedente;
			pesc.setMulta(multa);
			
			if(excedente > 0) {
				pesc.setStatus(true);
			}
			
			else {
				pesc.setStatus(false);
			}
		}
		
		else if(pesc.getCidade().equals("Ubatuba")) {
			excedente = pesc.getQuantidade() - limiteUbatuba;
			pesc.setExcedente(excedente);
			multa = MULTABASE * excedente;
			pesc.setMulta(multa);
			
			if(excedente > 0) {
				pesc.setStatus(true);
			}
			
			else {
				pesc.setStatus(false);
			}
		}
		
		return pesc;
		
	}
}
