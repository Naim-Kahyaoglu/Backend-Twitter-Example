package main.java.com.twitter.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "retweets", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "original_tweet_id"})
})
public class Retweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_tweet_id", nullable = false)
    private Tweet originalTweet;

    // Optional comment on the retweet
    @Size(max = 280)
    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;
}