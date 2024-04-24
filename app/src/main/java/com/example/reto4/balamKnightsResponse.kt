package com.example.reto4

import com.google.gson.annotations.SerializedName

class balamKnightsResponse {
    @SerializedName("userID"   ) var userID   : String? = null
    @SerializedName("imageurl" ) var imageurl : String? = null
    @SerializedName("address"  ) var address  : String? = null
    @SerializedName("name"     ) var name     : String? = null
    @SerializedName("token"    ) var token    : String? = null
    @SerializedName("expires"  ) var expires  : String? = null
}