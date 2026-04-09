package com.abila.Store.repository;

import com.abila.Store.domain.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendasRepository extends JpaRepository<Vendas, Integer> {
}
