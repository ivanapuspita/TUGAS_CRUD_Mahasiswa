
import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class InputData extends JFrame{
    JLabel lnim,lnama,lalamat,ljudul;
    JTextField txnim,txnama,txalamat;
    JButton btnSimpan, btnKembali;
    Statement statement;
   
    public void InputData()
    {
        ljudul = new JLabel("Input Data Mahasiswa");
        lnim = new JLabel("NIM");
        lnama = new JLabel("Nama");        
        lalamat = new JLabel("Alamat");
        txnim = new JTextField("");
        txnama = new JTextField("");
        txalamat = new JTextField("");
        btnSimpan  = new JButton("Simpan");
        btnKembali  = new JButton("Kembali");
        
        setTitle("INPUT data mahasiswa");
        setSize(400,380);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        setLayout(null);
        add(ljudul);
        add(lnim);
        add(lnama);
        add(lalamat);
        add(txnim);
        add(txnama);
        add(txalamat);
        add(btnSimpan);
        add(btnKembali);
        
        ljudul.setBounds(50,30,300,25);
        lnim.setBounds(75, 75, 30, 20);
        lnama.setBounds(75, 100, 50, 20);
        lalamat.setBounds(75, 125, 50, 20);
        txnim.setBounds(150, 75, 150, 20);
        txnama.setBounds(150, 100, 150, 20);
        txalamat.setBounds(150, 125, 150, 100);
        btnKembali.setBounds(200,250,100,25);
        btnSimpan.setBounds(100,250,100,25);
        
        btnKembali.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Menu();
            }
        });
        
        btnSimpan.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                 btnSimpanactionListener();
            }

            private void btnSimpanactionListener() {
                KoneksiDB koneksi = new KoneksiDB();
                    try{

                    statement = koneksi.getKoneksi().createStatement();
                    statement.executeUpdate("INSERT INTO data_mhs VALUES ('" 
                            + txnim.getText() + "','" + txnama.getText() + "','" + txalamat.getText() + "')");

                    JOptionPane.showMessageDialog(null, "data berhasil di input!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
                    statement.close();

                    } catch (SQLException ex){
                        JOptionPane.showMessageDialog(null, "data gagal di input!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
                    } catch (ClassNotFoundException ex){
                        JOptionPane.showMessageDialog(null, "driver tidak ditemukan!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            
        });
    }


}
