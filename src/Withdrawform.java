/*
@Author Gilbertsb
 */
//importing libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
//Withdraw form public class
public class Withdrawform extends JFrame{
    private JLabel card_lbl;
    private JLabel pin_lbl;
    private JLabel with_lble;
    private JTextField card_with;
    private JPasswordField pin_with;
    private JTextField amount_with;
    private JRadioButton currentRadiow;
    private JRadioButton savingsRadiow;
    private ButtonGroup radios;
    private JButton withdrw;
    private JButton backtomenu;

    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement ps;

    //Change pin constructor
   public Withdrawform(){
       super("Withdraw");//Title
       Image img = Toolkit.getDefaultToolkit().getImage("D:/school/THIS YEAR20/programming/class/ATM-ProjectGUI/src/icon.png");
       setIconImage(img);

       setResizable(false);

       setSize(350, 350);
       setVisible(true);
       setLayout( new FlowLayout(FlowLayout.CENTER));

       //Getting card number from user
       card_lbl=new JLabel("RE-Enter YOUR CARD NUMBER");
       add(card_lbl);
       card_with=new JTextField(20);
       add(card_with);

       //Getting pin from user
       pin_lbl=new JLabel("RE-Enter YOUR PIN                        ");
       add(pin_lbl);
       pin_with=new JPasswordField(20);
       add(pin_with);

       //Getting withdraw amount from user
       with_lble=new JLabel("Enter AMOUNT TO WITHDRAW          ");
       add(with_lble);
       amount_with=new JTextField(20);
       add(amount_with);

       //Radio buttons to choose either Current or Saving
       radios=new ButtonGroup();
       savingsRadiow= new JRadioButton("Savings");
       radios.add(savingsRadiow);
       currentRadiow = new JRadioButton("Current");
       radios.add(currentRadiow);
       add(savingsRadiow);
       add(currentRadiow);
       savingsRadiow.setSelected(true);

       //Withdraw button
       withdrw=new JButton("WITHDRAW");
       withdrw.setSize(15,3);
       withdrw.setFont(new Font("Century Gothic", Font.BOLD, 15));
       withdrw.setBackground(Color.LIGHT_GRAY);
       add(withdrw);
       //Action listner to withdraw button
       withdrw.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               try {
                   String c=card_with.getText();
                   String p=pin_with.getText();
                   String wi=amount_with.getText();
                   int x = Integer.parseInt(c.trim());  //Converting string to int
                   int y =Integer.parseInt(p.trim());    //Converting string to int
                   int z =Integer.parseInt(wi.trim());   //Converting string to int
                   connect(x,y,z);    //calling connection method
               }catch (Exception ex){   //Catching any exception
                   JOptionPane.showMessageDialog(null,"Fill all Fields");
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
               dispose();   //make current form disappear
               Menuform border=new Menuform();
               border.setSize(500,300);  //Setting th form size
               border.getContentPane().setBackground(Color.GRAY);
               border.setVisible(true);

           }
       });


   }

    public void connect(int card_ch,int pin_ch, int with_amount ){

        try
        {
            //Connecting to the database with using path
            Connection con= DriverManager.getConnection("jdbc:ucanaccess:////C:/db_atm/atm.accdb");
            Statement st = con.createStatement();
            String sql = "SELECT * FROM atm WHERE card_number='" + card_ch + "' AND pin='" + pin_ch + "'";
            //Query to select data from database
            ResultSet rs = st.executeQuery(sql);

            //Loop to go through table
            if(rs.next()){
                int balance_sv=rs.getInt("balance_sv");
                int balance_cr=rs.getInt("balance_cr");
                //check is saving radio button is selected
                if(savingsRadiow.isSelected()){
                    if(balance_sv>with_amount){ //Checking if balance is greater than withdraw amount
                        int new_balance=balance_sv-with_amount;
                        //Updating the table records
                        ps = con.prepareStatement("UPDATE atm SET balance_sv = '" +new_balance + "'WHERE pin = '" + pin_ch + "'AND card_number='" + card_ch + "'");
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Take your money "+with_amount);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"    Insufficient Balance");
                    }

                }
                else if(currentRadiow.isSelected()){  //check is current radio button is selected
                    if(balance_cr>with_amount){       //Checking if balance is greater than withdraw amount
                        int new_balance_cr=balance_cr-with_amount;
                        //Updating the table records
                        ps = con.prepareStatement("UPDATE atm SET balance_cr = '" +new_balance_cr + "'WHERE pin = '" + pin_ch + "'AND card_number='" + card_ch + "'");
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Take your money "+with_amount);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"    Insufficient Balance");
                    }

                }

            }
            else {
                JOptionPane.showMessageDialog(null,"Incorrect Card number or pin");
            }


        } catch (SQLException var5) {       //Catching any sql exception
            System.err.println("Unable to connect");
            var5.printStackTrace();
            JOptionPane.showMessageDialog(null,"Pin Didn't Chang Try again plz");
        }
    }

}
