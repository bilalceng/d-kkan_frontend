package com.bilalberekgm.dukkan

import com.bilalberekgm.dukkan.models.DukkanData

object DataSource {

    private val  dataList: ArrayList<DukkanData> = ArrayList<DukkanData>()

    fun getDataList(): List<DukkanData>{
        return dataList.apply {
            add(DukkanData(title = "dukkan1", imageId = R.drawable.burger, openAt = "08.00 am", closedAt = "10.00 pm"))
            add(DukkanData(title = "dukkan1",imageId = R.drawable.cigarettee_pack,  openAt = "08.00 am", closedAt = "10.00 pm"))
            add(DukkanData(title = "dukkan1",imageId = R.drawable.coffee_cup,  openAt = "08.00 am", closedAt = "10.00 pm"))
            add(DukkanData(title = "dukkan1",imageId = R.drawable.watermelon_slice,  openAt = "08.00 am", closedAt = "10.00 pm"))
            add(DukkanData(title = "dukkan1",imageId = R.drawable.burger,  openAt = "08.00 am", closedAt ="10.00 pm"))
            add(DukkanData(title = "dukkan1",imageId = R.drawable.watermelon_slice, openAt = "08.00 am", closedAt = "10.00 pm"))
            add(DukkanData(title = "dukkan1",imageId = R.drawable.cigarettee_pack, openAt = "08.00 am", closedAt = "10.00 pm"))
            add(DukkanData(title = "dukkan1",imageId = R.drawable.cigarettee_pack,  openAt = "08.00 am", closedAt = "10.00 pm"))
            add(DukkanData(title = "dukkan1",imageId = R.drawable.coffee_cup,  openAt = "08.00 am", closedAt = "10.00 pm"))
            add(DukkanData(title = "dukkan1",imageId = R.drawable.burger, openAt = "08.00 am", closedAt = "10.00 pm"))


        }
    }
}