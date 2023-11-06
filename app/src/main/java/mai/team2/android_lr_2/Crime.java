package mai.team2.android_lr_2;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId; // id преступления
    private String mTitle; // название преступления
    private Date mDate;  //
    private boolean mSolved; // состояние преступления раскрыто/не раскрыто
    private boolean mRequiresPolice;

    public Crime() {
        /*mId = UUID.randomUUID();   //генерация id
        mDate = new Date();        // определение*/
        this(UUID.randomUUID());
        /*mRequiresPolice = false;*/
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
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
        //return DateFormat.getDateInstance().format(mDate);
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }
    public boolean isSolved() {
        return mSolved;
    }
    public void setSolved(boolean solved) {
        mSolved = solved;
    }
    public boolean isRequiresPolice() {
        return mRequiresPolice;
    }
    public void setRequiresPolice(boolean RequiresPolice) {
        mRequiresPolice = RequiresPolice;
    }
}
