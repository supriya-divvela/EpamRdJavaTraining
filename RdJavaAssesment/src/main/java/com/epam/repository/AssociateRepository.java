package com.epam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.model.Associate;
@Repository
public interface AssociateRepository extends JpaRepository<Associate, Integer>{

	List<Associate> findAllByGender(String gender);

}
