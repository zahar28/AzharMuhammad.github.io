
package frame;

import com.mysql.jdbc.PreparedStatement;
import db.Koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Penerbit;


public class PenerbitTambahFrame extends JFrame {
    int status;
    
    private final int SEDANG_TAMBAH = 101;
    private final int SEDANG_UBAH = 102;
    
    JLabel jLabel1 = new JLabel("id");
    JLabel jLabel2 = new JLabel("Penerbit");
    
    JTextField eid = new JTextField();
    JTextField epenerbit = new JTextField();
    
    JButton bSimpan = new JButton("Save");
    JButton bBatal = new JButton ("Cancel");
    

public void setKomponen (){
    getContentPane().setLayout(null);
    getContentPane().add (jLabel1);
    getContentPane().add (jLabel2);
    getContentPane().add (eid);
    getContentPane().add (epenerbit);
    getContentPane().add (bSimpan);
    getContentPane().add (bBatal);
    
    jLabel1.setBounds(70,10,50,25);
    jLabel2.setBounds(30,40,50,25);
    
    eid.setBounds(100,10,50,25);
    epenerbit.setBounds(100,40,270,25);
    
    bSimpan.setBounds(160,70,100,25);
    bBatal.setBounds(270,70,100,25);
    
    eid.setEditable(false);
    setVisible(true);
    epenerbit.requestFocus();
    setListener();







}

    public PenerbitTambahFrame() {
    status = SEDANG_TAMBAH;
    setSize (420,180);
    setLocationRelativeTo (null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setKomponen();
    }
    public PenerbitTambahFrame(Penerbit penerbit) {
    status = SEDANG_UBAH;
    setSize (420,180);
    setLocationRelativeTo (null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    eid.setText(String.valueOf(penerbit.getId()));
    epenerbit.setText(penerbit.getPenerbit());
    setKomponen();
    }

    public void setListener (){
        bBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            
            }
        });
         bSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Koneksi koneksi = new Koneksi();
                    Connection con = koneksi.getConnection();
                    PreparedStatement ps;
                    if (status== SEDANG_TAMBAH){
                        String executeQuery = "insert into tb_penerbit (penerbit) value (?)";
                        ps = (PreparedStatement) con.prepareStatement(executeQuery);
                        ps.setString(1, epenerbit.getText());
                    
                    }else {
                        String executeQuery = "update tb_penerbit set penerbit = ? where id=?";
                        ps = (PreparedStatement) con.prepareStatement(executeQuery);
                        ps.setString(1, epenerbit.getText());
                        ps.setString(2, eid.getText());
                    }
                    ps.executeUpdate();
                } catch (SQLException ex){
                    System.err.println(ex);
                }
                dispose();
            
            }
        });
    
    }

}
