package com.example.lineage5;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "relations")
public class RelationUser implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uId;

    @ColumnInfo(name="u_rel")
    public String person1;
    public String person2;

    public String relation;


    public RelationUser() {

    }

    protected RelationUser(Parcel in) {
        uId = in.readInt();
        person1 = in.readString();
        person2 = in.readString();
        relation = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uId);
        dest.writeString(person1);
        dest.writeString(person2);

        dest.writeString(relation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RelationUser> CREATOR = new Creator<RelationUser>() {
        @Override
        public RelationUser createFromParcel(Parcel in) {
            return new RelationUser(in);
        }

        @Override
        public RelationUser[] newArray(int size) {
            return new RelationUser[size];
        }
    };
}

