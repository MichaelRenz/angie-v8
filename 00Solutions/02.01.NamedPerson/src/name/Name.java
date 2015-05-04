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
package name;

/*
 * Here is the result that the various methods shall yield:
    getFirstName(): 	Angela
    getMiddleName(): 	Dorothea
    getMiddleInital(): 	D
    getLastName(): 	    Merkel
    getName(): 		    Angela D. Merkel
 */
public interface Name {
    String getFirstName();
    String getMiddleName();
    String getLastName();
    default char getMiddleInitial() {
        if (getMiddleName()!=null&&getMiddleName().length()>0)
            return getMiddleName().charAt(0);
        return ' ';
    }
    default String getName() {
        char c = getMiddleInitial();
        return getFirstName()
                +(c!=' '?" "+c+'.':"")
                +" "+getLastName();
    }
}
