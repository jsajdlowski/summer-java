package com.recruitment.fund_box.repository;

import com.recruitment.fund_box.domain.CollectionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionBoxRepository extends JpaRepository<CollectionBox, Long> {
}
