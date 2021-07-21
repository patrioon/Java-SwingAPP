import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame implements ActionListener {

    // Private variables of the GUI components
    JTextField tField;
    JTextField tField2;
    JTextField tField3;
    JTextArea tArea;





    public Main() {
        JFrame jf = new JFrame();

//        JPanel tfPanel = new JPanel(new GridLayout(3, 2, 10, 2));

        String[] buttomsLEFT_DOWN = {"1", "2", "3", "4", "5","6", " 7" ,"8" , "9"};
        JPanel jp_down = new JPanel();
        jp_down.setLayout(new GridLayout(3,3));
        jp_down.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 100));

        JButton[] tabJBut= new JButton[buttomsLEFT_DOWN.length];
        for(int i=0; i< buttomsLEFT_DOWN.length;i++){
            tabJBut[i] = new JButton(buttomsLEFT_DOWN[i]);
//            jp_down.add(new JButton(buttomsLEFT_DOWN[i]));
            tabJBut[i].addActionListener(this);
                jp_down.add(tabJBut[i]);
        }

        //TOP Loyaut
        JPanel jp_top = new JPanel();
        jp_top.setLayout(new GridLayout(0,2));
        add(jp_top);

        //A, B , C  LAYOUT add to TOP
        JPanel topRIGHT = new JPanel();
        topRIGHT.setLayout(new FlowLayout(2));
        JButton[] buttomsLEFT_TOP = {new JButton("A"),new JButton("B"), new JButton("C")};
        for(int i=0; i< buttomsLEFT_TOP.length;i++){
            topRIGHT.add(buttomsLEFT_TOP[i]);
            buttomsLEFT_TOP[i].addActionListener(this);
        }

        // LEFT ADD TO TOP
        JPanel leftTOP = new JPanel();
        leftTOP.setLayout(new FlowLayout(0));
        JButton[] buttomsRIGHT_TOP = {new JButton("FR"),new JButton("FG"), new JButton("FB")};
       leftTOP.add(buttomsRIGHT_TOP[0]);
       leftTOP.add(buttomsRIGHT_TOP[1]);
       leftTOP.add(buttomsRIGHT_TOP[2]);

        buttomsRIGHT_TOP[0].addActionListener(this);
        buttomsRIGHT_TOP[1].addActionListener(this);
        buttomsRIGHT_TOP[2].addActionListener(this);

       buttomsRIGHT_TOP[0].setBackground(Color.RED);
       buttomsRIGHT_TOP[1].setBackground(Color.GREEN);
       buttomsRIGHT_TOP[2].setBackground(Color.BLUE);

        jp_top.add(leftTOP);
        jp_top.add(topRIGHT);


        JPanel jbot = new JPanel();
        jbot.setLayout(new GridLayout(0, 2));
        JPanel tfPanel =new JPanel();
        tfPanel.setLayout(new GridLayout(3,3));
        jbot.add(jp_down);
        jbot.add(tfPanel);

        // Regular text field (Row 1)
        tField = new JTextField("Pole tekstowe 1 typu JTextField ");
        tField.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        tfPanel.add(tField);
        tField.addActionListener(e -> tArea.append("\n\n " + tField.getText()));

        // Regular text field (Row 2)
        tField2 = new JTextField("Pole tekstowe 2 typu JTextField ");
        tField2.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        tfPanel.add(tField2);
        tField2.addActionListener(e -> tArea.append("\n\n " + tField2.getText()));

        // Regular text field (Row 3)
        tField3 = new JTextField("Pole tekstowe 3 typu JTextField ");
        tField3.setBorder(BorderFactory.createLineBorder(Color.blue));

        tfPanel.add(tField3);
        tField3.addActionListener(e -> tArea.append("\n\n " + tField3.getText()));

        // Create a JTextArea
        tArea = new JTextArea("Obszar tekstowy typu JTextArea \n");
        tArea.setForeground(Color.RED);

        // JScrollPane
        JScrollPane scrollPane =
                new JScrollPane(tArea,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        tArea.setEditable(false);


        Container cp_mid = this.getContentPane();
        cp_mid.setLayout(new BorderLayout());
        cp_mid.add(jbot, BorderLayout.SOUTH);
        cp_mid.add(scrollPane, BorderLayout.CENTER);
        cp_mid.add(jp_top, BorderLayout.NORTH);




        jf.add(cp_mid);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setTitle("Simple Swing App 2");
        jf.pack();
        jf.setVisible(true);
    }


    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        switch (e.getActionCommand()){
            case "FR":
                tArea.setForeground(Color.RED);
//                tField.setBackground(Color.RED);
//                tField2.setBackground(Color.RED);
//                tField3.setBackground(Color.RED);
                break;
            case "FG":
                tArea.setForeground(Color.GREEN);
//                tField.setBackground(Color.GREEN);
//                tField2.setBackground(Color.GREEN);
//                tField3.setBackground(Color.GREEN);
                break;
            case "FB":
                tArea.setForeground(Color.BLUE);
//                tField.setBackground(Color.BLUE);
//                tField2.setBackground(Color.BLUE);
//                tField3.setBackground(Color.BLUE);
                break;
            case "A":
                tArea.setFont(new Font("Serif", Font.ITALIC, 16));
                break;
            case  "B":
                tArea.setFont(new Font("Bold", Font.BOLD, 20));
                break;
            case "C":
                tArea.setFont(new Font("Roman", Font.ROMAN_BASELINE, 16));

            case "1":
                tArea.append(" 1 " );
                break;
            case "2":
                tArea.append(" 2 " );
                break;
            case "3":
                tArea.append(" 3 " );
                break;
            case "4":
                tArea.append(" 4 " );
                break;
            case "5":
                tArea.append(" 5 " );
                break;
            case "6":
                tArea.append(" 6 " );
                break;
        }
    }
}