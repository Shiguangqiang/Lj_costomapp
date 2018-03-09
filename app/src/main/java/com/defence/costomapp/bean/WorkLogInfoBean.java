package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/8.
 */

public class WorkLogInfoBean {


    /**
     * message : 操作成功!
     * sign : 1
     * result : {"work_user":{"id":7,"username":"lxy","password":"","realname":"李新原","timeline":"","authorizationKey":"","registrationid":"","whichPhone":"","idcard":"","telphone":"13801202242","homeaddress":"","presentaddress":"","bathday":"","onthejob":"","userphoto":"","ismanage":0,"parentid":"","machineList":"","m_uList":""},"data_list":[{"machine":{"id":"70201802081408127329469001253257","machinename":"丰台富卓苑1号机器","address":"北京市北京市丰台区","detailedinstalladdress":"富卓苑小区1号西娱乐广场"},"work_log":[{"id":"70201803061702091042345201152055","itemNo":3,"shelvestype":1,"descVal":"3g（0元购）","commodityid":611,"machineNo":"lj-010-04-001-001","goods_map":{"id":465,"showName":"薄荷-观音茶"},"shangPinID":465,"machineID":"70201802081408127329469001253257","shelvesnum":15},{"id":"70201803061703153842587301155167","itemNo":14,"shelvestype":1,"descVal":"（0元购）","commodityid":541,"machineNo":"lj-010-04-001-001","goods_map":{"id":415,"showName":"维达手帕纸"},"shangPinID":415,"machineID":"70201802081408127329469001253257","shelvesnum":4},{"id":"70201803061703552704353101160224","itemNo":14,"shelvestype":1,"descVal":"（0元购）","commodityid":541,"machineNo":"lj-010-04-001-001","goods_map":{"id":415,"showName":"维达手帕纸"},"shangPinID":415,"machineID":"70201802081408127329469001253257","shelvesnum":4},{"id":"70201803061656456259227601125073","itemNo":23,"shelvestype":1,"descVal":"巧克力味","commodityid":561,"machineNo":"lj-010-04-001-001","goods_map":{"id":433,"showName":"3+2夹心卷"},"shangPinID":433,"machineID":"70201802081408127329469001253257","shelvesnum":7},{"id":"70201803061656556558307901113466","itemNo":54,"shelvestype":1,"descVal":"550ml","commodityid":507,"machineNo":"lj-010-04-001-001","goods_map":{"id":391,"showName":"冰露矿泉水"},"shangPinID":391,"machineID":"70201802081408127329469001253257","shelvesnum":2},{"id":"70201803061653077546046501143883","itemNo":57,"shelvestype":1,"descVal":"水蜜桃味450ml","commodityid":617,"machineNo":"lj-010-04-001-001","goods_map":{"id":469,"showName":"二环内汽水"},"shangPinID":469,"machineID":"70201802081408127329469001253257","shelvesnum":1},{"id":"70201803061655331868360301130287","itemNo":57,"shelvestype":1,"descVal":"水蜜桃味450ml","commodityid":617,"machineNo":"lj-010-04-001-001","goods_map":{"id":469,"showName":"二环内汽水"},"shangPinID":469,"machineID":"70201802081408127329469001253257","shelvesnum":5},{"id":"70201803061652515659760501116223","itemNo":58,"shelvestype":1,"descVal":"苹果味450ml","commodityid":615,"machineNo":"lj-010-04-001-001","goods_map":{"id":467,"showName":"二环内汽水"},"shangPinID":467,"machineID":"70201802081408127329469001253257","shelvesnum":5},{"id":"70201803061654130212274401173322","itemNo":59,"shelvestype":1,"descVal":"葱香味","commodityid":571,"machineNo":"lj-010-04-001-001","goods_map":{"id":441,"showName":"尝香棒火鸡面"},"shangPinID":441,"machineID":"70201802081408127329469001253257","shelvesnum":10}]}]}
     * timelineN : 2018-03-08 16:21:36
     */

    private String message;
    private int sign;
    private ResultBean result;
    private String timelineN;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getTimelineN() {
        return timelineN;
    }

    public void setTimelineN(String timelineN) {
        this.timelineN = timelineN;
    }

