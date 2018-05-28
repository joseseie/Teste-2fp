/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package visao;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 * @author  Amade Ernesto
 * @author Jofil
 */



public class TelaLogin extends JFrame implements ActionListener{
    JPanel butoesTela = new JPanel();
    JPanel TTela = new JPanel();
    JPanel TTela1 = new JPanel();
    JPanel JPCentro = new JPanel();
    JPanel JPMeio = new JPanel();
    JPanel JPDireito = new JPanel();
    JPanel JPEsquerdo = new JPanel();
    
    JLabel Username = new JLabel("Username");
    JLabel Password = new JLabel("Password");
    JButton Entrar = new JButton("Entrar");
    JButton Cancelar = new JButton("Cancelar");
    JTextField UserField = new JTextField(20);
    JPasswordField Passfield = new JPasswordField(20);
    
    
   
     
      public TelaLogin() 
    {setSize(400,400);
        setTitle("Login");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         
   JPCentro.setLayout(new MigLayout());
 
   JPCentro.add(Username);
   JPCentro.add(UserField,"wrap");
   JPCentro.add(Password);
   JPCentro.add(Passfield,"wrap");
   JPCentro.add(Entrar);
   JPCentro.add(Cancelar);
   
  Entrar.addActionListener(this);
  Cancelar.addActionListener(this);
   
     add(JPCentro);
     this.setVisible(true);
     
     
     
     
 
    
    }
      
      
//        private void JbuttonCancelarActionPerformed(java.awt.event.ActionEvent Avt){
//           System.exit(0);
//       }


        
  
        public static void main(String[] args) {
            
            
            new TelaLogin().setVisible(true);
            
        }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()== Entrar){
        
            //verificar username = 123
            //senh = 123
            if (UserField.getText().equals("123") && (new String(Passfield.getPassword())).equals("123")) {
                JOptionPane.showMessageDialog(this, "Login successfull");
                
                new FormularioEstudante().setVisible(true);
                dispose();
                
            } else {
                JOptionPane.showMessageDialog(this, "Usernme ou password incorrecos");
            }
            
        
        }
        
        if (ae.getSource()==Cancelar){
        
           dispose();
        
        }
        
      
    }
    
    

    
        
   
    }