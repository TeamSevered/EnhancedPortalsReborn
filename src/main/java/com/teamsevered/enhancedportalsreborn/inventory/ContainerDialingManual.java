package com.teamsevered.enhancedportalsreborn.inventory;

import com.teamsevered.enhancedportalsreborn.portal.GlyphIdentifier;
import com.teamsevered.enhancedportalsreborn.tile.TileDialingDevice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerDialingManual extends BaseContainer
{
    TileDialingDevice dial;

    public ContainerDialingManual(TileDialingDevice d, InventoryPlayer p)
    {
        super(null, p);
        dial = d;
        hideInventorySlots();
    }

    @Override
    public void handleGuiPacket(NBTTagCompound tag, EntityPlayer player)
    {
        if (tag.hasKey("dial"))
        {
            dial.getPortalController().connectionDial(new GlyphIdentifier(tag.getString("dial")), null, player);
        }
    }
}
