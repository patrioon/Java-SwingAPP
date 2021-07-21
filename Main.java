import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.awt.FlowLayout.*;


public class Main extends JFrame implements ActionListener, DocumentListener {
    private JTextArea textArea = new JTextArea(20, 60);
    private JFileChooser fc = new JFileChooser();
    private ButtonGroup btnGrp = new ButtonGroup();
    private boolean modified = false;
    private boolean saved = false;
    private boolean New = true ;
    private JLabel newI = new JLabel("New");


    public Main() {
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        fc.setCurrentDirectory(new File("."));

        textArea.getDocument().addDocumentListener( this);

        //MENU
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu options = new JMenu("Options");

        //W EDIT
        JMenu editIN = new JMenu("Adresy");
        editIN.add(Szkola);
        editIN.add(Dom);
        editIN.add(Praca);

        edit.add(editIN);


        //W OPTIONS
        JMenu optionsIN1 = new JMenu("Background");
        JMenu optionsIN2 = new JMenu("ForeGround");
        JMenu optionsIN3 = new JMenu("Size");


        //DODAJEMY DO MENU
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(options);

        //MNEMONIKI I ACCELATORY
        Open.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
        Open.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));

        Save.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
        Save.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));

        SaveAS.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
        SaveAS.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));

        Exit.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
        Exit.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));

        Szkola.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
        Szkola.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));

        Dom.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_D);
        Dom.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D,
                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));

        Praca.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
        Praca.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P,
                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));

        //FILE
        file.add(Open);
        file.add(Save);
        file.add(SaveAS);
        JSeparator separator = new JSeparator();
        separator.setBackground(Color.RED);
        file.add(separator);
        file.add(Exit);

        //RIGHT STATUS FILE
        JLabel newI1 = new JLabel(New ? "New" : (modified ? " Modified" : "Saved"));
        newI1.setText(New ? "New" : (modified ? " Modified" : "Saved"));

        //BOT LEFT STATUS
        JLabel some0 = new JLabel("fg" );
        JLabel some1 = new JLabel("bg" );
        JLabel some2 = new JLabel( "size");


        //OPCJA SIZE
        int sizeIN = 8;

        JMenuItem[] fontItem = new JMenuItem[9];
        for (int i = 0; i < 9; i++) {
            String tmp = sizeIN + i * 2 + "pts";
            fontItem[i] = new JMenuItem(tmp);
            fontItem[i].setFont(new Font("MAMPLAN", Font.PLAIN, sizeIN + i + 2));
            optionsIN3.add(fontItem[i]);

            int finalI=i;
            fontItem[i].addActionListener(e -> {
                textArea.setFont(new Font("Arial", Font.PLAIN, (sizeIN + finalI * 2)));
                some2.setText(String.valueOf(sizeIN + finalI * 2));
            });

        }

        //RIGHT STATUS FILE
//        JLabel newI1 = new JLabel(New ? "New" : (modified ? " Modified" : "Saved"));
////        JLabel newI2 = new JLabel("Modified" );
////        JLabel newI3 = new JLabel("Saved" );

        //SET LEFT SITE OF BOTTOM
        some0.setIcon(new OvalIcon(0, 0, getForeground()));
        some1.setIcon(new OvalIcon(0, 0, getBackground()));
        some2.getFont();
        JPanel jbot = new JPanel();
        jbot.setLayout(new GridLayout(0, 2));
        JPanel jbotLEFT = new JPanel();
        JPanel jbotRIGHT = new JPanel();
        jbotLEFT.setLayout(new FlowLayout(RIGHT));
        jbotRIGHT.setLayout(new FlowLayout(LEFT));
        jbotRIGHT.add(some1);
        jbotRIGHT.add(some0);
        jbotRIGHT.add(some2);

        jbotLEFT.add(newI);
