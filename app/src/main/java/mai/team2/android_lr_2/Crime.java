package mai.team2.android_lr_2;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId; // id преступления
    private String mTitle; // название преступления
    private Date mDate;  //
    private boolean mSolved; // состояние преступления раскрыто/не раскрыто

    private String mSuspect; // имя подозреваемого
    private boolean mSerious; //упр 8 для серьезных преступлений

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
        mSerious = false;
    }

    public UUID getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {

        mTitle = title;
    }
    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
//        mDate.setYear(date.getYear());
//        mDate.setDate(date.getDate());
//        mDate.setMonth(date.getMonth());
    }
    public void setTime(Date time) {
        mDate = time;
//        mDate.setHours(time.getHours());
//        mDate.setMinutes(time.getMinutes());
    }
    public boolean isSolved() {
        return mSolved;
    }
    public void setSolved(boolean solved) {

        mSolved = solved;
    }
    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }
    public boolean isSerious() {                                                                    //упр 8 для серьезных преступлений
        return mSerious;
    }
    public void setSerious(boolean Serious) {
        mSerious = Serious;
    }

    public String getSuspect() {
        return mSuspect;
    }
    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
