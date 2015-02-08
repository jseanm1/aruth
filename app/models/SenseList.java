package models;

import java.util.List;

import exceptions.AruthAPIException;
import exceptions.ErrorCodes;

import play.Logger;

public class SenseList {

	private List<String> senses;
	private List<Long> offsets;
	
	public SenseList (List<String> senses, List<Long> offsets) throws AruthAPIException {
		
		if (senses.size()  != offsets.size()) {
			String errorMessage = "Sense and offset size mismatch. Cannot create SenseList";
			Logger.error(errorMessage);
			
			throw new AruthAPIException(ErrorCodes.SENSE_OFFSET_MISMATCH, errorMessage, null);
		}
		
		this.senses = senses;
		this.offsets = offsets;
	}
	
	public List<String> getSenses() {
		return senses;
	}

	public void setSenses(List<String> senses) {
		this.senses = senses;
	}

	public List<Long> getOffsets() {
		return offsets;
	}

	public void setOffsets(List<Long> offsets) {
		this.offsets = offsets;
	}
}
