package com.library.dto;

import java.util.List;

import com.library.model.DVD;

public interface DVDDao {

	DVD addDVD(String title, DVD dvd) throws DVDDAOException;
	
	DVD removeDVD(String title) throws DVDDAOException;
	
	List<DVD> getAllDVDs() throws DVDDAOException;
	
	DVD getDVDByTitle(String title) throws DVDDAOException;
	
	DVD editDVDInfo(String title, String information, String change) throws DVDDAOException;
}
