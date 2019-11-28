package com.example.testaidl


import android.os.Parcel
import android.os.Parcelable


class MainObject(var path: String? = "") : Parcelable {

    companion object CREATOR : Parcelable.Creator<MainObject> {
        override fun createFromParcel(parcel: Parcel): MainObject {
            return MainObject(parcel)
        }

        override fun newArray(size: Int): Array<MainObject> {
            return Array(size) { MainObject() }
        }
    }

    private constructor(inParcel: Parcel) : this() {
        readFromParcel(inParcel)
    }

    override fun writeToParcel(outParcel: Parcel, flags: Int) {
        outParcel.writeString(path)
    }

    private fun readFromParcel(inParcel: Parcel) {
        path = inParcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }
}
