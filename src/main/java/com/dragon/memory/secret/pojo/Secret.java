package com.dragon.memory.secret.pojo;

import java.util.Date;

public class Secret {
    private int id;
    private String secretname;
    private String secretpicture;
    private String remark;
    private int star;
    private Date createdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecretname() {
        return secretname;
    }

    public void setSecretname(String secretname) {
        this.secretname = secretname;
    }

    public String getSecretpicture() {
        return secretpicture;
    }

    public void setSecretpicture(String secretpicture) {
        this.secretpicture = secretpicture;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }


}
