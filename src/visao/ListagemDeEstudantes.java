/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controlo.Controle;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Frame.NORMAL;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.awt.image.ImageObserver.ERROR;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import modelo.Estudante;
import modelo.ModeloTabela;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Jofil
 */
public class ListagemDeEstudantes extends JFrame implements ActionListener{
    
    JLabel tabelaLabel;
    JButton Cadastrados, Removidos, CadastradosHoje, Voltar;
    Color cor = new Color(20,200,60);
    Color cor2 = new Color(50,70,160);
    Image imagem;
    ImageIcon Pic;
    
    JTable tabela = new JTable(20, 3);
    JScrollPane scrol = new JScrollPane(tabela);
    ArrayList<Estudante> listDosEstudantes;
    int editIndex = -1;
    String tipo = "Cadastrados";

    
    
    public ListagemDeEstudantes() {
            
            setSize(900,700);
        setTitle("Listagem de usuários");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel thePanel = new JPanel();
        thePanel.setLayout(new BorderLayout());

     tabelaLabel = new JLabel("Estudantes cadastrados");
     Cadastrados = new JButton("Cadastrados");
     Removidos = new JButton("Removidos");
     CadastradosHoje = new JButton("CadastradosHoje");
     Voltar = new JButton("Voltar");

    
     
      JPanel thePanel2 = new JPanel();
     thePanel2.setLayout(new MigLayout());

        thePanel2.add(Cadastrados, "");
        thePanel2.add(CadastradosHoje, "");
        thePanel2.add(Removidos, "");
        thePanel2.add(Voltar,"wrap");
        
        thePanel2.add(tabelaLabel,"wrap" );
        thePanel2.add(scrol,"wrap");
        
        
        Cadastrados.addActionListener(this);
        Removidos.addActionListener(this);
        CadastradosHoje.addActionListener(this);
        Voltar.addActionListener(this);
        
        thePanel.add(thePanel2, BorderLayout.WEST);
        
        this.add(thePanel);
        this.setVisible(true);
        
        
        this.preencherTabela();
               
    }
    
    public void preencherTabela () {
    
        ArrayList dados = new ArrayList();
        String[] colunas = {"Nome ", "Curso", "Numero do Est.", "Data de Nasc."};
        
        Controle controle = new Controle(new File("").getAbsolutePath()+"estudantes.dat");

        listDosEstudantes = controle.listarTodos();
        
        for (Estudante e : listDosEstudantes) {
            
            System.out.println("DataCad " + e.getDataCadastro());
            
            if (tipo.equals("Cadastrados") && !e.isStatusRemovido())
            {
                
                dados.add(new Object[]{e.getNome(), e.getCurso(),
                    e.getNumeroDoEstudante(), e.getDataDeNascimento()});
                
            } 
            else if (tipo.equals("CadastradosHoje") 
                    && e.getDataCadastro() != null
                    && e.getDataCadastro().equals(new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH)))
            {
                
                dados.add(new Object[]{e.getNome(), e.getCurso(),
                    e.getNumeroDoEstudante(), e.getDataDeNascimento()});
                
            }
            else if (tipo.equals("Removidos") && e.isStatusRemovido())
            {
                
                dados.add(new Object[]{e.getNome(), e.getCurso(),
                    e.getNumeroDoEstudante(), e.getDataDeNascimento()});
                
            }
            
        }
        
        ModeloTabela modeloT = new ModeloTabela(dados, colunas);
        
        tabela.setModel(modeloT);

    }
    
    private boolean textoPesquisa (Estudante e) {
        
        if(tipo.length() == 0) return true;
        
        return e.getNome().toLowerCase().contains(tipo.toLowerCase()) 
                || e.getCurso().toLowerCase().contains(tipo.toLowerCase());
    }
    
    
 
    
    public static void main(String[] args) {
        new ListagemDeEstudantes();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == Cadastrados) {
                        
            tipo = "Cadastrados";
            tabelaLabel.setText("Estudantes Cadastrados");
            preencherTabela();
            
        } else if (ae.getSource() == CadastradosHoje) {
            
            tipo = "CadastradosHoje";
            tabelaLabel.setText("Estudantes cadastrados Hoje");
            preencherTabela();
            
        } else if (ae.getSource() == Removidos) {
            
            tipo = "Removidos";
            tabelaLabel.setText("Estudantes removidos");
            preencherTabela();
            
        } else if (ae.getSource() == Voltar) {
            
            new FormularioEstudante().setVisible(true);
            dispose();
            
        }
      
       


    }
    
    
    public void gravarOrEditar(Estudante e) {
                
        Controle controle = new Controle(new File("").getAbsolutePath()+"estudantes.dat");
        
        if (editIndex == -1) {
            
            listDosEstudantes.add(e);
            controle.salvar(listDosEstudantes); //grava novos estudantes
            
        } else {
            
            listDosEstudantes.set(editIndex, e); //actualizacao
            controle.salvar(listDosEstudantes); 
            
            editIndex = -1;

        }
        
        
        this.preencherTabela();
        
    }
    
    public void deletar () {
        
        Controle controle = new Controle(new File("").getAbsolutePath()+"estudantes.dat");
        
        if (editIndex > -1) {
            
            Estudante e = listDosEstudantes.get(editIndex);
            
            e.setStatusRemovido(true);
            
            listDosEstudantes.set(editIndex, e);
            controle.salvar(listDosEstudantes); 
            
            this.preencherTabela();
            editIndex = -1;
            
        } else {
            JOptionPane.showMessageDialog(this, "Selecione alguma linha");
        }
       
        
        
    }

    
}