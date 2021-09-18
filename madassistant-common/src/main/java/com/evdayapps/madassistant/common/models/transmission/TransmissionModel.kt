package com.evdayapps.madassistant.common.models.transmission

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

/**
 * Model for data that is transmitted from the client to the repository
 */
class TransmissionModel(
    val transmissionId: String?,
    var timestamp: Long,
    val numTotalSegments: Int,
    val currentSegmentIndex: Int,
    val type: Int,
    val encrypted: Boolean,
    val payload: ByteArray
) : Parcelable {

    constructor(parcel: Parcel) : this(
        transmissionId = parcel.readString(),
        timestamp = parcel.readLong(),
        numTotalSegments = parcel.readInt(),
        currentSegmentIndex = parcel.readInt(),
        type = parcel.readInt(),
        encrypted = parcel.readByte() != 0.toByte(),
        payload = parcel.createByteArray() ?: ByteArray(0)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(transmissionId)
        parcel.writeLong(timestamp)
        parcel.writeInt(numTotalSegments)
        parcel.writeInt(currentSegmentIndex)
        parcel.writeInt(type)
        parcel.writeByte(if (encrypted) 1 else 0)
        parcel.writeByteArray(payload)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<TransmissionModel> {
        override fun createFromParcel(parcel: Parcel): TransmissionModel {
            return TransmissionModel(parcel)
        }

        override fun newArray(size: Int): Array<TransmissionModel?> {
            return arrayOfNulls(size)
        }
    }


}