package com.library.dto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.library.model.DVD;

public class DVDDaoImpl implements DVDDao{
	
	Scanner sc;
	
private Map<String, DVD> DVDs = new HashMap<>();
	
	public static final String DVD_File = "DVD.txt";
	public static final String DELIMITER = "::";
	
	//Adding a particular DVD by title
	@Override
	public DVD addDVD(String title, DVD dvd) throws DVDDAOException{
		loadDVD();
		DVD newDVD = DVDs.put(title, dvd);
		writeDVD();
		return newDVD;
	}
	
	//Remove a particular DVD by title
	@Override
	public DVD removeDVD(String title) throws DVDDAOException {
		loadDVD();
		DVD removedDVD = DVDs.remove(title);
		writeDVD();
		return removedDVD;
	}
	
	//Get all DVDs from the DVD collection
	@Override
	public List<DVD> getAllDVDs() throws DVDDAOException{
		loadDVD();
		return new ArrayList<DVD>(DVDs.values());
	}
	
	//Get a particular DVD from the DVD collection
	@Override
	public DVD getDVDByTitle(String title) throws DVDDAOException{
		loadDVD();
		return DVDs.get(title);
	}
	
	//Edit particular information about the DVD
	@Override
	public DVD editDVDInfo(String title, String information, String change) throws DVDDAOException{
		loadDVD();
		DVD dvd = DVDs.get(title);
		
		if(dvd != null) {
			if(information.equalsIgnoreCase("Title")) {
				dvd.setTitle(change);
			}
			else if(information.equalsIgnoreCase("ReleaseDate")) {
				LocalDate date = LocalDate.parse(change);
						
				dvd.setReleaseDate(date);
			}
			else if(information.equalsIgnoreCase("MPAARating")) {
				dvd.setMPAARating(change);
			}
			else if(information.equalsIgnoreCase("DirectorName")) {
				dvd.setDirectorName(change);
			}
			else if(information.equalsIgnoreCase("Studio")) {
				dvd.setStudio(change);
			}
			else if(information.equalsIgnoreCase("Note")) {
				dvd.setUserNote(change);
			}
			else {
				dvd = null;
			}
		}
		if(dvd != null) DVDs.replace(title, dvd);
		writeDVD();
		return dvd;
		
	}
	
	//Translate data from file to an object in memory
	private DVD unmarshallDVD(String DVDAsText) {
		
		String[] DVDAsElements = DVDAsText.split(DELIMITER);
		String title = DVDAsElements[0];
		DVD dvdFromFile = new DVD();
		dvdFromFile.setTitle(title);
		dvdFromFile.setReleaseDate(LocalDate.parse(DVDAsElements[1]));
		dvdFromFile.setMPAARating(DVDAsElements[2]);
		dvdFromFile.setDirectorName(DVDAsElements[3]);
		dvdFromFile.setStudio(DVDAsElements[4]);
		dvdFromFile.setUserNote(DVDAsElements[5]);
		
		return dvdFromFile;
	}
	
	//Put all unmarshalled data into the DVD collection
	private void loadDVD() throws DVDDAOException{
		
		
		
		try {
			sc = new Scanner(new BufferedReader(new FileReader(DVD_File)));
		}
		catch(FileNotFoundException e) {
			throw new DVDDAOException("Could not locate the file", e);
		}
		
		String currentLine;
		DVD currentDVD;
		
		while(sc.hasNextLine()) {
			currentLine = sc.nextLine();
			currentDVD = unmarshallDVD(currentLine);
			DVDs.put(currentDVD.getTitle(), currentDVD);
		}
		sc.close();
	}
	
	//Translate data from object in memory into a text file.
	private String marshallDVD(DVD dvd) {
		
		String DVDAsText = dvd.getTitle() + DELIMITER;
		DVDAsText += dvd.getReleaseDate() + DELIMITER;
		DVDAsText += dvd.getMPAARating() + DELIMITER;
		DVDAsText += dvd.getDirectorName() + DELIMITER;
		DVDAsText += dvd.getStudio() + DELIMITER;
		DVDAsText += dvd.getUserNote() + DELIMITER;
		return DVDAsText;
	}
	
	//Write marshalled data into the text file.
	private void writeDVD() throws DVDDAOException{
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(DVD_File));
		}
		catch(Exception e) {
			throw new DVDDAOException("Could not save DVD data", e);
		}
		String DVDAsText;
		List<DVD> DVDList = this.getAllDVDs();
		for(DVD currentDVD : DVDList) {
			DVDAsText = marshallDVD(currentDVD);
			out.println(DVDAsText);
			out.flush();
		}
		out.close();
	}

}
