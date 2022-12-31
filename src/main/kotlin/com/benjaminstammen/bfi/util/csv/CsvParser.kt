package com.benjaminstammen.bfi.util.csv

import com.benjaminstammen.bfi.model.TransactionImportPartial
import com.opencsv.CSVReader
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException

@Component
class CsvParser(
    val transactionDateParser: TransactionDateResolver,
    val postDateResolver: PostDateResolver,
    val bankCategoryResolver: BankCategoryResolver,
    val bankDescriptionResolver: BankDescriptionResolver,
    val bankMemoResolver: BankMemoResolver,
    val amountResolver: AmountResolver
) {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    fun deriveTransactionPartials(csvInputStream: InputStream): List<TransactionImportPartial> {
        val isr = InputStreamReader(csvInputStream)
        val csvr = CSVReader(isr)

        val header = csvr.readNext()
        val stdColMap = deriveStandardHeaders(header)

        var row: Array<String>? = csvr.readNext()
        val transactionList = ArrayList<TransactionImportPartial>()
        while (row != null) {
            logger.info(row.joinToString(prefix = "[", postfix = "]", separator = ",\n"))

            if (row.any { it.isNotEmpty() }) {
                transactionList.add(
                    TransactionImportPartial(
                        transactionDate = transactionDateParser.resolveField(stdColMap, row),
                        postedDate = postDateResolver.resolveField(stdColMap, row),
                        bankCategory = bankCategoryResolver.resolveField(stdColMap, row),
                        bankDescription = bankDescriptionResolver.resolveField(stdColMap, row),
                        bankMemo = bankMemoResolver.resolveField(stdColMap, row),
                        amount = amountResolver.resolveField(stdColMap, row)
                    )
                )
            } else {
                logger.info("Skipping empty row.")
            }

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
