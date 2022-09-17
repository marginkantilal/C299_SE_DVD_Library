package com.library.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DVD {
	
	private String title;
	
	private LocalDate releaseDate;
	
	private String MPAARating;
	private String directorName;
	private String studio;
	private String userNote;
	
	@Override
	public int hashCode() {
		int hash = 6;
		hash = Objects.hash(title, releaseDate,MPAARating,directorName,studio,userNote);
		return hash;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DVD other = (DVD) obj;
		return Objects.equals(title, other.title)
				&& Objects.equals(releaseDate, other.releaseDate) && Objects.equals(MPAARating, other.MPAARating) 
				&& Objects.equals(directorName, other.directorName) && Objects.equals(studio, other.studio)&&
				Objects.equals(userNote, other.userNote);
	}
	@Override
	public String toString() {
		return "DVD [ Title=" + title + 
				", ReleaseDate=" + releaseDate +
				", MPAARating=" + MPAARating +
				", DirectorName=" + directorName +
				", Studio=" + studio +
				", UserNote=" + userNote + "]";
	}
	
	
}
