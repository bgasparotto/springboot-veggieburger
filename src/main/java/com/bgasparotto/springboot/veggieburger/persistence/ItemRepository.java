package com.bgasparotto.springboot.veggieburger.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bgasparotto.springboot.veggieburger.model.Item;

/**
 * @author Bruno Gasparotto
 *
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}