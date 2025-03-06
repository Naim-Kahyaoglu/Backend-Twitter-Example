package main.java.com.twitter.repository;



import com.twitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword%")
    List<User> searchUsers(String keyword);

    @Query(value = "SELECT * FROM users u WHERE u.id IN " +
            "(SELECT DISTINCT t.user_id FROM tweets t WHERE t.content LIKE %:keyword%)",
            nativeQuery = true)
    List<User> findUsersByTweetContent(String keyword);

    @Query("SELECT COUNT(f) > 0 FROM User u JOIN u.followers f WHERE u.id = :userId AND f.id = :followerId")
    boolean isFollowing(Long userId, Long followerId);
}