//        if boolin bedzie modifie or new or save
//        if (New==true){ jbotLEFT.add(newI1);}//New
//        if(modified==true){jbotLEFT.add(newI2);}//Modified
//        if(saved==true){jbotLEFT.add(newI3);}//Saved

        //HERE WE ADD ALL TO BOTTOM
        jbot.add(jbotRIGHT);
        jbot.add(jbotLEFT);


        //OPCJE FORE I BACK
        ButtonGroup group = new ButtonGroup();

        String[] colors1 = {"Green", "Orange", "Red", "Black", "White", "Yellow", "Blue"};

        Color[] colors = {Color.GREEN,Color.ORANGE,Color.RED,Color.BLACK,Color.WHITE, Color.YELLOW,Color.BLUE};

        JRadioButtonMenuItem[] radioForeground = new JRadioButtonMenuItem[colors.length];
        JRadioButtonMenuItem[] radioBackground = new JRadioButtonMenuItem[colors.length];

        for (int i = 0; i < colors.length; i++) {
            OvalIcon red = new OvalIcon(10,10,colors[i]);

            radioBackground[i] = new JRadioButtonMenuItem(colors1[i],red);
            radioForeground[i] = new JRadioButtonMenuItem(colors1[i],red);

            group.add(radioBackground[i]);
            group.add(radioForeground[i]);

            optionsIN1.add(radioBackground[i]);
            optionsIN2.add(radioForeground[i]);

            int finalI = i;
            radioBackground[i].addActionListener(e-> {
                some1.setIcon(new OvalIcon(15,15,textArea.getBackground()));
                jbotRIGHT.add(some1);
                textArea.setBackground(colors[finalI]);
            });

            radioForeground[i].addActionListener(e-> {
                some0.setIcon(new OvalIcon(15,15,textArea.getForeground()));
                jbotRIGHT.add(some0);
                textArea.setForeground(colors[finalI]);
            });
        }

        options.add(optionsIN1);
        options.add(optionsIN2);
        options.add(optionsIN3);



//        DODAWANIE DO KONTENERA
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        pane.add(menuBar, BorderLayout.NORTH);
        pane.add(scrollPane, BorderLayout.CENTER);
        pane.add(jbot, BorderLayout.SOUTH);

        //OSTATNIE VIZUALNE
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Prosty edytor - bez tytułu");

    }



    //FILE

    Action Open = new AbstractAction("Open") {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                openFile(fc.getSelectedFile().getAbsolutePath());
            }
        }
    };
    Action Save = new AbstractAction("Save") {
        @Override
        public void actionPerformed(ActionEvent e) {
            saved=true;
            New=false;
            modified=false;
            newI.setText(New ? "New" : (modified ? " Modified" : "Saved"));
            saveFile2();
        }
    };
    Action SaveAS = new AbstractAction("Save as") {
        @Override
        public void actionPerformed(ActionEvent e) {
            modified=false;
            saved=true;
            New=false;
            newI.setText(New ? "New" : (modified ? " Modified" : "Saved"));
            saveFile();
        }
    };
    Action Exit = new AbstractAction("Quit") {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };


    //TERAZ EDIT IN ADRESY
    Action Dom = new AbstractAction("Dom") {
        @Override
        public void actionPerformed(ActionEvent e) {
//            textArea.replaceSelection("");
            textArea.insert("C:\\Workplace\\Dom.java", textArea.getCaretPosition());
            setTitle("C:\\Workplace\\Dom.java");
        }
    };
    Action Szkola = new AbstractAction("Szkola") {
        @Override
        public void actionPerformed(ActionEvent e) {
//            textArea.replaceSelection("");
            textArea.insert("C:\\Workplace\\Szkola.java", textArea.getCaretPosition());
            setTitle("C:\\Workplace\\Szkola.java");

        }
    };

    Action Praca = new AbstractAction("Praca") {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.insert("C:\\Workplace\\Praca.java", textArea.getCaretPosition());
            setTitle("C:\\Workplace\\Praca.java");
        }
    };

    public void openFile(String fileName) {
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            textArea.read(fr, null);
            fr.close();
            setTitle("Prosty edytor -"+fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileWriter fw = null;
            try {
                fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
                textArea.write(fw);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFile2() {
            FileWriter fw = null;
            try {
                fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
                textArea.write(fw);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

    }
        public static void main (String[]args){
            new Main();

        }

// TUTAJ DOCUMENT LISTENER IF SAT CHANGES LIKE THIS BELOW FLAG CHANGE TOO

    @Override
    public void insertUpdate(DocumentEvent e) {
        modified=true;
        saved=false;
        New=false;
        newI.setText(New ? "New" : (modified ? " Modified" : "Saved"));
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        modified=true;
        saved=false;
        New=false;
        newI.setText(New ? "New" : (modified ? " Modified" : "Saved"));

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        modified=true;
        saved=false;
        New=false;
        newI.setText(New ? "New" : (modified ? " Modified" : "Saved"));
    }
}