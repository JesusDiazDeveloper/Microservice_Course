package com.jesusdiaz.inventory_service.repositories;

import com.jesusdiaz.inventory_service.model.entities.Inventory;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    Optional<Inventory> findBySku(String sku);

    /**
     * Uses Spring Data JPA interpretation to automatically generate a query for
     * finding inventories by a list of SKUs.
     *
     * @param sku List of SKUs
     * @return List of Inventory objects matching the provided SKUs
     */
    List<Inventory> findBySkuIn(List<String>sku);
}
