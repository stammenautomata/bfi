package com.benjaminstammen.bfi.services

import com.benjaminstammen.bfi.model.*
import com.benjaminstammen.bfi.util.BankDescriptionParser
import com.benjaminstammen.bfi.util.ParsedBankDescription
import com.benjaminstammen.bfi.util.csv.CsvParser
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class TransactionGeneratorService(
    val merchantService: MerchantService,
    val categoryService: CategoryService,
    val csvParser: CsvParser,
    val bankDescriptionParser: BankDescriptionParser
) {
    fun generateTransactionsFromCsv(csvInputStream: InputStream): List<TransactionPrefill> {
        return csvParser.deriveTransactionPartials(csvInputStream)
            .map { generatePrefill(it) }
    }

    private fun generatePrefill(partial: TransactionImportPartial): TransactionPrefill {
        val tags = mutableSetOf<String>()

        // 1a. Parse the description
        var inferredMerchant: Merchant? = null
        var parsedDescription: ParsedBankDescription? = null
        if (partial.bankDescription != null) {
            parsedDescription = bankDescriptionParser.parseDescription(partial.bankDescription)
            val matchingMerchants = merchantService.listMerchantsByDescription(parsedDescription.merchantDescription)
            if (matchingMerchants.isNotEmpty()) {
                inferredMerchant = matchingMerchants[0]
                tags.addAll(inferredMerchant.autoTags)
            }
        }

        // 2. Assign category (if not null)
        var defaultCategory: Category? = null
        val defaultCategoryId = inferredMerchant?.defaultCategoryId
        if (defaultCategoryId != null) {
            defaultCategory = categoryService.getCategory(defaultCategoryId)
        }

        return TransactionPrefill(
            transactionDate = partial.transactionDate,
            postedDate = partial.postedDate,
            amount = partial.amount,
            categoryId = defaultCategory?.id,
            merchantId = inferredMerchant?.id,
            bankDescription = partial.bankDescription,
            parsedBankDescription = parsedDescription,
            bankCategory = partial.bankCategory,
            tags = tags.toList()
        )
    }
}
