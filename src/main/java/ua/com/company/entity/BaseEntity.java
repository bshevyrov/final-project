package ua.com.company.entity;

import java.sql.Date;
import java.sql.Timestamp;

public abstract class BaseEntity {
    private int id;
    private Date createDate;
    private Date updateDate;

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
