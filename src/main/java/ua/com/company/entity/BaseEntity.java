package ua.com.company.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1234567L;
    private int id;
    private Timestamp createDate;
    private Timestamp updateDate;

    public BaseEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate;
    }
}
