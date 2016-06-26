package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Evento;
import model.TipoEvento;

public class EventoDao {
	private static DaoHelper daoHelper;
	
	public EventoDao() {
		EventoDao.daoHelper = new DaoHelper();
	}
	
	public static void inserirEvento(Evento evento) {
		Connection conn = daoHelper.getConnection();
		String sql = "INSERT INTO evento(idMateria, tipoEvento, dataEvento, descricao, detalhes, valorNota, localEvento) VALUES(?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, evento.getIdMateria());
			stmt.setString(2, evento.getTipoEvento());
			stmt.setDate(3, evento.getDataEvento());
			stmt.setString(4, evento.getDescricao());
			stmt.setString(5, evento.getDetalhes());
			stmt.setFloat(6, evento.getValorNota());
			stmt.setString(7, evento.getLocalEvento());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			evento.setIdEvento(rs.getInt(1));
						
			daoHelper.releaseAll(stmt, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Evento> getAll(){
		return getBy("1", 1);
	}
	
	public static ArrayList<Evento> getEventoTalQue(String operator, Date dataAtual) {
		daoHelper = new DaoHelper();
		ArrayList<Evento> listaEventos = null;
		Connection conn = daoHelper.getConnection();
		String sql = "select * from evento where dataEvento" + operator + "'" + dataAtual +"'";
		
		try {
			listaEventos = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Evento usr = new Evento(
						rs.getInt("idmateria"),
						TipoEvento.valueOf(rs.getString("tipoevento").toUpperCase() ),
						rs.getDate("dataevento"),
						rs.getString("descricao"),
						rs.getString("detalhes"),
						rs.getFloat("valornota"),
						rs.getString("localevento")
						);
				listaEventos.add(usr);
			}
			daoHelper.releaseAll(rs, stmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaEventos;
	}
	
	public static <T1> ArrayList<Evento> getBy(String nomeCampo, T1 valorCampo ) {
		daoHelper = new DaoHelper();
		ArrayList<Evento> lista = new ArrayList<>();
		Connection c = daoHelper.getConnection();
		String sql = "SELECT * FROM evento where "+nomeCampo+" = '"+valorCampo+"'";
		
		try{
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while( rs.next() )
			{
				Evento usr = new Evento(
						rs.getInt("idmateria"),
						TipoEvento.valueOf(rs.getString("tipoevento").toUpperCase() ),
						rs.getDate("dataevento"),
						rs.getString("descricao"),
						rs.getString("detalhes"),
						rs.getFloat("valornota"),
						rs.getString("localevento")
						);
				lista.add(usr);
			}
			daoHelper.releaseAll(rs, ps, c);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return lista;
	}

}
