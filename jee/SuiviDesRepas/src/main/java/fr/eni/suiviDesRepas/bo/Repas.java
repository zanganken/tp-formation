package fr.eni.suiviDesRepas.bo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Zanganken
 *
 */
public class Repas {
	private LocalDateTime dateTime;
	private List<String> aliments;

	public Repas(LocalDateTime dateTime, List<String> aliments) {
		this.dateTime = dateTime;
		this.aliments = aliments;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public List<String> getAliments() {
		return aliments;
	}
	
	public void addAliment(String a) {
		aliments.add(a);
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Repas [dateTime=" + dateTime + ", aliments="
				+ (aliments != null ? aliments.subList(0, Math.min(aliments.size(), maxLen)) : null) + "]";
	}
}
