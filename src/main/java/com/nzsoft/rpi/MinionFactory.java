package com.nzsoft.rpi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinionFactory {

	public static Minion getMinion(String[] args) {

		Minion newMinion = null;

		switch (args[0]) {

		case "localtest":
			if (args.length > 1 && "bhv".equals(args[1])) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String pwd = null;
				System.out.println("Password:");
				try {
					pwd = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if ("hacktheworld".equals(pwd)) {
					newMinion = new BlackHatMinionImpl();
				} else {
					throw new IllegalStateException("Unauthorized");
				}
			} else {
				newMinion = new MockMinionImpl();
			}
			break;
		case "rpitest":
			newMinion = new RpiMinionImpl();
			break;
		default:
			newMinion = new RpiMinionImpl();
			break;
		}
		return newMinion;
	}
}
