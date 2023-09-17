/**
 *
 *  @author Kilman Miłosz S22758
 *
 */

package zad3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class Main implements ActionListener {

  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    SwingUtilities.invokeLater(
            () -> createGUI()
    );
  }

  protected void createGUI() {
    // utworzenie okna
    JFrame jf = new JFrame();

    // określenie tytułu okna
    jf.setTitle("ToDoList");

    // obsługa zamknięcia okna JFrame
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // okno wyśrodkowane na ekranie
    jf.setLocationRelativeTo(null);
    //panele do grupowania komponentów na górze
    JPanel jp1 = new JPanel();
    JPanel jpF = new JPanel();
    JPanel jpW = new JPanel();

    //LAYOUT DLA GÓRY
    jp1.setLayout(new BorderLayout());
    jpF.setLayout(new FlowLayout());
    //Etykieta stanu
    JLabel label = new JLabel();
    label.setText("Stan");
    //Dodawanie przycisków kolorowych
    JButton jbFR = new JButton("ToDo");
    JButton jbFG = new JButton("InProgress");
    JButton jbFB = new JButton("Done");
    jbFR.setBackground(Color.RED);
    jbFG.setBackground(Color.GREEN);
    jbFB.setBackground(Color.DARK_GRAY);
    jpF.add(jbFR);
    jpF.add(jbFG);
    jpF.add(jbFB);
    jp1.add(label, BorderLayout.NORTH);


    jp1.add(jpF, BorderLayout.CENTER);
//=====================================================
    //PANELE CENTRALNE
    JPanel jp2 = new JPanel();
    JPanel jpJList = new JPanel();
    //layout dla panelu centralnego
    jp2.setLayout(new FlowLayout());
    jpJList.setLayout(new FlowLayout());
    //ELEMENTY PANELU CENTRALNEGO
    DefaultListModel<String> l2 = new DefaultListModel<>();
    final JList<String> list = new JList<>(l2);

    //Obsługa zmiany koloru
    jbFR.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        list.setForeground(Color.RED);

      }
    });

    jbFG.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        list.setForeground(Color.GREEN);

      }
    });

    jbFB.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        list.setForeground(Color.darkGray);

      }
    });

    JScrollPane jsp = new JScrollPane(list);
    jsp.setPreferredSize(
            new Dimension(720, 380)
    );
    //dodanie suwaków
    jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    jp2.add(jsp);
//=========================================================
    // PANELE DOLNE
    JPanel jpL = new JPanel();

    // ustawianie zarządcy rozkładu dla panelu DOLNEGO
    jpL.setLayout(new GridLayout(3, 3));

    // dodawanie komponentów (z obs ługą zdarzeń za pomocą klas anonimowych) do panelu na dole

    //DODAWANIE JTF

    JTextField jtf1 = new JTextField("Enter task here");
    jpL.add(jtf1, BorderLayout.CENTER);

    JButton jb = new JButton("Add");
    jpL.add(jb);
    JButton jb1 = new JButton("Check");
    jpL.add(jb1);


    jb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        l2.addElement(jtf1.getText());

        jtf1.setText("");
      }
    });
    jb1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        label.setText("ToDo");

      }
    });


//=================================================

    // ustawianie zarządcy rozkladu dla okna
    jf.setLayout(new BorderLayout());

    // dodawanie komponentów do okna
    jf.add(jp1, BorderLayout.NORTH);
    jf.add(jp2, BorderLayout.CENTER);
    jf.add(jpL, BorderLayout.SOUTH);

    // ustawianie możliwości zmiany rozmiarów okna
    jf.setResizable(true);

    // upakowanie okna
    jf.pack();

    // wyświetlenie okna
    jf.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}

