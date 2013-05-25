package enhancedportals.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import enhancedportals.client.gui.GuiPortalModifierSlot;
import enhancedportals.lib.ItemIds;
import enhancedportals.lib.Textures;
import enhancedportals.portal.PortalTexture;
import enhancedportals.portal.upgrades.Upgrade;
import enhancedportals.portal.upgrades.modifier.UpgradeAdvancedDimensional;
import enhancedportals.portal.upgrades.modifier.UpgradeDimensional;
import enhancedportals.tileentity.TileEntityPortalModifier;

public class ContainerPortalModifier extends Container
{
    public TileEntityPortalModifier portalModifier;

    public ContainerPortalModifier(InventoryPlayer player, TileEntityPortalModifier modifier)
    {
        portalModifier = modifier;
        addSlotToContainer(new GuiPortalModifierSlot(portalModifier, 0, 152, 15));

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 67 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(player, i, 8 + i * 18, 125));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

    @Override
    public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer)
    {
        if (portalModifier.isActive())
        {
            return null;
        }

        return super.slotClick(par1, par2, par3, par4EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        if (slotObject != null && slotObject.getHasStack())
        {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            PortalTexture text = Textures.getTextureFromItemStack(stackInSlot);

            if (text == null && stackInSlot.itemID != ItemIds.PortalModifierUpgrade + 256)
            {
                return null;
            }
            else if (text != null)
            {
                String tempText = text.getID();

                if (tempText.equals(portalModifier.texture))
                {
                    tempText = Textures.getTextureFromItemStack(stackInSlot, portalModifier.texture).getID();

                    if (tempText != null && tempText.equals(portalModifier.texture))
                    {
                        return null;
                    }
                    else if (tempText == null)
                    {
                        return null;
                    }
                }
            }
            else if (stackInSlot.itemID == ItemIds.PortalModifierUpgrade + 256 && !portalModifier.upgradeHandler.hasUpgrade(Upgrade.getUpgrade(stackInSlot.getItemDamage())))
            {
                if (portalModifier.upgradeHandler.hasUpgrade(new UpgradeDimensional()) && stackInSlot.getItemDamage() == 3)
                {
                    return null;
                }
                else if (portalModifier.upgradeHandler.hasUpgrade(new UpgradeAdvancedDimensional()) && stackInSlot.getItemDamage() == 2)
                {
                    return null;
                }
            }
            else
            {
                return null;
            }

            ItemStack newStack = stackInSlot.copy();
            newStack.stackSize = 1;

            stack.stackSize--;
            stackInSlot.stackSize--;

            if (slot == 0)
            {
                if (!mergeItemStack(newStack, 1, 37, true))
                {
                    return null;
                }
            }

            else if (!mergeItemStack(newStack, 0, 1, false))
            {
                return null;
            }

            if (stackInSlot.stackSize == 0)
            {
                slotObject.putStack(null);
            }
            else
            {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize)
            {
                return null;
            }

            slotObject.onPickupFromSlot(player, stackInSlot);
        }

        if (player instanceof EntityPlayerMP)
        {
            ((EntityPlayerMP) player).mcServer.getConfigurationManager().syncPlayerInventory((EntityPlayerMP) player);
        }

        return stack;
    }
}