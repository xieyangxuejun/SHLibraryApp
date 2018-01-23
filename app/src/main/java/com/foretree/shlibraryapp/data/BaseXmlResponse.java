package com.foretree.shlibraryapp.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by silen on 21/01/2018.
 */

@Root(name = "soapenv:Envelope")
@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soapenv")
public class BaseXmlResponse<T> {
    @Element(name = "Body")
    public T body;

    public T getData() {
        return body;
    }
}
