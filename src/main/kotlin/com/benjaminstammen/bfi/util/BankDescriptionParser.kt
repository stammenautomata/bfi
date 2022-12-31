package com.benjaminstammen.bfi.util

import com.benjaminstammen.bfi.util.csv.StdCol
import org.springframework.stereotype.Component

/*
Examples:
MACDONALD'S F6807
CHICK-FIL_A #02857
COSTCO WHSE #1160
TST* COLVE INDIAN BISTRO
SP THE WOOBLES

So there are a set number of payment processors that might prefix the transaction
TST* - Toast
SP - Shopify
SQ - Square

After those are detected, it's probably some combination of the business itself and a location. Should be able to throw
a regular expression together for that pretty easily.
 */
@Component
class BankDescriptionParser {
    private val locationRegex = Regex(" (- .*|(#|\\w)?\\d+)$")

    fun parseDescription(description: String): ParsedBankDescription {

        // 1. Payment Processor
        var descriptionRemainder = description.trim()
        var paymentProcessor: PaymentProcessor? = null
        for (processor in PaymentProcessor.values()) {
            if (description.startsWith(processor.transactionPrefix)) {
                paymentProcessor = processor
                descriptionRemainder = descriptionRemainder
                    .substring(processor.transactionPrefix.length + 1)
                    .trim()
            }
        }

        // 2. Location Information, if any
        var locationInfo: String? = null
        val match = locationRegex.find(descriptionRemainder)
        if (match != null) {
            locationInfo = match.value
            descriptionRemainder = descriptionRemainder.substring(0, descriptionRemainder.length - match.value.length)
        }

        // 3. Core description (i.e. the probable name of the business) is what remains
        return ParsedBankDescription(
            paymentProcessor = paymentProcessor,
            merchantDescription = descriptionRemainder,
            locationInfo = locationInfo
        )
    }
}

data class ParsedBankDescription(
    val paymentProcessor: PaymentProcessor?,
    val merchantDescription: String,
    val locationInfo: String?
)

enum class PaymentProcessor(
    val transactionPrefix: String
) {
    TOAST("TST*"),
    SHOPIFY("SP"),
    SQUARE("SQ");

    companion object {

        private val headerMappings: Map<String, StdCol>

        init {
            val map = mutableMapOf<String, StdCol>()
            for (x in StdCol.values()) {
                for (y in x.aliasSet) {
                    map[y] = x
                }
            }
            headerMappings = map
        }

        fun getHeaderForString(s: String): StdCol? {
            return headerMappings[s.lowercase()]
        }
    }

}
