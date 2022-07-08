package ru.oldjew.bankapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(name = "FIN_OPERATIONS")
public class FinanceOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    @NonNull
    @JsonIgnore
    private User user;

    @NonNull
    @Column(name = "OPERATION", nullable = false)
    private String operation;

    @NonNull
    @Column(name = "VALUE", nullable = false)
    private BigDecimal value;

    @Column(name = "DATE")
    private LocalDate date;

    public FinanceOperation(@NonNull User user, @NonNull String operation, @NonNull BigDecimal value, LocalDate date) {
        this.user = user;
        this.operation = operation;
        this.value = value;
        this.date = date;
    }
}
