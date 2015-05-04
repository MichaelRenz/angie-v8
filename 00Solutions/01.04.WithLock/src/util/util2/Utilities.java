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
package util.util2;

import java.util.concurrent.locks.Lock;

public class Utilities {
    private Utilities() {
        throw new UnsupportedOperationException();
    }



    public static <R> R withLock(Lock lock, CriticalRegion.Generic<? extends R> cr) {
        lock.lock();
        try {
            return cr.apply();
        } finally {
            lock.unlock();
        }
    }

    public static <R,E extends Exception>
    R withLockChecked(Lock lock, CriticalRegion.GenericWithException<? extends R, ? extends E> cr) throws E {
        lock.lock();
        try {
            return cr.apply();
        } finally {
            lock.unlock();
        }
    }

}
