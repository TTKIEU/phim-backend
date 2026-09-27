package com.phim.phim_backend.idempotency;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdempotencyRepository
        extends JpaRepository<IdempotencyRecord, UUID> {

    Optional<IdempotencyRecord> findByUserIdAndIdempotencyKey(
            String userId,
            String idempotencyKey
    );
}