package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Professor;

public class ProfessorDao {
	private DaoHelper daoHelper;
	
	public ProfessorDao() {
		this.daoHelper = new DaoHelper();
	}
	
	public void inserirProfessor(Professor professor) {
		Connection conn = daoHelper.getConnection();
		String sql = "INSERT INTO professor(nomeProfessor, email) VALUES(?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, professor.getNomeProfessor());
			stmt.setString(2, professor.getEmail());
			stmt.executeQuery();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			professor.setIdProfessor(rs.getInt(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
