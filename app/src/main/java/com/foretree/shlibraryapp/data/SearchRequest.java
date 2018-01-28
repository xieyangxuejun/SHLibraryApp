package com.foretree.shlibraryapp.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/**
 * 搜索请求xml
 * Created by silen on 21/01/2018.
 */
@Root(name = "soapenv:Envelope")
public class SearchRequest extends BaseXmlRequest<SearchRequest.RequestBody> {

    @Root(name = "soapenv:Body")
    public static class RequestBody {
        @Element(name = "shl:Mobile_iPac")
        private RequestModel model;

        public RequestBody(RequestModel model) {
            this.model = model;
        }

        public RequestModel getModel() {
            return model;
        }
    }

    @Root(name = "shl:Mobile_iPac")
    @Order(elements = {"Type", "KeyWord", "PageSize", "StartRow"})
    public static class RequestModel {
        @Element(name = "Type")
        private String type;
        @Element(name = "KeyWord")
        private String keyWord;
        @Element(name = "PageSize")
        private int pageSize;
        @Element(name = "StartRow")
        private int startRow;

        public RequestModel(@Element(name = "Type") String type,
                            @Element(name = "KeyWord") String keyWord,
                            @Element(name = "PageSize") int pageSize,
                            @Element(name = "StartRow") int startRow) {
            this.type = type;
            this.keyWord = keyWord;
            this.pageSize = pageSize;
            this.startRow = startRow;
        }

        public String getType() {
            return type;
        }

        public String getKeyWord() {
            return keyWord;
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getStartRow() {
            return startRow;
        }
    }
}
