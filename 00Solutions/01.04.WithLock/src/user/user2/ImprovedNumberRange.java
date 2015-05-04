/*
  Based on course material for "Lambdas & Streams", a seminar prepared
  and owned by Angelika Langer & Klaus Kreft.
  contact: http://www.AngelikaLanger.com/ or mailto: info@AngelikaLanger.com

  © Copyright 2013-2014 by Angelika Langer & Klaus Kreft. All rights reserved.

  Permission to use, copy, and modify this software for any non-profit
  purpose is hereby granted to attendants of the above mentioned seminar
  without fee, provided that the above copyright notice appears in all
  copies.  Angelika Langer and Klaus Kreft make no representations about
  the suitability of this software for any purpose.  It is provided
  "as is" without express or implied warranty.
*/
package user.user2;

import user.NumberRange;
import util.util2.CriticalRegion;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.Exceptions.throwUnchecked;
import static util.util2.Utilities.withLock;

public final class ImprovedNumberRange implements NumberRange {
    private final Lock lock = new ReentrantLock();
    private volatile int lower = 0;
    private volatile int upper = 0;

    public ImprovedNumberRange() {
        super();
    }
    public ImprovedNumberRange(int l, int u) throws BrokenRangeException {
        lower=l;
        upper=u;
        isValid();
    }

    public void setLower(int i) {
        withLock(lock, () -> {
            if (i > upper)
                throw new IllegalArgumentException("can't set lower to " + i + " > upper");
            lower = i;
            return (Void)null;
        });
    }
    public void setUpper(int i) {
        withLock(lock, () -> {
            if (i < lower)
                throw new IllegalArgumentException(
                        "can't set upper to " + i + " < lower");
            upper = i;
            return (Void)null;
        });
    }

    public int getLower() {
        return lower;
    }
    public int getUpper() {
        return upper;
    }

    public int[] getRange() {
        int[][] retval = new int[][] {null};
        withLock(lock, ()->{
            retval[0] = new int[] {lower,upper};
            return (Void)null;
        });
        return retval[0];
    }
    public String toString() {
        return
        withLock(lock, ()->{
            return "["+lower+","+upper+"]";
        });
    }
    public boolean isInRange(int i) {
        return
                withLock(lock, ()->{
                    return (i >= lower && i <= upper);
                });
    }

    public boolean isValid() throws BrokenRangeException {
        return
        withLock(lock,(CriticalRegion.Generic<Boolean>) () -> {
            if (lower <= upper) {
                return true;
            } else  {
                throwUnchecked(new BrokenRangeException(this));
                return false;
             }
        });
    }
}
