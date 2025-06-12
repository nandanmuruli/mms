package com.medicalManagement.model;

import java.sql.Date;

public class Bill {
	private int billId;
	private int patientId;
	private String patientName;
	private String insuranceType;
	private double amount; // FIX: Renamed from 'Amount' to follow camelCase convention
	private String paymentMethod;
	private Date billingDate;
	private int doctorId;
	private String visitDescription; // FIX: Changed type from 'int' to 'String', which is more appropriate

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount; // FIX: Corrected assignment
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getVisitDescription() {
		return visitDescription;
	}

	public void setVisitDescription(String visitDescription) {
		this.visitDescription = visitDescription;
	}
}