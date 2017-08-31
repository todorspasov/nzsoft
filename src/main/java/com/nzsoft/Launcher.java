package com.nzsoft;

import static com.nzsoft.rpi.Minion.LedState.OFF;
import static com.nzsoft.rpi.Minion.LedState.ON;
import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.nzsoft.morse.MorseCodes;
import com.nzsoft.rpi.Minion;
import com.nzsoft.rpi.MockMinionImpl;
import com.nzsoft.rpi.RpiMinionImpl;
import com.nzsoft.tasks.DriveTaskOperations;
import com.nzsoft.tasks.MockTaskOperationsImpl;
import com.nzsoft.tasks.TaskOperations;

/**
 * Forma za ocenqvane ot asistentite:
 * 
 * EASY zadacha dava 100 tochki
 * 
 * MEDIUM zadacha dava 250 tochki
 * 
 * Parvata reshena HARD zadacha dava 500 tochki
 * Vsqka sledvashta reshena HARD zadacha dava +100tochki poveche ot predhodnata HARD zadacha (hard zadachite davat saotvetno 500,600,...)
 * 
 * Ne e nujno da napravite vsichki EASY zadachi za da preminete na MEDIUM zadachite. Ne e nujno da napravite vsichki MEDIUM zadachi za da preminete na hard zadachite.
 * 
 * Ima obshto:
 * 9 EASY zadachi -> max 9x100=900 tochki.
 * 5 MEDIUM zadachi -> max 5x250=1250 tochki
 * 5 HARD zadachi -> max 500+600+700+800+900=3500 tochki
 *
 * Maksimalen vazmojen rezultat:
 * resheni vsichki zadachi: 9x100 + 5x250 + (500+600+700+800+900) = 5650 tochki.
 * 
 */
public final class Launcher {

	// EASY 1) Napravete predavaneto na morzoviq kod da stane 3 pati predi da
	// ugasne signala
	private static final int MORSE_CODE_EMIT_RETRIES = 1;

	// EASY 2) Napravete zabavqneto mejdu pre-povtarqneto na morzoviq kod da
	// stane deset sekundi. Stoinostta koqto vavejdate trqbva da e vav
	// milisekundi
	private static final long MORSE_CODE_DELAY_BETWEEN_RETRIES_MILLISECONDS = 10000;

	public static void main(String[] args) throws Exception {
		TaskOperations taskOperations = null;
		Minion minion = null;

		// EASY 3) Izpishete na konzolata imeto na vashiq otbor.
		System.out.println("War Minion, version 2017, NZsoft, all rights reserved®");

		if ("rpitest".equalsIgnoreCase(args[0])) {
			minion = new RpiMinionImpl();
			taskOperations = new MockTaskOperationsImpl();/// ???;//Mock task minions impl interface
		} else if ("localtest".equalsIgnoreCase(args[0])) {
			taskOperations = new MockTaskOperationsImpl();/// ???;//Mock task minions impl interface
			minion = new MockMinionImpl();
		} else {
			taskOperations = new DriveTaskOperations();
			minion = new RpiMinionImpl();
		}
		
		// MEDIUM 3) Pusnete programata vav testov softueren rejim rejim (bez vrazka kam
		// minion).

		// HARD 1) Napravete taka che kogato se startira miniona da e vav dvoen testov rejim (bez vrazka kam miniona i bez vrazka kam google drive). Podskazka smenete kliuchovite String konstanti za sravnqvane na edna i sashta vav dvata if bloka po-gore.
		
		// HARD 2) Napishete imeto na vashiq otbor kato morzov kod prez
		// lampichkata na miniona.
		// Podskazki: vijte nadolu reda sadarjasht emitMorseCode(...)

		// HARD 3) Sled kato se emitira imeto na otbora, izvikaite metoda za
		// chakane na natiskane na butona.

		// HARD 4) Polzvaite FOR operator za da predadete kato morzov kod na lampichkata na miniona, imeto na otbora 4 pati sas pauza ot 5 sekundi (sleep()) mejdu vsqko predavane na imeto na otbora.

		while (true) {
			// EASY 6) Dobavete saobshtenie na konzolata predi izvikvaneto za
			// poluchavane na radio signal
			// System.???
			getRadioSignal(taskOperations, minion);
			// EASY 7) Dobavete saobshtenie na konzolata sled poluchavaneto na
			// radio signal
			// System.???
		}
	}

	private static void getRadioSignal(TaskOperations taskOperations, Minion minion) throws Exception {
		String taskName = taskOperations.getTask();
		if (isNotBlank(taskName)) {
			// HARD 5) Napravete miniona vmesto da darji lampichkata pusnata
			// postoqnno, da miga na interval ot 75 milisekundi pri poluchavane
			// na nov kod (sledvashtiq red)
			minion.switchLed(ON);

			waitForButtonPress(minion);
			
			//EASY 8) Napishete na konzolata che e polucheno natiskane na butona.
			//System.???
			
			
			//MEDIUM 4) Dobavete edin red za da nakarate miniona da izchaka 5 sekundi predi da e pochnal da predava morzoviq kod
			//podskazka - izpolzvaite metoda sleep
			
			String taskBody = taskOperations.getTaskBody(taskName);
			for (int i = 0; i < MORSE_CODE_EMIT_RETRIES; i++) {
				minion.emitMorseCode(MorseCodes.convertToMorse(taskBody));
				sleep(MORSE_CODE_DELAY_BETWEEN_RETRIES_MILLISECONDS);
			}
			
			//MEDIUM 5) Dobavete edin red za da vkliuchite LED lampichkata na miniona da sveti postoianno dokato ne se natisne butona
			
			
			waitForButtonPress(minion);

			taskOperations.markTaskCompleted(taskName);
			
			minion.switchLed(OFF);

			sleep(1000);
		} else {
			System.out.println("No tasks available. Waiting...");
			sleep(1000);
		}
	}

	private static void waitForButtonPress(Minion minion) throws InterruptedException {
		while (!minion.isButtonPressed()) {
			System.out.println("Waiting to hit the button...");
			// EASY 9) Napravete intervala za zasichane dali butona e natisnat
			// na 100 mili sekundi.
			sleep(75);
		}
	}
}
