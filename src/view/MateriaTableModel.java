package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.MateriaDao;
import model.EstadoMateria;
import model.Evento;
import model.Materia;
import model.Professor;

public class MateriaTableModel extends AbstractTableModel {
	
	private static final int COL_MATERIA= 0;
	private static final int COL_PROFESSOR = 1;
	private static final int COL_CARGA= 2;
	
	List<Materia> materias;
	
	public MateriaTableModel() {
		this.materias = new ArrayList<>();
		this.materias = MateriaDao.materiasEstado(EstadoMateria.PENDENTE);
	}
	
	public MateriaTableModel(List<Materia> materias){
		this.materias = materias;
	}
	
	public void addRow(Materia m){
		this.materias.add(m);
		this.fireTableDataChanged();
	}
	
	public void removeRow(int linha){
		this.materias.remove(linha);
		this.fireTableRowsDeleted(linha, linha);
	}

	public Materia get(int linha){
		return this.materias.get(linha);
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return materias.size();
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case COL_MATERIA:
			return "Matéria";
		case COL_PROFESSOR:
			return "Professor";
		case COL_CARGA:
			return "Carga";
		default:
			return "";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Materia materia = materias.get(rowIndex);
		switch (columnIndex) {
		case COL_MATERIA:
			return materia.getNomeMateria();
		case COL_PROFESSOR:
			return materia.getNomeProfessor();
		case COL_CARGA:
			return materia.getCargaHoraria();
		default:
			return "";
		}
	}

}
