package it.uniroma3.helper;

import javax.servlet.http.Part;

/**
 * Helper class for file name manipulation
 *
 * @author Gaetano
 *
 */

public class FileHelper {

	public FileHelper() {}

	public String getFileNameFromHeader(Part file){
		String header = file.getHeader("content-disposition");	
		for(String piece : header.split(";")){
			if(piece.trim().startsWith("filename")){
				String filename = piece.substring(piece.indexOf("=")+1).trim().replace("\"","");
				return filename.substring(filename.lastIndexOf('/')+1).substring(filename.lastIndexOf('\\')+1); //MSIE
			}
		}
		return null;
	}
	
	public String makePath(String dir, String subdir){
		
		if(dir.contains("\\")) { // Windows
			subdir = subdir + "\\";
		}
		else { // Unix
			subdir = subdir + "/";
		}
		
		return subdir;
	}
	
	public String getExtension(String fileName){
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		
		return extension;
	}
	
}


