package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Instituicao;

public class InstituicaoDao {
	private DaoManager daoManager;
	
	public InstituicaoDao() {
		this.setDaoManager(new DaoManager());
	}

	public void setDaoManager(DaoManager daoManager) {
		this.daoManager = daoManager;
	}
	
	public void createTable() throws SQLException {
		Connection conn = daoManager.getConnection();
		String sql = "create table if not exists instituicao(idInstituicao serial, nomeInstituicao varchar(50),primary key(idInstituicao))";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeQuery();
	}
	
	public void inserirInstituicao(Instituicao instituicao) {
		
		Connection conn = null;
		try {
			conn = daoManager.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "INSERT INTO instituicao (nomeinstituicao, cidade) VALUES(? ,?) RETURNING 'idinstituicao'";
        try {
			PreparedStatement stmt = conn.prepareStatement(sql);	
			stmt.setString(1, instituicao.getNomeInstituicao());
			stmt.setString(2, instituicao.getNomeCidade());
			stmt.executeQuery();
			
			ResultSet keys = stmt.getGeneratedKeys();
			while(keys.next()) {
				instituicao.setIdINstituicao(keys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}