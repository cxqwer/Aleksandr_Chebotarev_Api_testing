package beans.board;

import java.util.List;

@SuppressWarnings("unused")
public class Prefs {

    private String background;
    private String backgroundBottomColor;
    private String backgroundBrightness;
    private String backgroundImage;
    private List<BackgroundImageScaled> backgroundImageScaled;
    private Boolean backgroundTile;
    private String backgroundTopColor;
    private Boolean calendarFeedEnabled;
    private Boolean canBeEnterprise;
    private Boolean canBeOrg;
    private Boolean canBePrivate;
    private Boolean canBePublic;
    private Boolean canInvite;
    private String cardAging;
    private Boolean cardCovers;
    private String comments;
    private String invitations;
    private String permissionLevel;
    private Boolean selfJoin;
    private String voting;

    public Boolean getCardCovers() {
        return cardCovers;
    }

    public String getComments() {
        return comments;
    }

    public String getInvitations() {
        return invitations;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public Boolean getSelfJoin() {
        return selfJoin;
    }

    public String getVoting() {
        return voting;
    }

    public String getBackground() {
        return background;
    }

    public String getBackgroundBottomColor() {
        return backgroundBottomColor;
    }

    public String getBackgroundBrightness() {
        return backgroundBrightness;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public List<BackgroundImageScaled> getBackgroundImageScaled() {
        return backgroundImageScaled;
    }

    public Boolean getBackgroundTile() {
        return backgroundTile;
    }

    public String getBackgroundTopColor() {
        return backgroundTopColor;
    }

    public Boolean getCalendarFeedEnabled() {
        return calendarFeedEnabled;
    }

    public Boolean getCanBeEnterprise() {
        return canBeEnterprise;
    }

    public Boolean getCanBeOrg() {
        return canBeOrg;
    }

    public Boolean getCanBePrivate() {
        return canBePrivate;
    }

    public Boolean getCanBePublic() {
        return canBePublic;
    }

    public Boolean getCanInvite() {
        return canInvite;
    }

    public String getCardAging() {
        return cardAging;
    }
}
