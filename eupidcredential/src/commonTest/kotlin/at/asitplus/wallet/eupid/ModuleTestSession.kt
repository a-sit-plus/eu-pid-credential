package at.asitplus.wallet.eupid

import de.infix.testBalloon.framework.TestSession

class ModuleTestSession : TestSession() {
    init {
        Initializer.initWithVCK()
    }
}