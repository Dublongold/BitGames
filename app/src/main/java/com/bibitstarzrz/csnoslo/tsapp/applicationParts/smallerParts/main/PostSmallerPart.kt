package com.bibitstarzrz.csnoslo.tsapp.applicationParts.smallerParts.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bibitstarzrz.csnoslo.tsapp.R
import com.bibitstarzrz.csnoslo.tsapp.consts.NavData
import com.bibitstarzrz.csnoslo.tsapp.helpers.smallerPartInflater

class PostSmallerPart(private val title: String): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return smallerPartInflater(R.layout.smaller_part_main_post, inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            findViewById<ImageButton>(R.id.main_privacyPolicyButton).setOnClickListener {
                NavData.setNavData?.invoke(NavData.PRIVACY_POLICY to null)
            }
            findViewById<ImageButton>(R.id.main_homeButton).setOnClickListener {
                NavData.setNavData?.invoke(NavData.POP_BACK_STACK to null)
            }
            if(title != getString(R.string.main_wild_spin)) {
                val post = getPost()
                findViewById<TextView>(R.id.main_post_textView_title).text = post.title
                findViewById<TextView>(R.id.main_post_textView_text).text = post.text
                findViewById<TextView>(R.id.main_post_textView_author).text = post.author
                findViewById<ImageView>(R.id.main_post_imageView).setImageResource(post.image)
            }
        }
    }

    private fun getPost(): Post {
        val booksGiza = getString(R.string.main_books_giza)
        val royalLotus = getString(R.string.main_royal_lotus)
        val blackJack = getString(R.string.main_blackjack)
        val wolfGold = getString(R.string.main_wolf_gold)
        val fiveLions = getString(R.string.main_five_lions)
        val elvisFrog = getString(R.string.main_elvis_frog)
        val buffalo = getString(R.string.main_buffalo)
        val image = when(title) {
            booksGiza -> R.drawable.books_giza
            royalLotus -> R.drawable.royal_lotus
            blackJack -> R.drawable.blackjack
            wolfGold -> R.drawable.wolf_gold
            fiveLions -> R.drawable.five_lions
            elvisFrog -> R.drawable.elvis_frog
            buffalo -> R.drawable.buffalo
            else -> R.drawable.wild_spin
        }
        val text = getString(when(title) {
            booksGiza -> R.string.main_books_giza_text
            royalLotus -> R.string.main_royal_lotus_text
            blackJack -> R.string.main_blackjack_text
            wolfGold -> R.string.main_wolf_gold_text
            fiveLions -> R.string.main_five_lions_text
            elvisFrog -> R.string.main_elvis_frog_text
            buffalo -> R.string.main_buffalo_text
            else -> R.string.main_wild_spin_text
        })
        val author = getString(if(title == elvisFrog) R.string.main_by_bgaming else R.string.main_by_platipus)
        return Post(title, text, author, image)
    }

    data class Post(
        val title: String,
        val text: String,
        val author: String,
        val image: Int
    )
}