    public static class ResultBean {
        /**
         * work_user : {"id":7,"username":"lxy","password":"","realname":"李新原","timeline":"","authorizationKey":"","registrationid":"","whichPhone":"","idcard":"","telphone":"13801202242","homeaddress":"","presentaddress":"","bathday":"","onthejob":"","userphoto":"","ismanage":0,"parentid":"","machineList":"","m_uList":""}
         * data_list : [{"machine":{"id":"70201802081408127329469001253257","machinename":"丰台富卓苑1号机器","address":"北京市北京市丰台区","detailedinstalladdress":"富卓苑小区1号西娱乐广场"},"work_log":[{"id":"70201803061702091042345201152055","itemNo":3,"shelvestype":1,"descVal":"3g（0元购）","commodityid":611,"machineNo":"lj-010-04-001-001","goods_map":{"id":465,"showName":"薄荷-观音茶"},"shangPinID":465,"machineID":"70201802081408127329469001253257","shelvesnum":15},{"id":"70201803061703153842587301155167","itemNo":14,"shelvestype":1,"descVal":"（0元购）","commodityid":541,"machineNo":"lj-010-04-001-001","goods_map":{"id":415,"showName":"维达手帕纸"},"shangPinID":415,"machineID":"70201802081408127329469001253257","shelvesnum":4},{"id":"70201803061703552704353101160224","itemNo":14,"shelvestype":1,"descVal":"（0元购）","commodityid":541,"machineNo":"lj-010-04-001-001","goods_map":{"id":415,"showName":"维达手帕纸"},"shangPinID":415,"machineID":"70201802081408127329469001253257","shelvesnum":4},{"id":"70201803061656456259227601125073","itemNo":23,"shelvestype":1,"descVal":"巧克力味","commodityid":561,"machineNo":"lj-010-04-001-001","goods_map":{"id":433,"showName":"3+2夹心卷"},"shangPinID":433,"machineID":"70201802081408127329469001253257","shelvesnum":7},{"id":"70201803061656556558307901113466","itemNo":54,"shelvestype":1,"descVal":"550ml","commodityid":507,"machineNo":"lj-010-04-001-001","goods_map":{"id":391,"showName":"冰露矿泉水"},"shangPinID":391,"machineID":"70201802081408127329469001253257","shelvesnum":2},{"id":"70201803061653077546046501143883","itemNo":57,"shelvestype":1,"descVal":"水蜜桃味450ml","commodityid":617,"machineNo":"lj-010-04-001-001","goods_map":{"id":469,"showName":"二环内汽水"},"shangPinID":469,"machineID":"70201802081408127329469001253257","shelvesnum":1},{"id":"70201803061655331868360301130287","itemNo":57,"shelvestype":1,"descVal":"水蜜桃味450ml","commodityid":617,"machineNo":"lj-010-04-001-001","goods_map":{"id":469,"showName":"二环内汽水"},"shangPinID":469,"machineID":"70201802081408127329469001253257","shelvesnum":5},{"id":"70201803061652515659760501116223","itemNo":58,"shelvestype":1,"descVal":"苹果味450ml","commodityid":615,"machineNo":"lj-010-04-001-001","goods_map":{"id":467,"showName":"二环内汽水"},"shangPinID":467,"machineID":"70201802081408127329469001253257","shelvesnum":5},{"id":"70201803061654130212274401173322","itemNo":59,"shelvestype":1,"descVal":"葱香味","commodityid":571,"machineNo":"lj-010-04-001-001","goods_map":{"id":441,"showName":"尝香棒火鸡面"},"shangPinID":441,"machineID":"70201802081408127329469001253257","shelvesnum":10}]}]
         */

        private WorkUserBean work_user;
        private List<DataListBean> data_list;

        public WorkUserBean getWork_user() {
            return work_user;
        }

        public void setWork_user(WorkUserBean work_user) {
            this.work_user = work_user;
        }

        public List<DataListBean> getData_list() {
            return data_list;
        }

        public void setData_list(List<DataListBean> data_list) {
            this.data_list = data_list;
        }

        public static class WorkUserBean {
            /**
             * id : 7
             * username : lxy
             * password :
             * realname : 李新原
             * timeline :
             * authorizationKey :
             * registrationid :
             * whichPhone :
             * idcard :
             * telphone : 13801202242
             * homeaddress :
             * presentaddress :
             * bathday :
             * onthejob :
             * userphoto :
             * ismanage : 0
             * parentid :
             * machineList :
             * m_uList :
             */

