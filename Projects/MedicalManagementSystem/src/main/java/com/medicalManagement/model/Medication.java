package com.medicalManagement.model;

import java.sql.Date;

public class Medication {
	private int medicationId;
	private String medicationName;
	private String dosage;
	private String frequency;
	private Date dateIssued;

	public Medication() {} // NOTE: Added a no-argument constructor

	public Medication(int medicationId, String medicationName, String dosage, String frequency, Date dateIssued) {
		// FIX: Corrected parameter typo and added missing 'dosage' field
		this.medicationId = medicationId;
		this.medicationName = medicationName;
		this.dosage = dosage;
		this.frequency = frequency;
		this.dateIssued = dateIssued;
	}

	public int getMedicationId() {
		return medicationId;
	}

	// FIX: Corrected setter name from 'setDiagnosisId' to 'setMedicationId'
	public void setMedicationId(int medicationId) {
		this.medicationId = medicationId;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Date getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
	}
}