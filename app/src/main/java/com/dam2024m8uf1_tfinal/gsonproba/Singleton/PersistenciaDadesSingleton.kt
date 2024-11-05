import android.content.Context
import com.dam2024m8uf1_tfinal.gsonproba.Entitis.Persona
import com.google.gson.Gson
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class PersistenciaDadesSingleton private constructor() {
 companion object {
  @Volatile
  private var instance: PersistenciaDadesSingleton? = null

  fun getInstance(): PersistenciaDadesSingleton {
   if (instance == null) {
    synchronized(this) {
     if (instance == null) {
      instance = PersistenciaDadesSingleton()
     }
    }
   }
   return instance!!
  }
 }

 private val gson = Gson()
 private val nomFitxer = "persona.json"

 fun guardarPersona(persona: Persona, context: Context) {
  val json = gson.toJson(persona)
  try {
   context.openFileOutput(nomFitxer, Context.MODE_PRIVATE).use { fos ->
    OutputStreamWriter(fos).use { writer ->
     writer.write(json)
    }
   }
  } catch (e: Exception) {
   e.printStackTrace()
  }
 }

 fun carregarPersona(context: Context): Persona? {
  return try {
   context.openFileInput(nomFitxer).use { fis ->
    InputStreamReader(fis).use { reader ->
     gson.fromJson(reader, Persona::class.java)
    }
   }
  } catch (e: Exception) {
   e.printStackTrace()
   null
  }
 }

 fun anemAferCoses() = "anem a fer coses"
}
