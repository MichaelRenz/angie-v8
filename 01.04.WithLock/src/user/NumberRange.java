/*
  Based on course material for "Lambdas & Streams", a seminar prepared
  and owned by Angelika Langer & Klaus Kreft.
  contact: http://www.AngelikaLanger.com/ or mailto: info@AngelikaLanger.com

  Â© Copyright 2013-2014 by Angelika Langer & Klaus Kreft. All rights reserved.

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
import java.util.function.Function;

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
		lower = l;
		upper = u;
		isValid();
	}

	public void setLower(int i) {
		handleLockUnlockWithExceptionVoid(() -> {
			if (i > upper)
				throw new IllegalArgumentException("can't set lower to " + i
						+ " > upper");
			lower = i;
		});
	}

	public void setUpper(int i) {
		handleLockUnlockWithExceptionVoid(() -> {
			if (i < lower)
				throw new IllegalArgumentException("can't set upper to " + i
						+ " < lower");
			upper = i;
		});

	}

	public int getLower() {
		return lower;
	}

	public int getUpper() {
		return upper;
	}

	public int[] getRange() {
		return handleLockUnlock(() -> {
			return new int[] { lower, upper };
		});

		// lock.lock();
		// try {
		// return new int[] {lower,upper};
		// } finally {
		// lock.unlock();
		// }
	}

	public String toString() {
		return handleLockUnlock(() -> {
			return "[" + lower + "," + upper + "]";
		});

	}

	public boolean isInRange(int i) {
		return handleLockUnlock(() -> {
			return (i >= lower && i <= upper);
		});
	}

	public boolean isValid() throws BrokenRangeException {
		return handleLockUnlockWithException(() -> {
			if (lower <= upper)
				return true;
			else
				throw new BrokenRangeException(this);
		});
	}

	<T> T handleLockUnlockWithException(
			ReflectiveExecutionWithException<T> action) {
		lock.lock();
		try {
			return action.run();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		} catch (BrokenRangeException bre) {
			bre.printStackTrace();
		} finally {
			lock.unlock();
		}
		return null;

	}
	
	void handleLockUnlockWithExceptionVoid(
			ReflectiveExecutionVoid action) {
		lock.lock();
		try {
			action.run();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	<T> T handleLockUnlock(ReflectiveExecution<T> action) {
		lock.lock();
		try {
			return action.run();
		} finally {
			lock.unlock();
		}

	}
}
