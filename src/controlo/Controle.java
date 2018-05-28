package controlo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.HeadlessException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Estudante;

/**
 *
 * @author Yacub Mussa
 */
public class Controle {

    private String caminho;
        
    public Controle(String caminho){
        this.caminho = caminho;    
    }

    public ArrayList<Estudante> listarTodos(){
          
  
        try {
            
            FileInputStream fis = new FileInputStream(caminho);
            ObjectInputStream ois  = new ObjectInputStream(fis);
        
            
            ArrayList<Estudante> estudante  = (ArrayList<Estudante>) ois.readObject();
                            
            return estudante;
            
        }catch(EOFException ex){
            return null;
        }catch (HeadlessException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar " + ex);
            return null;
        } catch (IOException ex) {
            return null;
        }  
    }

    public boolean salvar(ArrayList<Estudante> estudante){
 
        try {
            FileOutputStream fos = new FileOutputStream(caminho);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(estudante);
            oos.close();
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar Estudante" + ex.getMessage());
            return false;
        }
    }  
}
