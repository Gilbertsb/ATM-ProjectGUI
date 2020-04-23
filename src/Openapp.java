/*
@Author Gilbert,Alice,Neba
 */
import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;

//Public class with main method
public class Openapp extends JFrame{
    //main method
    public static void main(String[] args){
        Loginfrm open = new Loginfrm();     //calling the Menu
        open.setSize(400, 150);
        open.getContentPane().setBackground(Color.GRAY);
        open.setVisible(true);

    }
}

