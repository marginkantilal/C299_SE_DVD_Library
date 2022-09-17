package com.library.view;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import com.library.model.DVD;

public class DVDView {

	private UserIO io;
	
	public DVDView(UserIO io) {
		this.io = io;
	}
	
	//Display the menu to the user.
	public int printMenu() {
		
		io.print("Please select from the following choices:");
		io.print("1. Add a DVD to the collection");
		io.print("2. Remove a DVD from the collection");
		io.print("3. Edit information for existing DVD");
		io.print("4. List all DVDs in the collection");
		io.print("5. Display information for a particular DVD");
		io.print("6. Exit");
		
		io.print("Please enter a number from 1 to 6");
		return io.readInteger();
	}
	
	//Allow the user to enter information about the DVD
	public DVD getNewDVDInfo() throws ParseException {
		
		io.print("Please enter the DVD's title");
		String title = io.readString();
	
		io.print("Please enter the release date of the DVD YYYY-MM-DD");
		String releaseDate = io.readString();
		LocalDate date;
		date = LocalDate.parse(releaseDate);
		
		
		io.print("Please enter the MPAARating of the DVD");
		String MPAARating = io.readString();
		
		io.print("Please enter the director's name");
		String directorName = io.readString();
		
		io.print("Please enter the studio's name");
		String studio = io.readString();
		
		io.print("Please enter any additional information about the DVD");
		String note = io.readString();
		
		DVD currentDVD = new DVD();
		currentDVD.setTitle(title);
		currentDVD.setReleaseDate(date);
		currentDVD.setMPAARating(MPAARating);
		currentDVD.setDirectorName(directorName);
		currentDVD.setStudio(studio);
		currentDVD.setUserNote(note);
		
		return currentDVD;
	}
	
	//Display a new message for creating a DVD
	public void displayCreateDVDMessage() {
	    io.print("----Creating DVD -----");
	}
	
	//Show that the DVD HAS BEEN CREATED successful.
	public void displayDVDCreatedSuccessMessage() {
	    io.print("DVD has been successfully created, please press enter to continue");
	    io.readString();
	}
	
	//Display all DVDs and their information from a DVD collection
	public void displayDVDList(List<DVD> DVDList) {
		
		for(DVD dvd : DVDList) {
			String dvdInfo = "Title: " + dvd.getTitle() 
			+ "\nRelease Date: " + dvd.getReleaseDate()
			+ "\nMPAA Rating: " + dvd.getMPAARating()
			+ "\nDirector Name: " + dvd.getDirectorName()
			+ "\nStudio: " + dvd.getStudio()
			+ "\nNotes: " + dvd.getUserNote();
			
			io.print(dvdInfo);
		}
		io.print("Please press enter to continue");
		io.readString();
	}
	
	//Show that the DVDs are being displayed.
	public void displayDisplayAllBanner() {
		io.print("----------------Display all DVDs------------------");
	}
	
	//Show that the display for all DVDs has been successful.
	public void displayDisplayAllSuccessMessage() {
		io.print("All DVDs were displayed");
	}
	
	//Request title name from the user.
	public String DVDTitleRequest() {
		io.print("Please Enter the DVD's title");
		String title = io.readString();
		return title;
	}
	
	//Display information about a particular DVD
	public DVD displayDVD(DVD dvd) {
		
		if(dvd != null) {
			String dvdInfo = "Title: " + dvd.getTitle() 
			+ "\nRelease Date: " + dvd.getReleaseDate()
			+ "\nMPAA Rating: " + dvd.getMPAARating()
			+ "\nDirector Name: " + dvd.getDirectorName()
			+ "\nStudio: " + dvd.getStudio()
			+ "\nUser Notes: " + dvd.getUserNote();
			
			io.print(dvdInfo);
		}
		io.print("Please press enter to continue");
		io.readString();
		
		return dvd;
	}
	
	//Show that the DVD is being displayed.
	public void displayDisplayedBanner() {
		io.print("------------Display DVD-------------");
	}
	
	//Determine whether the display was successful or not.
	public void showDisplayedDVDSuccessMessage(DVD dvd) {
		
		if(dvd != null) {
			io.print("DVD was successfully displayed");
		}
		else {
			io.print("No such DVD found");
		}
	}
	
	//Determine whether the removal was successful or not
	public void removeDVDSuccessMessage(DVD dvd) {
		
		if(dvd != null) {
			io.print("DVD was removed successfully");
		}
		else {
			io.print("No such DVD found");
		}
	}
	
	//Display a message for unknown command.
	public void displayUnknownCommandMessage() {
		io.print("Unknown message, please try again");
	}
	
	//Display exit message
	public void displayExitMessage() {
		io.print("Good bye!");
	}
	
	//Display error message
	public void displayErrorMessage(String errorMsg) {
		io.print("-----------Error---------- ");
		io.print(errorMsg);
	}
	
	//Request piece of information associated with a DVD class
	public String getInformation() {
		io.print("Please enter information related to the DVD");
		String userInput = io.readString();
		return userInput;
	}
	
	//Request a change from the user.
	public String getChange() {
		io.print("Please enter your new change that you'd like to make");
		String userInput = io.readString();
		return userInput;
	}
	
	//Determine whether the change was successful or not.
	public void displayMakeChangeSuccessMessage(DVD dvd) {
		
		if(dvd != null) {
			io.print("DVD information changed successfully");
		}
		else {
			io.print("DVD change was unsuccessful, please make sure that you enter correct DVD "
					+ "title and correct piece of information");
		}
	}
}
