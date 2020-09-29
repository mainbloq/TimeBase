package deltix.qsrv.hf.tickdb.http.download;

import deltix.timebase.messages.IdentityKey;
import deltix.qsrv.hf.tickdb.http.IdentityKeyListAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "changeEntities")
public class EntitiesRequest extends CursorRequest {

    @XmlElement()
    public ChangeAction mode;

    @XmlElement()
    @XmlJavaTypeAdapter(IdentityKeyListAdapter.class)
    public IdentityKey[] entities;
}
