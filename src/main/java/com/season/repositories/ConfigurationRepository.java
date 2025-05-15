package com.season.repositories;

import com.season.entities.Configuration;
import com.season.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

public interface ConfigurationRepository  extends JpaRepository<Configuration, Long> {
}
=======
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    Optional<Configuration> findByStore(Store store);
}
>>>>>>> 4928aa1bce3501c8a93e65ca2ef489b110be779b
