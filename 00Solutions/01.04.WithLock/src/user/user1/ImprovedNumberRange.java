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
package user.user1;

import user.NumberRange;
import util.util1.CriticalRegion;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static util.util1.Utilities.*;
import static util.Exceptions.*;

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
        });
    }
    public void setUpper(int i) {
        withLock(lock, () -> {
            if (i < lower)
                throw new IllegalArgumentException(
                        "can't set upper to " + i + " < lower");
            upper = i;
        });
    }

    public int getLower() {
        return lower;
    }
    public int getUpper() {
        return upper;
    }
//    public int[] getRange() {
//        return
//        withLock(lock, ()->{
//            return new int[] {lower,upper};
//        });
//    }
    public int[] getRange() {
        int[][] retval = new int[][] {null};
        withLock(lock, ()->{
           retval[0] = new int[] {lower,upper};
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
    /*
     *  Solution that uses yet another helper function withLockChecked() and functional interface.
     *  The ugly cast is necessary due to the overloaded versions for generic and primitive return types;
     *  it has nothing to do with the checked exception problem.
     */
//    public boolean isValid() throws BrokenRangeException {
//        return
//        withLockChecked(lock,(CriticalRegion.BooleanWithException<BrokenRangeException>) () -> {
//            if (lower <= upper) return true;
//            else throw new BrokenRangeException(this);
//        });
//    }
    /*
     * Alternative solution that does not need the extra helper function and functional interface,
     * but requires of the users to throw checked exceptions via the throwUnchecked() utility.
     */
    public boolean isValid() throws BrokenRangeException {
        return
        withLock(lock,(CriticalRegion.Boolean) () -> {
            if (lower <= upper) {
                return true;
            } else  {
                throwUnchecked(new BrokenRangeException(this));
                return false;
             }
        });
    }
}
