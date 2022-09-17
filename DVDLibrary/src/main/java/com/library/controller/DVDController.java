package com.library.controller;

import java.text.ParseException;

import com.library.dto.DVDDao;
import com.library.dto.DVDDAOException;
import com.library.model.DVD;
import com.library.view.DVDView;
import com.library.view.UserIO;
import com.library.view.UserIOConsoleImpl;

public class DVDController {
	
	private DVDView view;
	private DVDDao dao;
	
	private UserIO io = new UserIOConsoleImpl();
	
	public DVDController(DVDView view, DVDDao dao) {
		this.view = view;
		this.dao = dao;
	}
	
	//This is where the controller begins executing
	public void run() throws ParseException {
		boolean isKeepOn = true;
		int userSelection = 0;
		
		try {
		while(isKeepOn) {
			
			//Getting selection from user
			userSelection = getMenuSelection();
			
			switch(userSelection) {
				
			case 1:
				System.out.println("Creating new");
				createDVD();
				break;
			case 2:
				System.out.println("Removing DVD");
				removeDVD();
				break;
			case 3:
				editDVD();
				break;
			case 4:
				displayDVDs();
				break;
			case 5:
				displayDVDByTitle();
				break;
			case 6:
				isKeepOn = false;
				break;
			default:
				unknownCommand();
			}
		}
		exitMessage();
		}
		catch(DVDDAOException e) {
			view.displayErrorMessage(e.getMessage());
		}
	}
	
	//Receive menu selection from user
	private int getMenuSelection() {
		return view.printMenu();
	}
	
	//Create a new DVD
	private void createDVD() throws DVDDAOException, ParseException{
		view.displayCreateDVDMessage();
		DVD newDVD = view.getNewDVDInfo();
		dao.addDVD(newDVD.getTitle(), newDVD);
		view.displayDVDCreatedSuccessMessage();
	}
	
	//Display all DVDs in the collection
	private void displayDVDs() throws DVDDAOException{
		view.displayDisplayAllBanner();
		view.displayDVDList(dao.getAllDVDs());
		view.displayDisplayAllSuccessMessage();
	}
	
	//Display DVD by title
	private void displayDVDByTitle() throws DVDDAOException{
		view.displayDisplayedBanner();
		String DVDtitle = view.DVDTitleRequest();
		DVD tempDVD = view.displayDVD(dao.getDVDByTitle(DVDtitle));
		view.showDisplayedDVDSuccessMessage(tempDVD);
	}
	
	//Remove a particular DVD
	private void removeDVD() throws DVDDAOException{
		String title = view.DVDTitleRequest();
		DVD dvd = dao.removeDVD(title);
		view.removeDVDSuccessMessage(dvd);
	}
	
	//Edit a particular DVD
	private void editDVD() throws DVDDAOException{
		
		String title = view.DVDTitleRequest();
		String information = view.getInformation();
		String change = view.getChange();
		DVD dvd = dao.editDVDInfo(title, information, change);
		view.displayMakeChangeSuccessMessage(dvd);
	}
	
	//Tell the user that the message is unknown
	private void unknownCommand() {
		view.displayUnknownCommandMessage();
	}
	
	//Tell the user that the program has ended
	private void exitMessage() {
		view.displayExitMessage();
	}

}
