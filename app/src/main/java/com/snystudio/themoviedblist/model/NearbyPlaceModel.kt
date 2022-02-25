package com.snystudio.themoviedblist.model

import com.google.gson.annotations.SerializedName

data class NearbyPlaceModel(var results:List<Results>)

data class Location (

    @SerializedName("lat" ) var lat : Double? = null,
    @SerializedName("lng" ) var lng : Double? = null

)
data class Northeast (

    @SerializedName("lat" ) var lat : Double? = null,
    @SerializedName("lng" ) var lng : Double? = null

)

data class Southwest (

    @SerializedName("lat" ) var lat : Double? = null,
    @SerializedName("lng" ) var lng : Double? = null

)
data class Viewport (

    @SerializedName("northeast" ) var northeast : Northeast? = Northeast(),
    @SerializedName("southwest" ) var southwest : Southwest? = Southwest()

)
data class Geometry (

    @SerializedName("location" ) var location : Location? = Location(),
    @SerializedName("viewport" ) var viewport : Viewport? = Viewport()

)
data class Photos (

    @SerializedName("height"            ) var height           : Int?              = null,
    @SerializedName("html_attributions" ) var htmlAttributions : ArrayList<String> = arrayListOf(),
    @SerializedName("photo_reference"   ) var photoReference   : String?           = null,
    @SerializedName("width"             ) var width            : Int?              = null

)
data class Results (

    @SerializedName("geometry"              ) var geometry            : Geometry?         = Geometry(),
    @SerializedName("icon"                  ) var icon                : String?           = null,
    @SerializedName("icon_background_color" ) var iconBackgroundColor : String?           = null,
    @SerializedName("icon_mask_base_uri"    ) var iconMaskBaseUri     : String?           = null,
    @SerializedName("name"                  ) var name                : String?           = null,
    @SerializedName("photos"                ) var photos              : ArrayList<Photos> = arrayListOf(),
    @SerializedName("place_id"              ) var placeId             : String?           = null,
    @SerializedName("reference"             ) var reference           : String?           = null,
    @SerializedName("scope"                 ) var scope               : String?           = null,
    @SerializedName("types"                 ) var types               : ArrayList<String> = arrayListOf(),
    @SerializedName("vicinity"              ) var vicinity            : String?           = null

)