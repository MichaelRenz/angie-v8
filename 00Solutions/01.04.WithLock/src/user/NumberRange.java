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

public interface NumberRange {
    void setLower(int i);
    void setUpper(int i);
    int getLower();
    int getUpper();
    boolean isInRange(int i);
    int[] getRange();
    boolean isValid() throws BrokenRangeException;

    public static class BrokenRangeException extends Exception {
        public BrokenRangeException(NumberRange range) {
            super(range.toString());
        }
    }
}
