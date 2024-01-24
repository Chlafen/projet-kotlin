import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun toggleBookmark(articleID: String) {
        val editor = sharedPreferences.edit()
        var bmAs = getBookmarks()
        if(getBookmarks().contains(articleID)){
            bmAs = bmAs.minusElement(articleID)
        }else{
            bmAs = bmAs.plusElement(articleID)
        }
        editor.putStringSet("BOOKMARKS", bmAs)
        editor.apply()
    }

    fun isBookmarked(articleID: String):Boolean {
        var bmAs = getBookmarks()
        if(getBookmarks().contains(articleID)){
            Log.d("preferences","true")
            return true
        }
        Log.d("preferences","false")
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