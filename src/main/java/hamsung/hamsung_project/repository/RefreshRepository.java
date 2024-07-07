package hamsung.hamsung_project.repository;

import hamsung.hamsung_project.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshRepository extends JpaRepository<RefreshToken, Long> {

    Boolean existsByRefresh(String refresh);

    @Transactional
    void deleteByRefresh(String refresh);

    @Transactional
    @Modifying
    @Query("DELETE FROM RefreshToken r WHERE r.expiration < CURRENT_TIMESTAMP")
    void deleteExpiredTokens();
}
