package com.sugosumadesu.buntafujikawa.rssreader.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.sugosumadesu.buntafujikawa.rssreader.AwsNewestInformationFragment
import com.sugosumadesu.buntafujikawa.rssreader.AwsProblemInformationFragment
import com.sugosumadesu.buntafujikawa.rssreader.AwsTweetFragment
import com.sugosumadesu.buntafujikawa.rssreader.LinkListFragment
import com.sugosumadesu.buntafujikawa.rssreader.common.LinkListType

class LinkListPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    enum class TabMenu(val title: String) {
        Newest("最新情報") {
            override fun createFragment(): Fragment = AwsNewestInformationFragment.newInstance(LinkListType.Newest)
        },
        TROUBLE("障害情報(EC2)") {
            override fun createFragment(): Fragment = AwsProblemInformationFragment.newInstance(LinkListType.TROUBLE)
        },
        TWITTER("ツイート") {
            override fun createFragment(): Fragment = AwsTweetFragment.newInstance(LinkListType.TWITTER)
        },
        OPTIONAL("OPTIONAL") {
            override fun createFragment(): Fragment = LinkListFragment.newInstance(LinkListType.OPTIONAL)
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
