package id.ac.ui.cs.mobileprogramming.aryodh.helloworld.schedule

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object ScheduleContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<ScheduleItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    private val ITEM_MAP: MutableMap<String, ScheduleItem> = HashMap()

    private val ITEM_SCHEDULE: MutableMap<String, List<String>> = HashMap()


    init {
        generateScheduleDetail()

        var i = 1
        for ((title, detail) in ITEM_SCHEDULE) {
            addItem(createScheduleItem(i, title, detail))
            i++
        }
    }

    private fun addItem(item: ScheduleItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createScheduleItem(position: Int, title: String, details: List<String>): ScheduleItem {
        return ScheduleItem(position.toString(), title, details)
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class ScheduleItem(val id: String, val title: String, val details: List<String>) {
        override fun toString(): String = title
    }

    private fun generateScheduleDetail() {
        ITEM_SCHEDULE["Workshop Android"] = listOf(
            "Workshop Android",
            "12 Oktober 2020",
            "10.00 - 15.00",
            "Google Meet",
            "Workshop Android diadakan oleh Google Indonesia dengan pembicara Charles Frans."
        )
        ITEM_SCHEDULE["Belajar Ujian"] = listOf(
            "Belajar Ujian",
            "13 Oktober 2020",
            "13.00 - 17.00",
            "Rumah",
            "Belajar untuk persiapan ujian TKTPL dengan membaca slide dan mereview kodingan."
        )
        ITEM_SCHEDULE["Ujian TKTPL"] = listOf(
            "Ujian TKTPL",
            "14 Oktober 2020",
            "14.00 - 16.00",
            "Scele",
            "Ujian TKTPL sesuai dengan materi yang sudah dipelajari"
        )
        ITEM_SCHEDULE["Game Night"] = listOf(
            "Game Night",
            "15 Oktober 2020",
            "20.00 - 23.55",
            "Discord",
            "Bermain dan mengobrol bersama teman-teman."
        )

    }
}