package com.nzsoft.radio.operations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.nzsoft.drive.DriveHelper;

public class DriveRadioOperations implements RadioOperations {
	private static final String MINION_FOLDER_NAME = "MinionTasks";
	private static final String MINION_DATA_FILE_NAME = "Data.txt";
	private static final String COMPLETED_FILE_PREFIX = "[COMPLETED]";

	private final Drive service;

	public DriveRadioOperations() throws IOException {
		service = DriveHelper.getDriveService();
	}

	@Override
	public String getSignal() {
		String signalId = null;
		try {
			FileList folderResult = service.files().list()
					.setQ("name = '" + MINION_FOLDER_NAME + "' and mimeType = 'application/vnd.google-apps.folder'")
					.setSpaces("drive").execute();
			File folder = folderResult.getFiles().get(0);
			String minionFolderId = folder.getId();
			System.out.println("minion folder Id = " + minionFolderId);
			FileList result = service.files().list()
					.setQ("name = '" + MINION_DATA_FILE_NAME + "' and '" + minionFolderId + "' in parents")
					.setSpaces("drive").execute();
			for (File file : result.getFiles()) {
				System.out.printf("Found file: %s (%s)\n", file.getName(), file.getId());
				signalId = file.getId();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return signalId;
	}

	@Override
	public Priority getSignalPriority(String signalId) {
		return null;
	}

	@Override
	public String getSignalBody(String signalId) {
		String signalBody = null;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			service.files().get(signalId).executeMediaAndDownloadTo(outputStream);
			signalBody = outputStream.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return signalBody;
	}

	@Override
	public void markSignalReceived(String signalId) {
		try {
			File signalsFile = service.files().get(signalId).execute();
			File renamedFile = new File();
			renamedFile.setName(COMPLETED_FILE_PREFIX + signalsFile.getName());
			service.files().update(signalId, renamedFile).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
