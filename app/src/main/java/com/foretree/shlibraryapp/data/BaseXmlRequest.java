package com.foretree.shlibraryapp.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by silen on 21/01/2018.
 */

@Root(name = "soapenv:Envelope")
@NamespaceList({
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soapenv"),
        @Namespace(reference = "http://www.example.org/Shlib_Mobile_WS_Solr/", prefix = "shl")
})

public class BaseXmlRequest<T> {
    @Element(name = "soapenv:Body")
    private T body;
//    @Element(name = "soapenv:Header")
//    public BaseXmlHeader header;

    public void setBody(T body) {
        this.body = body;
    }

    public T getData() {
        return body;
    }
//    @Root(name = "soapenv:Header")
//    private static class BaseXmlHeader {
//
//    }
}
