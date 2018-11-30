package model;

    public class User {

        private String mobile, email, name;

        public User(String mobile, String email, String name) {
            this.mobile = mobile;
            this.email = email;
            this.name = name;
        }

        public String getmobile() {
            return mobile;
        }

        public String getEmail() {
            return email;
        }

        public String getname() {
            return name;
        }
    }

