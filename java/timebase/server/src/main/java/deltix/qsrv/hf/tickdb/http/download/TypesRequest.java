package deltix.qsrv.hf.tickdb.http.download;

import deltix.qsrv.hf.tickdb.http.download.ChangeAction;
import deltix.qsrv.hf.tickdb.http.download.CursorRequest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "changeTypes")
public class TypesRequest extends CursorRequest {
    @XmlElement()
    public ChangeAction         mode;

    @XmlElementWrapper
    @XmlElement(name = "item")
    public String[]             types;
}
