package com.medicalManagement.model;

public class Diagnosis {
	private int diagnosisId;
	private String diagnosis;
	private String criticality;

	public Diagnosis() {} // NOTE: Added a no-argument constructor for flexibility

	public Diagnosis(int diagnosisId, String diagnosis, String criticality) {
		// FIX: Corrected constructor parameter name for consistency
		this.diagnosisId = diagnosisId;
		this.diagnosis = diagnosis;
		this.criticality = criticality;
	}

	public int getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getCriticality() {
		return criticality;
	}

	public void setCriticality(String criticality) {
		this.criticality = criticality;
	}
}