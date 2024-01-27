import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun toggleBookmark(articleID: String) {
        val editor = sharedPreferences.edit()
        var bmAs = getBookmarks()
        bmAs = if(getBookmarks().contains(articleID)){
            bmAs.minusElement(articleID)
        }else{
            bmAs.plusElement(articleID)
        }
        editor.putStringSet("BOOKMARKS", bmAs)
        editor.apply()
    }

    fun isBookmarked(articleID: String):Boolean {
        if(getBookmarks().contains(articleID)){
            return true
        }
        return false
    }


    fun getBookmarks(): Set<String> {
        val bmAs = sharedPreferences.getStringSet("BOOKMARKS", mutableSetOf(""))
        if(bmAs.isNullOrEmpty()){
            return setOf()
        }
        return bmAs
    }
}