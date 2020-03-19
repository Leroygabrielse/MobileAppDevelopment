package com.example.studentportal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


//Object Portal which is parcelable. (accesable between activities)

@Parcelize
data class Portal (
    var portalName : String,
    var PortalUrl: String
) : Parcelable
