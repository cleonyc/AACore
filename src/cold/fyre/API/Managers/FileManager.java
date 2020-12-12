package cold.fyre.API.Managers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.logging.Level;

import org.bukkit.Bukkit;

/**
 * Contains several functions that allow accessing, changing, or otherwise interacting with files and their directories.
 * 
 * @author Armeriness
 * @author Sommod
 * @since 2.0
 *
 */
public class FileManager {
	
	/**
	 * Returns a File instance of the Folder of the path given. If the
	 * folder does not exist, then one is created.
	 * @param path - Path of folder.
	 * @return File of Folder.
	 */
	public static File getFolder(String path) {
		File toReturn = new File(path);
		if(!toReturn.exists())
			toReturn.mkdir();
		return toReturn;
	}
	
	/**
	 * Gets the Folder that contains all the plugins and their PluginDataFolder.
	 * @return File of plugins Folder.
	 */
	public static File getPluginsFolder() { return new File(Bukkit.getServer().getWorldContainer() +"/plugins"); }
	
	/**
	 * Checks if the file is not null. If the file is null, then the file is created and returned.
	 * @param file - file to check.
	 * @return file
	 */
	public static File NotNull(File file) {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return file;
	}
	
	/**
	 * Renames the current file to the name given then returns the file.
	 * @param file - file to rename.
	 * @param newName - Name of file.
	 * @return File
	 */
	public static File renameFile(File file, String newName) {
		String fileExtension = "." + file.getName().split(".")[1];
		File toChange = new File(file.getPath() + newName + fileExtension);
		file.renameTo(toChange);
		return file;
	}
	
	/**
	 * Moves a file from one folder to another.
	 * @param from - File to move.
	 * @param folder - Folder to move to.
	 * @return File with new location.
	 */
	public static File moveFileToFolder(File from, File folder) {
		File toMove = new File(folder, from.getName());
		
		try {
			if(!toMove.exists())
				toMove.createNewFile();
			
			Files.copy(from.toPath(), new FileOutputStream(toMove));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		from.delete();
		return toMove;
	}
	
	/**
	 * This logs an exception to a file within the plugins folder. Note that
	 * name of the plugin must be the name of the <b>plugin folder</b>. If the 
	 * name is incorrect, meaning the folder is not found, then a default folder
	 * of <b>Error Logs</b> will be used.
	 * @param pluginName
	 * @param e
	 */
	public static void logExceptionToFile(String pluginName, Exception e) {
		File folder = null;
		
		if(pluginName != null)
			folder = new File(getPluginsFolder(), pluginName);
		
		if(folder == null || !folder.exists()) {
			folder = new File(getPluginsFolder(), "Error Logs");
			folder.mkdir();
			Bukkit.getServer().getConsoleSender().sendMessage("�cError: Could not log Exception to �b" + pluginName + "�c, folder does not exist."
					+ "�c Logging Exception to �bError Logs�c folder in the �bplugins�c folder.");
		}
		
		File toLog = new File(folder, getDate() + ".txt");
		NotNull(toLog);
		toLog.setWritable(true);
		
		try {
			FileWriter fw = new FileWriter(toLog);
			for(StackTraceElement element : e.getStackTrace())
				fw.append(element.toString() + "\n");
			fw.close();
		} catch (IOException e1) {
			Bukkit.getServer().getLogger().log(Level.WARNING, "�cError, could not write error data to file, another error occurred.");
		}
		
	}
	
	// Used for formating the date used in the LogExceptionToFile method.
	private static String getDate() {
		String toReturn = "";
		toReturn += Calendar.YEAR + "-" + Calendar.MONTH + "-" + Calendar.DAY_OF_MONTH + "-" + Calendar.HOUR_OF_DAY + Calendar.MINUTE + Calendar.SECOND;
		return toReturn;
	}

}