package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dao.InstituicaoDao;
import model.Instituicao;

public class InstituicaoDaoTest {

	@Test
	public void test() {
		InstituicaoDao dao = new InstituicaoDao();
		ArrayList<Instituicao> l = dao.getAll();
		assertTrue( l.size()>0 );
	}
	
	@Test
	public void test2() {
		InstituicaoDao dao = new InstituicaoDao();
		ArrayList<Instituicao> l = dao.getBy("nomeinstituicao","UTFPR");
		assertTrue( l.size()>0 );
		
	}

}
