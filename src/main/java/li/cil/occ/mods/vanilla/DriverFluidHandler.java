package li.cil.occ.mods.vanilla;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverTileEntity;
import li.cil.occ.mods.ManagedTileEntityEnvironment;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

public final class DriverFluidHandler extends DriverTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return IFluidHandler.class;
    }

    @Override
    public ManagedEnvironment createEnvironment(final World world, final int x, final int y, final int z) {
        return new Environment((IFluidHandler) world.getTileEntity(x, y, z));
    }

    public static final class Environment extends ManagedTileEntityEnvironment<IFluidHandler> {
        public Environment(final IFluidHandler tileEntity) {
            super(tileEntity, "fluid_handler");
        }

        @Callback(doc = "function([side:number=6]):table -- Get some information about the tank accessible from the specified side.")
        public Object[] getTankInfo(final Context context, final Arguments args) {
            ForgeDirection side = args.count() > 0 ? ForgeDirection.getOrientation(args.checkInteger(0)) : ForgeDirection.UNKNOWN;
            return tileEntity.getTankInfo(side);
        }
    }
}
