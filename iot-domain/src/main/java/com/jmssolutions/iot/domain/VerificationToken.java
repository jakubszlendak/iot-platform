package com.jmssolutions.iot.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jakub on 30.01.16.
 */
@Entity
@Table(name = "token")
public class VerificationToken {

    private final static int EXPIRATION = 60*24;
    @Id
    @GeneratedValue
    @Column(name = "token_id")
    private long ID;

    @Column(name = "token_value")
    private String token;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public VerificationToken(String token) {
        this.token = token;
        this.expirationDate = calculateExpirationTime(EXPIRATION);
    }

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationDate = calculateExpirationTime(EXPIRATION);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerificationToken that = (VerificationToken) o;

        if (ID != that.ID) return false;
        if (!token.equals(that.token)) return false;
        if (!expirationDate.equals(that.expirationDate)) return false;
        return user.equals(that.user);

    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + token.hashCode();
        result = 31 * result + expirationDate.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
                "expirationDate=" + expirationDate +
                ", token='" + token + '\'' +
                '}';
    }

    public VerificationToken() {}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Date calculateExpirationTime(int minutesToExpire){
        Calendar c = Calendar.getInstance();
        c.setTime(new Timestamp(c.getTime().getTime()));
        c.add(Calendar.MINUTE, minutesToExpire);
        return new Date(c.getTime().getTime());
    }

}
