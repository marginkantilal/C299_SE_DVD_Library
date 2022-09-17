package com.library.main;

import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.library.controller.DVDController;
import com.library.dto.DVDDao;
import com.library.dto.DVDDaoImpl;
import com.library.view.DVDView;
import com.library.view.UserIO;
import com.library.view.UserIOConsoleImpl;

@SpringBootApplication
public class DvdLibraryApplication {

	public static void main(String[] args) throws ParseException {


		UserIO myIo = new UserIOConsoleImpl();
		DVDDao myDao = new DVDDaoImpl();
		DVDView myView = new DVDView(myIo);
		DVDController Controller = new DVDController(myView, myDao);
		Controller.run();
		
	
	}

}
