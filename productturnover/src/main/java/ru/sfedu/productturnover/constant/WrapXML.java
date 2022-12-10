package ru.sfedu.productturnover.constant;

import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "List")
public class WrapXML<T> {
    @ElementList(inline = true, required = false)
    public List<T> list;

    public WrapXML() {
    }

    public WrapXML(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
