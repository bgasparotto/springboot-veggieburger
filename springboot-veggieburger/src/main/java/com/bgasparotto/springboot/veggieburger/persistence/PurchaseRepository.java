package com.bgasparotto.springboot.veggieburger.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bgasparotto.springboot.veggieburger.model.Purchase;

/**
 * @author Bruno Gasparotto
 *
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}