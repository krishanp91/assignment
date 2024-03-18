package com.smashtaps.assignment.repository;

import com.smashtaps.assignment.entity.Shopper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Integer> {
    Optional<Shopper> findByShopperId(String shopperId);

    @Query("SELECT s FROM Shopper s JOIN s.shelves v JOIN v.product p WHERE s.shopperId = :shopperId " +
            "AND p.category = :category AND p.brand = :brand")
    Page<Shopper> filterShopperDetails(String shopperId, String category, String brand, Pageable pageable);
}
