/*
@Author Gilbertsb
 */
//importing libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
//Chang pin form public class
public class Changepin_form extends JFrame {
    private JLabel card_lbel;
    private JLabel pin_lble;
    private JTextField card_chang;
    private JPasswordField pin_chang;
    private JButton change;
    private JLabel newpin_lble;
    private JPasswordField newpin_chang;
    private JButton backtomenu;
    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    //Change pin constructor
    public Changepin_form(){
        super("Change Pin");
        Image img = Toolkit.getDefaultToolkit().getImage("D:/school/THIS YEAR20/programming/class/ATM-ProjectGUI/src/icon.png");
        setIconImage(img);

        setResizable(false);

        setSize(350, 350);
        setVisible(true);
        setLayout( new FlowLayout(FlowLayout.CENTER));

        //Getting card number from user
        card_lbel=new JLabel("Enter YOUR CARD NUMBER");
        add(card_lbel);
        card_chang=new JTextField(20);
        add(card_chang);
        //Getting pin from user
        pin_lble=new JLabel("Enter YOUR USUAL PIN         ");
        add(pin_lble);
        pin_chang=new JPasswordField(20);
        add(pin_chang);
        //Getting new from user
        newpin_lble=new JLabel("Enter YOUR NEW PIN             ");
        add(newpin_lble);
        newpin_chang=new JPasswordField(20);
        add(newpin_chang);

        //Change pin button
        change=new JButton("CHANGE PIN");
        change.setSize(15,3);
        change.setFont(new Font("Century Gothic", Font.BOLD, 15));
        change.setBackground(Color.LIGHT_GRAY);
        add(change);
        //Action listner to change pin button
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   String c=card_chang.getText();  //
                   String p=pin_chang.getText();
                   String n=newpin_chang.getText();
                    int x = Integer.parseInt(c.trim());  //Converting string to int
                    int y =Integer.parseInt(p.trim());   //Converting string to int
                    int z =Integer.parseInt(n.trim());   //Converting string to int
                    connect(x,y,z);  //calling connection method
                }catch (Exception ex){  //Catching any exception
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
                border.setSize(500,300);  //Setting th form size
                border.getContentPane().setBackground(Color.GRAY);
                border.setVisible(true);

            }
        });


    }

    public void connect(int card_ch,int pin_ch, int new_pin ){

        try
        {
            //Connecting to the database with using path
            Connection con= DriverManager.getConnection("jdbc:ucanaccess:////C:/db_atm/atm.accdb");
            Statement st = con.createStatement();
            //Query to select data from database
            String sql = "SELECT * FROM atm WHERE card_number='" + card_ch + "' AND pin='" + pin_ch + "'";
            //executing query
            ResultSet rs = st.executeQuery(sql);

               //Message to the user
            if(rs.next()){
                ps = con.prepareStatement("UPDATE atm SET pin = '" + new_pin+ "'WHERE pin = '" + pin_ch + "'AND card_number='" + card_ch + "'");
                ps.executeUpdate();  //executing query
                JOptionPane.showMessageDialog(null,"Pin Changed Successfully");
            }
            else {
                JOptionPane.showMessageDialog(null,"Incorrect Card number or pin");
            }



        } catch (SQLException var5) { //catch any sql exception
            System.err.println("Unable to connect");
            var5.printStackTrace();
            JOptionPane.showMessageDialog(null,"Pin Didn't Chang Try again plz");
        }
    }
}
