package at.asitplus.wallet.eupid

import io.kotest.core.config.AbstractProjectConfig

class KotestConfig : AbstractProjectConfig() {
    init {
        Initializer.initWithVCK()
    }
}