package com.foretree.shlibraryapp.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * <p>
 * <soapenv:Body>
 * <ns1:Mobile_iPacResponse xmlns:ns1="http://www.example.org/Shlib_Mobile_WS_Solr/">
 * <PageSize>10</PageSize>
 * <MaxRows>162</MaxRows>
 * <CurrentRow>0</CurrentRow>
 * <QueryString>机器学习</QueryString>
 * <BookItem>
 * <id>1665370</id>
 * <title>机器学习,Machine learning</title>
 * <callno>TP181/9942</callno>
 * <category>TP181</category>
 * <content>本书展示了机器学习中核心的算法和理论，并阐明了算法的运行过程。本书综合了许多的研究成果，例如统计学、人工智能、哲学、信息论、生物学、认知科学、计算复杂性和控制论等。</content>
 * <isbn>7111109937</isbn>
 * <publisher>机械工业出版社</publisher>
 * <author>(美)Tom M. Mitchell著,曾华军, 张银奎等译</author>
 * <place>北京</place>
 * <date>2003</date>
 * </BookItem>
 * </p>
 * Created by silen on 21/01/2018.
 */
@Root(name = "soapenv:Envelope")
public class SearchResponse extends BaseXmlResponse<SearchResponse.ResponseBody> {

    @Root(name = "Body")
    public static class ResponseBody {
        @Element(name = "Mobile_iPacResponse")
        public ResponseModel model;
    }

    @Root(name = "Mobile_iPacResponse")
    public static class ResponseModel {
        @Attribute(name = "xmlns:ns1", empty = "http://www.example.org/Shlib_Mobile_WS_Solr/",required = false)
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
