package enhancedportals.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import enhancedportals.tileentity.TileDialingDevice;

public class ContainerDialingEditTexture extends ContainerTextureFrame
{
    TileDialingDevice dial;

    public ContainerDialingEditTexture(TileDialingDevice d, InventoryPlayer p)
    {
        super(d.getPortalController(), p);
        dial = d;
    }
}
