package fr.eni.location.test;

import java.time.LocalDate;
import java.time.Month;

import fr.eni.location.bo.Cycle;
import fr.eni.location.bo.Gyropode;
import fr.eni.location.bo.Gyroroue;
import fr.eni.location.bo.Velo;

public class Test {
	
	public static void main(String[] args) {
		new Velo(LocalDate.of(2017, Month.JUNE, 2), "Lapierre", "speed 400", 27);
		new Velo(LocalDate.of(2018, Month.APRIL, 9), "Btwin", "riverside 900", 10);
		new Gyropode(LocalDate.of(2018, Month.JUNE, 5), "Segway", "Ninebot Elite", 40, 150);
		new Gyropode(LocalDate.of(2017, Month.MAY, 2), "Weebot", "Echo", 35, 160);
		new Gyroroue(LocalDate.of(2018, Month.MARCH, 25), "Immotion", "v8", 40);
		new Gyroroue(LocalDate.of(2018, Month.MARCH, 25), "Segway", "Ninebot One E+", 30);
		
		System.out.println(Cycle.getCycles());
	}
}
