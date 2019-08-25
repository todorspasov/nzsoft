package com.nzsoft.rpi;

import java.io.IOException;
import java.util.Collections;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.nzsoft.drive.DriveHelper;

public final class BlackHatMinionImpl implements Minion {

	private static final String fileName = "anonymous.txt";

	@Override
	public void switchLed(LedState state) {
		System.out.println("LED is " + state.name());
	}

	@Override
	public boolean isButtonPressed() {
		return Math.random() < 0.1;
	}

	@Override
	public void emitMorseCode(String[] morseCode) {
		System.out.println("BlackHatMinion : Will now create hacker file on Drive....");
		try {
			Drive service = DriveHelper.getDriveService();
			FileList folderResult = service.files().list()
					.setQ("name = 'MinionTasks' and mimeType = 'application/vnd.google-apps.folder'").setSpaces("drive")
					.execute();
			File folder = folderResult.getFiles().get(0);
			String minionFolderId = folder.getId();
			File hackFile = new File();
			hackFile.setName(fileName);
			hackFile.setParents(Collections.singletonList(minionFolderId));
			System.out.println("hacker file " + service.files().get(fileName));

			FileList result = service.files().list()
					.setQ("name = '" + fileName + "' and '" + minionFolderId + "' in parents").setSpaces("drive")
					.execute();
			boolean fileFound = false;
			for (File file : result.getFiles()) {
				System.out.printf("Found file: %s (%s)\n", file.getName(), file.getId());
				fileFound = true;
			}
			if (!fileFound) {
				service.files().create(hackFile).execute();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.exit(0);
	}
}
