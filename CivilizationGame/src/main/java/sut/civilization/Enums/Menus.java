package sut.civilization.Enums;

public enum Menus {
    LOGIN_MENU,MAIN_MENU,GAME_MENU,SCORE_BOARD,PROFILE_MENU,CHAT_MENU;

    @Override
    public String toString() {
        return this.name();
    }
}
