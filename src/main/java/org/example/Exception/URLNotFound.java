package org.example.Exception;

public class URLNotFound extends Exception{
    public URLNotFound(final String e){
        super(e);
    }

    public URLNotFound(final String e, final Throwable cause){
        super(e, cause);
    }
}
