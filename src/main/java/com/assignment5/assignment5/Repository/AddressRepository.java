package com.assignment5.assignment5.Repository;

import com.assignment5.assignment5.model.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByEmployeeId(Long postId);

    @Transactional
    void deleteByEmployeeId(long tutorialId);
}