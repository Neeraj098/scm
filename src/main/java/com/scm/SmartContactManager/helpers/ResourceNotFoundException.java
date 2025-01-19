package com.scm.SmartContactManager.helpers;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String message)
    {
        super(message);

    }

    public ResourceNotFoundException()
    {
        super("Resource Not Found");

    }


}
