package com.server.database.enums;

public enum User {
    Table {
        public String toString() {
            return "users";
        }
    },

    Id {
        public String toString() {
            return "user_id";
        }
    },

    Nickname {
        public String toString() {
            return "nickname";
        }
    },

    Role {
        public String toString() {
            return "role";
        }
    },

    Login {
        public String toString() {
            return "login";
        }
    },

    Password {
        public String toString() {
            return "password";
        }
    }
}

