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
public class FormularioEstudante extends JFrame implements ActionListener{
    
    JLabel Nome, NumeroDoEstudante, DataDeNascimento, Curso, Pesquisa;
    JButton Salvar, Listar, Deletar;
    JTextField NomeField, CursoField , NumeroDoEstudanteField, DataDeNascimentoField, PesquisaField;
    Color cor = new Color(20,200,60);
    Color cor2 = new Color(50,70,160);
    Image imagem;
    ImageIcon Pic;
    
    JTable tabela = new JTable(20, 3);
    JScrollPane scrol = new JScrollPane(tabela);
    ArrayList<Estudante> listDosEstudantes;
    Estudante estudanteSelecionado = null;
    String txtPesquisa = "";

    
    
    public FormularioEstudante() {
            
            setSize(900,700);
        setTitle("Registo  de estudantes");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel thePanel = new JPanel();
        thePanel.setLayout(new BorderLayout());
        
     Nome = new JLabel("Nome");
     NumeroDoEstudante = new JLabel("Numero Do Estudante");
     Curso = new JLabel("Curso");
     Pesquisa = new JLabel("Pesquise pelo NOME ou CURSO");
     DataDeNascimento = new JLabel("Data De Nascimento");
//     Tipo        = new JLabel("Tipo");
     Salvar = new JButton("Salvar");
     Listar = new JButton("Listagem de Estudantes");
     Deletar = new JButton("Deletar");
     NomeField = new JTextField(20);
     CursoField = new JTextField(20);
     PesquisaField = new JTextField(20);
     NumeroDoEstudanteField = new JTextField(20);
     DataDeNascimentoField = new JPasswordField(20);
//     TipoCombo  = new JComboBox<String>();
     
     
      JPanel thePanel2 = new JPanel();
     thePanel2.setLayout(new MigLayout());
        
        
        thePanel2.add(Nome);
        thePanel2.add(NomeField,"wrap");
        thePanel2.add(Curso);
        thePanel2.add(CursoField,"wrap");
        thePanel2.add(NumeroDoEstudante);
        thePanel2.add(NumeroDoEstudanteField,"wrap");
        thePanel2.add(DataDeNascimento);
        thePanel2.add(DataDeNascimentoField,"wrap");
        thePanel2.add(Salvar, "");
        thePanel2.add(Deletar, "");
        thePanel2.add(Listar, "wrap");
        
        thePanel2.add(Pesquisa,"wrap" );
        thePanel2.add(PesquisaField, "wrap");
        thePanel2.add(scrol,"");
        
        Salvar.setForeground(cor);
        Salvar.setBackground(cor2);
        Deletar.setBackground(cor2);
        Salvar.addActionListener(this);
        Deletar.addActionListener(this);
        Listar.addActionListener(this);
        Listar.setForeground(cor);
        Listar.setBackground(cor2);
        
        thePanel.add(thePanel2, BorderLayout.WEST);
        
        this.add(thePanel);
        this.setVisible(true);
        
        
        this.preencherTabela();
        
        eventosTabela();
       
    }
    
    public void preencherTabela () {
    
        ArrayList dados = new ArrayList();
        String[] colunas = {"Nome ", "Curso", "Numero do Est.", "Data de Nasc."};
        
        Controle controle = new Controle(new File("").getAbsolutePath()+"estudantes.dat");

        listDosEstudantes = controle.listarTodos();
        
        for (Estudante e : listDosEstudantes) {
            
            if (!e.isStatusRemovido())
            {                
                if (textoPesquisa(e)) { //chamando o metodo que faz pesquisa
                    dados.add(new Object[]{e.getNome(), e.getCurso(),
                    e.getNumeroDoEstudante(), e.getDataDeNascimento()});
                }
                
            }
            
        }
        
        ModeloTabela modeloT = new ModeloTabela(dados, colunas);
        
        tabela.setModel(modeloT);

    }
    
    private boolean textoPesquisa (Estudante e) {
        
        if(txtPesquisa.length() == 0) return true;
        
        return e.getNome().toLowerCase().contains(txtPesquisa.toLowerCase()) 
                || e.getCurso().toLowerCase().contains(txtPesquisa.toLowerCase());
    }
    
    
 
    
    public static void main(String[] args) {
        new FormularioEstudante();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == Salvar) {
                        
            Estudante e = new Estudante();
            
            e.setCurso(CursoField.getText());
            e.setDataDeNascimento(DataDeNascimentoField.getText());
            e.setNome(NomeField.getText());
            e.setNumeroDoEstudante(Integer.parseInt(NumeroDoEstudanteField.getText()));
            //gravarmos...
            
            gravarOrEditar(e);
        } else if (ae.getSource() == Deletar) {
            
            deletar();
            
        } else if (ae.getSource() == Listar) {
            
            new ListagemDeEstudantes().setVisible(true);
            dispose();
            
        }
       


    }
    
    
    public void gravarOrEditar(Estudante e) {
                
        Controle controle = new Controle(new File("").getAbsolutePath()+"estudantes.dat");
        
        if (estudanteSelecionado == null) {
            
            listDosEstudantes.add(e);
            controle.salvar(listDosEstudantes); //grava novos estudantes
            
        } else {
            
            listDosEstudantes.set(listDosEstudantes.indexOf(estudanteSelecionado), e); //actualizacao
            controle.salvar(listDosEstudantes); 
            
            estudanteSelecionado = null;

        }
        
        
        this.preencherTabela();
        this.limparCampos();
        
    }
    
    public void deletar () {
        
        Controle controle = new Controle(new File("").getAbsolutePath()+"estudantes.dat");
        
        if (estudanteSelecionado != null) {
            
            Estudante e = estudanteSelecionado;
            
            e.setStatusRemovido(true);
            
            listDosEstudantes.set(listDosEstudantes.indexOf(estudanteSelecionado), e);
            controle.salvar(listDosEstudantes); 
            
            this.preencherTabela();
            this.limparCampos();
            estudanteSelecionado = null;
            
        } else {
            JOptionPane.showMessageDialog(this, "Selecione alguma linha");
        }
       
        
        
    }
          
    public Estudante pesquisarEst (String nome) {
        
        for (Estudante e : listDosEstudantes) {
            if (e.getNome().equals(nome)) return e;
        }
        
        return null;
    }
    
    public void eventosTabela () {
        
        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                String nome = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
                                
                Estudante est = pesquisarEst(nome);
                
                estudanteSelecionado = est;
                
                CursoField.setText(est.getCurso());
                DataDeNascimentoField.setText(est.getDataDeNascimento());
                NomeField.setText(est.getNome());
                NumeroDoEstudanteField.setText(est.getNumeroDoEstudante() + "");


            }

            @Override
            public void mousePressed(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        PesquisaField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
                txtPesquisa = PesquisaField.getText();
                preencherTabela();

            }
        });
    }
    
    
    public void limparCampos () {
        
        CursoField.setText("");
        DataDeNascimentoField.setText("");
        NomeField.setText("");
        NumeroDoEstudanteField.setText("");
        
    }
    
}