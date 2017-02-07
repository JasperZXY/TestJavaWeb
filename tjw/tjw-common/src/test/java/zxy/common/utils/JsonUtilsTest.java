package zxy.common.utils;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Jasper.Zhong
 */
public class JsonUtilsTest {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setBirtyday(new Date());
        userInfo.setId(10);
        userInfo.setName("Jasper");
        System.out.println(JsonUtils.toString(Arrays.asList(userInfo)));
    }

    static class UserInfo {
        private int id;
        private String name;
        private Date birtyday;
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
    }
}
