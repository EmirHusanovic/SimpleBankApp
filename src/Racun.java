import java.io.Serializable;
import java.util.ArrayList;

public class Racun implements Serializable {

	private int brRacuna;
	private String imeVlasnika;
	private double iznos;

	Racun(int brRacuna, String imeVlasnika, double iznos) {
		
		this.brRacuna = brRacuna;
		this.imeVlasnika = imeVlasnika;
		this.iznos = iznos;

		}

	public int getBrRacuna() {
		return brRacuna;
	}

	public void setBrRacuna(int brRacuna) {
		this.brRacuna = brRacuna;
	}

	public String getImeVlasnika() {
		return imeVlasnika;
	}

	public void setImeVlasnika(String imeVlasnika) {
		this.imeVlasnika = imeVlasnika;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

}
