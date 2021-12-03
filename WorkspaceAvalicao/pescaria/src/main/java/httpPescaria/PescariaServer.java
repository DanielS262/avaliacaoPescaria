package httpPescaria;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controle.PescariaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Pescador;


@WebServlet("/pescadores")

public class PescariaServer extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		
		PrintWriter pw = resp.getWriter();
		JSONArray ja = new JSONArray();
		PescariaDAO pescDAO = new PescariaDAO();
		
		ja = pescDAO.consultaTabela();
		
		pw.write(ja.toString());
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		
		
		String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		try {
			
			JSONObject jo = new JSONObject(body);
			PrintWriter pw = resp.getWriter();
			PescariaDAO pescDAO = new PescariaDAO();
			Pescador pesc = new Pescador();
			
			pesc.setCidade(jo.getString("cidade"));
			pesc.setQuantidade(jo.getInt("quantidade"));
			
			
			if(pescDAO.inserirPescador(pesc)) {
				
				jo.put("id", pesc.getId());
				
				if(pesc.getStatus() == true) {
					jo.put("status", "Ultrapassou o limite permitido");
				}
				
				else{
					jo.put("status", "Não ultrapassou o limite permitido");
				}
				
				
				jo.put("excedente", pesc.getExcedente());
				jo.put("multa", pesc.getMulta());
				
				pw.write(jo.toString());
				
			}
			
			else {
				resp.setStatus(402);
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
	
}
