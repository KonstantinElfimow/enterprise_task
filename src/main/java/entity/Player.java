package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "id", nullable = true)
    private Long id;

    @JsonProperty("playerId")
    public Long getId() {
        return id;
    }

    @JsonProperty("playerId")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonProperty("currencies")
    public List<Currency> getCurrencies() {
        return currencies;
    }

    @JsonProperty("currencies")
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @JsonProperty("items")
    public List<Item> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Item> items) {
        this.items = items;
    }


    @Column(name = "nickname", nullable = true)
    private String nickname;

    @Column(nullable = true)
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "currency_id"))
    private List<Currency> currencies;

    @Column(nullable = true)
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;


}
