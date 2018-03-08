package com.sugosumadesu.buntafujikawa.rssreader.common

enum class LinkListType(val rawValue: Int) {
    Newest(1), TROUBLE(2), TWITCH(3), OPTIONAL(4);

    companion object {
        fun from(findValue: Int) = values().first { it.rawValue == findValue }
    }

    val title: String
        get() {
            return when (this) {
                Newest -> "最新情報"
                TROUBLE -> "障害情報"
                TWITCH -> "TWITCH"
                OPTIONAL -> "追加項目"
            }
        }
}
