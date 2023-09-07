package br.com.joelmaciel.food.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "freight_rate", nullable = false)
    private BigDecimal freightRate;

    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @Embedded
    private Address address;

    private Boolean active = Boolean.TRUE;

    private Boolean open = Boolean.FALSE;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime creationDate;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updateDate;

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurant_form_payment",
            joinColumns = @JoinColumn(name = "restaurant_Id"),
            inverseJoinColumns = @JoinColumn(name = "form_payment_id"))
    private Set<FormPayment> formPayments = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "restaurant_responsible_user",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> responsible = new HashSet<>();

    public void activate() {
        setActive(true);
    }

    public void inactivate() {
        setActive(false);
    }

    public boolean removeFormPayment(FormPayment formPayment) {
        return getFormPayments().remove(formPayment);
    }

    public boolean addFormPayment(FormPayment formPayment) {
        return getFormPayments().add(formPayment);
    }

    public boolean acceptPaymentForm(FormPayment formPayment) {
        return getFormPayments().contains(formPayment);
    }
    public boolean doesNotAcceptPaymentForm(FormPayment formPayment) {
        return !getFormPayments().contains(formPayment);
    }

    public void open() {
        setOpen(true);
    }

    public void close() {
        setOpen(false);
    }

    public boolean addResponsible(User user) {
        return getResponsible().add(user);
    }

    public boolean removeResponsible(User user) {
        return getResponsible().remove(user);
    }

}



