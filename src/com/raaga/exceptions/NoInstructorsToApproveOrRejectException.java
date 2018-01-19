package com.raaga.exceptions;

@SuppressWarnings("serial")
public class NoInstructorsToApproveOrRejectException extends Exception {

	public NoInstructorsToApproveOrRejectException()
	{
		super("No leave Applications Available");
	}
}
