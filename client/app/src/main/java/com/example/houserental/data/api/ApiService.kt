package com.example.houserental.data.api


import LoginRequest
import LoginResponse
//import com.example.houserental.data.model.FavoriteResponse
import RegisterRequest
import RegisterResponse
import com.example.houserental.data.model.FavoriteRequest
import com.example.houserental.data.model.FavoriteResponse
import com.example.houserental.data.model.HouseDetailResponse
import com.example.houserental.data.model.HouseListing
import com.example.houserental.data.model.ListingResponse
import com.example.houserental.data.model.MessageResponse
import com.example.houserental.data.model.PropertyResponse
import com.example.houserental.data.model.UpdatePropertyRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import com.example.houserental.data.model.UserResponse

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("api/housetype")
    suspend fun getHousesByType(
        @Query("type_id") typeId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<ListingResponse>
    @DELETE("api/house/{id}")
    suspend fun deleteHouse(@Path("id") id: Int): Response<Unit>

    @GET("api/users/getAllUsers")
    suspend fun getAllUsers(): Response<UserResponse>
    @DELETE("api/users/user/{id}")
    suspend fun deleteUser(@Path("id") id: Int): retrofit2.Response<Unit>

    @GET("api/housetype")
    suspend fun getHousesByType(@Query("type_id") typeId: Int): ListingResponse
    @POST("api/users/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>
   
    @POST("api/users/register")
    suspend fun registerUser(
        @Body user: RegisterRequest
    ): RegisterResponse

    @GET("house/{id}")
    suspend fun getHouseDetail(@Path("id") id: Int): Response<HouseDetailResponse>
    @POST("api/addfavourite")
    suspend fun addToFavorite(@Body request: FavoriteRequest): Response<MessageResponse>
    @GET("api/favorite/{user_id}")
    suspend fun getFavorites(@Path("user_id") userId: Int): FavoriteResponse
    // Define the function to remove a favorite
    @DELETE("api/removefavourite/{user_id}/{listing_id}")
    suspend fun removeFromFavorite(
        @Path("user_id") userId: Int,
        @Path("listing_id") listingId: Int
    ): Response<MessageResponse>


    @GET("api/house")
    suspend fun getAllHouse():Response<ListingResponse>


    @Multipart
    @POST("api/addHouse")
    suspend fun addHouse(
        @Part photos: List<MultipartBody.Part>,
        @Part("title") title: RequestBody,
        @Part("price") price: RequestBody,
        @Part("description") description: RequestBody,
        @Part("bathroomCount") bathroomCount: RequestBody,
        @Part("bedroomCount") bedroomCount: RequestBody,
        @Part("country") country: RequestBody,
        @Part("area") area: RequestBody,
        @Part("facilities") facilities: RequestBody,
        @Part("category") category: RequestBody,     // ← ADD
        @Part("type") type: RequestBody,             // ← ADD
        @Part("streetAddress") streetAddress: RequestBody,         // ← ADD
        @Part("city") city: RequestBody,             // ← ADD
        @Part("province") province: RequestBody      // ← ADD
    ): Response<Unit>

    @GET("api/house/{id}")
    suspend fun getPropertyById(@Path("id") id: Int): Response<HouseDetailResponse>


    @PATCH("api/listings/{id}")
    suspend fun updateProperty(
        @Path("id") id: Int,
        @Body updatedProperty: UpdatePropertyRequest
    ): Response<HouseListing>
    @GET("/favorite/{user_id}")
    suspend fun getFavoriteHouses(@Path("user_id") userId: String): FavoriteResponse

    @GET("listings")
    suspend fun getHousesByLocation(
        @Query("city") city: String,
        @Query("type") typeId: Int?,
        @Query("minPrice") minPrice: Int?,
        @Query("maxPrice") maxPrice: Int?
    ): Response<ListingResponse>








}
