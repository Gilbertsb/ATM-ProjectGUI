/*
@Author Gilbert,Alice,Neba
 */

//Importing Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Public class that define and hold Menu
public class Menuform extends JFrame  {
    private JLabel headerLabel;
    private final JButton withdraw;
    private final JButton checkbalance;
    private final JButton changepin;
    private final JButton exit;

//Constructor of public class Menuform
    public Menuform(){
        super("Menu");//Title
        Image img = Toolkit.getDefaultToolkit().getImage("D:/school/THIS YEAR20/programming/class/ATM-ProjectGUI/src/icon.png");
        setIconImage(img);
        setResizable(false);
        setSize(200, 200);
        setVisible(true);
        setLayout(new GridLayout(5,1,5,5));//Grid to hold Menu
         //Header lable to hold title
        headerLabel=new JLabel("  CHOOSE OPERATION YOU WANT TO PERFORM BY CLICKING ON IT");
        headerLabel.setForeground(Color.GREEN);
        headerLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        add(headerLabel);
        //Defining Withdraw button
        withdraw=new JButton("WITHDRAW");
        withdraw.setSize(15,3);
        withdraw.setFont(new Font("Century Gothic", Font.BOLD, 15));
        withdraw.setBackground(Color.LIGHT_GRAY);
        add(withdraw);
         //Action listner to the withdraw button
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //Make the current disappear
                Withdrawform withfrm=new Withdrawform();//Calling withdraw form
                withfrm.pack();
                withfrm.setSize(500,300); //Setting the size of the form
                withfrm.setVisible(true); //make the form appear
            }
        });

        //Defining Check balance button
        checkbalance=new JButton("CHECK BALANCE");
        checkbalance.setFont(new Font("Century Gothic", Font.BOLD, 15));
        checkbalance.setBackground(Color.LIGHT_GRAY);
        add(checkbalance);
         //Action listner to the check balance button
        checkbalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //Make the current disappear
                Checkblnce_form checkfrm=new Checkblnce_form(); //Calling checking balance button
                checkfrm.pack();
                checkfrm.setSize(500,300);//Setting the size of the form
                checkfrm.setVisible(true);//make the form appear
            }
        });

        //Defining Chenge pin button
        changepin=new JButton("CHANGE PIN");
        changepin.setFont(new Font("Century Gothic", Font.BOLD, 15));
        changepin.setBackground(Color.LIGHT_GRAY);
        add(changepin);
         //Action listner to the Change pin button
        changepin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //Make the current disappear
                Changepin_form changefrm=new Changepin_form();//calling change pin form
                changefrm.pack();
                changefrm.setSize(500,300); //Setting the size of the form
                changefrm.setVisible(true);//make the form appear
            }
        });

        //Defining Exit button
        exit=new JButton("EXIT");
        exit.setFont(new Font("Century Gothic", Font.BOLD, 15));
        exit.setBackground(Color.LIGHT_GRAY);
        add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //Make the current disappear
                JOptionPane.showMessageDialog(null,"THANKS FOR USING THIS ATM");
                System.exit(0);//Exit the program
            }
        });



        }
    }



