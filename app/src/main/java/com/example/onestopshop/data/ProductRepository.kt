package com.example.onestopshop.data

object ProductRepository {
    val products: List<Product> = listOf(
        Product(
            id = 1, name = "Floral Midi Dress",
            description = "Flowing V-neck dress with botanical print in lightweight crepe. Effortlessly feminine.",
            price = 89.99,
            imageUrl = "https://images.unsplash.com/photo-1515372039744-b8f02a3ae446?w=500&q=80",
            category = "Dresses", gender = "Women", isNew = true, badge = "NEW"
        ),
        Product(
            id = 2, name = "Structured Linen Blazer",
            description = "Relaxed-fit blazer in premium linen blend with notch lapels and functional pockets.",
            price = 149.99,
            imageUrl = "https://images.unsplash.com/photo-1591047139829-d91aecb6caea?w=500&q=80",
            category = "Jackets", gender = "Women", badge = "BESTSELLER"
        ),
        Product(
            id = 3, name = "High-Rise Straight Jeans",
            description = "Classic straight-leg denim with high waist. A timeless wardrobe essential.",
            price = 79.99,
            imageUrl = "https://images.unsplash.com/photo-1541099649105-f69ad21f3246?w=500&q=80",
            category = "Bottoms", gender = "Women"
        ),
        Product(
            id = 4, name = "Silk Satin Blouse",
            description = "Luxurious silk-satin blouse with draped cowl neckline. Understated elegance.",
            price = 75.99,
            imageUrl = "https://images.unsplash.com/photo-1490481651871-ab68de25d43d?w=500&q=80",
            category = "Tops", gender = "Women", isNew = true, badge = "NEW"
        ),
        Product(
            id = 5, name = "Wool Trench Coat",
            description = "Double-breasted trench in premium wool blend. A timeless investment piece.",
            price = 229.99,
            imageUrl = "https://images.unsplash.com/photo-1539533018447-63fcce2678e4?w=500&q=80",
            category = "Outerwear", gender = "Women",
            isSale = true, originalPrice = 299.99, badge = "SALE"
        ),
        Product(
            id = 6, name = "Ribbed Knit Sweater",
            description = "Cosy crew-neck sweater in merino wool blend with dropped shoulders.",
            price = 65.99,
            imageUrl = "https://images.unsplash.com/photo-1576566588028-4147f3842f27?w=500&q=80",
            category = "Tops", gender = "Women"
        ),
        Product(
            id = 7, name = "Pleated Maxi Skirt",
            description = "Flowing pleated skirt in lightweight satin. Elegant drape for effortless styling.",
            price = 89.99,
            imageUrl = "https://images.unsplash.com/photo-1583496661160-fb5886a0aaaa?w=500&q=80",
            category = "Bottoms", gender = "Women", isNew = true, badge = "NEW"
        ),
        Product(
            id = 8, name = "Evening Wrap Dress",
            description = "Graceful wrap dress in fluid crepe with deep V-neckline and self-tie belt.",
            price = 189.99,
            imageUrl = "https://images.unsplash.com/photo-1566174053879-31528523f8ae?w=500&q=80",
            category = "Dresses", gender = "Women", badge = "EXCLUSIVE"
        ),
        Product(
            id = 9, name = "Slim Fit Wool Suit",
            description = "Sharp two-piece suit in premium Italian wool. From boardroom to occasion.",
            price = 279.99,
            imageUrl = "https://images.unsplash.com/photo-1594938298603-c8148c4b3c4b?w=500&q=80",
            category = "Suits", gender = "Men", badge = "BESTSELLER"
        ),
        Product(
            id = 10, name = "Oxford Button-Down Shirt",
            description = "Classic Oxford cotton shirt in slim cut. Business casual perfection.",
            price = 69.99,
            imageUrl = "https://images.unsplash.com/photo-1620012253295-c15cc3e65df4?w=500&q=80",
            category = "Shirts", gender = "Men"
        ),
        Product(
            id = 11, name = "Slim Chino Trousers",
            description = "Refined slim-fit chinos in stretch cotton blend. Smart casual essential.",
            price = 79.99,
            imageUrl = "https://images.unsplash.com/photo-1473966968600-fa801b869a1a?w=500&q=80",
            category = "Bottoms", gender = "Men", isNew = true, badge = "NEW"
        ),
        Product(
            id = 12, name = "Leather Biker Jacket",
            description = "Premium genuine leather with asymmetric zip. Edgy, refined, iconic.",
            price = 329.99,
            imageUrl = "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500&q=80",
            category = "Outerwear", gender = "Men",
            isSale = true, originalPrice = 399.99, badge = "SALE"
        ),
        Product(
            id = 13, name = "Linen Resort Shirt",
            description = "Breathable linen with relaxed fit. Perfect for warm-weather occasions.",
            price = 59.99,
            imageUrl = "https://images.unsplash.com/photo-1602810318383-e386cc2a3ccf?w=500&q=80",
            category = "Shirts", gender = "Men"
        ),
        Product(
            id = 14, name = "Camel Wool Overcoat",
            description = "Sophisticated overcoat in luxurious camel wool. A true wardrobe investment.",
            price = 249.99,
            imageUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=500&q=80",
            category = "Outerwear", gender = "Men", badge = "BESTSELLER"
        ),
        Product(
            id = 15, name = "Washed Denim Jacket",
            description = "Vintage-washed denim with lived-in feel. Casual cool, season-less staple.",
            price = 109.99,
            imageUrl = "https://images.unsplash.com/photo-1544441893-675973e31985?w=500&q=80",
            category = "Jackets", gender = "Men"
        )
    )
}