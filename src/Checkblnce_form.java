/*
@Author Gilbert,Alice,Neba
 */
//importing libraries
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
//Check balance form public class
public class Checkblnce_form extends JFrame {
    private JTextField card_ceckk;
    private JPasswordField pin_ceckk;
    private JRadioButton currentRadio = new JRadioButton("Current");
    private JRadioButton savingsRadio= new JRadioButton("Savings");
    private ButtonGroup radios=new ButtonGroup();
    private JLabel card_lb;
    private JLabel pin_lb;
    private JButton check;
    private JButton backtomenu;
    Connection con;
    Statement st;
    ResultSet rs;
    public String z;
    public String w;
     //Public constructor
    public Checkblnce_form(){
        super("Check Balance"); //Title of check balance
        Image img = Toolkit.getDefaultToolkit().getImage("D:/school/THIS YEAR20/programming/class/ATM-ProjectGUI/src/icon.png");
        setIconImage(img);
        setResizable(false);
        setSize(200, 200);
        setVisible(true);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        card_lb=new JLabel("RE-Enter YOUR CARD NUMBER");  //Text field to tak card number
        add(card_lb);
        card_ceckk=new JTextField(20);
        add(card_ceckk);
        //Text field to take pin from user
        pin_lb=new JLabel("RE-Enter YOUR PIN                                    ");
        add(pin_lb);
        pin_ceckk=new JPasswordField(20);
        add(pin_ceckk);

        //Radio buttons to choose either Current or Saving
        ButtonGroup radios=new ButtonGroup();
        radios.add(savingsRadio);
        radios.add(currentRadio);
        add(savingsRadio);
        add(currentRadio);
        savingsRadio.setSelected(true);

        //Check balance button
        check=new JButton("CHECK BALANCE");
        check.setSize(15,3);
        check.setFont(new Font("Century Gothic", Font.BOLD, 15));
        check.setBackground(Color.LIGHT_GRAY);
        add(check);
        //Action listner to the checkbalance button
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    z=card_ceckk.getText();
                    w=pin_ceckk.getText();
                    int x = Integer.parseInt(z.trim());//converting string into number
                    int y =Integer.parseInt(w.trim()); //converting string into number
                    connect(x,y);    //Calling connection method
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"INCORRECT CARD OR PIN");
                }
            }
        });

         //Back to menu form
        backtomenu=new JButton("BACK TO MENU");
        backtomenu.setFont(new Font("Century Gothic", Font.BOLD, 15));
        backtomenu.setBackground(Color.LIGHT_GRAY);
        add(backtomenu);
        //action listner to backmenu
        backtomenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //make current form disappear
                Menuform border=new Menuform();
                border.setSize(500,300); //Setting th form size
                border.getContentPane().setBackground(Color.GRAY);
                border.setVisible(true);

            }
        });

    }
    public void connect(int card_ceck,int pin_ceck){

        try
        {

              //Connecting to the database with using path
            Connection con=DriverManager.getConnection("jdbc:ucanaccess:////C:/db_atm/atm.accdb");
            Statement st = con.createStatement();
            //Query to select data from database
            String sql = "SELECT * FROM atm WHERE card_number='" + card_ceck + "' AND pin='" + pin_ceck + "'";
             //executing query
            ResultSet rs = st.executeQuery(sql);

              //Loop to go through table
            if(rs.next()){
                int balance_sv=rs.getInt("balance_sv");
                int balance_cr=rs.getInt("balance_cr");
                //check is saving radio button is selected
                if(savingsRadio.isSelected()){
                    JOptionPane.showMessageDialog(null,"Balance is "+balance_sv);

                }
                //check is current radio button is selected
                else if(currentRadio.isSelected()){
                    JOptionPane.showMessageDialog(null,"Balance is "+balance_cr);

                }
                //if nothing selected
                if(rs==null){
                    JOptionPane.showMessageDialog(null,"Fill all fields");
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Incorrect Card number or pin");
            }
        //Any sql exception
        } catch (SQLException var5) {
                JOptionPane.showMessageDialog(null,"Not connected to the database");
            }
    }

}
