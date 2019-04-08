package cn.aethli.gttss.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Batch {
    String batchId;
    String batchName;
    Timestamp batchDate;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Timestamp getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(Timestamp batchDate) {
        this.batchDate = batchDate;
    }
}
