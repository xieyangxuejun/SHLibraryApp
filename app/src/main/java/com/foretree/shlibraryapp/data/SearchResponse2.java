package com.foretree.shlibraryapp.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by silen on 21/01/2018.
 */
@Root(name = "soapenv:Envelope")
@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soapenv")
public class SearchResponse2 {
    @Element(name = "Body")
    public ResponseBody body;

    @Root(name = "Body")
    public static class ResponseBody {
        @Element(name = "Mobile_iPacResponse")
        public ResponseModel model;

    }

    @Root(name = "Mobile_iPacResponse")
    public static class ResponseModel {
        @Attribute(name = "xmlns:ns1", empty = "http://www.example.org/Shlib_Mobile_WS_Solr/", required = false)
        public String nameSpace;
        @Element(name = "PageSize")
        public String pageSize;
        @Element(name = "MaxRows")
        public String maxRows;
        @Element(name = "CurrentRow")
        public String currentRow;
        @Element(name = "QueryString")
        public String queryString;
        @ElementList(name = "BookItem", inline = true, entry = "BookItem")
        public List<BookItem> bookItems;
    }

    @Root(name = "BookItem")
    public static class BookItem {
        @Element(name = "id")
        public String id;
        @Element(name = "title")
        public String title;
        @Element(name = "callno")
        public String callno;
        @Element(name = "category")
        public String category;
        @Element(name = "content")
        public String content;
        @Element(name = "isbn")
        public String isbn;
        @Element(name = "publisher")
        public String publisher;
        @Element(name = "author")
        public String author;
        @Element(name = "place")
        public String place;
        @Element(name = "date")
        public String date;
    }

}
