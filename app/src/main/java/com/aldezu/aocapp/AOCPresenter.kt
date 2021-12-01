package com.aldezu.aocapp

import android.content.Context
import android.graphics.Color
import android.view.View
import com.aldezu.aocapp.AOCTextView.Font
import com.aldezu.aocapp.AOCTextView.Style
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

object AOCPresenter {

    private const val ASSETS_FILE = "assets.json"

    fun init(context: Context) {
        nukeFiles(context.filesDir, false)
        val typeFactoryBundle = TypeFactory.defaultInstance().constructCollectionType(ArrayList::class.java, BundledFile::class.java)
        ObjectMapper().readValue<List<BundledFile>>(context.assets.open(ASSETS_FILE), typeFactoryBundle).forEach { file ->
            val extractLocation = "${context.filesDir.absolutePath}${File.separator}${file.subdir}"
            val extractDirectory = File(extractLocation)
            extractDirectory.mkdirs()
            val extractFile = File(extractLocation, file.name)
            val extractFileInputStream = context.assets.open(file.location)
            val bufferedOutputStream = BufferedOutputStream(FileOutputStream(extractFile))
            val bytesIn = ByteArray(4096)
            var read = 0
            while(true) {
                read = extractFileInputStream.read(bytesIn)
                if (read == -1) {
                    break
                }
                bufferedOutputStream.write(bytesIn, 0, read)
            }
            bufferedOutputStream.flush()
            bufferedOutputStream.close()
            extractFileInputStream.close()
        }
    }

    fun viewsToAdd(context: Context): List<View> {
        val inputLocation = "${context.filesDir}/input/day01.txt"
        val inputFile = File(inputLocation)
        val input = if (inputFile.exists()) {
            inputFile.readText()
        } else {
            ""
        }
        val inputLines = if (inputFile.exists()) {
            inputFile.readLines()
        } else {
            listOf()
        }
        var deeperCount = 0
//        Part 1
        for (i in 0 until (inputLines.count() - 1)) {
            val previousValue = inputLines[i].toInt()
            val nextValue = inputLines[i + 1].toInt()
            if (nextValue > previousValue) {
                deeperCount ++
            }
        }
//        Part 2
        for (i in 2 until (inputLines.count() - 1)) {
            val previousValue = inputLines[i].toInt() + inputLines[i - 1].toInt() + inputLines[i - 2].toInt()
            val nextValue = inputLines[i + 1].toInt() + inputLines[i].toInt() + inputLines[i - 1].toInt()
            if (nextValue  > previousValue) {
                deeperCount ++
            }
        }
        val listOfViews = mutableListOf<View>()
        listOfViews.add(
            AOCTextView(context).apply {
                text("Answer: $deeperCount")
                textColourRes(context, R.color.orange)
                textFontSize(24)
                textFontStyle(Font.AVALON, Style.DEMI)
                padding(30)
            }
        )
        listOfViews.add(
            AOCTextView(context).apply {
                textList(inputLines)
                textColour(Color.YELLOW)
                textFontSize(15)
                textFontStyle(Font.AVALON, Style.MEDIUM)
            }
        )
        return listOfViews
    }

    private fun nukeFiles(rootDirectory: File?, deleteFolder: Boolean) {
        rootDirectory?.let {
            it.listFiles()?.forEach { file ->
                nukeFiles(file, true)
            }
            if (deleteFolder) {
                rootDirectory.delete()
            }
        }
    }
}

data class BundledFile(
    @JsonProperty("Name") val name: String,
    @JsonProperty("Location") val location: String,
    @JsonProperty("Subdir") val subdir: String
)