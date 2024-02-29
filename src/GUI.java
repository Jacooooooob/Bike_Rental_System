import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Locale;

class Price {
    private final int price;

    public Price(int x) {
        price = x;
    }
    public void Cost() {
        JOptionPane.showMessageDialog(null, "You need to pay RMB" + price + " in total.","Prompt",JOptionPane.PLAIN_MESSAGE);
    }
}

class prompt{
    int n = 20 - GUI.total;
    public void warning() {
        JOptionPane.showMessageDialog(null, "Your input is invalid, please re-enter!", "Error",JOptionPane.ERROR_MESSAGE);
    }

    public void warning1() {
        JOptionPane.showMessageDialog(null, "Sorry, we don't have enough bikes", "Error",JOptionPane.WARNING_MESSAGE);
    }

    public void warning2() {
        JOptionPane.showMessageDialog(null, "Please check whether the rental type of the returned bike is correct.", "Error",JOptionPane.WARNING_MESSAGE);
    }

    public void warning3() {
        JOptionPane.showMessageDialog(null, "You don't need to return so many bikes. You only need to return " + n + ".", "Error",JOptionPane.WARNING_MESSAGE);
    }

    public void returnPrompt() {
        JOptionPane.showMessageDialog(null, "You have successfully returned these bikes, thank you for your patronage!\n" + "Now you still have " + n + " bikes left to return.","Prompt",JOptionPane.PLAIN_MESSAGE);
    }
}

public class GUI {
    static int total = 20;
    static int Day = 0;
    static int Hour = 0;
    static int Week = 0;
    final int MAX = 20;

    private int toInt(String str) {
        int ans = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                ans = ans * 10 + str.charAt(i) - '0';
            }
            else return -1;
        }
        return ans;
    }

    public void init() {
        JFrame jf = new JFrame();
        jf.setBounds(0, 0, 500, 500);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(new GridLayout(7, 10, 0, 10));
        jf.setTitle("Bike Rental System");
        JLabel jLabel = new JLabel("######### Welcome to My", JLabel.RIGHT);
        JLabel jLabel1 = new JLabel(" Bike Shop! #########");
        JLabel jLabel2 = new JLabel("######### We have "+total, JLabel.RIGHT);
        JLabel jLabel3 = new JLabel(" bikes available. #########");
        JLabel jLabel4 = new JLabel("######### Are you here to bor", JLabel.RIGHT);
        JLabel jLabel5 = new JLabel("row or return bikes? #########", JLabel.LEFT);
        JLabel jLabel6 = new JLabel("Hour", JLabel.CENTER);
        JLabel jLabel7 = new JLabel("Day", JLabel.CENTER);
        JLabel jLabel8 = new JLabel("Week", JLabel.CENTER);
        JTextArea jTextArea = new JTextArea();
        JTextArea jTextArea1 = new JTextArea();
        JTextArea jTextArea2 = new JTextArea();
        JButton Rent = new JButton("Rent");
        Rent.addActionListener((ActionEvent e) -> {
            String temp1 = jTextArea.getText();
            String temp2 = jTextArea1.getText();
            String temp3 = jTextArea2.getText();
            int h = toInt(temp1);
            int d = toInt(temp2);
            int w = toInt(temp3);
            if(h == -1 || d == -1 || w == -1 || h + d + w == 0) {
                new prompt().warning();
            }
            else if (total - (h + d + w) < 0 ) {
                new prompt().warning1();
            }
            else {
                Day += d;
                Hour += h;
                Week += w;
                total -= h + d + w;
                int price = h * 20 + d * 80 + w * 400;
                new Price(price).Cost();
                jf.setVisible(false);
                new GUI().init();
            }
        });
        JButton Return = new JButton("Return");
        Return.addActionListener((ActionEvent e) -> {
            String temp1 = jTextArea.getText();
            String temp2 = jTextArea1.getText();
            String temp3 = jTextArea2.getText();
            int h = toInt(temp1);
            int d = toInt(temp2);
            int w = toInt(temp3);
            if(h == -1 || d == -1 || w == -1 || h + d + w == 0) {
                new prompt().warning();
            }
            else if (total + h + d + w > MAX) {
                new prompt().warning3();
            }
            else if (d > Day || h > Hour || w > Week) {
                new prompt().warning2();
            }
            else {
                Day -= d;
                Hour -= h;
                Week -= w;
                total += h + d + w;
                new prompt().returnPrompt();
                jf.setVisible(false);
                new GUI().init();
            }
        });
        jf.add(jLabel);
        jf.add(jLabel1);
        jf.add(jLabel2);
        jf.add(jLabel3);
        jf.add(jLabel4);
        jf.add(jLabel5);
        jf.add(jTextArea);
        jf.add(jLabel6);
        jf.add(jTextArea1);
        jf.add(jLabel7);
        jf.add(jTextArea2);
        jf.add(jLabel8);
        jf.add(Rent);
        jf.add(Return);
        jf.setVisible(true);
    }
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en","US"));
        JOptionPane.showMessageDialog(null, "Welcome to the bike rental system!\nYou can borrow or return bikes here.\nRent bikes on hourly basis RMB20 per hour.\nRent bikes on a daily basis RMB100 per day.\nRent bikes on a weekly basis RMB400 per week.", "Bike Rental System",JOptionPane.PLAIN_MESSAGE);
        GUI g = new GUI();
        g.init();
    }
}