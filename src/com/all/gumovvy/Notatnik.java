package com.all.gumovvy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

public class Notatnik extends JFrame implements ActionListener {
	private JLabel lLogin, lHaslo, lRozmiarCzcionki, lKolorTekstu, lZmienTlo;
	private JTextField tLogin, tSzukajTekstu;
	private JPasswordField psHaslo;
	private JTextArea taNotatnik;
	private JMenuBar mbMenu;
	private JMenu mPlik, mPomoc;
	private JMenuItem miOtworz, miZapisz, miWyjscie, miKopjuj, miWklej, miWytnij, miDolacz, miOProgramie;
	private JPopupMenu pmPopUpMenu;
	private JButton bSzukaj, bZmienKolorTekstu, bZastosujZmianeTekstu, bOK;
	private ButtonGroup bgRozmiarCzcionki;
	private JRadioButton rbMaly, rbDuzy, rbSredni;
	private JCheckBox chPogrubiony;
	private JComboBox<String> cbKolorTla;
	private String kopiowanyTekst = "";
	private int loginLosowy = (int) Math.round(Math.random() * 1000);
	private int hasloLosowe = (int) Math.round(Math.random() * 1000);

	public Notatnik() {
		Toolkit zestaw = Toolkit.getDefaultToolkit();
		Dimension rozdzielczoszEkranu = zestaw.getScreenSize();
		int szerokoscEkranu = rozdzielczoszEkranu.width;
		int wysokoscEkranu = rozdzielczoszEkranu.height;
		setSize(szerokoscEkranu / 2, wysokoscEkranu / 2);
		setLocation(szerokoscEkranu / 4, wysokoscEkranu / 4);
		setLayout(null);
		setTitle("Notatnik");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// /////////////////////////////////////////////

		lLogin = new JLabel("Login: ");
		lLogin.setBounds(10, 20, 80, 25);
		add(lLogin);

		tLogin = new JTextField("");
		tLogin.setToolTipText("Podaj login");
		tLogin.setBounds(60, 20, 120, 25);
		add(tLogin);

		lHaslo = new JLabel("Hasło:");

		lHaslo.setBounds(190, 20, 120, 25);
		add(lHaslo);

		psHaslo = new JPasswordField("");
		psHaslo.setToolTipText("Podaj hasło");
		psHaslo.addActionListener(this);
		psHaslo.setBounds(240, 20, 120, 25);
		add(psHaslo);

		bOK = new JButton("OK");
		bOK.setBounds(370, 20, 65, 25);
		bOK.addActionListener(this);
		add(bOK);

		// //////////////////////////////////////////////

		taNotatnik = new JTextArea();
		JScrollPane scrolowany = new JScrollPane(taNotatnik);
		taNotatnik.setFont(new Font("Dialog", Font.PLAIN, 12));
		scrolowany.setBounds(10, 50, 765, 250);
		scrolowany.setVisible(true);
		taNotatnik.setEditable(false);
		taNotatnik.setEnabled(false);
		taNotatnik.setText("Wejdz do pomocy.");
		add(scrolowany);

		// //////////////////////////////////////////////

		mbMenu = new JMenuBar();
		setJMenuBar(mbMenu);
		mPlik = new JMenu("Plik");
		mPomoc = new JMenu("Pomoc");
		miOtworz = new JMenuItem("Otworz");
		miZapisz = new JMenuItem("Zapisz");
		miWyjscie = new JMenuItem("Wyjscie");
		miOProgramie = new JMenuItem("O programie");

		mbMenu.add(mPlik);
		mbMenu.add(mPomoc);
		mPomoc.add(miOProgramie);
		mPlik.add(miOtworz);
		mPlik.add(miZapisz);
		mPlik.addSeparator();
		mPlik.add(miWyjscie);

		miWyjscie.addActionListener(this);
		miOProgramie.addActionListener(this);

		// /////////////////////////////////////////////////
		pmPopUpMenu = new JPopupMenu();
		taNotatnik.setComponentPopupMenu(pmPopUpMenu);

		miKopjuj = new JMenuItem("Kopjuj");
		miWytnij = new JMenuItem("Wytnij");
		miWklej = new JMenuItem("Wklej");
		miDolacz = new JMenuItem("Dolacz");

		pmPopUpMenu.add(miKopjuj);
		pmPopUpMenu.add(miWytnij);
		pmPopUpMenu.add(miWklej);
		pmPopUpMenu.add(miDolacz);

		miWklej.addActionListener(this);
		miKopjuj.addActionListener(this);
		miWytnij.addActionListener(this);
		miDolacz.addActionListener(this);

		// /////////////////////////////////////////////////////

		bSzukaj = new JButton("Szukaj");
		bSzukaj.setBounds(480, 20, 100, 25);

		bSzukaj.addActionListener(this);
		add(bSzukaj);

		// //////////////////////////////////////////////////////

		tSzukajTekstu = new JTextField("");
		tSzukajTekstu.addActionListener(this);
		tSzukajTekstu.setBounds(590, 20, 200, 25);
		add(tSzukajTekstu);

		// /////////////////////////////////////////////////////

		bgRozmiarCzcionki = new ButtonGroup();
		rbMaly = new JRadioButton("Mały", true);
		rbSredni = new JRadioButton("Średni", false);
		rbDuzy = new JRadioButton("Duży", false);

		bgRozmiarCzcionki.add(rbMaly);
		bgRozmiarCzcionki.add(rbSredni);
		bgRozmiarCzcionki.add(rbDuzy);

		lRozmiarCzcionki = new JLabel("Rozmiar czcionki: ");
		lRozmiarCzcionki.setBounds(20, 310, 130, 25);
		add(lRozmiarCzcionki);

		rbMaly.setBounds(150, 310, 60, 25);
		add(rbMaly);

		rbSredni.setBounds(210, 310, 80, 25);
		add(rbSredni);

		rbDuzy.setBounds(290, 310, 60, 25);
		add(rbDuzy);

		chPogrubiony = new JCheckBox("Pogrubiony");
		chPogrubiony.setBounds(370, 310, 110, 25);
		add(chPogrubiony);
		chPogrubiony.addActionListener(this);

		bZastosujZmianeTekstu = new JButton("Zastosuj");
		bZastosujZmianeTekstu.setBounds(480, 310, 105, 25);
		bZastosujZmianeTekstu.addActionListener(this);
		add(bZastosujZmianeTekstu);

		lKolorTekstu = new JLabel("Zmien kolor tekstu: ");
		lKolorTekstu.setBounds(20, 350, 145, 25);
		add(lKolorTekstu);

		bZmienKolorTekstu = new JButton("Zmien");
		bZmienKolorTekstu.setBounds(170, 350, 100, 25);
		bZmienKolorTekstu.addActionListener(this);
		add(bZmienKolorTekstu);

		// //////////////////////////////////////////////////////////

		lZmienTlo = new JLabel("Zmien tło: ");
		lZmienTlo.setBounds(300, 350, 100, 25);
		add(lZmienTlo);

		cbKolorTla = new JComboBox<String>();
		cbKolorTla.setBounds(380, 350, 145, 25);
		cbKolorTla.addItem("Jasny");
		cbKolorTla.addItem("Ciemny");
		cbKolorTla.addActionListener(this);
		add(cbKolorTla);

	}

