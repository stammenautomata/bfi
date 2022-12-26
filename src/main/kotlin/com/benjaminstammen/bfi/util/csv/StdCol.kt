package com.benjaminstammen.bfi.util.csv

enum class StdCol(
    val aliasSet: Set<String>
) {
    ACCOUNT(setOf("account", "account number", "card no.")),
    TRANSACTION_DATE(setOf("transaction date")),
    POST_DATE(setOf("post date", "posted date")),
    DESCRIPTION(setOf("transaction description", "description")),
    CATEGORY(setOf("category")),
    TYPE(setOf("transaction type", "type")),
    AMOUNT(setOf("transaction amount")),
    DEBIT(setOf("debit")),
    CREDIT(setOf("credit")),
    BALANCE(setOf("balance")),
    MEMO(setOf("memo"));

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
