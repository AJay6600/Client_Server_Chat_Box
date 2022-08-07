import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

class ServerBox implements ActionListener
{

JTextArea status;
JFrame f;
JLabel l1 ,l2,l3;
Color c1;

JTextField t1;
JButton b;
String name=" ";

ServerSocket sc ;
Socket c ;
DataOutputStream ot;
DataInputStream it;
BufferedReader br ;

ServerBox()
{
setSocket();
f = new JFrame();
getResponse();

c1=new Color(0,100,255);

//f.setForeground(Color.WHITE);
Font normal=new Font(Font.MONOSPACED,Font.PLAIN,40);

l1 = new JLabel("Client Server ChatBox");
l1.setBounds(400,100,600,100);
l1.setForeground(Color.WHITE);
l1.setFont(normal);

Font normal1=new Font(Font.MONOSPACED,Font.PLAIN,30);
l2 = new JLabel("Server Side");
l2.setBounds(550,150,600,100);
l2.setForeground(Color.GREEN);
l2.setFont(normal1);

Font normal5=new Font(Font.MONOSPACED,Font.PLAIN,20);
status= new JTextArea();
status.setBounds(300,250,700,400);
status.setFont(normal5);
status.setForeground(Color.BLACK);
status.setBackground(Color.GRAY);

Font normal2=new Font(Font.MONOSPACED,Font.PLAIN,21);
l3 = new JLabel("Enter Massage :");
l3.setBounds(300,630,200,100);
l3.setForeground(Color.GREEN);
l3.setFont(normal2);

Font normal3=new Font(Font.MONOSPACED,Font.PLAIN,25);
t1 = new JTextField();
t1.setBounds(510,660,300,50);
t1.setBackground(Color.GRAY);
t1.setFont(normal3);

b = new JButton("SEND");

b.setBounds(820,660,180,50);
b.setBackground(Color.GREEN);
b.setFont(normal3);
b.addActionListener(this);

f.add(l1);
f.add(l2);
f.add(status);
f.add(l3);
f.add(t1);
f.add(b);

f.setBackground(Color.GRAY);
f.getContentPane().setBackground(Color.DARK_GRAY);
f.setLayout(null);
f.setVisible(true);

}

public void actionPerformed(ActionEvent e)
{
String text;
text=t1.getText();
name = name.concat("You: ");
name = name.concat(text);
name = name.concat("\n");

status.setText(name);
t1.setText(" ");

try
{
ot.writeUTF(text);

ot.flush();
}
catch(Exception ex)
{

}
getResponse();
}
public void setSocket()
{
try
{
sc = new ServerSocket(1800);
Socket c = sc.accept();
ot= new DataOutputStream(c.getOutputStream());
it= new DataInputStream(c.getInputStream());
br = new BufferedReader(new InputStreamReader(System.in));

}

catch(Exception es)
{
}
}
public void getResponse()
{
try{
String str2 = (String)it.readUTF();
name = name.concat("Client: ");
name = name.concat(str2);
name = name.concat("\n");
status.setText(name);
}
catch(Exception esd)
{ }
}
public static void main(String arg[])
{
new ServerBox();
}
}