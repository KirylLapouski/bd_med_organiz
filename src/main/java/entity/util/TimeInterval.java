package entity.util;

import java.util.Date;

/**
 * Created by lapko on 17.11.2017.
 */
public class TimeInterval {
    private Date since;
    private Date to;

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
