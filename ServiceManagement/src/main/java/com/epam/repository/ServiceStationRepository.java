package com.epam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.ServiceStation;
@Repository
public interface ServiceStationRepository extends JpaRepository<ServiceStation, Integer>{

	List<ServiceStation> findAllByRegistrationNo(int registrationNo);

}
