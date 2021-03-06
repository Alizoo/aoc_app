package com.aldezu.aocapp

import android.content.Context
import android.graphics.Color
import android.view.View
import com.aldezu.aocapp.AOCTextView.Font
import com.aldezu.aocapp.AOCTextView.Style
import com.aldezu.aocapp.AOCUtils.then
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.math.pow

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
            while (true) {
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

    /**
     * .trim() - remove whitespace
     * .split("-") - create list, breaking on split character
     * .map { it.toInt() } - convert Strings to Ints
     * val regex = """(/d)-(/d)-(/d)""".toRegex()
     * val (a, b, c) = regex.find("1-2-3")?.destructured ?: error("Can't destructure!") -> a=1, b=2, c=3
     * .substringBefore("-") - returns String before delimiter
     * .substringAfter("-") - returns String after delimiter
     * .removePrefix/removeSuffix
     * .removeSurrounding
     */

    fun viewsToAdd(context: Context): List<View> {
        val inputLocation = "${context.filesDir}/input/day03.txt"
        val inputFile = File(inputLocation)
        val input = if (inputFile.exists()) {
            inputFile.readText()
        } else {
            ""
        }
        val inputLines = if (inputFile.exists()) {
            inputFile.readLines().toMutableList()
        } else {
            mutableListOf()
        }

        /**
         * Day 1
         */
//        var deeperCount = 0
//        Part 1
//        for (i in 0 until (inputLines.count() - 1)) {
//            val previousValue = inputLines[i].toInt()
//            val nextValue = inputLines[i + 1].toInt()
//            if (nextValue > previousValue) {
//                deeperCount ++
//            }
//        }
//        Part 2
//        for (i in 2 until (inputLines.count() - 1)) {
//            val previousValue = inputLines[i].toInt() + inputLines[i - 1].toInt() + inputLines[i - 2].toInt()
//            val nextValue = inputLines[i + 1].toInt() + inputLines[i].toInt() + inputLines[i - 1].toInt()
//            if (nextValue  > previousValue) {
//                deeperCount ++
//            }
//        }
//        val listOfViews = mutableListOf<View>()
//        listOfViews.add(
//            AOCTextView(context).apply {
//                text("Answer: $deeperCount")
//                textColourRes(context, R.color.orange)
//                textFontSize(24)
//                textFontStyle(Font.AVALON, Style.DEMI)
//                padding(30)
//            }
//        )

        /**
         * Day 2
         */
//        var horPosition = 0
//        var depth = 0
//        var aim = 0
        // Part 1
//        inputLines.forEach { line ->
//            val regex = """(\w*)\s(\d*)""".toRegex()
//            val (a, b) = regex.find(line)?.destructured ?: error("Can't destructure!")
//            when (a) {
//                "forward" -> horPosition += b.toInt()
//                "down" -> depth += b.toInt()
//                "up" -> depth -= b.toInt()
//            }
//        }
        // Part 2
//        inputLines.forEach { line ->
//            val regex = """(\w*)\s(\d*)""".toRegex()
//            val (a, b) = regex.find(line)?.destructured ?: error("Can't destructure!")
//                when (a) {
//                    "forward" -> {
//                        horPosition += b.toInt()
//                        depth += (aim * b.toInt())
//                    }
//                    "down" -> aim += b.toInt()
//                    "up" -> aim -= b.toInt()
//            }
//        }
        /**
         * Day 1
         */
//        Part 1
//        val gamma = mutableListOf<Int>()
//        val epsilon = mutableListOf<Int>()
//        for (i in inputLines[0].indices) {
//            var zeroCount = 0
//            var oneCount = 0
//            inputLines.forEachIndexed { index, line ->
//                if (line.last().toString() == "0") {
//                    zeroCount ++
//                } else {
//                    oneCount ++
//                }
//                inputLines[index] = line.dropLast(1)
//            }
//            gamma.add(0, (zeroCount > oneCount) then { 0 } ?: 1 )
//            epsilon.add(0, (zeroCount < oneCount) then { 0 } ?: 1 )
//        }
//        var gammaNonBinary = 0F
//        gamma.forEachIndexed { index, i ->
//            gammaNonBinary += i.toFloat() * 2F.pow(((gamma.indices.count() - 1) - index).toFloat())
//        }
//        var epsilonNonBinary = 0F
//        epsilon.forEachIndexed { index, i ->
//            epsilonNonBinary += i.toFloat() * 2F.pow(((epsilon.indices.count() - 1) - index).toFloat())
//        }
//        val listOfViews = mutableListOf<View>()
//        listOfViews.add(
//            AOCTextView(context).apply {
//                text("Final: Gamma - $gamma, Epsilon - $epsilon")
//                textColourRes(context, R.color.orange)
//                textFontSize(24)
//                textFontStyle(Font.AVALON, Style.DEMI)
//                padding(30)
//            }
//        )
//        listOfViews.add(
//            AOCTextView(context).apply {
//                text("Final: GammaNonBinary - $gammaNonBinary, EpsilonNonBinary - $epsilonNonBinary")
//                textColourRes(context, R.color.orange)
//                textFontSize(24)
//                textFontStyle(Font.AVALON, Style.DEMI)
//                padding(30)
//            }
//        )
//        listOfViews.add(
//            AOCTextView(context).apply {
//                text("Answer: ${gammaNonBinary * epsilonNonBinary}")
//                textColourRes(context, R.color.orange)
//                textFontSize(24)
//                textFontStyle(Font.AVALON, Style.DEMI)
//                padding(30)
//            }
//        )
//        Part 2
        var inputLinesO2Copy = inputLines.toList()
        for (i in inputLinesO2Copy[0].indices) {
            val inputLinesCopy = inputLinesO2Copy.toMutableList()
            var zeroCount = 0
            var oneCount = 0
            inputLinesCopy.forEach { line ->
                if (line[i].toString() == "0") {
                    zeroCount++
                } else {
                    oneCount++
                }
            }
            val o2DealBreaker = if (zeroCount == oneCount) {
                1
            } else {
                (zeroCount > oneCount) then { 0 } ?: 1
            }
            inputLinesO2Copy = inputLinesO2Copy.filter { it[i].toString() == o2DealBreaker.toString() }
            if (inputLinesO2Copy.count() == 1) {
                break
            }
        }
        var inputLinesCo2Copy = inputLines.toList()
        for (i in inputLinesCo2Copy[0].indices) {
            val inputLinesCopy = inputLinesCo2Copy.toMutableList()
            var zeroCount = 0
            var oneCount = 0
            inputLinesCopy.forEach { line ->
                if (line[i].toString() == "0") {
                    zeroCount++
                } else {
                    oneCount++
                }
            }
            val o2DealBreaker = if (zeroCount == oneCount) {
                0
            } else {
                (zeroCount < oneCount) then { 0 } ?: 1
            }
            inputLinesCo2Copy = inputLinesCo2Copy.filter { it[i].toString() == o2DealBreaker.toString() }
            if (inputLinesCo2Copy.count() == 1) {
                break
            }
        }
        var o2NonBinary = 0F
        for (i in inputLinesO2Copy[0].indices) {
            o2NonBinary += inputLinesO2Copy[0][i].toString().toFloat() * 2F.pow(((inputLinesO2Copy[0].indices.count() - 1) - i).toFloat())
        }
        var c02NonBinary = 0F
        for (i in inputLinesCo2Copy[0].indices) {
            c02NonBinary += inputLinesCo2Copy[0][i].toString().toFloat() * 2F.pow(((inputLinesCo2Copy[0].indices.count() - 1) - i).toFloat())
        }
        val answer = (o2NonBinary * c02NonBinary).toInt()
        val listOfViews = mutableListOf<View>()
        listOfViews.add(
            AOCTextView(context).apply {
                text("Final: O2 - ${o2NonBinary.toInt()}, CO2 - ${c02NonBinary.toInt()}")
                textColourRes(context, R.color.orange)
                textFontSize(24)
                textFontStyle(Font.AVALON, Style.DEMI)
                padding(30)
            }
        )
        listOfViews.add(
            AOCTextView(context).apply {
                text("Answer: $answer")
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