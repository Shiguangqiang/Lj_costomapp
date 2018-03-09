package com.defence.costomapp.bean;

import java.io.Serializable;

/**
 * Created by Sgq
 * on 2018/3/5.
 */

public class MachineEntity implements Serializable {

    private String id;
    private String machinename;
    private String machinenumber;
    private String machinemodel;
    private String ipaddress;
    private String province;
    private String city;
    private String area;
    private String address;
    private String detailedinstalladdress;
    private int usestate;
    private double longitude;
    private double latitude;
    private int returnratio;
    private String returnuser;
    private int returnUGType;
    private String returntime;
    private int isdistribution;
    private String oldmachinenumbers;
    private String synchronoustime;
    private String returnusername;
    private int machinestate;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMachinename(String machinename) {
        this.machinename = machinename;
    }

    public String getMachinename() {
        return machinename;
    }

    public void setMachinenumber(String machinenumber) {
        this.machinenumber = machinenumber;
    }

    public String getMachinenumber() {
        return machinenumber;
    }

    public void setMachinemodel(String machinemodel) {
        this.machinemodel = machinemodel;
    }

    public String getMachinemodel() {
        return machinemodel;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDetailedinstalladdress(String detailedinstalladdress) {
        this.detailedinstalladdress = detailedinstalladdress;
    }

    public String getDetailedinstalladdress() {
        return detailedinstalladdress;
    }

    public void setUsestate(int usestate) {
        this.usestate = usestate;
    }

    public int getUsestate() {
        return usestate;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setReturnratio(int returnratio) {
        this.returnratio = returnratio;
    }

    public int getReturnratio() {
        return returnratio;
    }

    public void setReturnuser(String returnuser) {
        this.returnuser = returnuser;
    }

    public String getReturnuser() {
        return returnuser;
    }

    public void setReturnUGType(int returnUGType) {
        this.returnUGType = returnUGType;
    }

    public int getReturnUGType() {
        return returnUGType;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setIsdistribution(int isdistribution) {
        this.isdistribution = isdistribution;
    }

    public int getIsdistribution() {
        return isdistribution;
    }

    public void setOldmachinenumbers(String oldmachinenumbers) {
        this.oldmachinenumbers = oldmachinenumbers;
    }

    public String getOldmachinenumbers() {
        return oldmachinenumbers;
    }

    public void setSynchronoustime(String synchronoustime) {
        this.synchronoustime = synchronoustime;
    }

    public String getSynchronoustime() {
        return synchronoustime;
    }

    public void setReturnusername(String returnusername) {
        this.returnusername = returnusername;
    }

    public String getReturnusername() {
        return returnusername;
    }

    public void setMachinestate(int machinestate) {
        this.machinestate = machinestate;
    }

    public int getMachinestate() {
        return machinestate;
    }

}
