package br.com.joelmaciel.food.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime creationDate;

    @ManyToMany
    @JoinTable(name = "user_cluster",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "cluster_id"))
    private Set<Cluster> clusters = new HashSet<>();

    public boolean passwordMatches(String password) {
        return getPassword().equals(password);
    }

    public boolean passwordDoesNotMatch(String password) {
        return !passwordMatches(password);
    }

    public boolean removeCluster(Cluster cluster) {
        return getClusters().remove(cluster);
    }

    public boolean addCluster(Cluster cluster) {
        return getClusters().add(cluster);
    }
}







