package com.sugosumadesu.buntafujikawa.rssreader.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.sugosumadesu.buntafujikawa.rssreader.AwsNewestInformationFragment
import com.sugosumadesu.buntafujikawa.rssreader.AwsProblemInformationFragment
import com.sugosumadesu.buntafujikawa.rssreader.LinkListFragment
import com.sugosumadesu.buntafujikawa.rssreader.common.LinkListType

class LinkListPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    enum class TabMenu(val title: String) {
        Newest("最新情報") {
            override fun createFragment(): Fragment = AwsNewestInformationFragment.newInstance(LinkListType.Newest)
        },
        TROUBLE("障害情報") {
            override fun createFragment(): Fragment = AwsProblemInformationFragment.newInstance(LinkListType.TROUBLE)
        },
        TWITTER("Tweet") {
            // TODO フラグメントを作成したら変更をする
            // RetrofitでAPIを取得する部分をディレクトリから作成する必要がある
            override fun createFragment() = LinkListFragment.newInstance(LinkListType.TWITTER)
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
