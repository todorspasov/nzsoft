package com.nzsoft.tasks;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.nzsoft.drive.DriveHelper;

public class DriveTaskOperations implements TaskOperations {
	private static final String MINION_FOLDER_NAME = "MinionTasks";
	private static final String MINION_DATA_FILE_NAME = "Data.txt";

	private final Drive service;

	public DriveTaskOperations() throws IOException {
		service = DriveHelper.getDriveService();
	}

	@Override
	public String getTask() {
		String taskId = null;
		try {
			FileList folderResult = service.files().list()
					.setQ("name = '" + MINION_FOLDER_NAME + "' and mimeType = 'application/vnd.google-apps.folder'")
					.setSpaces("drive").execute();
			File folder = folderResult.getFiles().get(0);
			String minionFolderId = folder.getId();
			System.out.println("minion folder Id = " + minionFolderId);
			FileList result = service.files().list()
					.setQ("name contains '" + MINION_DATA_FILE_NAME + "' and '" + minionFolderId + "' in parents")
					.setSpaces("drive").execute();
			for (File file : result.getFiles()) {
				System.out.printf("Found file: %s (%s)\n", file.getName(), file.getId());
				taskId = file.getId();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return taskId;
	}

	@Override
	public Priority getTaskPriority(String taskId) {
		return null;
	}

	@Override
	public String getTaskBody(String taskId) {
		try {
			OutputStream outputStream = new FileOutputStream("output.txt");
			service.files().get(taskId).executeMediaAndDownloadTo(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sos SoS";
	}

	@Override
	public void markTaskCompleted(String taskId) {
		// TODO Auto-generated method stub

	}

}
