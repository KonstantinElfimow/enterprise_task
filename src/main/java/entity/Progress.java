package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "progress")
public class Progress {

    @Id
    @Column(name = "id", nullable = true)
    private Long id;

    @Column(name = "resourceid", nullable = true)
    private Long resourceId;

    @Column(name = "score", nullable = true)
    private Long score;

    @Column(name = "maxscore", nullable = true)
    private Long maxScore;


    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "player_id")
    private Player player;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Long maxScore) {
        this.maxScore = maxScore;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
