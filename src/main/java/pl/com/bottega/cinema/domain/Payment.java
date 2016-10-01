package pl.com.bottega.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by bernard.boguszewski on 01.10.2016.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    private String type;
    private Long cashierId;
    private boolean successful;
    //private TransactionData transactionData;

    public Payment(String type, Long cashierId){
        this.type = type;
        this. cashierId = cashierId;
    }

}
