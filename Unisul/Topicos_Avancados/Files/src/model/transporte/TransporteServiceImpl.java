package model.transporte;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.aluno.Aluno;

public class TransporteServiceImpl implements TransporteService{
	
	private static final String TRANPORTE_TXT = "transportes.txt";
	private static final String ALUNOS_TXT = "alunos.txt";
	private static final String UNIDOS_TXT = "unidos.txt";

	@Override
	public void lerArquivo(List<Transporte> transportes, TableView<Transporte> tableView) {
		
		transportes.clear();
		
		try(
				FileReader fileReader = new FileReader(TRANPORTE_TXT);
				BufferedReader bufferedReader = new BufferedReader(fileReader)
			) {
			
			
			String linha="";
			
			while ((linha = bufferedReader.readLine()) != null) {
				Transporte transporte = new Transporte();

				String[] linhas = linha.split(",");
				
				transporte.setTipoTranposte(linhas[0]);
				transporte.setDistancia(Double.parseDouble(linhas[1]));
				transporte.setCustoMensal(Double.parseDouble(linhas[2]));
				transportes.add(transporte);
			}
			
			tableView.setItems(FXCollections.observableArrayList(transportes));
			
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

	@Override
	public void gravar(RadioButton rdProprio, RadioButton rdPublico, TextField txtDistancia, TextField txtCustoMensal) {
		try (
				FileWriter fileWriter = new FileWriter(TRANPORTE_TXT, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
						
			){
			
			Transporte transporte = new Transporte();
			transporte.setTipoTranposte(transporteSelecionado(rdProprio));
			transporte.setDistancia(Double.parseDouble(txtDistancia.getText()));
			transporte.setCustoMensal(Double.parseDouble(txtCustoMensal.getText()));
			
			bufferedWriter.append(transporte.getTipoTranposte()+","+
					transporte.getDistancia()+","+
					transporte.getCustoMensal()+"\n");
			
		} catch (Exception e) {
			e.getMessage();
		}

	}

	private String transporteSelecionado(RadioButton rdProprio) {
		
		return rdProprio.isSelected() ? TipoTransporte.PROPRIO.name() : TipoTransporte.PUBLICO.name();
	}

	@Override
	public void inicializaTableView(TableColumn<Transporte, String> colTipo,
			TableColumn<Transporte, Number> colDistancia, TableColumn<Transporte, Number> colCusto) {
		
		colTipo.setCellValueFactory(cellData -> cellData.getValue().tipoTranposteProperty());
		colDistancia.setCellValueFactory(cellData -> cellData.getValue().distanciaProperty());
		colCusto.setCellValueFactory(cellData -> cellData.getValue().custoMensalProperty());
	}

	@Override
	public void unirAlunoETransporte() {
		
		try (
				FileWriter fileWriter = new FileWriter(UNIDOS_TXT, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				FileReader fileReaderTransportes = new FileReader(TRANPORTE_TXT);
				BufferedReader bufferedReaderTransportes = new BufferedReader(fileReaderTransportes);
				FileReader fileReaderAlunos = new FileReader(ALUNOS_TXT);
				BufferedReader bufferedReaderAlunos = new BufferedReader(fileReaderAlunos);
						
			){
			
			String linhaTransporte="";
			String linhaAluno = "";
			
			while (Objects.nonNull(linhaTransporte = bufferedReaderTransportes.readLine()) 
					&& Objects.nonNull(linhaAluno = bufferedReaderAlunos.readLine()) ) {
				
				Transporte transporte = new Transporte();
				Aluno aluno =  new Aluno();

				String[] linhasTranportes = linhaTransporte.split(",");
				String[] linhasAlunos = linhaAluno.split(",");
				
				aluno.setNome(linhasAlunos[0]);
				aluno.setSemestre(Integer.parseInt(linhasAlunos[1]));
				aluno.setCurso(linhasAlunos[2]);
				
				transporte.setTipoTranposte(linhasTranportes[0]);
				transporte.setDistancia(Double.parseDouble(linhasTranportes[1]));
				transporte.setCustoMensal(Double.parseDouble(linhasTranportes[2]));
				
				bufferedWriter.append(aluno.getNome()+","+
						aluno.getSemestre()+","+
						aluno.getCurso()+","+
						transporte.getTipoTranposte()+","+
						transporte.getDistancia()+","+
						transporte.getCustoMensal()+"\n");
			}
			
			
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

}
