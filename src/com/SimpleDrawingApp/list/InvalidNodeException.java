/* InvalidNodeException.java */

package com.SimpleDrawingApp.list;

/**
 *  Implements an Exception that signals an attempt to use an invalid ListNode.
 */

public class InvalidNodeException extends Exception {
    private static final long serialVersionUID = 1L; //eclipse complained...

    protected InvalidNodeException() {
	super();
    }

    protected InvalidNodeException(String s) {
	super(s);
    }
}
