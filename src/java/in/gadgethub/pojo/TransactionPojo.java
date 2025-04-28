/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.pojo;

import java.util.Date;

/**
 *
 * @author ashmi
 */
public class TransactionPojo {
    private String transactionId;
    private String useremail;
    private Date transactionDate;
    private double amount;

    public TransactionPojo() {
    }

    public TransactionPojo(String transactionId, String useremail, Date transactionDate, double amount) {
        this.transactionId = transactionId;
        this.useremail = useremail;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionPojo{" + "transactionId=" + transactionId + ", useremail=" + useremail + ", transactionDate=" + transactionDate + ", amount=" + amount + '}';
    }
}
