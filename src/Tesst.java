
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tesst {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {

		Scanner unos = new Scanner(System.in);
		ArrayList<Racun> imee = new ArrayList<>();

		// Get previous state
		try {
			FileInputStream fis = new FileInputStream("racuni.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			imee = (ArrayList) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}

		// Choose operation
		System.out.println("Da dodate novog korisnika unesite 0! ");
		System.out.println("Da prebacite novac sa jednog na drugi racun, unesite 1! ");
		System.out.println("Da ispisete racun, unesite 2! ");
		int odabir = unos.nextInt();
		int brRacuna = 0;
		int iznos1 = 0;
		switch (odabir) {

		// Input users
		case 0:
			System.out.println("Unesite broj racuna, ime i iznos: ");
			boolean nastavak = true;
			do {
				try {
					brRacuna = unos.nextInt();
					if (brRacuna < 0) {
						System.out.println("Broj mora biti pozitivan, unesite ponovo! ");
					} else {
						nastavak = false;
					}
				} catch (InputMismatchException e) {
					System.out.println("Pogresan unos, unesite ponovo! ");
					unos.nextLine();
				}
			} while (nastavak);
			// Checking if number already exist
			for (int i = 0; i < imee.size(); i++) {
				while (brRacuna == imee.get(i).getBrRacuna()) {
					System.out.println("Racun pod tim brojem vec postoji, unesite ponovo! ");
					brRacuna = unos.nextInt();
				}
			}
			String imeVla = unos.next();
			boolean nastavak1 = true;
			do {
				try {
					iznos1 = unos.nextInt();
					if (iznos1 < 0) {
						System.out.println("Iznos mora biti pozitivan, unesite ponovo! ");
					} else {
						nastavak1 = false;
					}

				} catch (InputMismatchException e) {
					System.out.println("Pogresan unos, unesite ponovo! ");
					unos.nextLine();
				}
			} while (nastavak1);
			unos(brRacuna, imeVla, iznos1, imee);
			break;

		// Transfer
		case 1:
			System.out.print("Unesite broj sa kojeg prebacujete: ");
			int source = unos.nextInt();
			System.out.print("Unesite broj na koji prebacujete: ");
			int target = unos.nextInt();
			System.out.print("Unesite iznos: ");
			int iznos = unos.nextInt();
			transfer(source, target, iznos, imee);
			break;

		// Display all users
		case 2:
			for (int i = 0; i < imee.size(); i++) {
				System.out.println("Korisnik " + i + ":");
				System.out.println(imee.get(i).getBrRacuna());
				System.out.println(imee.get(i).getImeVlasnika());
				System.out.println(imee.get(i).getIznos());
			}

			break;
		}
		saveState(imee);
		// Write to txt file
		Path path = Paths.get("racuni.txt");
		if (!Files.exists(path)) {
			Files.createFile(path);
		}
		BufferedWriter bWrite = Files.newBufferedWriter(path);
		for (int i = 0; i < imee.size(); i++) {
			bWrite.write("Korisnik " + i );
			bWrite.newLine();
			bWrite.write(imee.get(i).getImeVlasnika());
			bWrite.newLine();
			bWrite.write(String.valueOf(imee.get(i).getBrRacuna()));
			bWrite.newLine();
			bWrite.write(String.valueOf(imee.get(i).getIznos()));
			bWrite.newLine();
		}
		bWrite.flush();
	}

	public static void saveState(ArrayList<Racun> imee) {
		try {
			FileOutputStream fos = new FileOutputStream("racuni.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(imee);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void unos(int brRacuna, String imeVla, int iznos, ArrayList<Racun> ime) {

		Racun a = new Racun(brRacuna, imeVla, iznos);
		ime.add(a);

	}

	public static void transfer(int source, int target, double iznos, ArrayList<Racun> ime) {
		boolean a = true;

		for (int j = 0; j < ime.size(); j++) {
			if ((ime.get(j).getBrRacuna() != target)) {

				a = false;

			}
		}
		for (int i = 0; i < ime.size(); i++) {

			if ((ime.get(i).getBrRacuna() == source) && (a = true) && (ime.get(i).getIznos() > iznos)) {

				int e = (int) (ime.get(i).getIznos() - iznos);
				ime.get(i).setIznos(e);

			}

		}
		for (int i = 0; i < ime.size(); i++) {
			if ((ime.get(i).getBrRacuna() == target) && (a = true) && (ime.get(i).getIznos() > iznos)) {

				int e = (int) (ime.get(i).getIznos() + iznos);
				ime.get(i).setIznos(e);

			}

		}

	}

}
