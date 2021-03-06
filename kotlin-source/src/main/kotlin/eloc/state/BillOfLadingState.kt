package eloc.state

import eloc.LetterOfCreditDataStructures.Good
import eloc.LetterOfCreditDataStructures.Port
import eloc.LetterOfCreditDataStructures.Weight
import eloc.LetterOfCreditDataStructures.Company
import eloc.LetterOfCreditDataStructures.Person
import eloc.LetterOfCreditDataStructures.Location
import net.corda.core.contracts.LinearState
import net.corda.core.contracts.UniqueIdentifier
import net.corda.core.identity.Party
import net.corda.core.serialization.CordaSerializable
import java.io.InputStream
import java.time.Instant
import java.time.LocalDate

data class BillOfLadingState(
        val owner: Party,
        val buyer: Party,
        val advisory: Party,
        val issuer: Party,
        val timestamp: Instant,
        val props: BillOfLadingProperties) : LinearState {

    override val linearId = UniqueIdentifier(props.billOfLadingID)

    override val participants get() = listOf(owner, buyer, advisory, issuer)
}

@CordaSerializable
data class BillOfLadingProperties(
        val billOfLadingID: String,
        val issueDate: LocalDate,
        val carrierOwner: net.corda.core.identity.Party,
        val nameOfVessel: String,
        val descriptionOfGoods: List<Good>,
        val portOfLoading: Port,
        val portOfDischarge: Port,
        val grossWeight: Weight,
        val dateOfShipment: LocalDate?,
        val shipper: Company?,
        val notify: Person?,
        val consignee: Company?,
        val placeOfReceipt: Location?,
        val attachment: InputStream? = null)