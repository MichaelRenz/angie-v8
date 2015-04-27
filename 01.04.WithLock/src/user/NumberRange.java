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
package user;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class NumberRange {
    public static class BrokenRangeException extends Exception {
        public BrokenRangeException(NumberRange range) {
            super(range.toString());
        }
    }
    private final Lock lock = new ReentrantLock();
    private volatile int lower = 0;
    private volatile int upper = 0;

    public NumberRange() {
        super();
    }
    public NumberRange(int l, int u) throws BrokenRangeException {
        lower=l;
        upper=u;
        isValid();
    }

    public void setLower(int i) {
        lock.lock();
        try {
            if (i > upper)
                throw new IllegalArgumentException(
                        "can't set lower to " + i + " > upper");
            lower=i;
        } finally {
            lock.unlock();
        }
    }
    public void setUpper(int i) {
        lock.lock();
        try {
            if (i < lower)
                throw new IllegalArgumentException(
                        "can't set upper to " + i + " < lower");
            upper=i;
        } finally {
            lock.unlock();
        }
    }
    public int getLower() {
        return lower;
    }
    public int getUpper() {
        return upper;
    }
    public int[] getRange() {
        lock.lock();
        try {
            return new int[] {lower,upper};
        } finally {
            lock.unlock();
        }
    }

    public String toString() {
        lock.lock();
        try {
            return "["+lower+","+upper+"]";
        } finally {
            lock.unlock();
        }
    }
    public boolean isInRange(int i) {
        lock.lock();
        try {
            return (i >= lower && i <= upper);
        } finally {
            lock.unlock();
        }
    }
    public boolean isValid() throws BrokenRangeException {
        lock.lock();
        try {
            if (lower <= upper) return true;
            else throw new BrokenRangeException(this);
        } finally {
            lock.unlock();
        }
    }
}
