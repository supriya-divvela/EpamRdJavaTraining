package com.epam.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServiceStation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private int registrationNo;
	private int engineNo;
	private LocalDate deliveryDate;
	public Integer getId() {
		return id;
	}
	public int getRegistrationNo() {
		return registrationNo;
	}
	public int getEngineNo() {
		return engineNo;
	}
	public LocalDate getDeliveryDate() {
		this.deliveryDate=LocalDate.now();
		return deliveryDate;
	}
}
