/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.AcessoMySQL;

/**
 *
 * @author Deivid Facio
 */
public class AlunosControl {

    PreparedStatement pstm;
    ResultSet rs;

    //Códigos SQL
    String cadastrarAluno = "INSERT INTO ALUNOS(NOME, CPF, EMAIL, CELULAR, ENDERECO) VALUES (?,?,?,?,?)";
    String consultarAluno = "SELECT * FROM ALUNOS";
    String alterarAluno = "UPDATE ALUNOS SET NOME=?, CPF=?, EMAIL=?, CELULAR=?, ENDERECO=? WHERE ID_ALUNOS=?";
    String excluirAluno = "DELETE FROM ALUNOS WHERE ID_ALUNOS = ?";

    public void cadastrarAluno(AlunosBean aluno) {
        try {
            AcessoMySQL mySql = new AcessoMySQL();
            pstm = mySql.conectar().prepareStatement(cadastrarAluno);
            pstm.setString(1, aluno.getNomeAluno());
            pstm.setString(2, aluno.getCpfAluno());
            pstm.setString(3, aluno.getEmailAluno());
            pstm.setString(4, aluno.getCelAluno());
            pstm.setString(5, aluno.getEndAluno());
            pstm.executeUpdate();
            mySql.desconectar();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar Contato" + ex);
        }
    }

    public void alterarAluno(AlunosBean aluno) {
        try {
            AcessoMySQL mySql = new AcessoMySQL();
            String alterarAluno = "UPDATE ALUNOS SET NOME=?, CPF=?, EMAIL=?, CELULAR=?, ENDERECO=? WHERE ID_ALUNOS=?";
            pstm = mySql.conectar().prepareStatement(alterarAluno);

            if (aluno.getNomeAluno().isEmpty() || aluno.getCpfAluno().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome e CPF são campos obrigatórios.");
                return;
            }

            pstm.setString(1, aluno.getNomeAluno());
            pstm.setString(2, aluno.getCpfAluno());
            pstm.setString(3, aluno.getEmailAluno());
            pstm.setString(4, aluno.getCelAluno());
            pstm.setString(5, aluno.getEndAluno());
            pstm.setInt(6, aluno.getIdAluno());
            pstm.executeUpdate();
            mySql.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar Aluno" + ex);
        }
    }

    public void excluirAluno(int idAluno) {
        try {
            AcessoMySQL mySql = new AcessoMySQL();
            String excluirAluno = "DELETE FROM ALUNOS WHERE ID_ALUNOS = ?";

            pstm = mySql.conectar().prepareStatement(excluirAluno);
            pstm.setInt(1, idAluno);
            pstm.executeUpdate();
            mySql.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir aluno: " + ex);
        }
    }

    public List<AlunosBean> listarAlunos() {
        List<AlunosBean> aluno = new ArrayList();
        try {
            AcessoMySQL mySql = new AcessoMySQL();
            pstm = mySql.conectar().prepareStatement(consultarAluno);
            rs = pstm.executeQuery();
            AlunosBean listAlunos;
            while (rs.next()) {
                listAlunos = new AlunosBean();
                listAlunos.setIdAluno(rs.getInt("ID_ALUNOS"));
                listAlunos.setNomeAluno(rs.getString("NOME"));
                listAlunos.setCpfAluno(rs.getString("CPF"));
                listAlunos.setEmailAluno(rs.getString("EMAIL"));
                listAlunos.setCelAluno(rs.getString("CELULAR"));
                listAlunos.setEndAluno(rs.getString("ENDERECO"));
                aluno.add(listAlunos);
            }
            mySql.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao listar Informações de Aluno" + ex);
        }
        return aluno;
    }

}
