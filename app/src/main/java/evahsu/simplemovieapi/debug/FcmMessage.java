package evahsu.simplemovieapi.debug;

/**
 * Created by EvaHsu on 2017/11/9.
 */

public class FcmMessage {
    public String msg;
    public String title;
    public String salesName;
    public String salesDept;
    public String salesMPhone;
    public String salesPhone;
    public String salesTitle;
    public String serviceName;
    public String serviceDept;
    public String serviceMPhone;
    public String servicePhone;
    public String serviceTitle;
    public String pushUuid;
    public String contactDataStr;
    public FcmMessage(String msg, String title) {
        this.msg = msg;
        this.title = title;
    }

    public void setContactDataStr(String contactDataStr) {
        this.contactDataStr = contactDataStr;
    }

    public FcmMessage(String msg, String salesName, String salesDept, String salesMPhone, String salesPhone, String salesTitle,
                      String serviceName, String serviceDept, String serviceMPhone, String servicePhone, String serviceTitle, String pushUuid) {
        this.msg = msg;
        this.salesName = salesName;
        this.salesDept = salesDept;
        this.salesMPhone = salesMPhone;
        this.salesPhone = salesPhone;
        this.salesTitle = salesTitle;
        this.serviceName = serviceName;
        this.serviceDept = serviceDept;
        this.serviceMPhone = serviceMPhone;
        this.servicePhone = servicePhone;
        this.serviceTitle = serviceTitle;
        this.pushUuid = pushUuid;
    }
}
