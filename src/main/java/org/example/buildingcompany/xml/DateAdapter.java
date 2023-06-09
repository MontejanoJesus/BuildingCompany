package org.example.buildingcompany.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateAdapter extends XmlAdapter<String, Date> {
    @Override
    public Date unmarshal(String s) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(s);
        return new Date(date.getTime());
    }

    @Override
    public String marshal(Date date) throws Exception {
        return null;
    }
}
