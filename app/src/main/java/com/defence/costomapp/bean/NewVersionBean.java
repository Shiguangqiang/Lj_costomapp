package com.defence.costomapp.bean;

/**
 * Created by author Sgq
 * on 2018/5/23.
 */

public class NewVersionBean {
    /**
     * id : 177
     * which : 4
     * version : 1.23
     * versionInt : 23
     * newVersionTime : 2018-05-23
     * forceUpdate : 0
     * description : 客服Android版本23--------.
     * href : http://www.bj-defence.com/swz.d.html?from=101&_t=1805231657
     */

    private int id;
    private int which;
    private String version;
    private int versionInt;
    private String newVersionTime;
    private int forceUpdate;
    private String description;
    private String href;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWhich() {
        return which;
    }

    public void setWhich(int which) {
        this.which = which;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getVersionInt() {
        return versionInt;
    }

    public void setVersionInt(int versionInt) {
        this.versionInt = versionInt;
    }

    public String getNewVersionTime() {
        return newVersionTime;
    }

    public void setNewVersionTime(String newVersionTime) {
        this.newVersionTime = newVersionTime;
    }

    public int getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(int forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