            private int id;
            private String username;
            private String password;
            private String realname;
            private String timeline;
            private String authorizationKey;
            private String registrationid;
            private String whichPhone;
            private String idcard;
            private String telphone;
            private String homeaddress;
            private String presentaddress;
            private String bathday;
            private String onthejob;
            private String userphoto;
            private int ismanage;
            private String parentid;
            private String machineList;
            private String m_uList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getTimeline() {
                return timeline;
            }

            public void setTimeline(String timeline) {
                this.timeline = timeline;
            }

            public String getAuthorizationKey() {
                return authorizationKey;
            }

            public void setAuthorizationKey(String authorizationKey) {
                this.authorizationKey = authorizationKey;
            }

            public String getRegistrationid() {
                return registrationid;
            }

            public void setRegistrationid(String registrationid) {
                this.registrationid = registrationid;
            }

            public String getWhichPhone() {
                return whichPhone;
            }

            public void setWhichPhone(String whichPhone) {
                this.whichPhone = whichPhone;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getTelphone() {
                return telphone;
            }

            public void setTelphone(String telphone) {
                this.telphone = telphone;
            }

            public String getHomeaddress() {
                return homeaddress;
            }

            public void setHomeaddress(String homeaddress) {
                this.homeaddress = homeaddress;
            }

            public String getPresentaddress() {
                return presentaddress;
            }

            public void setPresentaddress(String presentaddress) {
                this.presentaddress = presentaddress;
            }

            public String getBathday() {
                return bathday;
            }

            public void setBathday(String bathday) {
                this.bathday = bathday;
            }

            public String getOnthejob() {
                return onthejob;
            }

            public void setOnthejob(String onthejob) {
                this.onthejob = onthejob;
            }

            public String getUserphoto() {
                return userphoto;
            }

            public void setUserphoto(String userphoto) {
                this.userphoto = userphoto;
            }

            public int getIsmanage() {
                return ismanage;
            }

            public void setIsmanage(int ismanage) {
                this.ismanage = ismanage;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public String getMachineList() {
                return machineList;
            }

            public void setMachineList(String machineList) {
                this.machineList = machineList;
            }

            public String getM_uList() {
                return m_uList;
            }

            public void setM_uList(String m_uList) {
                this.m_uList = m_uList;
            }
        }

        public static class DataListBean {
            /**
             * machine : {"id":"70201802081408127329469001253257","machinename":"丰台富卓苑1号机器","address":"北京市北京市丰台区","detailedinstalladdress":"富卓苑小区1号西娱乐广场"}
             * work_log : [{"id":"70201803061702091042345201152055","itemNo":3,"shelvestype":1,"descVal":"3g（0元购）","commodityid":611,"machineNo":"lj-010-04-001-001","goods_map":{"id":465,"showName":"薄荷-观音茶"},"shangPinID":465,"machineID":"70201802081408127329469001253257","shelvesnum":15},{"id":"70201803061703153842587301155167","itemNo":14,"shelvestype":1,"descVal":"（0元购）","commodityid":541,"machineNo":"lj-010-04-001-001","goods_map":{"id":415,"showName":"维达手帕纸"},"shangPinID":415,"machineID":"70201802081408127329469001253257","shelvesnum":4},{"id":"70201803061703552704353101160224","itemNo":14,"shelvestype":1,"descVal":"（0元购）","commodityid":541,"machineNo":"lj-010-04-001-001","goods_map":{"id":415,"showName":"维达手帕纸"},"shangPinID":415,"machineID":"70201802081408127329469001253257","shelvesnum":4},{"id":"70201803061656456259227601125073","itemNo":23,"shelvestype":1,"descVal":"巧克力味","commodityid":561,"machineNo":"lj-010-04-001-001","goods_map":{"id":433,"showName":"3+2夹心卷"},"shangPinID":433,"machineID":"70201802081408127329469001253257","shelvesnum":7},{"id":"70201803061656556558307901113466","itemNo":54,"shelvestype":1,"descVal":"550ml","commodityid":507,"machineNo":"lj-010-04-001-001","goods_map":{"id":391,"showName":"冰露矿泉水"},"shangPinID":391,"machineID":"70201802081408127329469001253257","shelvesnum":2},{"id":"70201803061653077546046501143883","itemNo":57,"shelvestype":1,"descVal":"水蜜桃味450ml","commodityid":617,"machineNo":"lj-010-04-001-001","goods_map":{"id":469,"showName":"二环内汽水"},"shangPinID":469,"machineID":"70201802081408127329469001253257","shelvesnum":1},{"id":"70201803061655331868360301130287","itemNo":57,"shelvestype":1,"descVal":"水蜜桃味450ml","commodityid":617,"machineNo":"lj-010-04-001-001","goods_map":{"id":469,"showName":"二环内汽水"},"shangPinID":469,"machineID":"70201802081408127329469001253257","shelvesnum":5},{"id":"70201803061652515659760501116223","itemNo":58,"shelvestype":1,"descVal":"苹果味450ml","commodityid":615,"machineNo":"lj-010-04-001-001","goods_map":{"id":467,"showName":"二环内汽水"},"shangPinID":467,"machineID":"70201802081408127329469001253257","shelvesnum":5},{"id":"70201803061654130212274401173322","itemNo":59,"shelvestype":1,"descVal":"葱香味","commodityid":571,"machineNo":"lj-010-04-001-001","goods_map":{"id":441,"showName":"尝香棒火鸡面"},"shangPinID":441,"machineID":"70201802081408127329469001253257","shelvesnum":10}]
             */

            private MachineBean machine;
            private List<WorkLogBean> work_log;

            public MachineBean getMachine() {
                return machine;
            }

            public void setMachine(MachineBean machine) {
                this.machine = machine;
            }

            public List<WorkLogBean> getWork_log() {
                return work_log;
            }

            public void setWork_log(List<WorkLogBean> work_log) {
                this.work_log = work_log;
            }

            public static class MachineBean {
                /**
                 * id : 70201802081408127329469001253257
                 * machinename : 丰台富卓苑1号机器
                 * address : 北京市北京市丰台区
                 * detailedinstalladdress : 富卓苑小区1号西娱乐广场
                 */

                private String id;
                private String machinename;
                private String address;
                private String detailedinstalladdress;


                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMachinename() {
                    return machinename;
                }

                public void setMachinename(String machinename) {
                    this.machinename = machinename;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getDetailedinstalladdress() {
                    return detailedinstalladdress;
                }

                public void setDetailedinstalladdress(String detailedinstalladdress) {
                    this.detailedinstalladdress = detailedinstalladdress;
                }
            }

            public static class WorkLogBean {
                /**
                 * id : 70201803061702091042345201152055
                 * itemNo : 3
                 * shelvestype : 1
                 * descVal : 3g（0元购）
                 * commodityid : 611
                 * machineNo : lj-010-04-001-001
                 * goods_map : {"id":465,"showName":"薄荷-观音茶"}
                 * shangPinID : 465
                 * machineID : 70201802081408127329469001253257
                 * shelvesnum : 15
                 */

                private String id;
                private int itemNo;
                private int shelvestype;
                private String descVal;
                private int commodityid;
                private String machineNo;
                private GoodsMapBean goods_map;
                private int shangPinID;
                private String machineID;
                private int shelvesnum;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getItemNo() {
                    return itemNo;
                }

                public void setItemNo(int itemNo) {
                    this.itemNo = itemNo;
                }

                public int getShelvestype() {
                    return shelvestype;
                }

                public void setShelvestype(int shelvestype) {
                    this.shelvestype = shelvestype;
                }

                public String getDescVal() {
                    return descVal;
                }

                public void setDescVal(String descVal) {
                    this.descVal = descVal;
                }

                public int getCommodityid() {
                    return commodityid;
                }

                public void setCommodityid(int commodityid) {
                    this.commodityid = commodityid;
                }

                public String getMachineNo() {
                    return machineNo;
                }

                public void setMachineNo(String machineNo) {
                    this.machineNo = machineNo;
                }

                public GoodsMapBean getGoods_map() {
                    return goods_map;
                }

                public void setGoods_map(GoodsMapBean goods_map) {
                    this.goods_map = goods_map;
                }

                public int getShangPinID() {
                    return shangPinID;
                }

                public void setShangPinID(int shangPinID) {
                    this.shangPinID = shangPinID;
                }

                public String getMachineID() {
                    return machineID;
                }

                public void setMachineID(String machineID) {
                    this.machineID = machineID;
                }

                public int getShelvesnum() {
                    return shelvesnum;
                }

                public void setShelvesnum(int shelvesnum) {
                    this.shelvesnum = shelvesnum;
                }

                public static class GoodsMapBean {
                    /**
                     * id : 465
                     * showName : 薄荷-观音茶
                     */

                    private int id;
                    private String showName;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getShowName() {
                        return showName;
                    }

                    public void setShowName(String showName) {
                        this.showName = showName;
                    }
                }
            }
        }
    }
}
