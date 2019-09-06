package com.nzsoft.rpi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Base64;


public class MinionFactory {

	public static Minion getMinion(String[] args) {

		Minion newMinion = null;
		if (args.length == 0) {
			unsupportedModeExit();
		}

		switch (args[0]) {

		case "localtest":
			if (args.length < 3) {
				newMinion = new MockMinionImpl();
			} else if (args.length == 3 && "bhm".equals(args[2])) {
				if (!checkPassword()) {
					throw new IllegalStateException("Unauthorized");
				} else {
					newMinion = new BlackHatMinionLocalImpl(args[1]);
				}
			} else {
				unsupportedModeExit();
			}
			break;
		case "rpitest":
			System.out.println("No naistina ne sam args.lenght = " + args.length);
			if (args.length < 3) {
				newMinion = new RpiMinionImpl();
			} else if (args.length == 3 && "bhm".equals(args[2])) {
				if (!checkPassword()) {
					throw new IllegalStateException("Unauthorized");
				} else {
					newMinion = new BlackHatMinionRpiImpl(args[1]);
				}
			} else {
				unsupportedModeExit();
			}
			break;
		default:
			newMinion = new RpiMinionImpl();
			break;
		}
		return newMinion;

	}

	private static void unsupportedModeExit() {
		System.out.println("\nMissing input arguments. Supported modes:\n");
		System.out.println("    - localtest : Mock minion and GCP");
		System.out.println("    - rpitest   : Mock GCP");
		System.out.println();
		System.out.println("    - localtest <url>  : Mock minion, GCP on provided url");
		System.out.println("    - rpitest   <url>  : Real minion, GCP on provided url");
		System.exit(1);
	}

	private static boolean checkPassword() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String pwd = null;
		System.out.println("Password:");
		try {
			pwd = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String encodedPassword = Base64.getEncoder().encodeToString(pwd.getBytes());
		return "aGFja3RoZXdvcmxk".equals(encodedPassword);

	}
}
