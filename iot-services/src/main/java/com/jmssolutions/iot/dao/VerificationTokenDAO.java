package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.VerificationToken;

/**
 * Created by jakub on 30.01.16.
 */
public interface VerificationTokenDAO {
    VerificationToken insertToken(VerificationToken token);
    VerificationToken findByToken(String token);
    VerificationToken deleteByUserId(long id);
}
