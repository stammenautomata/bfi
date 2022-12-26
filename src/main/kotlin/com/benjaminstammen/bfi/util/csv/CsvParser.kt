package com.benjaminstammen.bfi.util.csv

import com.benjaminstammen.bfi.model.TransactionPartial
import com.opencsv.CSVReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException

class CsvParser(
    val transactionDateParser: TransactionDateParser,
    val postDateParser: PostDateParser,
    val bankCategoryParser: BankCategoryParser,
    val bankDescriptionParser: BankDescriptionParser,
    val bankMemoParser: BankMemoParser,
    val amountParser: AmountParser
) {
    fun deriveTransaction(csvInputStream: InputStream): List<TransactionPartial> {
        val isr = InputStreamReader(csvInputStream)
        val csvr = CSVReader(isr)

        val header = csvr.readNext()
        val stdColMap = deriveStandardHeaders(header)

        var row: Array<String>? = csvr.readNext()
        val transactionList = ArrayList<TransactionPartial>()
        while (row != null) {
            transactionList.add(
                TransactionPartial(
                    transactionDate = transactionDateParser.parseField(stdColMap, row),
                    postedDate = postDateParser.parseField(stdColMap, row),
                    bankCategory = bankCategoryParser.parseField(stdColMap, row),
                    bankDescription = bankDescriptionParser.parseField(stdColMap, row),
                    bankMemo = bankMemoParser.parseField(stdColMap, row),
                    amount = amountParser.parseField(stdColMap, row)
                )
            )

            row = csvr.readNext()
        }

        return transactionList
    }

    private fun deriveStandardHeaders(header: Array<String>): Map<StdCol, Int> {
        val map = mutableMapOf<StdCol, Int>()
        header.forEachIndexed { i, s ->
            val standardColumn = StdCol.getHeaderForString(s)
            if (standardColumn != null) {
                if (map.containsKey(standardColumn)) throw RuntimeException("Duplicate column '$s' - is CSV valid?")
                map[standardColumn] = i
            }
        }
        return map
    }
}
