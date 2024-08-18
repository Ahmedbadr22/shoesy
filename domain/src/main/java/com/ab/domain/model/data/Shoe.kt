package com.ab.domain.model.data

data class Shoe(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val quantity: Int,
    val sizes: List<Int>,
    val colors: List<Color>,
    val image: String,
    val brand: Brand,
    val isFavorite: Boolean,
    val reviews: List<Review>
)


fun Shoe.getAverageRate() : Double {
    val sum = reviews.sumOf { review -> review.rating.toDouble() }
    val count = reviews.size

    return if (count == 0) 0.0
    else (sum / count)
}
fun Shoe.getReviewCount() : Int = reviews.size