	public static void main(String[] args) {
		Notatnik aplikacja = new Notatnik();
		aplikacja.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object zrodlo = e.getSource();

		// Wyszukiwanie ,zapis i otwieranie

		if (zrodlo == miOtworz) {
			JFileChooser fc = new JFileChooser();

			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File plik = fc.getSelectedFile();

				try {
					Scanner skaner = new Scanner(plik);

					while (skaner.hasNext()) {
						taNotatnik.append(skaner.nextLine() + "\n");
					}

				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				}
			}

		} else if (zrodlo == miZapisz) {
			JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				File plik = fc.getSelectedFile();
				try {
					PrintWriter pw = new PrintWriter(plik);
					Scanner skaner = new Scanner(taNotatnik.getText());
					while (skaner.hasNext()) {
						pw.println(skaner.nextLine() + "\n");
					}
					pw.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		} else if (zrodlo == bSzukaj || zrodlo == tSzukajTekstu) {

			String calyTekst = taNotatnik.getText();
			String szukanyTekst = tSzukajTekstu.getText();
			String wyszukiwanie = "";

			int i = 0;
			int index = 0;
			int startIndex = 0;
			if (szukanyTekst.equals("")) {
				JOptionPane.showMessageDialog(null, "Wpisz fraz�.");
			} else {
				while ((index = calyTekst.indexOf(szukanyTekst, startIndex)) != -1) {
					startIndex = index + szukanyTekst.length();
					i++;
					wyszukiwanie = wyszukiwanie + " " + index;
				}

				JOptionPane.showMessageDialog(null, szukanyTekst + " wystapil " + i + " razy na pozycjach " + wyszukiwanie);
			}
		}

		// Zmiana koloru czcionki, rozmiaru i pogrubienia

		if (zrodlo == bZmienKolorTekstu) {
			Color kolor = JColorChooser.showDialog(null, "Wybor koloru", Color.black);
			taNotatnik.setForeground(kolor);
		}
		if (zrodlo == bZastosujZmianeTekstu) {
			if (chPogrubiony.isSelected() && rbMaly.isSelected()) {
				taNotatnik.setFont(new Font("Dialog", Font.BOLD, 12));
			} else if (!chPogrubiony.isSelected() && rbMaly.isSelected()) {
				taNotatnik.setFont(new Font("Dialog", Font.PLAIN, 12));
			} else if (chPogrubiony.isSelected() && rbSredni.isSelected()) {
				taNotatnik.setFont(new Font("Dialog", Font.BOLD, 16));
			} else if (!chPogrubiony.isSelected() && rbSredni.isSelected()) {
				taNotatnik.setFont(new Font("Dialog", Font.PLAIN, 16));
			} else if (chPogrubiony.isSelected() && rbDuzy.isSelected()) {
				taNotatnik.setFont(new Font("Dialog", Font.BOLD, 20));
			} else if ((!chPogrubiony.isSelected() && rbDuzy.isSelected())) {
				taNotatnik.setFont(new Font("Dialog", Font.PLAIN, 20));
			}
		}

		// Zmiana t�a i czcionki;
		if (zrodlo == cbKolorTla) {
			String tlo = cbKolorTla.getSelectedItem().toString();
			if (tlo.equals("Jasny")) {
				taNotatnik.setForeground(Color.black);
				taNotatnik.setBackground(Color.white);

			} else if (tlo.equals("Ciemny")) {
				taNotatnik.setForeground(Color.white);
				taNotatnik.setBackground(Color.gray);
			}
		}

		// KOpjuj wytnij dolacz wkelj

		if (zrodlo == miKopjuj) {
			kopiowanyTekst = taNotatnik.getSelectedText();

		} else if (zrodlo == miWklej) {
			taNotatnik.insert(kopiowanyTekst, taNotatnik.getCaretPosition());
		} else if (zrodlo == miWytnij) {
			kopiowanyTekst = taNotatnik.getSelectedText();
			taNotatnik.replaceSelection("");
		} else if (zrodlo == miDolacz) {
			taNotatnik.append(kopiowanyTekst + "\n");
		}
		// wyjscie pomoc
		if (zrodlo == miWyjscie) {
			dispose();
		} else if (zrodlo == miOProgramie) {
			JOptionPane.showMessageDialog(null, "Notatnik wersja 1.1.\nDo poprawnego dzia�ania wpisz:\nlogin: " + loginLosowy + " oraz haslo: " + hasloLosowe
					+ " \n\n Projected and designed by Gmv.");
		}

		// login i haselo
		if (zrodlo == bOK || zrodlo == psHaslo) {
			String sLoginLosowy = String.valueOf(loginLosowy);
			String sHasloLosowe = String.valueOf(hasloLosowe);
			String login = tLogin.getText();
			String haslo = new String(psHaslo.getPassword());
			if (login.equals(sLoginLosowy) && haslo.equals(sHasloLosowe)) {
				miOtworz.addActionListener(this);
				miZapisz.addActionListener(this);
				taNotatnik.setEditable(true);
				taNotatnik.setEnabled(true);
				taNotatnik.setText("");
			} else {
				taNotatnik.setEditable(false);
				taNotatnik.setEnabled(false);
			}
		}

	}
}
