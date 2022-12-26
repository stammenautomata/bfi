package com.benjaminstammen.bfi.util.csv

import java.lang.RuntimeException
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface CsvFieldParser<T> {
    fun parseField(colMap: Map<StdCol, Int>, row: Array<String>): T
}

class TransactionDateParser(private val dateTimeFormatter: DateTimeFormatter): CsvFieldParser<LocalDate> {
    override fun parseField(colMap: Map<StdCol, Int>, row: Array<String>): LocalDate {
        val colIdx = colMap[StdCol.TRANSACTION_DATE] ?: throw RuntimeException("No Transaction Date column?")
        return LocalDate.parse(row[colIdx], dateTimeFormatter)
    }
}

class PostDateParser(private val dateTimeFormatter: DateTimeFormatter): CsvFieldParser<LocalDate?> {
    override fun parseField(colMap: Map<StdCol, Int>, row: Array<String>): LocalDate? {
        val colIdx = colMap[StdCol.POST_DATE] ?: return null
        return LocalDate.parse(row[colIdx], dateTimeFormatter)
    }
}

class BankCategoryParser: CsvFieldParser<String?> {
    override fun parseField(colMap: Map<StdCol, Int>, row: Array<String>): String? {
        val colIdx = colMap[StdCol.CATEGORY] ?: return null
        return row[colIdx]
    }
}

class BankDescriptionParser: CsvFieldParser<String?> {
    override fun parseField(colMap: Map<StdCol, Int>, row: Array<String>): String? {
        val colIdx = colMap[StdCol.DESCRIPTION] ?: return null
        return row[colIdx]
    }
}

class BankMemoParser: CsvFieldParser<String?> {
    override fun parseField(colMap: Map<StdCol, Int>, row: Array<String>): String? {
        val colIdx = colMap[StdCol.MEMO] ?: return null
        return row[colIdx]
    }
}

class AmountParser: CsvFieldParser<BigDecimal> {
    private val amountSet = arrayOf(StdCol.AMOUNT).toSet()
    private val debitCreditSet = arrayOf(StdCol.DEBIT, StdCol.CREDIT).toSet()

    override fun parseField(colMap: Map<StdCol, Int>, row: Array<String>): BigDecimal {
        // Use amount if it's available in all cases
        val colIdx = colMap[StdCol.AMOUNT]
            ?: colMap[StdCol.DEBIT]
            ?: colMap[StdCol.CREDIT]
            ?: throw RuntimeException("No way to determine amount from mappings")
        return BigDecimal(row[colIdx])
    }
}
