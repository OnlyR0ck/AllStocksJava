package com.client.enumerations;

public enum SceneType {
    MainMenuView {
        @Override
        public String toString() {
            return "main-menu-view.fxml";
        }
    },
    SignInView {
        @Override
        public String toString() {
            return "sign-in-view.fxml";
        }
    },
    SignUpView {
        @Override
        public String toString() {
            return "sign-up-view.fxml";
        }
    },
    UserView {
        @Override
        public String toString() {
            return "user-view.fxml";
        }
    },
    AdminView {
        @Override
        public String toString() {
            return "admin-view.fxml";
        }
    }
}
