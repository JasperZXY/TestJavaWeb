package zxy.common.util;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Jasper.Zhong
 */
public class ObjectUtilsTest {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setBirtyday(new Date());
        userInfo.setId(10);
        userInfo.setName("Jasper");
        System.out.println(JsonUtils.toString(Arrays.asList(userInfo)));

        StudentInfo studentInfo = ObjectUtils.convert(userInfo, StudentInfo.class);
        System.out.println(JsonUtils.toString(studentInfo));

        studentInfo.setStudentCode("007");
        UserInfo userInfo2 = ObjectUtils.convert(studentInfo, UserInfo.class);
        System.out.println(JsonUtils.toString(userInfo2));

        User user = ObjectUtils.convert(studentInfo, User.class);
        System.out.println(JsonUtils.toString(user));
    }

    static class User {
        private int uid;
        private String name;
        private Date birtyday;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

    static class StudentInfo extends UserInfo {
        private int id;
        private String name;
        private Date birtyday;
        private String studentCode;

        @Override
        public int getId() {
            return id;
        }

        @Override
        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public Date getBirtyday() {
            return birtyday;
        }

        @Override
        public void setBirtyday(Date birtyday) {
            this.birtyday = birtyday;
        }

        public String getStudentCode() {
            return studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }
    }
}
