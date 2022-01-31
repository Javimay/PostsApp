package com.javimay.postsapp.utils

import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.data.model.parcels.PostParcel

fun toParcelable(post: Post): PostParcel {
    return PostParcel(post.idPost, post.body, post.id, post.title, post.userId, post.favorite, post.read)
}

fun toPost(postParcel: PostParcel): Post {
    return Post(
        postParcel.idPost,
        postParcel.body,
        postParcel.id,
        postParcel.title,
        postParcel.userId,
        postParcel.favorite,
        postParcel.read
    )
}