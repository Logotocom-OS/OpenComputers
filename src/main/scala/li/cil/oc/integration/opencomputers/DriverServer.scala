package li.cil.oc.integration.opencomputers

import li.cil.oc.Constants
import li.cil.oc.api
import li.cil.oc.api.driver.EnvironmentHost
import li.cil.oc.api.network.ManagedEnvironment
import li.cil.oc.common.Slot
import li.cil.oc.common.tileentity
import li.cil.oc.server.component
import li.cil.oc.util.ExtendedInventory._
import net.minecraft.item.ItemStack

object DriverServer extends Item {
  override def worksWith(stack: ItemStack): Boolean = isOneOf(stack,
    api.Items.get(Constants.ItemName.ServerTier1),
    api.Items.get(Constants.ItemName.ServerTier2),
    api.Items.get(Constants.ItemName.ServerTier3),
    api.Items.get(Constants.ItemName.ServerCreative))

  override def createEnvironment(stack: ItemStack, host: EnvironmentHost): ManagedEnvironment = host match {
    case rack: tileentity.Rack => new component.Server(rack, rack.indexOf(stack))
    case _ => null // Welp.
  }

  override def slot(stack: ItemStack): String = Slot.RackMountable
}
