package com.example.buntafujikawa.rssreader.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.example.buntafujikawa.rssreader.LinkListFragment
import com.example.buntafujikawa.rssreader.common.LinkListType

class LinkListPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    enum class TabMenu(val title: String) {
        Newest("最新情報") {
            override fun createFragment(): Fragment = LinkListFragment.newInstance(LinkListType.Newest)
        },
        TROUBLE("障害情報") {
            override fun createFragment() = LinkListFragment.newInstance(LinkListType.TROUBLE)
        },
        TWITCH("Twitch") {
            override fun createFragment() = LinkListFragment.newInstance(LinkListType.TWITCH)
        },
        OPTIONAL("OPTIONAL") {
            override fun createFragment() = LinkListFragment.newInstance(LinkListType.OPTIONAL)
        };

        abstract fun createFragment(): Fragment
    }

    override fun getItem(position: Int): Fragment? {
        return TabMenu.values()[position].createFragment()
    }

    override fun getCount(): Int {
        return TabMenu.values().size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TabMenu.values()[position].title
    }
}
