
/*
@Author Gilbertsb
*/
import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;

    //Public class with main method
    public class Loginfrm extends JFrame {
        private JTextField card_login;
        private JPasswordField pin_login;
        private JLabel card_lblgn;
        private JLabel pin_lblgn;
        private JButton login;
        private JButton cancel;
        Connection con;
        Statement st;
        ResultSet rs;
        public String y;
        public String x;

        //main method
        public Loginfrm() {
            super("Login"); //Title of check balance
            Image img = Toolkit.getDefaultToolkit().getImage("D:/school/THIS YEAR20/programming/class/ATM-ProjectGUI/src/icon.png");
            setIconImage(img);
            setResizable(false);
            setSize(200, 200);
            setVisible(true);
            setLayout(new FlowLayout(FlowLayout.CENTER));
            card_lblgn = new JLabel("Enter YOUR CARD NUMBER");  //Text field to tak card number
            add(card_lblgn);
            card_login = new JTextField(20);
            add(card_login);
            //Text field to take pin from user
            pin_lblgn = new JLabel("Enter YOUR PIN                       ");
            add(pin_lblgn);
            pin_login = new JPasswordField(20);
            add(pin_login);


            //Check balance button
            login = new JButton("LOGIN");
            login.setSize(15, 3);
            login.setFont(new Font("Century Gothic", Font.BOLD, 15));
            login.setBackground(Color.LIGHT_GRAY);
            add(login);
            //Action listner to the checkbalance button
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        y = card_login.getText();
                        x = pin_login.getText();
                        int w = Integer.parseInt(y.trim());//converting string into number
                        int z = Integer.parseInt(x.trim()); //converting string into number
                        connect(w, z);    //Calling connection method
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "INCORRECT CARD OR PIN");
                    }
                }
            });

            //Back to menu form
            cancel = new JButton("CLOSE");
            cancel.setFont(new Font("Century Gothic", Font.BOLD, 15));
            cancel.setBackground(Color.LIGHT_GRAY);
            add(cancel);
            //action listner to backmenu
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); //Make the current disappear
                    JOptionPane.showMessageDialog(null, "THANKS FOR USING THIS ATM");
                    System.exit(0);//Exit the program

                }
            });

        }

        public void connect(int card_ceck, int pin_ceck) {

            try {
                String dbdir = "C:/db_atm"; //Create directory in C:
                File f = new File(dbdir);    //call File class
                if (!f.exists())          //If the folder doesn't exist
                    f.mkdir();             // create it
                String dbName = "atm.accdb";  // the file name(Database name)
                String dbPath = dbdir + "/" + dbName;   // create Path
                File f2 = new File(dbPath);          //Call File class
                if (!f2.exists()) {                //If the path doesn't exist
                    InputStream istream = Loginfrm.class.getResourceAsStream("database/" + dbName);  //Set the path
                    try {
                        Files.copy(istream, f2.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IIOException e) {
                        e.printStackTrace();
                    }
                }

                //Connecting the form to the database with using the path of where the database is located
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + dbPath);
                Statement st = con.createStatement();
                //Creating query to select from database
                String sql = "SELECT * FROM atm WHERE card_number='" + card_ceck + "' AND pin='" + pin_ceck + "'";
                ResultSet rs = st.executeQuery(sql);//executing query
                if (rs.next()) {
                    dispose();
                    Menuform border = new Menuform();     //calling the Menu
                    border.setSize(500, 300);
                    border.getContentPane().setBackground(Color.GRAY);
                    border.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(null, " Incorrect Card number or pin");
                }

            } catch (SQLException var5) {
                System.err.println("Unable to connect");//Error will occur if you are unable to connect to the database
                var5.printStackTrace();
            } catch (Exception ex) { //If card number and pin don't match
                JOptionPane.showMessageDialog(null, "Card number and pin don't match");
                Menuform border = new Menuform();
                border.setVisible(false); //an exception occur

            }
        }
    }









