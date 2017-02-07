package zxy.common.excel;

import java.util.Date;

public class User {
    @ExcelField(title = "ID", column = 0)
    private int id;
    @ExcelField(title = "姓名", column = 1)
    private String name;
    @ExcelField(title = "生日", column = 2)
    private Date birtyday;
    @ExcelField(title = "身高", column = 3)
    private Double height;  //  身高，单位米
    @ExcelField(title = "手机", column = 4)
    private String mobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirtyday() {
        return birtyday;
    }

    public void setBirtyday(Date birtyday) {
        this.birtyday = birtyday;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birtyday=" + birtyday +
                ", height=" + height +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
