package com.benjaminstammen.bfi.util.csv

import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface CsvFieldResolver<T> {
    fun resolveField(colMap: Map<StdCol, Int>, row: Array<String>): T
}

@Component
class TransactionDateResolver(private val dateTimeFormatter: DateTimeFormatter): CsvFieldResolver<LocalDate> {
    override fun resolveField(colMap: Map<StdCol, Int>, row: Array<String>): LocalDate {
        val colIdx = colMap[StdCol.TRANSACTION_DATE] ?: throw RuntimeException("No Transaction Date column?")
        return LocalDate.parse(row[colIdx], dateTimeFormatter)
    }
}

@Component
class PostDateResolver(private val dateTimeFormatter: DateTimeFormatter): CsvFieldResolver<LocalDate?> {
    override fun resolveField(colMap: Map<StdCol, Int>, row: Array<String>): LocalDate? {
        val colIdx = colMap[StdCol.POST_DATE] ?: return null
        return LocalDate.parse(row[colIdx], dateTimeFormatter)
    }
}

@Component
class BankCategoryResolver: CsvFieldResolver<String?> {
    override fun resolveField(colMap: Map<StdCol, Int>, row: Array<String>): String? {
        val colIdx = colMap[StdCol.CATEGORY] ?: return null
        return row[colIdx]
    }
}

@Component
class BankDescriptionResolver: CsvFieldResolver<String?> {
    override fun resolveField(colMap: Map<StdCol, Int>, row: Array<String>): String? {
        val colIdx = colMap[StdCol.DESCRIPTION] ?: return null
        return row[colIdx]
    }
}

@Component
class BankMemoResolver: CsvFieldResolver<String?> {
    override fun resolveField(colMap: Map<StdCol, Int>, row: Array<String>): String? {
        val colIdx = colMap[StdCol.MEMO] ?: return null
        return row[colIdx]
    }
}

@Component
class AmountResolver: CsvFieldResolver<BigDecimal> {

    // TODO: Probably could use a refactor
    override fun resolveField(colMap: Map<StdCol, Int>, row: Array<String>): BigDecimal {
        // Use amount if it's available in all cases
        val amountIdx = colMap[StdCol.AMOUNT]
        val debitIdx = colMap[StdCol.DEBIT]
        val creditIdx = colMap[StdCol.CREDIT]

        if (amountIdx != null) {
            return BigDecimal(
                throwIfBlank(row[amountIdx])
            )
        } else if (debitIdx != null && creditIdx != null) {
            val debitString = row[debitIdx]
            return if (debitString.isNotBlank()) {
                BigDecimal(debitString).negate()
            } else {
                BigDecimal(throwIfBlank(row[creditIdx]))
            }
        }

        throw RuntimeException("No way to determine amount from mappings")
    }

    private fun throwIfBlank(string: String): String {
        if (string.isBlank()) {
            throw RuntimeException("String is blank when it shouldn't be.")
        }

        return string
    }
}
